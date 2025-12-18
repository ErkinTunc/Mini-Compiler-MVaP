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

  void emitFunction(String fonctionName)
  {
    
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
    
    // Sort functions by name or keep definition order? Hashmap is unordered.
    // If order matters (it shouldn't for MVaP usually), we iterate values.
    for (FunctionInfo f : functions.values()) {
        if (f.used) {
            System.out.println(f.code.toString());
        }
    }
    
    // Main instruction label if needed, but mainly:
    System.out.println(instructions.toString());
    // End of program (HALT)
    System.out.println("HALT");
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
        v.type = VarType.INT; // Should map t1.value
        v.address = lastAddress++;
        varTab.put($id1.getText(), v);
    } (
		',' tn = type idn = ID {
         VarEntry vn = new VarEntry();
         vn.type = VarType.INT; 
         vn.address = lastAddress++;
         varTab.put($idn.getText(), vn);
    }
	)*;

code: (instruction | SEMICOLON | NEWLINE)*;

appel:
	name = ID '(' (args = expr (',' args2 = expr)*)? ')' {
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
	declInstr
	| assignInstr
	| e = expr
	| 'Afficher' e = expr
	| struc_conditionnel
	| boucle
	| bloc;

declInstr: t = type ids = idList;

type
	returns[String value]: TYPE { $value = $TYPE.getText(); };
idList
	returns[List<String> value]: // ids = id start
	id1 = ID {
    $value = new ArrayList<>();
    $value.add($id1.getText());
    
    // Allocate address
    VarEntry v = new VarEntry();
    v.type = VarType.INT; // Defaulting to INT for now based on grammar flow, ideally passed from 'type' rule
    v.address = lastAddress++;
    varTab.put($id1.getText(), v);
      
  } (
		',' idn = ID { 
      $value.add($idn.getText());
      
       // Allocate address
      VarEntry vn = new VarEntry();
      vn.type = VarType.INT;
      vn.address = lastAddress++;
      varTab.put($idn.getText(), vn);
      
   }
	)*;

assignInstr:
	id = ID '=' e = expr {
    VarEntry v = varTab.get($id.getText());
    if (v != null) {
       emit("STORE " + v.address); 
    } else {
       System.err.println("Undefined variable: " + $id.getText());
    }
};

// Nous allons reprendre les régles des TPs précédents pour les expressions arithmétiques et
// booléennes Cela nous permettra de passer au tests
expr: e1 = arithmexpr | e2 = boolexpr;

arithmexpr:
	e1 = ENTIER { emit("PUSHI " + $e1.getText()); }
	| id = ID {
         VarEntry v = varTab.get($id.getText());
         if (v != null) {
             emit("LOAD " + v.address);
         } else {
             System.err.println("Undefined variable: " + $id.getText());
         }
    }
	| left=arithmexpr op=MULDIV right=arithmexpr {
        if ($op.getText().equals("*")) emit("MUL");
        else emit("DIV");
    }
	| left=arithmexpr op=ADDSUB right=arithmexpr {
        if ($op.getText().equals("+")) emit("ADD");
        else emit("SUB");
    }
	| appel // Function call
	| '(' expr ')'; // Grouping

boolexpr:
	b1 = 'true' {
    emit("PUSHI 1");
  }
	| b2 = 'false' {
    emit("PUSHI 0");
  }
  | e1=arithmexpr op=LOGICOP e2=arithmexpr {
      if ($op.getText().equals("==")) emit("EQUAL");
      else if ($op.getText().equals("<")) emit("INF");
      else if ($op.getText().equals("<=")) emit("INFE");
      else if ($op.getText().equals(">")) emit("SUP");
      else if ($op.getText().equals(">=")) emit("SUPE");
      else if ($op.getText().equals("<>")) emit("NEQ");
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
	@init 
{
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

MULDIV: ('*' | '/');
// $MULDIV.getText()  | $MULDIV.getType() 
ADDSUB: ('+' | '-');
// $ADDSUB.getText()  | $ADDSUB.getType()
INCDEC: '++' | '--';
// $INCDEC.getText()  | $INCDEC.getType()
LOGICOP: ('==' | '<>' | '<' | '<=' | '>' | '>=');
// $LOGICOP.getText()  | $LOGICOP.getType()

ENTIER: ('0' ..'9')+;
// match integers , all sequences of digits

TYPE: 'int' | 'bool';
// match types
ID: [a-zA-Z_] [a-zA-Z0-9_]*;
// match identifiers

WS: (' ' | '\t')+ -> skip;
// ignore spaces and tabs

//UNMATCH : . -> skip;        // ignore any other character 

