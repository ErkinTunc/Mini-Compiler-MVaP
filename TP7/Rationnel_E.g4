// Compilation : antlr4 Rationnel_E.g4             Execution : javac Rationnel_E*.java

// --- To run the parser on an input file --- grun Rationnel_E start -tree

//--- To run the parser and show tokens --- grun Rationnel_E start -tokens --- To run the parser
// with a GUI parse tree viewer --- grun Rationnel_E start -gui

// --- To generate MVaP code --- grun Rationnel_E start < input.txt > out.mvap

// --- To assemble the generated MVaP code --- java MVaPAssembler out.mvap

// --- To run the generated MVaP program --- java CBaP out.mvap.cbap

// ---------- Notes on runtime representation ----------
/*
 == Booleans on MVaP == false is 0 true is 1 Every boolean expression must leave exactly one integer
 on top of the stack: 0 or 1
 
 == Rationals on MVaP stack == Pick one representation and never change it.
 
 + A rational = two ints on the stack: - [..., num, den] with den on top
 
 + So after compiling a rational expression r, the stack ends as: - ..., num(r), den(r)
 
 == Temporary global cells == ecide now how many temp cells you want and what they’re for.
 
 Example: g0..g3 reserved for temp rationals: g0, g1, g2, g3 = scratch for num/den during +, -, *,
 /, comparisons, etc.
 
 Later you can add more if absolutely necessary, but start small.
 */

grammar Rationnel_E;

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

  // ---------- Rational operations code generation ----------
  String genAddRat(String c1, String c2) {
    StringBuilder c = new StringBuilder();
    c.append(c1); // ..., n1, d1
    c.append(c2); // ..., n1, d1, n2, d2

    c.append("STOREG " + G_TMP0 + "\n"); // d2
    c.append("STOREG " + G_TMP1 + "\n"); // n2
    c.append("STOREG " + G_TMP2 + "\n"); // d1
    c.append("STOREG " + G_TMP3 + "\n"); // n1

    // num = n1*d2 + n2*d1
    c.append("PUSHG " + G_TMP3 + "\n");
    c.append("PUSHG " + G_TMP0 + "\n");
    c.append("MUL\n");
    c.append("PUSHG " + G_TMP1 + "\n");
    c.append("PUSHG " + G_TMP2 + "\n");
    c.append("MUL\n");
    c.append("ADD\n");

    // den = d1*d2
    c.append("PUSHG " + G_TMP2 + "\n");
    c.append("PUSHG " + G_TMP0 + "\n");
    c.append("MUL\n");

    c.append(genNormalizeDenSign());
    return c.toString();
  }

  String genSubRat(String c1, String c2) {
    StringBuilder c = new StringBuilder();
    c.append(c1);
    c.append(c2);

    c.append("STOREG " + G_TMP0 + "\n"); // d2
    c.append("STOREG " + G_TMP1 + "\n"); // n2
    c.append("STOREG " + G_TMP2 + "\n"); // d1
    c.append("STOREG " + G_TMP3 + "\n"); // n1

    // num = n1*d2 - n2*d1
    c.append("PUSHG " + G_TMP3 + "\n");
    c.append("PUSHG " + G_TMP0 + "\n");
    c.append("MUL\n");
    c.append("PUSHG " + G_TMP1 + "\n");
    c.append("PUSHG " + G_TMP2 + "\n");
    c.append("MUL\n");
    c.append("SUB\n");

    // den = d1*d2
    c.append("PUSHG " + G_TMP2 + "\n");
    c.append("PUSHG " + G_TMP0 + "\n");
    c.append("MUL\n");

    c.append(genNormalizeDenSign());
    return c.toString();
  }

  String genMulRat(String c1, String c2) {
    StringBuilder c = new StringBuilder();
    c.append(c1);
    c.append(c2);

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

    c.append(genNormalizeDenSign());
    return c.toString();
  }

  String genDivRat(String c1, String c2) {
    StringBuilder c = new StringBuilder();
    c.append(c1);
    c.append(c2);

    c.append("STOREG " + G_TMP0 + "\n"); // d2
    c.append("STOREG " + G_TMP1 + "\n"); // n2
    c.append("STOREG " + G_TMP2 + "\n"); // d1
    c.append("STOREG " + G_TMP3 + "\n"); // n1

    // assert n2 != 0
    c.append("PUSHG " + G_TMP1 + "\n");
    c.append(genAssertNonZeroTop());
    c.append("POP\n");

    // num = n1*d2
    c.append("PUSHG " + G_TMP3 + "\n");
    c.append("PUSHG " + G_TMP0 + "\n");
    c.append("MUL\n");

    // den = d1*n2
    c.append("PUSHG " + G_TMP2 + "\n");
    c.append("PUSHG " + G_TMP1 + "\n");
    c.append("MUL\n");

    c.append(genNormalizeDenSign());
    return c.toString();
  }

 // ----------- Code generation helpers ----------
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

      // Evaluate and save originals
      c.append(aCode);
      c.append("STOREG " + G_TMP7 + "\n"); // A0
      c.append(bCode);
      c.append("STOREG " + G_TMP0 + "\n"); // B0

      String L_CHECK_B = newLabel("LCM_CHECK_B");
      String L_COMPUTE = newLabel("LCM_COMPUTE");
      String L_END     = newLabel("LCM_END");

      // if (A0 == 0) -> result 0
      c.append("PUSHG " + G_TMP7 + "\n");
      c.append("PUSHI 0\n");
      c.append("EQUAL\n");
      c.append("JUMPF " + L_CHECK_B + "\n"); // if A0!=0 check B
      c.append("PUSHI 0\n");
      c.append("JUMP " + L_END + "\n");

      // if (B0 == 0) -> result 0
      c.append("LABEL " + L_CHECK_B + "\n");
      c.append("PUSHG " + G_TMP0 + "\n");
      c.append("PUSHI 0\n");
      c.append("EQUAL\n");
      c.append("JUMPF " + L_COMPUTE + "\n"); // if B0!=0 compute
      c.append("PUSHI 0\n");
      c.append("JUMP " + L_END + "\n");

      // ---- normal compute path (A0!=0 and B0!=0) ----
      c.append("LABEL " + L_COMPUTE + "\n");

      // g = gcd(A0, B0)  (genGcd uses TMP4/TMP5/TMP6 internally, that's fine)
      c.append(genGcd("PUSHG " + G_TMP7 + "\n", "PUSHG " + G_TMP0 + "\n"));
      c.append("STOREG " + G_TMP6 + "\n"); // g

      // absA -> TMP1  (safe: genGcd doesn't use TMP1)
      c.append("PUSHG " + G_TMP7 + "\n");
      c.append(genAbsTop());
      c.append("STOREG " + G_TMP1 + "\n");

      // absB -> TMP2  (safe: genGcd doesn't use TMP2)
      c.append("PUSHG " + G_TMP0 + "\n");
      c.append(genAbsTop());
      c.append("STOREG " + G_TMP2 + "\n");

      // l = (absA / g) * absB
      c.append("PUSHG " + G_TMP1 + "\n"); // absA
      c.append("PUSHG " + G_TMP6 + "\n"); // g
      c.append("DIV\n");
      c.append("PUSHG " + G_TMP2 + "\n"); // absB
      c.append("MUL\n");

      c.append("LABEL " + L_END + "\n");
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

    String genAssertNonZeroTop() {
    // stack: ..., x
    // if x == 0 => HALT, else keep x
    String L_OK = newLabel("NZ_OK");
    StringBuilder c = new StringBuilder();
    c.append("DUP\n");
    c.append("PUSHI 0\n");
    c.append("EQUAL\n");            // ..., x, (x==0)
    c.append("JUMPF " + L_OK + "\n"); // if false (x!=0) jump
    c.append("HALT\n");             // x==0
    c.append("LABEL " + L_OK + "\n"); // x still on stack
    return c.toString();
    }

    // ---------------- TP7 Step 1: Globals memory model + symbol table ----------------

    // Temp globals already reserved: 0..12  (ALLOC 13)
    static final int BASE_VARS = 13;  // first address for user variables

    static class Sym {
    final String name;
    final String type;   // "int" | "bool" | "rat"
    final int addr;      // base address in MVaP globals
    final int size;      // 1 for int/bool, 2 for rat

    Sym(String name, String type, int addr, int size) {
        this.name = name;
        this.type = type;
        this.addr = addr;
        this.size = size;
    }
    }

    Map<String, Sym> globals = new HashMap<>();
    int nextVarAddr = BASE_VARS;

    int typeSize(String t) {
    if (t.equals("rat")) return 2;
    if (t.equals("int")) return 1;
    if (t.equals("bool")) return 1;
    throw new RuntimeException("Unknown type: " + t);
    }

    Sym declareGlobal(String name, String type) {
    if (globals.containsKey(name)) {
        throw new RuntimeException("Variable already declared: " + name);
    }
    int sz = typeSize(type);
    Sym s = new Sym(name, type, nextVarAddr, sz);
    globals.put(name, s);
    nextVarAddr += sz;
    return s;
    }

    Sym requireGlobal(String name) {
    Sym s = globals.get(name);
    if (s == null) throw new RuntimeException("Undeclared variable: " + name);
    return s;
    }

    // Load variable value onto stack, following runtime representation:
    // int/bool: [..., v]
    // rat:      [..., num, den]   (den at top)
    String genLoadVar(String name) {
    Sym s = requireGlobal(name);
    StringBuilder c = new StringBuilder();
    if (s.type.equals("rat")) {
        c.append("PUSHG ").append(s.addr).append("\n");       // num
        c.append("PUSHG ").append(s.addr + 1).append("\n");   // den
    } else {
        c.append("PUSHG ").append(s.addr).append("\n");
    }
    return c.toString();
    }

    // Store value from stack into variable:
    // int/bool: stack [..., v]
    // rat:      stack [..., num, den]
    String genStoreVar(String name) {
    Sym s = requireGlobal(name);
    StringBuilder c = new StringBuilder();
    if (s.type.equals("rat")) {
        c.append("STOREG ").append(s.addr + 1).append("\n"); // den
        c.append("STOREG ").append(s.addr).append("\n");     // num
    } else {
        c.append("STOREG ").append(s.addr).append("\n");
    }
    return c.toString();
    }

  

}

// ---------------- Parser rules ----------------
start: (SEMICOLON | NEWLINE)* (
		declaration (SEMICOLON | NEWLINE)*
	)* (instruction (SEMICOLON | NEWLINE)*)* EOF {
      int tempCells = 13;
      int varCells  = nextVarAddr - BASE_VARS;

      StringBuilder prog = new StringBuilder();
      prog.append("ALLOC ").append(tempCells + varCells).append("\n\n");
      prog.append(code.toString());
      prog.append("FREE ").append(tempCells + varCells).append("\n");
      prog.append("HALT\n");
      System.out.println(prog.toString());
    };

declaration:
	t = type ids = idList {
      for (String name : $ids.names) {
        declareGlobal(name, $t.tname);
      }
    } (
		'=' e = expr {
        // Optional init: type check + store
        Sym s = requireGlobal($ids.names.get(0)); // init only for single var
        if ($ids.names.size() != 1) {
          throw new RuntimeException("Initialization only allowed for a single variable in this fast version.");
        }
        // type check
        if (s.type.equals("bool") && !$e.exprType.equals("bool"))
          throw new RuntimeException("Type mismatch: expected bool");
        if (s.type.equals("int")  && !$e.exprType.equals("int"))
          throw new RuntimeException("Type mismatch: expected int");
        if (s.type.equals("rat")  && !($e.exprType.equals("rat") || $e.exprType.equals("int")))
          throw new RuntimeException("Type mismatch: expected rat");

        emit($e.code);

        // int -> rat promotion if needed
        if (s.type.equals("rat") && $e.exprType.equals("int")) {
          // stack had: num, den(=1) already, OK
        }
        emit(genStoreVar(s.name));
      }
	)?;

type
	returns[String tname]:
	'entier' { $tname = "int";  }
	| 'booleen' { $tname = "bool"; }
	| 'rationnel' { $tname = "rat";  };

idList
	returns[java.util.List<String> names]:
	a = ID {
      $names = new java.util.ArrayList<>();
      $names.add($a.getText());
    } (COMMA b = ID { $names.add($b.getText()); })*;

instruction:
	id = ID '=' e = expr {
      Sym s = requireGlobal($id.getText());

      // type check
      if (s.type.equals("bool") && !$e.exprType.equals("bool"))
        throw new RuntimeException("Type mismatch: expected bool for " + s.name);
      if (s.type.equals("int")  && !$e.exprType.equals("int"))
        throw new RuntimeException("Type mismatch: expected int for " + s.name);
      if (s.type.equals("rat")  && !($e.exprType.equals("rat") || $e.exprType.equals("int")))
        throw new RuntimeException("Type mismatch: expected rat for " + s.name);

      emit($e.code);

      // int value is already num/1 in your compiler, so OK for rat too
      emit(genStoreVar(s.name));
    }
	| block
	| c = expr '?' a = instruction (':' s = instruction)? {
        if (!$c.exprType.equals("bool"))
            throw new RuntimeException("Condition must be bool in ?:");

        String L_ELSE = newLabel("COND_ELSE");
        String L_END  = newLabel("COND_END");

        emit($c.code);
        emit("JUMPF " + L_ELSE);  // pops condition

        // then branch
        // (instruction action already emits its code)
        // so we must "call" it by just letting parser match it
        // BUT since instruction emits directly, we only need labels around.
        emit("JUMP " + L_END);
        emit("LABEL " + L_ELSE);

        // else branch if exists; if not, do nothing
        emit("LABEL " + L_END);
        }
      | c=expr '?' 
        {
        if (!$c.exprType.equals("bool"))
            throw new RuntimeException("Condition must be bool in ?:");

        String L_ELSE = newLabel("COND_ELSE");
        String L_END  = newLabel("COND_END");

        emit($c.code);
        emit("JUMPF " + L_ELSE);   // if false jump else (pops cond)
            }
                a=instruction
            {
        emit("JUMP " + L_END);
        emit("LABEL " + L_ELSE);
            }
                ( ':' s=instruction )?
            {
        emit("LABEL " + L_END);
        }
     | 'Pour' idx=ID '=' d=intExpr '..' f=intExpr 'Faire'
        {
        Sym s = requireGlobal($idx.getText());
        if (!s.type.equals("int"))
            throw new RuntimeException("Pour index must be an int variable: " + s.name);

        // idx = debut
        emit($d.code);
        emit("STOREG " + s.addr);

        // save fin in a temp global (reuse one safe temp cell)
        // we'll use G_TMP0 for fin (int)
        emit($f.code);
        emit("STOREG " + G_TMP0);

        String L_CHECK = newLabel("FOR_CHECK");
        String L_BODY  = newLabel("FOR_BODY");
        String L_END   = newLabel("FOR_END");

        emit("LABEL " + L_CHECK);

        // if idx <= fin then body else end
        emit("PUSHG " + s.addr);
        emit("PUSHG " + G_TMP0);
        emit("INFEQ");
        emit("JUMPF " + L_END);

        emit("LABEL " + L_BODY);
        }
        body=instruction
        {
        // idx = idx + 1
        Sym s = requireGlobal($idx.getText());
        emit("PUSHG " + s.addr);
        emit("PUSHI 1");
        emit("ADD");
        emit("STOREG " + s.addr);

        emit("JUMP " + L_CHECK);
        emit("LABEL " + L_END);

        // spec says index ends equal to fin -> force it
        emit("PUSHG " + G_TMP0);
        emit("STOREG " + s.addr);
        }
	| 'Afficher' '(' e = expr ')' {
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
      };

block: '{' (instruction (SEMICOLON | NEWLINE)*)* '}';

instrCode returns [String code]
  : i=instruction { $code = ""; } // placeholder, aşağıda override edeceğiz
  ;

// Combined expressions
expr
	returns[String code, String exprType]:
	b = boolOr { $code = $b.code; $exprType = "bool"; }
	| a = arithmExpr { $code = $a.code; $exprType = $a.exprType; };

// Arithmetic expressions:  unary +/- > * / > + -
arithmExpr
	returns[String code, String exprType]:
	t1 = arithmTerm {
        $code = $t1.code;
        $exprType = $t1.exprType;
      } (
		op = ADDSUB t2 = arithmTerm {
          if ($op.getText().equals("+")) {
            $code = genAddRat($code, $t2.code);
          } else {
            $code = genSubRat($code, $t2.code);
          }

          if ($exprType.equals("int") && $t2.exprType.equals("int")) {
            $exprType = "int";
          } else {
            $exprType = "rat";
          }
        }
	)*;

arithmTerm
	returns[String code, String exprType]:
	f1 = arithmPow {
        $code = $f1.code;
        $exprType = $f1.exprType;
      } (
		op = (MUL | DIV) f2 = arithmPow {
          if ($op.getText().equals("*")) {
            $code = genMulRat($code, $f2.code);
            if ($exprType.equals("int") && $f2.exprType.equals("int")) $exprType = "int";
            else $exprType = "rat";
          } else {
            $code = genDivRat($code, $f2.code);
            $exprType = "rat";
          }
        }
	)*;

arithmAtom
	returns[String code, String exprType]:
	'num' '(' r = arithmExpr ')' {
        // r: rat veya int olabilir ama sonuç her zaman int
        StringBuilder c = new StringBuilder();
        c.append($r.code);                  // ..., num, den
        c.append("STOREG " + G_TMP0 + "\n");// g0 = den, stack: ..., num
        c.append("PUSHI 1\n");              // num/1
        $code = c.toString();
        $exprType = "int";
      }
	| 'denum' '(' r = arithmExpr ')' {
        StringBuilder c = new StringBuilder();
        c.append($r.code);                  // ..., num, den
        c.append("STOREG " + G_TMP0 + "\n");// g0 = den, stack: ..., num
        c.append("POP\n");                  // num at
        c.append("PUSHG " + G_TMP0 + "\n"); // den
        c.append("PUSHI 1\n");              // den/1
        $code = c.toString();
        $exprType = "int";
      }
	| 'pgcd' '(' ia = intExpr COMMA ib = intExpr ')' {
        StringBuilder c = new StringBuilder();
        c.append(genGcd($ia.code, $ib.code)); // ..., gcd
        c.append("PUSHI 1\n");                // ..., gcd, 1
        $code = c.toString();
        $exprType = "int";
      }
	| 'ppcm' '(' ia = intExpr COMMA ib = intExpr ')' {
        StringBuilder c = new StringBuilder();
        c.append(genLcm($ia.code, $ib.code)); // ..., lcm
        c.append("PUSHI 1\n");                // ..., lcm, 1
        $code = c.toString();
        $exprType = "int";
      }
	| 'sim' '(' r = arithmExpr ')' // simplify 
	{ $code = genSim($r.code); $exprType = "rat"; }
	| LBRACK r = arithmExpr RBRACK // round to nearest int
	{ $code = genRoundNearestInt($r.code); $exprType = "int"; }
	| x = signedInt DIV y = signedInt {
    StringBuilder c = new StringBuilder();
    c.append($x.code);
    c.append($y.code);
    c.append(genAssertNonZeroTop());  // y != 0
    c.append(genNormalizeDenSign());  // den > 0
    $code = c.toString();
    $exprType = "rat";
  }
	| ADDSUB a = arithmAtom // unary + / -
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
	| ENTIER // integer literal: n  -> n/1
	{
        String n = $ENTIER.getText();
        StringBuilder c = new StringBuilder();
        c.append("PUSHI ").append(n).append("\n"); // num
        c.append("PUSHI 1\n");                     // den
        $code = c.toString();
        $exprType = "int";
      }
	| '(' e = arithmExpr ')' {
        $code = $e.code;
        $exprType = $e.exprType;
      }
	| 'lire' '(' ')' {
        StringBuilder c = new StringBuilder();
        c.append("READ\nREAD\n");          // num, den
        c.append(genAssertNonZeroTop());   // den != 0
        c.append(genNormalizeDenSign());   // den > 0
        $code = c.toString();
        $exprType = "rat";
    }
	| id = ID {
      Sym s = requireGlobal($id.getText());
      $code = genLoadVar(s.name);
      $exprType = s.type;
    };

arithmPow
	returns[String code, String exprType]:
	a1 = arithmAtom {
      $code = $a1.code;
      $exprType = $a1.exprType;   // pas de POW => type de la base
    } (
		// ---------- (1) static exponent: a ** 3 ----------
		POW e = ENTIER {
        int n = Integer.parseInt($e.getText());
        if (n < 0) throw new RuntimeException("Negative exponent not supported: " + n);

        StringBuilder c = new StringBuilder();

        // base -> globals
        c.append($a1.code); // ..., baseNum, baseDen
        c.append("STOREG " + G_POW_BASE_DEN + "\n");
        c.append("STOREG " + G_POW_BASE_NUM + "\n");

        // res = 1/1
        c.append("PUSHI 1\n");
        c.append("PUSHI 1\n");
        c.append("STOREG " + G_POW_RES_DEN + "\n");
        c.append("STOREG " + G_POW_RES_NUM + "\n");

        // exp = n
        c.append("PUSHI " + n + "\n");
        c.append("STOREG " + G_POW_EXP + "\n");

        String L_CHECK = newLabel("POW_S_CHECK");
        String L_BODY  = newLabel("POW_S_BODY");
        String L_END   = newLabel("POW_S_END");

        // while (exp != 0)
        c.append("LABEL " + L_CHECK + "\n");
        c.append("PUSHG " + G_POW_EXP + "\n");
        c.append("PUSHI 0\n");
        c.append("EQUAL\n");                 // exp == 0 ?
        c.append("JUMPF " + L_BODY + "\n");  // false => continue
        c.append("JUMP " + L_END + "\n");    // true  => end

        c.append("LABEL " + L_BODY + "\n");

        // res = res * base   (reuse genMulRat)
        c.append(
          genMulRat(
            "PUSHG " + G_POW_RES_NUM + "\n" +
            "PUSHG " + G_POW_RES_DEN + "\n",
            "PUSHG " + G_POW_BASE_NUM + "\n" +
            "PUSHG " + G_POW_BASE_DEN + "\n"
          )
        );

        // store back into res globals (stack: ..., num, den)
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
        $exprType = $a1.exprType;   // IMPORTANT: same as base
      }

		// ---------- (2) runtime exponent: a ** lire() ----------
		| POW 'lire' '(' ')' {
        StringBuilder c = new StringBuilder();

        // base: ..., num, den
        c.append($a1.code);

        // save base
        c.append("STOREG " + G_POW_BASE_DEN + "\n");
        c.append("STOREG " + G_POW_BASE_NUM + "\n");

        // read exponent (INT)
        c.append("READ\n");
        c.append("STOREG " + G_POW_EXP + "\n");   // stack empty

        // reject negative exponent
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

        // res = res * base   (reuse genMulRat)
        c.append(
          genMulRat(
            "PUSHG " + G_POW_RES_NUM + "\n" +
            "PUSHG " + G_POW_RES_DEN + "\n",
            "PUSHG " + G_POW_BASE_NUM + "\n" +
            "PUSHG " + G_POW_BASE_DEN + "\n"
          )
        );

        // store back into res globals
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
        $exprType = $a1.exprType;   // IMPORTANT: same as base
      }
	)?;

intExpr
	returns[String code]:
	s = signedInt { $code = $s.code; }
	| 'num' '(' r = arithmExpr ')' {
      StringBuilder c = new StringBuilder();
      c.append($r.code);
      c.append("STOREG " + G_TMP0 + "\n"); // pop den, stack: ..., num
      $code = c.toString();
    }
	| 'denum' '(' r = arithmExpr ')' {
      StringBuilder c = new StringBuilder();
      c.append($r.code);
      c.append("STOREG " + G_TMP0 + "\n"); // pop den
      c.append("POP\n");                   // pop num
      c.append("PUSHG " + G_TMP0 + "\n");  // push den
      $code = c.toString();
    }
	| 'pgcd' '(' a = intExpr COMMA b = intExpr ')' { $code = genGcd($a.code, $b.code); }
	| 'ppcm' '(' a = intExpr COMMA b = intExpr ')' { $code = genLcm($a.code, $b.code); };

signedInt
	returns[String code]:
	ADDSUB? ENTIER {
      String s = ($ADDSUB != null) ? $ADDSUB.getText() : "";
      $code = "PUSHI " + s + $ENTIER.getText() + "\n";
    };

boolOr
	returns[String code]:
	a = boolAnd { $code = $a.code; } (
		'ou' b = boolAnd { $code = genOr($code, $b.code); }
	)*;

boolAnd
	returns[String code]:
	a = boolNot { $code = $a.code; } (
		'et' b = boolNot { $code = genAnd($code, $b.code); }
	)*;

boolNot
	returns[String code]:
	'non' b = boolNot { $code = genNot($b.code); }
	| boolAtom { $code = $boolAtom.code; };

boolAtom
	returns[String code]:
	'true' {
        $code = "PUSHI 1\n";
      }
	| 'false' {
        $code = "PUSHI 0\n";
      }
	| a1 = arithmExpr op = LOGICOP a2 = arithmExpr {
        $code = genCmp($a1.code, $op.getText(), $a2.code);
      }
	| '(' b = boolOr ')' { $code = $b.code; }
	| 'lireBool' '(' ')' {
        StringBuilder c = new StringBuilder();
        c.append("READ\n");
        c.append("PUSHI 0\n");
        c.append("NEQ\n");
        $code = c.toString();
      };

// ---------------- Lexer rules ----------------
NEWLINE: '\r'? '\n'; // match newlines
SEMICOLON: ';'; // match semicolons

POW: '**'; // $POW.getText()  | $POW.getType()
MUL: '*';
DIV: '/';
ADDSUB: ('+' | '-'); // $ADDSUB.getText()  | $ADDSUB.getType()

LOGICOP: ('==' | '<>' | '<' | '<=' | '>' | '>='); // $LOGICOP.getText()  | $LOGICOP.getType()

ENTIER: ('0' ..'9')+; // match integers , all sequences of digits

LBRACK: '[';
RBRACK: ']';
COMMA: ',';

ID: [a-zA-Z_][a-zA-Z_0-9]*;

LINE_COMMENT: '//' ~[\r\n]* -> skip;

WS: (' ' | '\t')+ -> skip; // ignore spaces and tabs
//UNMATCH : . -> skip;        // ignore any other character // -> il mange les paranthèses <EOF>