// Compilation : antlr4 Rationnel.g4
// Execution   : javac Rationnel*.java

// --- To run the parser on an input file ---
// grun Rationnel start -tree

//--- To run the parser and show tokens ---
// grun Rationnel start -tokens

// --- To run the parser with a GUI parse tree viewer ---
// grun Rationnel start -gui


// --- To generate MVaP code ---
// grun Rationnel start < input.txt > out.mvap

// --- To assemble the generated MVaP code ---
// java MVaPAssembler out.mvap

// --- To run the generated MVaP program ---
// java CBaP out.mvap.cbap
   
// ---------- Notes on runtime representation ----------
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

    // ---- extra gloabals for tmp (gcd/sim/round) ----
    static final int G_TMP4 = 9;
    static final int G_TMP5 = 10;
    static final int G_TMP6 = 11;
    static final int G_TMP7 = 12;

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

  String genAbsTop() {
    // stack: ..., x
    // result: ..., |x|
    String L_POS = newLabel("ABS_POS");
    String L_END = newLabel("ABS_END");
    StringBuilder c = new StringBuilder();

    c.append("DUP\n");          // x, x
    c.append("PUSHI 0\n");      // x, x, 0
    c.append("INF\n");          // x, (x<0)
    c.append("JUMPF " + L_POS + "\n"); // if false => x>=0, jump (pops bool)

    // negative: turn x into -x
    c.append("PUSHI -1\n");
    c.append("MUL\n");
    c.append("JUMP " + L_END + "\n");

    c.append("LABEL " + L_POS + "\n");
    // already positive, do nothing

    c.append("LABEL " + L_END + "\n");
    return c.toString();
  }

  String genGcd(String aCode, String bCode) {
    String L_LOOP = newLabel("GCD_LOOP");
    String L_BODY = newLabel("GCD_BODY");
    String L_END  = newLabel("GCD_END");

    StringBuilder c = new StringBuilder();

    // push a then b
    c.append(aCode);
    c.append(bCode);

    // store into globals: A=g_tmp4, B=g_tmp5
    c.append("STOREG " + G_TMP5 + "\n"); // B
    c.append("STOREG " + G_TMP4 + "\n"); // A

    // A = abs(A), B = abs(B)
    c.append("PUSHG " + G_TMP4 + "\n");
    c.append(genAbsTop());
    c.append("STOREG " + G_TMP4 + "\n");

    c.append("PUSHG " + G_TMP5 + "\n");
    c.append(genAbsTop());
    c.append("STOREG " + G_TMP5 + "\n");

    // while (B != 0)
    c.append("LABEL " + L_LOOP + "\n");
    c.append("PUSHG " + G_TMP5 + "\n");
    c.append("PUSHI 0\n");
    c.append("EQUAL\n");                 // (B==0)
    c.append("JUMPF " + L_BODY + "\n");  // false => continue
    c.append("JUMP " + L_END + "\n");    // true  => end

    c.append("LABEL " + L_BODY + "\n");

    // T = A % B
    c.append("PUSHG " + G_TMP4 + "\n");  // A
    c.append("PUSHG " + G_TMP5 + "\n");  // B
    c.append("MOD\n");                   // A%B
    c.append("STOREG " + G_TMP6 + "\n"); // T

    // A = B
    c.append("PUSHG " + G_TMP5 + "\n");
    c.append("STOREG " + G_TMP4 + "\n");

    // B = T
    c.append("PUSHG " + G_TMP6 + "\n");
    c.append("STOREG " + G_TMP5 + "\n");

    c.append("JUMP " + L_LOOP + "\n");

    // end: push A
    c.append("LABEL " + L_END + "\n");
    c.append("PUSHG " + G_TMP4 + "\n");

    return c.toString();
  }


  String genLcm(String aCode, String bCode) {
    StringBuilder c = new StringBuilder();

    // Save originals
    c.append(aCode);
    c.append("STOREG " + G_TMP7 + "\n"); // A0
    c.append(bCode);
    c.append("STOREG " + G_TMP0 + "\n"); // B0

    // Compute g = gcd(A0,B0) using tmp4/tmp5/tmp6
    c.append("PUSHG " + G_TMP7 + "\n");
    c.append("STOREG " + G_TMP4 + "\n");
    c.append("PUSHG " + G_TMP0 + "\n");
    c.append("STOREG " + G_TMP5 + "\n");

    // A=abs(A), B=abs(B)
    c.append("PUSHG " + G_TMP4 + "\n");
    c.append(genAbsTop());
    c.append("STOREG " + G_TMP4 + "\n");
    c.append("PUSHG " + G_TMP5 + "\n");
    c.append(genAbsTop());
    c.append("STOREG " + G_TMP5 + "\n");

    String L_LOOP = newLabel("LCM_GCD_LOOP");
    String L_BODY = newLabel("LCM_GCD_BODY");
    String L_END  = newLabel("LCM_GCD_END");

    // while (B != 0)
    c.append("LABEL " + L_LOOP + "\n");
    c.append("PUSHG " + G_TMP5 + "\n");
    c.append("PUSHI 0\n");
    c.append("EQUAL\n");                 // (B==0)
    c.append("JUMPF " + L_BODY + "\n");  // false => continue
    c.append("JUMP " + L_END + "\n");    // true  => end

    c.append("LABEL " + L_BODY + "\n");

    // T = A % B
    c.append("PUSHG " + G_TMP4 + "\n");
    c.append("PUSHG " + G_TMP5 + "\n");
    c.append("MOD\n");
    c.append("STOREG " + G_TMP6 + "\n");

    // A = B
    c.append("PUSHG " + G_TMP5 + "\n");
    c.append("STOREG " + G_TMP4 + "\n");

    // B = T
    c.append("PUSHG " + G_TMP6 + "\n");
    c.append("STOREG " + G_TMP5 + "\n");

    c.append("JUMP " + L_LOOP + "\n");

    // end: gcd is in A (=tmp4)
    c.append("LABEL " + L_END + "\n");
    c.append("PUSHG " + G_TMP4 + "\n");   // g
    c.append("STOREG " + G_TMP6 + "\n");  // tmp6 = g

    // l = abs((A0 / g) * B0)
    c.append("PUSHG " + G_TMP7 + "\n");  // A0
    c.append("PUSHG " + G_TMP6 + "\n");  // g
    c.append("DIV\n");                   // A0/g

    c.append("PUSHG " + G_TMP0 + "\n");  // B0
    c.append("MUL\n");                   // (A0/g)*B0

    c.append(genAbsTop());               // abs(l)

    return c.toString();
  }

  String genSim(String ratCode) {
      StringBuilder c = new StringBuilder();

      // rat -> ..., num, den
      c.append(ratCode);

      // Save original den/num in safe temps (NOT used by genGcd)
      c.append("STOREG " + G_TMP0 + "\n"); // den0
      c.append("STOREG " + G_TMP1 + "\n"); // num0

      // Prepare |num0| and |den0| in gcd temps (genGcd uses TMP4/TMP5/TMP6 internally)
      c.append("PUSHG " + G_TMP1 + "\n");  // num0
      c.append(genAbsTop());
      c.append("STOREG " + G_TMP4 + "\n"); // A = abs(num0)

      c.append("PUSHG " + G_TMP0 + "\n");  // den0
      c.append(genAbsTop());
      c.append("STOREG " + G_TMP5 + "\n"); // B = abs(den0)

      // g = gcd(abs(num0), abs(den0))
      c.append(genGcd("PUSHG " + G_TMP4 + "\n", "PUSHG " + G_TMP5 + "\n"));
      c.append("STOREG " + G_TMP2 + "\n"); // g

      // num = num0 / g
      c.append("PUSHG " + G_TMP1 + "\n");
      c.append("PUSHG " + G_TMP2 + "\n");
      c.append("DIV\n");
      c.append("STOREG " + G_TMP1 + "\n");

      // den = den0 / g
      c.append("PUSHG " + G_TMP0 + "\n");
      c.append("PUSHG " + G_TMP2 + "\n");
      c.append("DIV\n");
      c.append("STOREG " + G_TMP0 + "\n");

      // normalize sign: if den < 0 then num=-num, den=-den
      String L_OK = newLabel("SIM_DEN_OK");
      c.append("PUSHG " + G_TMP0 + "\n");
      c.append("PUSHI 0\n");
      c.append("INF\n");               // den < 0 ?
      c.append("JUMPF " + L_OK + "\n");

      // den negative
      c.append("PUSHG " + G_TMP1 + "\n");
      c.append("PUSHI -1\n");
      c.append("MUL\n");
      c.append("STOREG " + G_TMP1 + "\n");

      c.append("PUSHG " + G_TMP0 + "\n");
      c.append("PUSHI -1\n");
      c.append("MUL\n");
      c.append("STOREG " + G_TMP0 + "\n");

      c.append("LABEL " + L_OK + "\n");

      // push back num, den
      c.append("PUSHG " + G_TMP1 + "\n");
      c.append("PUSHG " + G_TMP0 + "\n");

      return c.toString();
  }


  String genRoundNearestInt(String ratCode) {
    // returns an int rational: k/1
    String L_POS = newLabel("ROUND_POS");
    StringBuilder c = new StringBuilder();

    // stack: ..., num, den
    c.append(ratCode);

    c.append("STOREG " + G_TMP5 + "\n"); // den
    c.append("STOREG " + G_TMP4 + "\n"); // num

    // sign = (num < 0) ? 1 : 0   in tmp6
    c.append("PUSHG " + G_TMP4 + "\n");
    c.append("PUSHI 0\n");
    c.append("INF\n");
    c.append("STOREG " + G_TMP6 + "\n"); // sign

    // numAbs -> tmp4
    c.append("PUSHG " + G_TMP4 + "\n");
    c.append(genAbsTop());
    c.append("STOREG " + G_TMP4 + "\n");

    // denAbs -> tmp5
    c.append("PUSHG " + G_TMP5 + "\n");
    c.append(genAbsTop());
    c.append("STOREG " + G_TMP5 + "\n");

    // k = (numAbs + denAbs/2) / denAbs
    // compute denAbs/2
    c.append("PUSHG " + G_TMP5 + "\n");
    c.append("PUSHI 2\n");
    c.append("DIV\n");                 // denAbs/2

    c.append("PUSHG " + G_TMP4 + "\n"); // numAbs
    c.append("ADD\n");                  // numAbs + denAbs/2

    c.append("PUSHG " + G_TMP5 + "\n"); // denAbs
    c.append("DIV\n");                  // k (positive)
    c.append("STOREG " + G_TMP7 + "\n"); // k

    // if sign==1 => k = -k
    c.append("PUSHG " + G_TMP6 + "\n");
    c.append("JUMPF " + L_POS + "\n");
    c.append("PUSHG " + G_TMP7 + "\n");
    c.append("PUSHI -1\n");
    c.append("MUL\n");
    c.append("STOREG " + G_TMP7 + "\n");
    c.append("LABEL " + L_POS + "\n");

    // return k/1
    c.append("PUSHG " + G_TMP7 + "\n");
    c.append("PUSHI 1\n");

    return c.toString();
  }


  String genNormalizeDenSign() {
    // stack: ..., num, den
    // ensure den > 0 (if den<0 then num=-num, den=-den)
    String L_OK = newLabel("DEN_OK");
    StringBuilder c = new StringBuilder();

    c.append("STOREG " + G_TMP5 + "\n"); // den
    c.append("STOREG " + G_TMP4 + "\n"); // num

    c.append("PUSHG " + G_TMP5 + "\n");
    c.append("PUSHI 0\n");
    c.append("INF\n");                   // den < 0 ?
    c.append("JUMPF " + L_OK + "\n");

    c.append("PUSHG " + G_TMP4 + "\n");
    c.append("PUSHI -1\n");
    c.append("MUL\n");
    c.append("STOREG " + G_TMP4 + "\n");

    c.append("PUSHG " + G_TMP5 + "\n");
    c.append("PUSHI -1\n");
    c.append("MUL\n");
    c.append("STOREG " + G_TMP5 + "\n");

    c.append("LABEL " + L_OK + "\n");
    c.append("PUSHG " + G_TMP4 + "\n"); // num
    c.append("PUSHG " + G_TMP5 + "\n"); // den

    return c.toString();
  }



  

}

// ---------------- Parser rules ----------------
start
      : ((SEMICOLON | NEWLINE)* instruction (SEMICOLON | NEWLINE)*)* EOF
    {
      StringBuilder prog = new StringBuilder();
      prog.append("ALLOC 20\n\n");
      prog.append(code.toString());
      prog.append("FREE 20\n");
      prog.append("HALT\n");
      System.out.println(prog.toString());
    }
  ;

instruction
    : 'Afficher' '(' e=expr ')'
      {
        emit($e.code);

        if ($e.exprType == null) {
            throw new RuntimeException("Internal error: exprType is null");
        }

        if ($e.exprType.equals("bool")) {
            emit("WRITE");
            emit("POP");
        } else if ($e.exprType.equals("int")) {
            emit("POP");   // drop den (=1)
            emit("WRITE"); // print num
            emit("POP");   // drop num  
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


// Combined expressions
expr returns [String code, String exprType]
    : b=boolOr      { $code = $b.code; $exprType = "bool"; }
    | a=arithmExpr  { $code = $a.code; $exprType = $a.exprType; }
    ;


// Arithmetic expressions:  unary +/- > * / > + -
arithmExpr returns [String code, String exprType]
    : t1=arithmTerm
      {
        $code = $t1.code;
        $exprType = $t1.exprType;
      }
      ( op=ADDSUB t2=arithmTerm
        {
          StringBuilder c = new StringBuilder();
          c.append($code);
          c.append($t2.code);

          StringBuilder opCode = new StringBuilder();
          opCode.append("STOREG " + G_TMP0 + "\n"); // d2
          opCode.append("STOREG " + G_TMP1 + "\n"); // n2
          opCode.append("STOREG " + G_TMP2 + "\n"); // d1
          opCode.append("STOREG " + G_TMP3 + "\n"); // n1

          if ($op.getText().equals("+")) {
              opCode.append("PUSHG " + G_TMP3 + "\n");
              opCode.append("PUSHG " + G_TMP0 + "\n");
              opCode.append("MUL\n");
              opCode.append("PUSHG " + G_TMP1 + "\n");
              opCode.append("PUSHG " + G_TMP2 + "\n");
              opCode.append("MUL\n");
              opCode.append("ADD\n");
          } else {
              opCode.append("PUSHG " + G_TMP3 + "\n");
              opCode.append("PUSHG " + G_TMP0 + "\n");
              opCode.append("MUL\n");
              opCode.append("PUSHG " + G_TMP1 + "\n");
              opCode.append("PUSHG " + G_TMP2 + "\n");
              opCode.append("MUL\n");
              opCode.append("SUB\n");
          }

          opCode.append("PUSHG " + G_TMP2 + "\n");
          opCode.append("PUSHG " + G_TMP0 + "\n");
          opCode.append("MUL\n");

          // NOW stack is: ..., num, den  (den on top)
          opCode.append(genNormalizeDenSign());

          c.append(opCode.toString());
          $code = c.toString();

          // doğru tip çıkarımı: sol (güncel) + sağ (t2)
          if ($exprType.equals("int") && $t2.exprType.equals("int")) {
              $exprType = "int";
          } else {
              $exprType = "rat";
          }
        }
      )*
    ;

arithmTerm returns [String code, String exprType]
    : f1=arithmPow
      {
        $code = $f1.code;
        $exprType = $f1.exprType;
      }
      ( '/' 'lire' '(' ')'
        {
          StringBuilder c = new StringBuilder();
          c.append($code);          // n1, d1

          c.append("READ\n");       // n

          c.append("STOREG " + G_TMP0 + "\n"); // n
          c.append("STOREG " + G_TMP2 + "\n"); // d1
          c.append("STOREG " + G_TMP3 + "\n"); // n1

          // num = n1
          c.append("PUSHG " + G_TMP3 + "\n");
          // den = d1 * n
          c.append("PUSHG " + G_TMP2 + "\n");
          c.append("PUSHG " + G_TMP0 + "\n");
          c.append("MUL\n");

          c.append(genNormalizeDenSign());

          $code = c.toString();
          $exprType = "rat";  // çünkü bölme
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

              if ($exprType.equals("int") && $f2.exprType.equals("int")) {
                  $exprType = "int";
              } else {
                  $exprType = "rat";
              }

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

              $exprType = "rat";
          }

          opCode.append(genNormalizeDenSign());

          c.append(opCode.toString());
          $code = c.toString();
        }
      )*
    ;


arithmAtom returns [String code, String exprType]
    : 'num' '(' r=arithmExpr ')'
      {
        // r: rat veya int olabilir ama sonuç her zaman int
        StringBuilder c = new StringBuilder();
        c.append($r.code);                  // ..., num, den
        c.append("STOREG " + G_TMP0 + "\n");// g0 = den, stack: ..., num
        c.append("PUSHI 1\n");              // num/1
        $code = c.toString();
        $exprType = "int";
      }
    | 'denum' '(' r=arithmExpr ')'
      {
        StringBuilder c = new StringBuilder();
        c.append($r.code);                  // ..., num, den
        c.append("STOREG " + G_TMP0 + "\n");// g0 = den, stack: ..., num
        c.append("POP\n");                  // num at
        c.append("PUSHG " + G_TMP0 + "\n"); // den
        c.append("PUSHI 1\n");              // den/1
        $code = c.toString();
        $exprType = "int";
      }
     | 'pgcd' '(' ia=intExpr COMMA ib=intExpr ')'
      {
        StringBuilder c = new StringBuilder();
        c.append(genGcd($ia.code, $ib.code)); // ..., gcd
        c.append("PUSHI 1\n");                // ..., gcd, 1
        $code = c.toString();
        $exprType = "int";
      }

    | 'ppcm' '(' ia=intExpr COMMA ib=intExpr ')'
      {
        StringBuilder c = new StringBuilder();
        c.append(genLcm($ia.code, $ib.code)); // ..., lcm
        c.append("PUSHI 1\n");                // ..., lcm, 1
        $code = c.toString();
        $exprType = "int";
      }
    | 'sim' '(' r=arithmExpr ')' // simplify 
      { $code = genSim($r.code); $exprType = "rat"; }
    | LBRACK r=arithmExpr RBRACK // round to nearest int
      { $code = genRoundNearestInt($r.code); $exprType = "int"; }
    | ADDSUB a=arithmAtom      // unary + / -
      {
        String op = $ADDSUB.getText();
        StringBuilder c = new StringBuilder();
        c.append($a.code);           // ..., num, den

        if (op.equals("+")) {
            $code = c.toString();
        } else {
            c.append("STOREG " + G_TMP0 + "\n"); // g0 = den, stack: ..., num
            c.append("PUSHI -1\n");
            c.append("MUL\n");                  // num = -num
            c.append("PUSHG " + G_TMP0 + "\n"); // den geri
            $code = c.toString();
        }
        // unary + veya - tipi değiştirmez
        $exprType = $a.exprType;
      }
    | x=signedInt '/' y=signedInt
      {
        StringBuilder c = new StringBuilder();
        c.append($x.code);              // num
        c.append($y.code);              // den
        c.append(genNormalizeDenSign()); // den>0
        $code = c.toString();
        $exprType = "rat";
      }

    | ENTIER        // integer literal: n  -> n/1
      {
        String n = $ENTIER.getText();
        StringBuilder c = new StringBuilder();
        c.append("PUSHI ").append(n).append("\n"); // num
        c.append("PUSHI 1\n");                     // den
        $code = c.toString();
        $exprType = "int";
      }
    | '(' e=arithmExpr ')'
      {
        $code = $e.code;
        $exprType = $e.exprType;
      }
    | 'lire' '(' ')'   // rational read
      {
        StringBuilder c = new StringBuilder();
        c.append("READ \nREAD \n");          // num, den
        c.append(genNormalizeDenSign());
        $code = c.toString();
        $exprType = "rat";
      }
    ;

arithmPow returns [String code, String exprType]
    : a1=arithmAtom
      {
        $code = $a1.code;
        $exprType = $a1.exprType;   // pow yoksa base tipi
      }
      (
        // ---------- (1) static exponent: a ** 3 ----------
        POW e=ENTIER
        {
          int n = Integer.parseInt($e.getText());
          if (n < 0) {
              throw new RuntimeException("Negative exponent not supported: " + n);
          }

          StringBuilder c = new StringBuilder();

          if (n == 0) {
              c.append("PUSHI 1\n");
              c.append("PUSHI 1\n");
              $exprType = $a1.exprType.equals("rat") ? "rat" : "int";
          } else {
              // r^n: base'i n kez çarp
              c.append($a1.code); // r

              for (int i = 1; i < n; i++) {
                  c.append($a1.code);   // base'i tekrar koy (n2,d2)

                  // stack: ..., n1,d1,n2,d2
                  c.append("STOREG " + G_TMP0 + "\n"); // d2
                  c.append("STOREG " + G_TMP1 + "\n"); // n2
                  c.append("STOREG " + G_TMP2 + "\n"); // d1
                  c.append("STOREG " + G_TMP3 + "\n"); // n1

                  // num = n1*n2
                  c.append("PUSHG " + G_TMP3 + "\n");
                  c.append("PUSHG " + G_TMP1 + "\n");
                  c.append("MUL\n");

                  // den = d1*d2
                  c.append("PUSHG " + G_TMP2 + "\n");
                  c.append("PUSHG " + G_TMP0 + "\n");
                  c.append("MUL\n");
              }

              $exprType = $a1.exprType; // base rat -> rat, base int -> int
          }

          $code = c.toString();
        }

        // ---------- (2) runtime exponent: a ** lire() ----------
      | POW 'lire' '(' ')'
        {
          StringBuilder c = new StringBuilder();

          // base: ..., num, den
          c.append($a1.code);

          // save base
          c.append("STOREG " + G_POW_BASE_DEN + "\n");
          c.append("STOREG " + G_POW_BASE_NUM + "\n");

          // read exponent
          c.append("READ\n");
          c.append("STOREG " + G_POW_EXP + "\n");   // stack empty

          // ---- reject negative exponent safely ----
          String L_EXP_OK = newLabel("POW_EXP_OK");

          c.append("PUSHG " + G_POW_EXP + "\n");
          c.append("PUSHI 0\n");
          c.append("INF\n");                        // exp < 0 ?
          c.append("JUMPF " + L_EXP_OK + "\n");     // false => exp >= 0
          c.append("HALT\n");                       // true  => exp < 0

          c.append("LABEL " + L_EXP_OK + "\n");

          // res = 1/1
          c.append("PUSHI 1\n");
          c.append("PUSHI 1\n");
          c.append("STOREG " + G_POW_RES_DEN + "\n");
          c.append("STOREG " + G_POW_RES_NUM + "\n");

          String L_CHECK = newLabel("POW_CHECK");
          String L_BODY  = newLabel("POW_BODY");
          String L_END   = newLabel("POW_END");

          // while (exp != 0)
          c.append("LABEL " + L_CHECK + "\n");
          c.append("PUSHG " + G_POW_EXP + "\n");
          c.append("PUSHI 0\n");
          c.append("EQUAL\n");                      // exp == 0 ?
          c.append("JUMPF " + L_BODY + "\n");       // false => continue
          c.append("JUMP " + L_END + "\n");         // true  => end

          c.append("LABEL " + L_BODY + "\n");

          // res = res * base
          c.append("PUSHG " + G_POW_RES_NUM + "\n");
          c.append("PUSHG " + G_POW_RES_DEN + "\n");
          c.append("PUSHG " + G_POW_BASE_NUM + "\n");
          c.append("PUSHG " + G_POW_BASE_DEN + "\n");

          c.append("STOREG " + G_TMP0 + "\n"); // d2
          c.append("STOREG " + G_TMP1 + "\n"); // n2
          c.append("STOREG " + G_TMP2 + "\n"); // d1
          c.append("STOREG " + G_TMP3 + "\n"); // n1

          c.append("PUSHG " + G_TMP3 + "\n");
          c.append("PUSHG " + G_TMP1 + "\n");
          c.append("MUL\n");                    // num

          c.append("PUSHG " + G_TMP2 + "\n");
          c.append("PUSHG " + G_TMP0 + "\n");
          c.append("MUL\n");                    // den

          c.append("STOREG " + G_POW_RES_DEN + "\n");
          c.append("STOREG " + G_POW_RES_NUM + "\n");

          // exp--
          c.append("PUSHG " + G_POW_EXP + "\n");
          c.append("PUSHI 1\n");
          c.append("SUB\n");
          c.append("STOREG " + G_POW_EXP + "\n");

          c.append("JUMP " + L_CHECK + "\n");

          // end
          c.append("LABEL " + L_END + "\n");
          c.append("PUSHG " + G_POW_RES_NUM + "\n");
          c.append("PUSHG " + G_POW_RES_DEN + "\n");

          $code = c.toString();
          $exprType = "rat";
        }
      )*
    ;

intExpr returns [String code]
  : s=signedInt
    { $code = $s.code; }
  | 'num' '(' r=arithmExpr ')'
    {
      StringBuilder c = new StringBuilder();
      c.append($r.code);
      c.append("STOREG " + G_TMP0 + "\n"); // pop den, stack: ..., num
      $code = c.toString();
    }
  | 'denum' '(' r=arithmExpr ')'
    {
      StringBuilder c = new StringBuilder();
      c.append($r.code);
      c.append("STOREG " + G_TMP0 + "\n"); // pop den
      c.append("POP\n");                   // pop num
      c.append("PUSHG " + G_TMP0 + "\n");  // push den
      $code = c.toString();
    }
  | 'pgcd' '(' a=intExpr COMMA b=intExpr ')'
    { $code = genGcd($a.code, $b.code); }
  | 'ppcm' '(' a=intExpr COMMA b=intExpr ')'
    { $code = genLcm($a.code, $b.code); }
  ;

signedInt returns [String code]
  : ADDSUB? ENTIER
    {
      String s = ($ADDSUB != null) ? $ADDSUB.getText() : "";
      $code = "PUSHI " + s + $ENTIER.getText() + "\n";
    }
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
    | '(' b=boolOr ')'
      { $code = $b.code; }
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

LOGICOP : ('==' | '<>' | '<' | '<=' | '>' | '>='); // $LOGICOP.getText()  | $LOGICOP.getType()

ENTIER : ('0'..'9')+;       // match integers , all sequences of digits

LBRACK : '[' ; 
RBRACK : ']' ;
COMMA  : ',' ;

LINE_COMMENT : '//' ~[\r\n]* -> skip ;

WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
//UNMATCH : . -> skip;        // ignore any other character // -> il mange les paranthèses 


