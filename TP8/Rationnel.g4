grammar Rationnel;

@header {
 import java.util.*;
}

@parser::members {

  enum VarType { INT, BOOL, RATIONNEL }

  static class VarEntry
  {
    boolean initialized;
    VarType type ; 
    int address;   
    int size;      
  }

  static class FunctionInfo
  {
    String name;
    String returnType;
    List<String> paramTypes;

    String label ; 
    int paramCount ;
    StringBuilder code = new StringBuilder(); 
    boolean used = false;
  }
  
  interface CodeGenerator {
      String generate(Map<String, String> params);
  }

  FunctionInfo currentFunction = null;

  HashMap < String, VarEntry > varTab = new HashMap<>(); 

  HashMap < String, FunctionInfo > functions = new HashMap<>();
  
  HashMap < String, CodeGenerator > templates = new HashMap<>();

  StringBuilder variables = new StringBuilder();
  StringBuilder instructions = new StringBuilder();
  StringBuilder fonctions = new StringBuilder();

  int labelCounter = 0 ;
  int lastAddress = 0;

  String newLabel(String base)
  {
    return base + "_" + (labelCounter++);
  }

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
  
  void runTemplate(String name, Map<String, String> params) {
      if (templates.containsKey(name)) {
          emit(templates.get(name).generate(params));
      } else {
          System.err.println("Undefined template: " + name);
      }
  }

  void initTemplates() {
      templates.put("IF_START", p -> "JUMPF " + p.get("elseLabel"));
      templates.put("IF_ELSE_START", p -> "JUMP " + p.get("endLabel") + "\n" + p.get("elseLabel") + ":");
      templates.put("IF_END", p -> p.get("endLabel") + ":");
      
      templates.put("REPEAT_START", p -> p.get("startLabel") + ":");
      templates.put("REPEAT_CHECK", p -> "JUMPF " + p.get("startLabel"));
      
      templates.put("FOR_INIT", p -> "PUSHI " + p.get("startVal") + "\nSTORE " + p.get("addr"));

      templates.put("FOR_COND", p -> 
          p.get("loopCondLabel") + ":\n" +
          "LOAD " + p.get("addr") + "\n" +
          "PUSHI " + p.get("endVal") + "\n" +
          "LE\n" +
          "JUMPF " + p.get("endLabel")
      );

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

// ----------------- Parser rules ----------------

start
	@init { initTemplates(); }: (function | NEWLINE)+ EOF {
    System.out.println(variables.toString());
    
    FunctionInfo mainFunc = null;
    for(FunctionInfo f : functions.values()) {
        if (f.name.equals("main")) {
            mainFunc = f;
            break;
        }
    }
    
    if (mainFunc != null) {
        System.out.println("CALL " + mainFunc.label);
        System.out.println("HALT");
    } else {
        System.err.println("Error: No 'main' function found.");
        System.out.println("HALT");
    }

    for (FunctionInfo f : functions.values()) {
        System.out.println(f.label + ":");
        System.out.println(f.code.toString());
    }
};

function:
	t = type ID '(' paramlist? ')' {
        currentFunction = new FunctionInfo();
        currentFunction.name = $ID.getText();
        currentFunction.returnType = $t.value;
        currentFunction.label = newLabel("func_" + $ID.getText());
        functions.put($ID.getText(), currentFunction);
    } '{' functionCode '}' {
        emit("RET");
        currentFunction = null;
    };

paramlist: param (',' param)*;

param:
	t = type ID {
    VarEntry v = new VarEntry();
    if ($t.value.equals("rationnel")) {
        v.type = VarType.RATIONNEL;
        v.size = 2;
    } else {
        v.type = ($t.value.equals("bool")) ? VarType.BOOL : VarType.INT;
        v.size = 1;
    }
    v.address = lastAddress;
    lastAddress += v.size;
    varTab.put($ID.getText(), v);
};

functionCode: code;

retour:
	RET e = expr {
    emit("RETN"); 
    emit("RET");
};

code: (instruction | SEMICOLON | NEWLINE)*;

instruction:
	declAssignInstr
	| declInstr
	| assignInstr
	| cond_or_bool
	| boucle
	| bloc
	| e1 = arithmexpr
	| retour
	| 'Afficher' e = expr;

cond_or_bool
	@init {
    String elseLabel = newLabel("else");
    String endLabel = newLabel("end_if");
    Map<String, String> params = new HashMap<>();
    params.put("elseLabel", elseLabel);
    params.put("endLabel", endLabel);
    }:
	cond = boolexpr (
		'?' { runTemplate("IF_START", params); } thenInstr = code { runTemplate("IF_ELSE_START", params); 
			} (':' elseInstr = code)? { runTemplate("IF_END", params); }
		|
	);

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

bloc: '{' code '}';

type
	returns[String value]:
	'int' { $value = "int"; }
	| BOOL { $value = "bool"; }
	| TYPE_RAT_KEYWORD { $value = "rationnel"; };

idList[String typeName]
	returns[List<String> value]:
	id1 = ID {
    $value = new ArrayList<>();
    $value.add($id1.getText());
    
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

expr
	returns[VarType t]:
	e1 = arithmexpr { $t = $e1.t; }
	| e2 = boolexpr { $t = $e2.t; };

arithmexpr
	returns[VarType t]:
	n = INT { 
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
             $t = VarType.INT; 
         }
    }
	| r = RATIONNEL {
         String[] parts = $r.getText().split("/");
         emit("PUSHI " + parts[0]);
         emit("PUSHI " + parts[1]);
         $t = VarType.RATIONNEL;
    }
	| e1 = arithmexpr RAT e2 = arithmexpr {
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
                 if ($op.getText().equals("*")) {
                     int t1 = lastAddress; int t2 = lastAddress+1; int t3 = lastAddress+2; int t4 = lastAddress+3;
                     emit("STORE " + t4); emit("STORE " + t3); emit("STORE " + t2); emit("STORE " + t1);
                     emit("LOAD " + t1); emit("LOAD " + t3); emit("MUL");
                     emit("LOAD " + t2); emit("LOAD " + t4); emit("MUL");
                 } else { // DIV
                     int t1 = lastAddress; int t2 = lastAddress+1; int t3 = lastAddress+2; int t4 = lastAddress+3;
                     emit("STORE " + t4); emit("STORE " + t3); emit("STORE " + t2); emit("STORE " + t1);
                     emit("LOAD " + t1); emit("LOAD " + t4); emit("MUL");
                     emit("LOAD " + t2); emit("LOAD " + t3); emit("MUL");
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
                 int t1 = lastAddress; int t2 = lastAddress+1; int t3 = lastAddress+2; int t4 = lastAddress+3;
                 emit("STORE " + t4); emit("STORE " + t3); emit("STORE " + t2); emit("STORE " + t1);
                 emit("LOAD " + t1); emit("LOAD " + t4); emit("MUL");
                 emit("LOAD " + t3); emit("LOAD " + t2); emit("MUL");
                 if ($op.getText().equals("+")) emit("ADD"); else emit("SUB");
                 emit("LOAD " + t2); emit("LOAD " + t4); emit("MUL");
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

appel
	returns[String nameText]:
	name = ID '(' (args = expr (',' args2 = expr)*)? ')' {
    $nameText = $name.getText();
    FunctionInfo f = functions.get($name.getText());
    if (f != null) {
        f.used = true;
        emit("CALL " + f.label);
    } else {
         System.err.println("Undefined function: " + $name.getText());
    }
  };

boolexpr
	returns[VarType t]:
	b1 = TRUE { emit("PUSHI 1"); $t = VarType.BOOL; }
	| b2 = FALSE { emit("PUSHI 0"); $t = VarType.BOOL; }
	| e1 = arithmexpr op = LOGICOP e2 = arithmexpr {
      if ($e1.t != $e2.t) System.err.println("Type mismatch in boolean comparison.");
      $t = VarType.BOOL;
      if ($e1.t == VarType.INT) {
          if ($op.getText().equals("==")) emit("EQUAL");
          else if ($op.getText().equals("<")) emit("INF");
          else if ($op.getText().equals("<=")) emit("INFE");
          else if ($op.getText().equals(">")) emit("SUP");
          else if ($op.getText().equals(">=")) emit("SUPE");
          else if ($op.getText().equals("<>")) emit("NEQ");
      } else {
          int t1 = lastAddress; int t2 = lastAddress+1; int t3 = lastAddress+2; int t4 = lastAddress+3;
          emit("STORE " + t4); emit("STORE " + t3); emit("STORE " + t2); emit("STORE " + t1);
          emit("LOAD " + t1); emit("LOAD " + t4); emit("MUL");
          emit("LOAD " + t3); emit("LOAD " + t2); emit("MUL");
          if ($op.getText().equals("==")) emit("EQUAL");
          else if ($op.getText().equals("<")) emit("INF");
          else if ($op.getText().equals("<=")) emit("INFE");
          else if ($op.getText().equals(">")) emit("SUP");
          else if ($op.getText().equals(">=")) emit("SUPE");
          else if ($op.getText().equals("<>")) emit("NEQ");
      }
  };

boucle
	locals[Map<String, String> templateParams]
	@init { $templateParams = new HashMap<>(); }:
	'repeter' {
        String startLabel = newLabel("repeat_start");
        $templateParams.put("startLabel", startLabel);
        runTemplate("REPEAT_START", $templateParams);
    } code 'jusque' boolexpr {
        runTemplate("REPEAT_CHECK", $templateParams);
    }
	| 'Pour' id = ID '=' startVal = INT '..' fin = INT 'Faire' {
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

// ----------------- Lexer rules ----------------

RET: 'retourner';

// Tokens
INT: [0-9]+;
BOOL: 'boolean';
// RATIONNEL logic: We need a keyword for the type 'rationnel' vs the literal value 1/2.
TYPE_RAT_KEYWORD: 'rationnel';
RATIONNEL: [0-9]+ '/' [0-9]+;

TRUE: 'true';
FALSE: 'false';

MULDIV: ('*' | ':');
RAT: '/';
ADDSUB: ('+' | '-');
LOGICOP: ('==' | '<>' | '<' | '<=' | '>' | '>=');

OB: '{';
CB: '}';

SEMICOLON: ';';
NEWLINE: '\r'? '\n';

ID: [a-zA-Z_][a-zA-Z0-9_]*;
WS: [ \t\r\n]+ -> skip;
