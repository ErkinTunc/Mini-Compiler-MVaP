



instruction
    : afficher_instr
    | simplifier_instr
    ; 

afficher_instr 
  :
  'Afficher' '(' e=expr ')' 
  {
    // Generate code for expression
    emit($e.code);
    if ($e.exprType.equals("bool") || $e.exprType.equals("int"))
    {
      // stack: ..., v
      emit("WRITE");   // print v
      emit("POP");     // remove v
    } 
    else if ($e.exprType.equals("rat"))
    {
      // stack: ..., num, den (den on top)
      // Use G_TMP0 to save den while printing num
      emit("STOREG " + G_TMP0); // g0 = den, stack: ..., num
      emit("WRITE");            // print num
      emit("POP");              // pop num
      emit("PUSHG " + G_TMP0);  // restore den
      emit("WRITE");            // print den
      emit("POP");              // pop den
    } 
    else 
    {
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