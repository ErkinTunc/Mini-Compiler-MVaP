// Compilation : antlr4 Rationnel.g4
// Execution   : javac Rationnel*.java

// grun Rationnel start -tree
//  input : 

// grun Rationnel start -tokens
//  input : 
   
// grun Rationnel start -gui
/*   input : 
            Afficher(3 + 4);
            Afficher(3 * 4);
            Afficher(3 / 4);
            Afficher(3 - 4);

            Afficher(5/2);
            Afficher(3/4 + 1/2);
            Afficher((1/3) * (3/4))

            Afficher(-3);           --> -3  1  --> -3/1
            Afficher(-(3/4));
            Afficher(1 + -2);
            Afficher(-(1/2) + 3/4);

            Afficher( 1/2 < 3/4 );
            Afficher( 1/2 == 2/4 );
            Afficher( 3/4 >= 1/2 );

            Afficher( (1/2) ** 0 );
            Afficher( (1/2) ** 1 );
            Afficher( (1/2) ** 2 );

            Afficher( num(3/4 + 1/2) );
            Afficher( denum(3/4 + 1/2) );

            Afficher(true);
            Afficher(false);

            Afficher(1/2 < 3/4);
            Afficher(1/2 == 2/4);
            Afficher(1/2 >= 3/4);

            Afficher(non (1/2 < 3/4));
            Afficher((1/2 < 3/4) et (3/4 < 5/6));
            Afficher((1/2 < 3/4) ou (3/4 < 1/4));
            Afficher(non ((1/2 < 3/4) ou false));

 */

/*
  == Booleans on MVaP ==
    false is 0
    true is 1
    Every boolean expression must leave exactly one integer on top of the stack: 0 or 1
  
  == Rationals on MVaP stack ==
    Pick one representation and never change it.
    
    + A rational = two ints on the stack:
      - [..., num, den] with den on top

    + So after compiling a rational expression r, the stack ends as:
      - ..., num(r), den(r)

  == Temporary global cells ==
  ecide now how many temp cells you want and what they’re for. 
  
  Example:
      g0..g3 reserved for temp rationals:
          g0, g1, g2, g3 = scratch for num/den during +, -, *, /, comparisons, etc.

      Later you can add more if absolutely necessary, but start small.

 */


grammar Rationnel;

@header {
 import java.util.*;
 }

@parser::members { // Juste pour le parser // pas pour le lexer

    // ---------- Runtime representation on MVaP ----------

    // Booleans: 0 = false, 1 = true
    // Every bool expression leaves exactly one int (0 or 1) on top of the stack.

    // Rationals: two consecutive ints on the stack:
    //   [..., num, den]  with den at the TOP
    // After compiling a rational expression r:
    //   stack = [..., num(r), den(r)]

    // Temporary global cells (P[0..N-1]):
    // We reserve them at the beginning of every generated MVaP program.
    // They are used ONLY as scratch for rational operations.
    static final int G_TMP0 = 0;
    static final int G_TMP1 = 1;
    static final int G_TMP2 = 2;
    static final int G_TMP3 = 3;
    // If you need more later, add G_TMP4, G_TMP5, etc.

    // ---- extra globals for pow with lire() ----
    static final int G_POW_BASE_NUM = 4;
    static final int G_POW_BASE_DEN = 5;
    static final int G_POW_RES_NUM  = 6;
    static final int G_POW_RES_DEN  = 7;
    static final int G_POW_EXP      = 8;

    // Later: we will generate at program start:
    // PUSHI 0   ; g0 = G_TMP0
    // PUSHI 0   ; g1 = G_TMP1
    // PUSHI 0   ; g2 = G_TMP2
    // PUSHI 0   ; g3 = G_TMP3
    // ...

    StringBuilder code = new StringBuilder();  // holds the MVaP body

    int labelCounter = 0;
    String newLabel(String base) {
        return base + "_" + (labelCounter++);
    }

    void emit(String instr) {
        code.append(instr).append("\n");
    }

    String genAnd(String c1, String c2) {
    String L_FALSE = newLabel("AND_FALSE");
    String L_END   = newLabel("AND_END");
    StringBuilder c = new StringBuilder();

    // Evaluate left operand
    c.append(c1);                // stack: ..., v1

    // If v1 == 0, jump to false branch (JUMPF pops v1)
    c.append("JUMPF " + L_FALSE + "\n");

    // Here: v1 != 0 (true), but it has been popped
    // Evaluate right operand, its value is the final result
    c.append(c2);                // stack: ..., v2
    c.append("JUMP " + L_END + "\n");

    // False branch: v1 was 0 and has been popped
    c.append("LABEL " + L_FALSE + "\n");
    c.append("PUSHI 0\n");       // result = 0

    c.append("LABEL " + L_END + "\n");
    return c.toString();
}

String genOr(String c1, String c2) {
    String L_EVAL_E2 = newLabel("OR_EVAL_E2");
    String L_END     = newLabel("OR_END");
    StringBuilder c = new StringBuilder();

    // Evaluate left operand
    c.append(c1);                 // stack: ..., v1

    // If v1 == 0, need to evaluate e2 (JUMPF pops v1 when it is 0)
    c.append("JUMPF " + L_EVAL_E2 + "\n");

    // Here: v1 != 0 (true) and has been popped
    c.append("PUSHI 1\n");        // result = 1
    c.append("JUMP " + L_END + "\n");

    c.append("LABEL " + L_EVAL_E2 + "\n");
    // v1 was 0 and has been popped; now evaluate e2
    c.append(c2);                 // stack: ..., v2

    c.append("LABEL " + L_END + "\n");
    return c.toString();
}

String genNot(String inner) {
    StringBuilder c = new StringBuilder();
    c.append(inner);           // stack: ..., v
    c.append("PUSHI 0\n");     // ..., v, 0
    c.append("EQUAL\n");       // 1 iff v == 0
    return c.toString();
}

String genCmp(String c1, String opText, String c2) {
    StringBuilder c = new StringBuilder();
    c.append(c1);  // ..., n1,d1
    c.append(c2);  // ..., n1,d1,n2,d2

    StringBuilder opCode = new StringBuilder();
    // stack: ..., n1,d1,n2,d2
    opCode.append("STOREG " + G_TMP0 + "\n"); // d2
    opCode.append("STOREG " + G_TMP1 + "\n"); // n2
    opCode.append("STOREG " + G_TMP2 + "\n"); // d1
    opCode.append("STOREG " + G_TMP3 + "\n"); // n1

    // cross products L = n1*d2, R = n2*d1
    opCode.append("PUSHG " + G_TMP3 + "\n");
    opCode.append("PUSHG " + G_TMP0 + "\n");
    opCode.append("MUL\n"); // L

    opCode.append("PUSHG " + G_TMP1 + "\n");
    opCode.append("PUSHG " + G_TMP2 + "\n");
    opCode.append("MUL\n"); // R

    if (opText.equals("<")) {
        opCode.append("INF\n");
    } else if (opText.equals("<=")) {
        opCode.append("INFEQ\n");
    } else if (opText.equals(">")) {
        opCode.append("SUP\n");
    } else if (opText.equals(">=")) {
        opCode.append("SUPEQ\n");
    } else if (opText.equals("==")) {
        opCode.append("EQUAL\n");
    } else if (opText.equals("<>")) {
        opCode.append("NEQ\n");
    } else {
        throw new RuntimeException("Unknown LOGICOP: " + opText);
    }

    c.append(opCode.toString());
    return c.toString();
}

}

// ---------------- Parser rules ----------------
start
    : (instruction (SEMICOLON | NEWLINE)*)* EOF
      {
        StringBuilder prog = new StringBuilder();

        // Reserve temp globals (g0..g3)
        prog.append("ALLOC 9\n\n"); // reserve space for 9 temp globals
        // g0 = G_TMP0
        // g1 = G_TMP1
        // g2 = G_TMP2
        // g3 = G_TMP3

        prog.append(code.toString());  // body generated by instructions

        prog.append("FREE 9\n"); // free temp globals
        prog.append("HALT\n");

        System.out.println(prog.toString());
      }
    ;

instruction
    : afficher_instr
    | simplifier_instr
    ; 

afficher_instr 
    : 'Afficher' '(' e=expr ')'
      {
        emit($e.code);

        if ($e.exprType == null) {
            throw new RuntimeException("Internal error: exprType is null");
        }

        if ($e.exprType.equals("bool") ) {
            emit("WRITE");
            emit("POP");
        } else if ($e.exprType.equals("rat")) {
            emit("STOREG " + G_TMP0);
            emit("WRITE");
            emit("POP");
            emit("PUSHG " + G_TMP0);
            emit("WRITE");
            emit("POP");
        } else {
            throw new RuntimeException("Unknown expr type: " + $e.exprType);
        }
      }
    ;


simplifier_instr returns [ int n , int d ]
  : 'sim' '(' num=arithmExpr '/' denum=arithmExpr ')'
  //Simplification d'un nombre rationnel sim 
  //  (e.g., sim(4/2) devra donner 2/1 alors que sim(2/8)doit donner 1/4)
  {
    // Generate code for simplification
    String n = input.getText($num.start, $num.stop);
    String d = input.getText($denum.start, $denum.stop);
    // Compute GCD of n and d using Euclidean algorithm
    emit("PUSHI " + n);       // ..., n
    emit("PUSHI " + d);       // ..., n, d
    String startLabel = newLabel("gcd_start");
    String endLabel = newLabel("gcd_end");
    emit(startLabel + ":");
    emit("DUP");               // ..., n, d, d
    emit("PUSHI 0");          // ..., n, d, d, 0
    emit("EQUAL");            // ..., n, d, (d == 0)
    emit("JIF " + endLabel);  // if d == 0 goto end
    emit("DUP2");             // ..., n, d, n, d
    emit("MOD");              // ..., n, d, r = n % d
    emit("SWAP");             // ..., n, r, d
    emit("POP");              // ..., r, d
    emit("SWAP");             // ..., d, r
    emit("POP");              // ..., r
    emit("JMP " + startLabel);
    emit(endLabel + ":");
    emit("POP");              // remove 0
    emit("SWAP");             // ..., gcd
    // Now stack: ..., gcd
    emit("STOREG " + G_TMP0); // g0 = gcd
    // Compute simplified numerator: n / gcd
    emit("PUSHI " + n);       // ..., gcd, n
    emit("PUSHG " + G_TMP0);  // ..., gcd, n, gcd
    emit("DIV");              // ..., gcd, n/gcd
    // Compute simplified denominator: d / gcd
    emit("PUSHI " + d);       // ..., gcd, n/gcd, d
    emit("PUSHG " + G_TMP0);  // ..., gcd, n/gcd, d, gcd
    emit("DIV");              // ..., gcd, n/gcd, d/gcd
    // Final stack: ..., n/gcd, d/gcd
  }
  ;


// Combined expressions
expr returns [String code, String exprType]
    : b=boolOr      { $code = $b.code; $exprType = "bool"; }
    | a=arithmExpr  { $code = $a.code; $exprType = "rat";  } // non > et > ou
    ;


// Arithmetic expressions:  unary +/- > * / > + -
arithmExpr returns [String code]
    : t1=arithmTerm
      {
        $code = $t1.code;
      }
      ( op=ADDSUB t2=arithmTerm
        {
          StringBuilder c = new StringBuilder();
          c.append($code);
          c.append($t2.code);

          StringBuilder opCode = new StringBuilder();
          // stack: ..., n1,d1,n2,d2 (d2 on top)
          // temps: g0=d2, g1=n2, g2=d1, g3=n1
          opCode.append("STOREG " + G_TMP0 + "\n"); // g0 = d2
          opCode.append("STOREG " + G_TMP1 + "\n"); // g1 = n2
          opCode.append("STOREG " + G_TMP2 + "\n"); // g2 = d1
          opCode.append("STOREG " + G_TMP3 + "\n"); // g3 = n1

          if ($op.getText().equals("+")) {
              // num = n1*d2 + n2*d1
              opCode.append("PUSHG " + G_TMP3 + "\n"); // n1
              opCode.append("PUSHG " + G_TMP0 + "\n"); // d2
              opCode.append("MUL\n");                  // n1*d2
              opCode.append("PUSHG " + G_TMP1 + "\n"); // n2
              opCode.append("PUSHG " + G_TMP2 + "\n"); // d1
              opCode.append("MUL\n");                  // n2*d1
              opCode.append("ADD\n");                  // num

          } else { // '-'
              // num = n1*d2 - n2*d1
              opCode.append("PUSHG " + G_TMP3 + "\n"); // n1
              opCode.append("PUSHG " + G_TMP0 + "\n"); // d2
              opCode.append("MUL\n");                  // n1*d2
              opCode.append("PUSHG " + G_TMP1 + "\n"); // n2
              opCode.append("PUSHG " + G_TMP2 + "\n"); // d1
              opCode.append("MUL\n");                  // n2*d1
              opCode.append("SUB\n");                  // num
          }

          // den = d1*d2
          opCode.append("PUSHG " + G_TMP2 + "\n");
          opCode.append("PUSHG " + G_TMP0 + "\n");
          opCode.append("MUL\n");

          c.append(opCode.toString());
          $code = c.toString();
        }
      )*
    ;

arithmTerm returns [String code]
    : f1=arithmPow
      {
        $code = $f1.code;
      }
      ( '/' 'lire' '(' ')'   // special case: right is lire() as integer
        {
          StringBuilder c = new StringBuilder();
          c.append($code);          // stack: ..., n1, d1

          // Read integer n (denominator as integer)
          c.append("READ\n");       // stack: ..., n1, d1, n

          // Use temps:
          // g0 = n, g2 = d1, g3 = n1
          c.append("STOREG " + G_TMP0 + "\n"); // g0 = n
          c.append("STOREG " + G_TMP2 + "\n"); // g2 = d1
          c.append("STOREG " + G_TMP3 + "\n"); // g3 = n1

          // (n1/d1) / (n/1) = n1 / (d1 * n)
          // num = n1
          c.append("PUSHG " + G_TMP3 + "\n");  // num = n1

          // den = d1 * n
          c.append("PUSHG " + G_TMP2 + "\n");  // d1
          c.append("PUSHG " + G_TMP0 + "\n");  // n
          c.append("MUL\n");                   // den

          $code = c.toString();
        }
      | op=MULDIV f2=arithmPow
        {
          StringBuilder c = new StringBuilder();
          c.append($code);
          c.append($f2.code);

          StringBuilder opCode = new StringBuilder();
          // stack: ..., n1,d1,n2,d2
          // temps: g0=d2, g1=n2, g2=d1, g3=n1
          opCode.append("STOREG " + G_TMP0 + "\n"); // g0 = d2
          opCode.append("STOREG " + G_TMP1 + "\n"); // g1 = n2
          opCode.append("STOREG " + G_TMP2 + "\n"); // g2 = d1
          opCode.append("STOREG " + G_TMP3 + "\n"); // g3 = n1

          if ($op.getText().equals("*")) {
              // (n1/d1) * (n2/d2) = (n1*n2) / (d1*d2)
              // num
              opCode.append("PUSHG " + G_TMP3 + "\n"); // n1
              opCode.append("PUSHG " + G_TMP1 + "\n"); // n2
              opCode.append("MUL\n");                  // num
              // den
              opCode.append("PUSHG " + G_TMP2 + "\n"); // d1
              opCode.append("PUSHG " + G_TMP0 + "\n"); // d2
              opCode.append("MUL\n");                  // den

          } else { // '/'
              // (n1/d1) / (n2/d2) = (n1*d2) / (d1*n2)
              // num
              opCode.append("PUSHG " + G_TMP3 + "\n"); // n1
              opCode.append("PUSHG " + G_TMP0 + "\n"); // d2
              opCode.append("MUL\n");                  // num
              // den
              opCode.append("PUSHG " + G_TMP2 + "\n"); // d1
              opCode.append("PUSHG " + G_TMP1 + "\n"); // n2
              opCode.append("MUL\n");                  // den
          }

          c.append(opCode.toString());
          $code = c.toString();
        }
      )*
    ;


arithmAtom returns [String code]
     : 'num' '(' r=arithmExpr ')'
      {
        // after a: ..., num, den
        StringBuilder c = new StringBuilder();
        c.append($r.code);
        c.append("STOREG " + G_TMP0 + "\n"); // g0 = den, stack: ..., num
        c.append("PUSHI 1\n");               // make it num/1
        $code = c.toString();
      }
    | 'denum' '(' r=arithmExpr ')'
      {
        // after a: ..., num, den
        StringBuilder c = new StringBuilder();
        c.append($r.code);
        c.append("STOREG " + G_TMP0 + "\n"); // g0 = den, stack: ..., num
        c.append("POP\n");                   // drop num
        c.append("PUSHG " + G_TMP0 + "\n");  // push den
        c.append("PUSHI 1\n");               // den/1
        $code = c.toString();
      }
    | ADDSUB a=arithmAtom      // unary + / -
      {
        String op = $ADDSUB.getText();
        StringBuilder c = new StringBuilder();
        c.append($a.code); // stack: ..., num, den

        if (op.equals("+")) {
            // +x : hiçbir şey yapma
            $code = c.toString();
        } else {
            // -x : num'u işaret değiştir, den aynı kalsın
            // stack: ..., num, den
            c.append("STOREG " + G_TMP0 + "\n"); // g0 = den, stack: ..., num
            c.append("PUSHI -1\n");
            c.append("MUL\n");                  // num = num * -1
            c.append("PUSHG " + G_TMP0 + "\n"); // den'i geri koy
            $code = c.toString();
        }
      }
    | ENTIER '/' ENTIER    // rational literal: a/b
      {
          String a = $ENTIER(0).getText();
          String b = $ENTIER(1).getText();
          StringBuilder c = new StringBuilder();
          c.append("PUSHI ").append(a).append("\n");
          c.append("PUSHI ").append(b).append("\n");
          $code = c.toString();
      }

    | ENTIER        // integer literal: n  -> n/1
      {
        // literal n as rational n/1 -> ..., n,1
        String n = $ENTIER.getText();
        StringBuilder c = new StringBuilder();
        c.append("PUSHI ").append(n).append("\n"); // num
        c.append("PUSHI 1\n");                     // den
        $code = c.toString();
      }
    | '(' e=arithmExpr ')'         // parentheses
      {
        $code = $e.code;
      }
    | 'lire' '(' ')'   // rational read
      {
        // READ num, then READ den; stack: ..., num, den
        $code = "READ\nREAD\n";
      }
    ;

arithmPow returns [String code]
    : a1=arithmAtom
      {
        // Varsayılan: taban (power yoksa sadece a1)
        $code = $a1.code;
      }
      (
        // ---- (1) static exponent: (r) ** 3 ----
        POW e=ENTIER
        {
          int n = Integer.parseInt($e.getText());
          if (n < 0) {
              throw new RuntimeException("Negative exponent not supported: " + n);
          }

          StringBuilder c = new StringBuilder();

          if (n == 0) {
              // r^0 = 1/1
              c.append("PUSHI 1\n");
              c.append("PUSHI 1\n");
          } else {
              // r^1 = base
              c.append($a1.code);  // base'i yeniden üret

              for (int i = 1; i < n; i++) {
                  // stack: ..., r^i, num, den
                  c.append($a1.code);   // base'i tekrar koy

                  // iki rasyoneli çarp (r^i * base)
                  c.append("STOREG " + G_TMP0 + "\n"); // d2
                  c.append("STOREG " + G_TMP1 + "\n"); // n2
                  c.append("STOREG " + G_TMP2 + "\n"); // d1
                  c.append("STOREG " + G_TMP3 + "\n"); // n1

                  // num = n1 * n2
                  c.append("PUSHG " + G_TMP3 + "\n");
                  c.append("PUSHG " + G_TMP1 + "\n");
                  c.append("MUL\n");

                  // den = d1 * d2
                  c.append("PUSHG " + G_TMP2 + "\n");
                  c.append("PUSHG " + G_TMP0 + "\n");
                  c.append("MUL\n");
                  // stack: ..., num, den = r^(i+1)
              }
          }

          $code = c.toString();
        }

        // ---- (2) runtime exponent: (r) ** lire() ----
      | POW 'lire' '(' ')'
        {
          StringBuilder c = new StringBuilder();

          // 1. Tabanı üret: stack: ..., num, den
          c.append($a1.code);

          // 2. Tabanı global değişkenlere kaydet
          c.append("STOREG " + G_POW_BASE_DEN + "\n"); // base_den
          c.append("STOREG " + G_POW_BASE_NUM + "\n"); // base_num

          // 3. Üssü input'tan oku
          c.append("READ\n");                            // ..., n
          c.append("STOREG " + G_POW_EXP + "\n");        // exp = n, stack boş

          // 4. Sonucu 1/1 ile başlat: res = 1/1
          c.append("PUSHI 1\n");                         // num
          c.append("PUSHI 1\n");                         // den
          c.append("STOREG " + G_POW_RES_DEN + "\n");    // res_den
          c.append("STOREG " + G_POW_RES_NUM + "\n");    // res_num

          String L_CHECK = newLabel("POW_CHECK");
          String L_END   = newLabel("POW_END");

          // 5. Döngü: while (exp > 0) { res = res * base; exp--; }
          c.append("LABEL " + L_CHECK + "\n");

          // if exp == 0 -> get out
          c.append("PUSHG " + G_POW_EXP + "\n");
          c.append("PUSHI 0\n");
          c.append("EQUAL\n");
          c.append("JUMPT " + L_END + "\n");

          // ---- res = res * base ----
          // stack: ...
          // push res (n1,d1)
          c.append("PUSHG " + G_POW_RES_NUM + "\n");
          c.append("PUSHG " + G_POW_RES_DEN + "\n");

          // push base (n2,d2)
          c.append("PUSHG " + G_POW_BASE_NUM + "\n");
          c.append("PUSHG " + G_POW_BASE_DEN + "\n");

          // şimdi stack: ..., n1,d1,n2,d2

          // tmp'lere koy
          c.append("STOREG " + G_TMP0 + "\n"); // d2
          c.append("STOREG " + G_TMP1 + "\n"); // n2
          c.append("STOREG " + G_TMP2 + "\n"); // d1
          c.append("STOREG " + G_TMP3 + "\n"); // n1

          // num_new = n1 * n2
          c.append("PUSHG " + G_TMP3 + "\n");
          c.append("PUSHG " + G_TMP1 + "\n");
          c.append("MUL\n");

          // den_new = d1 * d2
          c.append("PUSHG " + G_TMP2 + "\n");
          c.append("PUSHG " + G_TMP0 + "\n");
          c.append("MUL\n");

          // yeni sonucu res_num / res_den olarak kaydet
          c.append("STOREG " + G_POW_RES_DEN + "\n");
          c.append("STOREG " + G_POW_RES_NUM + "\n");

          // exp--
          c.append("PUSHG " + G_POW_EXP + "\n");
          c.append("PUSHI 1\n");
          c.append("SUB\n");
          c.append("STOREG " + G_POW_EXP + "\n");

          // tekrar kontrol
          c.append("JUMP " + L_CHECK + "\n");

          // 6. Döngü bitti -> sonucu stack'e koy
          c.append("LABEL " + L_END + "\n");
          c.append("PUSHG " + G_POW_RES_NUM + "\n");
          c.append("PUSHG " + G_POW_RES_DEN + "\n");

          $code = c.toString();
        }
      )?
    ;



boolOr returns [String code]
    : a=boolAnd              { $code = $a.code; }
      ( 'ou' b=boolAnd       { $code = genOr($code, $b.code); } )*
    ;

boolAnd returns [String code]
    : a=boolNot              { $code = $a.code; }
      ( 'et' b=boolNot       { $code = genAnd($code, $b.code); } )*
    ;

boolNot returns [String code]
    : 'non' b=boolNot        { $code = genNot($b.code); }
    | boolAtom               { $code = $boolAtom.code; }
    ;

boolAtom returns [String code]
    : 'true'
      {
        $code = "PUSHI 1\n";
      }
    | 'false'
      {
        $code = "PUSHI 0\n";
      }
    | a1=arithmExpr op=LOGICOP a2=arithmExpr
      {
        $code = genCmp($a1.code, $op.getText(), $a2.code);
      }
    | '(' e=expr ')'
      {
        // Parantez içinde herhangi bir expr (bool veya rat)
        $code = $e.code;
      }
    | 'lire' '(' ')'
      {
        StringBuilder c = new StringBuilder();
        c.append("READ\n");
        c.append("PUSHI 0\n");
        c.append("NEQ\n");
        $code = c.toString();
      }
    ;


// ---------------- Lexer rules ----------------
NEWLINE : '\r'? '\n';       // match newlines
SEMICOLON : ';' ;           // match semicolons

POW    : '**' ;            // $POW.getText()  | $POW.getType()
MULDIV : ('*' | '/');      // $MULDIV.getText()  | $MULDIV.getType() 
ADDSUB : ('+' | '-');      // $ADDSUB.getText()  | $ADDSUB.getType()
INCDEC : '++' | '--' ;     // $INCDEC.getText()  | $INCDEC.getType()
LOGICOP : ('==' | '<>' | '<' | '<=' | '>' | '>='); // $LOGICOP.getText()  | $LOGICOP.getType()

ENTIER : ('0'..'9')+;       // match integers , all sequences of digits

TYPE : 'int' | 'bool' ;    // match types
ID : [a-zA-Z_] [a-zA-Z0-9_]* ;// match identifiers

WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
//UNMATCH : . -> skip;        // ignore any other character // -> il mange les paranthèses 

