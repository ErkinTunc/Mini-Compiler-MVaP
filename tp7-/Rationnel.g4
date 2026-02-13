/*
 Compilation : antlr4 ./Rationnel.g4 -o ./build/ & javac ./build/*.java
 
 Exécution : grun Rationnel 'start' -gui
 */

/**
 — Utilisation de variables globales : déclaration, affectation et utilisation dans des expressions.
 — Blocs. — Structures conditionnelles. — Boucles.
 */

grammar Rationnel;

@header {
 import java.util.*;
}

@parser::members {

  enum VarType { INT, BOOL, RATIONNEL }

  static class VarEntry
  {
    boolean initialized;
    VarType type ; // type \in {INT, BOOL, RATIONNEL}
    int address;   // address in MVaP memory
    int size;      // 1 for INT/BOOL, 2 for RATIONNEL
  }

  static class FunctionInfo
  {
    String name;
    String returnType;
    List<String> paramTypes;

    String label ; // label d’entrée de la fonction
    int paramCount ;
    StringBuilder code = new StringBuilder(); // Store function code
    boolean used = false;
  }
  
  // Interface for code generation templates
  interface CodeGenerator {
      String generate(Map<String, String> params);
  }

  FunctionInfo currentFunction = null;

  // holds variable names and types and addresses in MVaP
  HashMap < String, VarEntry > varTab = new HashMap<>(); 

  // holds function names and types and addresses in MVaP
  HashMap < String, FunctionInfo > functions = new HashMap<>();
  
  // Template map for modular control structures
  HashMap < String, CodeGenerator > templates = new HashMap<>();

  // holds the MVaP code
  StringBuilder variables = new StringBuilder();
  StringBuilder instructions = new StringBuilder();
  StringBuilder fonctions = new StringBuilder();

  int labelCounter = 0 ;
  int lastAddress = 0;

  String newLabel(String base)
  {
    return base + "_" + (labelCounter++);
  }

  /**
   * Emit MVaP instructions
   */
  void emit(String instr)
  {
    if (currentFunction != null) {
        currentFunction.code.append(instr).append("\n");
    } else {
        instructions.append(instr).append("\n");
    }
  }

  void emitVariable(String instr)
  {
    variables.append(instr).append("\n");
  }

  void emitFunction(String functionName, FunctionInfo function)
  {
    fonctions.append(functionName + ":\n");
    fonctions.append(function.code.toString());
  }
  
  /*
   * Helper to call templates
   */
  void runTemplate(String name, Map<String, String> params) {
      if (templates.containsKey(name)) {
          emit(templates.get(name).generate(params));
      } else {
          System.err.println("Undefined template: " + name);
      }
  }

  /*
   * Initialize standard MVaP control structure templates
   * Call this from @init of parser or start rule if needed, or static block.
   * Since this is inner class/members, we can use an intializer block or method.
   */
  void initTemplates() {
      // IF-ELSE Structures
      templates.put("IF_START", p -> "JUMPF " + p.get("elseLabel"));
      templates.put("IF_ELSE_START", p -> "JUMP " + p.get("endLabel") + "\n" + p.get("elseLabel") + ":");
      templates.put("IF_END", p -> p.get("endLabel") + ":");
      
      // REPEAT-UNTIL Structures
      templates.put("REPEAT_START", p -> p.get("startLabel") + ":");
      templates.put("REPEAT_CHECK", p -> "JUMPF " + p.get("startLabel"));
      
      // FOR LOOP Structures
      // Init: PUSHI start -> STORE var

      templates.put("FOR_INIT", p -> "PUSHI " + p.get("startVal") + "\nSTORE " + p.get("addr"));

      // Cond: LOAD var -> PUSHI end -> LE -> JUMPF endLabel
      templates.put("FOR_COND", p -> 
          p.get("loopCondLabel") + ":\n" +
          "LOAD " + p.get("addr") + "\n" +
          "PUSHI " + p.get("endVal") + "\n" +
          "LE\n" +
          "JUMPF " + p.get("endLabel")
      );

      // Incr: LOAD var -> PUSHI 1 -> ADD -> STORE var -> JUMP start
      templates.put("FOR_INCR", p -> 
          p.get("loopIncrLabel") + ":\n" +
          "LOAD " + p.get("addr") + "\n" +
          "PUSHI 1\n" +
          "ADD\n" +
          "STORE " + p.get("addr") + "\n" +
          "JUMP " + p.get("loopCondLabel")
      );
      templates.put("FOR_END", p -> p.get("endLabel") + ":");
  }
}

// ---------------- Parser rules ----------------

start
	@init { initTemplates(); }: (function | instruction (SEMICOLON | NEWLINE)*)* EOF {
    // Post-processing: Assemble code
    System.out.println(variables.toString());
    
    // Main instruction label if needed, but mainly:
    System.out.println(instructions.toString()); // TODO change this so the output is a .mvap file
    
    // End of program (HALT)
    System.out.println("HALT");

    // Sort functions by name or keep definition order? Hashmap is unordered.
    // If order matters (it shouldn't for MVaP usually), we iterate values.
    for (FunctionInfo f : functions.values()) {
        if (f.used) {
            System.out.println(f.code.toString());
        }
    }
};

function:
	t = type name = ID '(' params? ')' {
     // Create FunctionInfo
     currentFunction = new FunctionInfo();
     currentFunction.name = $name.getText();
     currentFunction.label = newLabel("func_" + $name.getText());
     functions.put($name.getText(), currentFunction);
     
     // Emit label to function code
     emit(currentFunction.label + ":");
     
     // Handle params (allocate addresses)
     // For simplicity, we just declare them as variables in scope?
     // MVaP params are typically PUSHI'd before call.
     // Inside function, they are accessible via relative stack or locals.
     // If we treat them as global vars (simple trick), it works but not recursive.
     // Given "Rationnel" type, let's assume global var simulation or local vars if logic existed.
     // I will implement params logic in 'params' rule or here if simplest.
  } '{' code '}' {
     // Reset context
     emit("RET"); // Return from function
     currentFunction = null; 
  };

params
	returns[List<String> names]:
	t1 = type id1 = ID {
        // Add param to vars
        VarEntry v = new VarEntry();
        v.type = VarType.INT; 
        v.size = 1;
        v.address = lastAddress;
        lastAddress += v.size;
        varTab.put($id1.getText(), v);
    } (
		',' tn = type idn = ID {
         VarEntry vn = new VarEntry();
         if ($tn.value.equals("rationnel")) {
             vn.type = VarType.RATIONNEL;
             vn.size = 2;
         } else {
             vn.type = ($tn.value.equals("bool")) ? VarType.BOOL : VarType.INT;
             vn.size = 1;
         }
         vn.address = lastAddress;
         lastAddress += vn.size;
         varTab.put($idn.getText(), vn);
    }
	)*;

code: (instruction | SEMICOLON | NEWLINE)*;

appel
	returns[String nameText]:
	name = ID '(' (args = expr (',' args2 = expr)*)? ')' {
    $nameText = $name.getText();
    FunctionInfo f = functions.get($name.getText());
    if (f != null) {
        f.used = true;
        // PUSHI params logic would be here if we were handling args properly on stack
        // For now, assume args put themselves on stack via expr evaluation
        emit("CALL " + f.label);
    } else {
         System.err.println("Undefined function: " + $name.getText());
    }
  };

bloc: '{' code '}';

instruction:
	declAssignInstr
	| declInstr
	| assignInstr
	| e = expr
	| 'Afficher' e = expr
	| struc_conditionnel
	| boucle
	| bloc;

declAssignInstr
	returns[List<String> listIds]:
	t = type ids = idList[$t.value] '=' e = expr {
    for (String id : $ids.value) {
        VarEntry v = varTab.get(id);
        if (v != null) {
            emit("STORE " + v.address);
        } else {
            System.err.println("Undefined variable: " + id);
        }
    }
};

declInstr: t = type ids = idList[$t.value];

type
	returns[String value]: TYPE { $value = $TYPE.getText(); };
idList[String typeName]
	returns[List<String> value]: // ids = id start
	id1 = ID {
    $value = new ArrayList<>();
    $value.add($id1.getText());
    
    // Allocate address
    VarEntry v = new VarEntry();
    if ($typeName.equals("rationnel")) {
        v.type = VarType.RATIONNEL;
        v.size = 2;
    } else {
        v.type = ($typeName.equals("bool")) ? VarType.BOOL : VarType.INT;
        v.size = 1;
    }
    v.address = lastAddress;
    lastAddress += v.size;
    varTab.put($id1.getText(), v);
      
  } (
		',' idn = ID { 
      $value.add($idn.getText());
      
       // Allocate address
      VarEntry vn = new VarEntry();
      if ($typeName.equals("rationnel")) {
          vn.type = VarType.RATIONNEL;
          vn.size = 2;
      } else {
          vn.type = ($typeName.equals("bool")) ? VarType.BOOL : VarType.INT;
          vn.size = 1;
      }
      vn.address = lastAddress;
      lastAddress += vn.size;
      varTab.put($idn.getText(), vn);
      
   }
	)*;

assignInstr:
	id = ID '=' e = expr {
    VarEntry v = varTab.get($id.getText());
    if (v != null) {
       if (v.type != $e.t) {
           System.err.println("Type mismatch in assignment. Expected " + v.type + " but got " + $e.t);
       }
       
       emit("STORE " + v.address);
       if (v.type == VarType.RATIONNEL) {
           emit("STORE " + (v.address + 1));
           emit("STORE " + v.address);
       }
    } else {
       System.err.println("Undefined variable: " + $id.getText());
    }
};

// Nous allons reprendre les régles des TPs précédents pour les expressions arithmétiques et
// booléennes Cela nous permettra de passer au tests
expr
	returns[VarType t]:
	e1 = arithmexpr { $t = $e1.t; }
	| e2 = boolexpr { $t = $e2.t; };

arithmexpr
	returns[VarType t]:
	n = ENTIER { 
        emit("PUSHI " + $n.getText()); 
        $t = VarType.INT;
    }
	| id = ID {
         VarEntry v = varTab.get($id.getText());
         if (v != null) {
             emit("LOAD " + v.address);
             if (v.type == VarType.RATIONNEL) {
                 emit("LOAD " + (v.address+1));
             }
             $t = v.type;
         } else {
             System.err.println("Undefined variable: " + $id.getText());
             $t = VarType.INT; // fallback
         }
    }
	| e1 = arithmexpr RAT e2 = arithmexpr {
        // Constructor for rational: e1 / e2
        // e1 (Num), e2 (Den)
        if ($e1.t != VarType.INT || $e2.t != VarType.INT) {
             System.err.println("Rational constructor expects Integers.");
        }
        $t = VarType.RATIONNEL;
    }
	| left = arithmexpr op = MULDIV right = arithmexpr {
        if ($left.t != $right.t) {
             System.err.println("Type mismatch in MULDIV operation.");
             $t = VarType.INT;
        } else {
             $t = $left.t;
             if ($t == VarType.INT) {
                 if ($op.getText().equals("*")) emit("MUL");
                 else emit("DIV");
             } else {
                 // RATIONNEL Operation
                 // Stack: [NumA, DenA, NumB, DenB] (Top)
                 // We need scratch vars. 
                 // Strategy: STORE denB, STORE numB, STORE denA, STORE numA
                 // Then perform logic.
                 // This requires reserving temp addresses. 
                 // Since we don't have dynamic temp allocator, strict stack logic is hard without SWAP.
                 // Let's rely on Java-side logic to generate the complex sequence.
                 
                 // Op: * (Multiply)
                 // A * B = (nA * nB) / (dA * dB)
                 // Stack: nA dA nB dB
                 if ($op.getText().equals("*")) {
                     // We need nA * nB -> NewNum
                     // dA * dB -> NewDen
                     // We can't reach nA easily.
                     // Assuming no SWAP/ROT, we MUST use temp vars.
                     // Let's use high addresses for temp? Or simple stack tricks?
                     // MVaP usually has STORE(addr) / LOAD(addr).
                     
                     // Let's assume we can use a "temp" base address.
                     // But we are in a recursive descent, we might overwrite temps?
                     // No, expressions evaluate fully.
                     int t1 = lastAddress;
                     int t2 = lastAddress + 1;
                     int t3 = lastAddress + 2;
                     int t4 = lastAddress + 3;
                     
                     emit("STORE " + t4); // dB
                     emit("STORE " + t3); // nB
                     emit("STORE " + t2); // dA
                     emit("STORE " + t1); // nA
                     
                     // Compute Num
                     emit("LOAD " + t1);
                     emit("LOAD " + t3);
                     emit("MUL");
                     
                     // Compute Den
                     emit("LOAD " + t2);
                     emit("LOAD " + t4);
                     emit("MUL");
                 }
                 // Op: : (Division) -> A : B = A * (1/B) = A * (dB/nB)
                 // = (nA * dB) / (dA * nB)
                 else {
                     int t1 = lastAddress; int t2 = lastAddress+1;
                     int t3 = lastAddress+2; int t4 = lastAddress+3;
                     emit("STORE " + t4); // dB
                     emit("STORE " + t3); // nB
                     emit("STORE " + t2); // dA
                     emit("STORE " + t1); // nA
                     
                     emit("LOAD " + t1);
                     emit("LOAD " + t4);
                     emit("MUL");
                     
                     emit("LOAD " + t2);
                     emit("LOAD " + t3);
                     emit("MUL");
                 }
             }
        }
    }
	| left = arithmexpr op = ADDSUB right = arithmexpr {
        if ($left.t != $right.t) {
             System.err.println("Type mismatch in ADDSUB operation.");
             $t = VarType.INT;
        } else {
             $t = $left.t;
             if ($t == VarType.INT) {
                 if ($op.getText().equals("+")) emit("ADD");
                 else emit("SUB");
             } else {
                 // RATIONNEL
                 // A + B = (nA*dB + nB*dA) / (dA*dB)
                 // A - B = (nA*dB - nB*dA) / (dA*dB)
                 int t1 = lastAddress; int t2 = lastAddress+1;
                 int t3 = lastAddress+2; int t4 = lastAddress+3;
                 emit("STORE " + t4); // dB
                 emit("STORE " + t3); // nB
                 emit("STORE " + t2); // dA
                 emit("STORE " + t1); // nA
                 
                 // Num
                 emit("LOAD " + t1);
                 emit("LOAD " + t4);
                 emit("MUL");
                 
                 emit("LOAD " + t3);
                 emit("LOAD " + t2);
                 emit("MUL");
                 
                 if ($op.getText().equals("+")) emit("ADD");
                 else emit("SUB");
                 
                 // Den
                 emit("LOAD " + t2);
                 emit("LOAD " + t4);
                 emit("MUL");
             }
        }
    }
	| appel { 
          FunctionInfo f = functions.get($appel.nameText);
          if (f != null) {
              if (f.returnType.equals("rationnel")) $t = VarType.RATIONNEL;
              else if (f.returnType.equals("bool")) $t = VarType.BOOL;
              else $t = VarType.INT;
          } else {
             $t = VarType.INT; 
          }
      }
	| '(' e = expr ')' { $t = $e.t; };

boolexpr
	returns[VarType t]:
	b1 = 'true' {
    emit("PUSHI 1");
    $t = VarType.BOOL;
  }
	| b2 = 'false' {
    emit("PUSHI 0");
    $t = VarType.BOOL;
  }
	| e1 = arithmexpr op = LOGICOP e2 = arithmexpr {
      if ($e1.t != $e2.t) {
           System.err.println("Type mismatch in boolean comparison.");
      }
      $t = VarType.BOOL;
      
      if ($e1.t == VarType.INT) {
          if ($op.getText().equals("==")) emit("EQUAL");
          else if ($op.getText().equals("<")) emit("INF");
          else if ($op.getText().equals("<=")) emit("INFE");
          else if ($op.getText().equals(">")) emit("SUP");
          else if ($op.getText().equals(">=")) emit("SUPE");
          else if ($op.getText().equals("<>")) emit("NEQ");
      } else {
          // RATIONNEL Comparison
          // A op B <=> nA/dA op nB/dB <=> nA*dB op nB*dA
          int t1 = lastAddress; int t2 = lastAddress+1;
          int t3 = lastAddress+2; int t4 = lastAddress+3;
          emit("STORE " + t4); // dB
          emit("STORE " + t3); // nB
          emit("STORE " + t2); // dA
          emit("STORE " + t1); // nA
          
          // LHS = nA * dB
          emit("LOAD " + t1);
          emit("LOAD " + t4);
          emit("MUL");
          
          // RHS = nB * dA
          emit("LOAD " + t3);
          emit("LOAD " + t2);
          emit("MUL");
          
          if ($op.getText().equals("==")) emit("EQUAL");
          else if ($op.getText().equals("<")) emit("INF");
          else if ($op.getText().equals("<=")) emit("INFE");
          else if ($op.getText().equals(">")) emit("SUP");
          else if ($op.getText().equals(">=")) emit("SUPE");
          else if ($op.getText().equals("<>")) emit("NEQ");
      }
  };

/*
 On accepte les instructions conditionnelles, possiblement imbriquées, de la forme suivante : exprC?
 A : S où exprC est une expression booléenne et la sémantique est celle classique : Si exprC est
 vraie, alors on n’exécute que A, sinon on n’exécute que S. Le : S est facultatif. De
 plus,l’ensemble des instructions A (ou S) peut être soit une seule instruction, soit un bloc
 d’instructions. Par exemple le code source suivant devrait être accepté :
 
 true? x=35; false? x=y; : z=x; x==y? { x=y; z=x*y;} 10==13? {x=y; z=x*y} : z=x; 10==13? {x=y;
 z=x*y} : {z=x;x=t;}
 */

struc_conditionnel
	@init {
    String elseLabel = newLabel("else");
    String endLabel = newLabel("end_if");
    Map<String, String> params = new HashMap<>();
    params.put("elseLabel", elseLabel);
    params.put("endLabel", endLabel);
}:
	cond = boolexpr '?' {
        runTemplate("IF_START", params);
    } thenInstr = code {
        runTemplate("IF_ELSE_START", params);
    } (':' elseInstr = code)? {
        runTemplate("IF_END", params);
    }
	| cond = boolexpr '?' {
        runTemplate("IF_START", params);
    } thenInstr = code {
        runTemplate("IF_ELSE_START", params);
        // Note: Original code emitted IF_ELSE_START logic (jump end, label else) then immediately label end?
        // Let's check original:
        // emit("JUMP " + endLabel);
        // emit(elseLabel + ":");
        // emit(endLabel + ":");
        // My template "IF_ELSE_START" does: Jump end, Label else.
        // My template "IF_END" does: Label end.
        // So calling IF_ELSE_START then IF_END is correct for this branch too.
        runTemplate("IF_END", params);
    };

/*
 Boucles. On accepte deux types de structures itératives :
 
 — Les instructions itératives, possiblement imbriquées de la forme, Pour index=debut .. fin Faire
 instr où index est un identifiant d’une variable globale déjà déclarée, debut et fin sont des
 expressions de type entier, et instr est soit une instruction, soit un bloc d’instructions. Le sens
 d’une telle instruction est la répétition fin-debut fois de instr. La valeur de index à la fin de
 la boucle est égale à fin.
 
 — Les instructions itératives, possiblement imbriquées de la forme suivante : repeter instr jusque
 exprC où instr est soit une seule instruction soit un bloc d’instructions et exprC est une
 expression booléenne. Le sens d’une telle instruction itérative est l’exécution de instr au moins
 une fois et tant que la condition exprC n’est pas satisfaite.
 */

/*
 * Refactored Loop with locals
 */
boucle
	locals[Map<String, String> templateParams]
	@init { $templateParams = new HashMap<>(); }:
	'repeter' {
        String startLabel = newLabel("repeat_start");
        $templateParams.put("startLabel", startLabel);
        runTemplate("REPEAT_START", $templateParams);
    } code 'jusque' boolexpr {
        // Run check
        runTemplate("REPEAT_CHECK", $templateParams);
    }
	| 'Pour' id = ID '=' startVal = ENTIER '..' fin = ENTIER 'Faire' {
      String startLabel = newLabel("for_start");
      String endLabel = newLabel("for_end");
      String loopCondLabel = newLabel("for_cond");
      String loopIncrLabel = newLabel("for_incr");
      
      VarEntry v = varTab.get($id.getText());
      if (v == null) {
          System.err.println("Undefined variable in loop: " + $id.getText());
      } else {
          $templateParams.put("startVal", $startVal.getText());
          $templateParams.put("addr", "" + v.address);
          $templateParams.put("endVal", $fin.getText());
          $templateParams.put("endLabel", endLabel);
          $templateParams.put("loopCondLabel", loopCondLabel);
          $templateParams.put("loopIncrLabel", loopIncrLabel);
          
          runTemplate("FOR_INIT", $templateParams);
          runTemplate("FOR_COND", $templateParams);
      }
  } body = code {
       VarEntry vIncr = varTab.get($id.getText());
       if (vIncr != null) {
           runTemplate("FOR_INCR", $templateParams);
       }
       runTemplate("FOR_END", $templateParams);
  };

// ---------------- Lexer rules ----------------
NEWLINE: '\r'? '\n';
// match newlines
SEMICOLON: ';';
// match semicolons

MULDIV: ('*' | ':');
RAT: '/';
// $MULDIV.getText()  | $MULDIV.getType() 
ADDSUB: ('+' | '-');
// $ADDSUB.getText()  | $ADDSUB.getType()
INCDEC: '++' | '--';
// $INCDEC.getText()  | $INCDEC.getType()
LOGICOP: ('==' | '<>' | '<' | '<=' | '>' | '>=');
// $LOGICOP.getText()  | $LOGICOP.getType()

ENTIER: ('0' ..'9')+;
// match integers , all sequences of digits

TYPE: 'int' | 'bool' | 'rationnel';
// match types
ID: [a-zA-Z_] [a-zA-Z0-9_]*;
// match identifiers

WS: (' ' | '\t')+ -> skip;
// ignore spaces and tabs

//UNMATCH : . -> skip;        // ignore any other character 

