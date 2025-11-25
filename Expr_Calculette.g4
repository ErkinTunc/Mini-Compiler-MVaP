// Compilation : antlr4 Expr_Calculette.g4 -o ./Expr_Calculette/bin
// Execution   : javac -d ./Expr_Calculette/bin/ ./Expr_Calculette/bin/*.java

// grun Expr_Calculette start -tree
//  input : 

// grun Expr_Calculette start -tokens
//  input : 
   
// grun Expr_Calculette start -gui
//  input : 

grammar Expr_Calculette;

@header {
 import java.util.*;
 }

@members {
    static class VarEntry {
        String type;        // "int" veya "bool"
        boolean initialized;
        Integer ivalue;     // sadece int için kullan
        Boolean bvalue;     // sadece bool için kullan
    }

    Map<String, VarEntry> symtab = new HashMap<>(); // symtab : symbole table
}

// ---------------- Parser rules ----------------
start
    : instruction
      ( (SEMICOLON | NEWLINE)+ instruction )*
      (SEMICOLON | NEWLINE)*
      EOF
    ;

instruction
    : declInstr                            // declInstruction
    | assignInstr                          // assignInstruction
    |  e=expr
      {
        if ($e.isBool)
            System.out.println("Afficher : " + $e.bvalue);
        else
            System.out.println("Afficher : " + $e.ivalue);
      }
    // “sadece Afficher : yazdırır” olduğu için bunu eklememek daha temiz.
    ;

declInstr
    : t=type ids=idList
      {
        for (String name : $ids.value) {
            if (symtab.containsKey(name)) {
                throw new RuntimeException("Variable already declared: " + name);
            }

            VarEntry e = new VarEntry();
            e.type = $t.value;
            e.initialized = false;
            e.ivalue = null;
            e.bvalue = null;
            
            symtab.put(name, e);
        }
      }
    ;

type returns [String value]
    : TYPE { $value = $TYPE.getText(); }
    ;

idList returns [List<String> value] // ids = id start
    : id1=ID
      {
          $value = new ArrayList<>();
          $value.add($id1.getText());
      }
      ( ',' idn=ID { $value.add($idn.getText()); } )*
    ;

assignInstr
    : id=ID '=' e=expr
      {
        String name = $id.getText();
        VarEntry v = symtab.get(name);  // v = variable entry
        if (v == null) {
            throw new RuntimeException("Undeclared variable: " + name);
        }

        // Control type compatibility
        if (v.type.equals("int")) {
            if ($e.isBool) {
                throw new RuntimeException("Type error: assigning bool to int variable " + name);
            }
            v.ivalue = $e.ivalue;
            v.bvalue = null;
        } else if (v.type.equals("bool")) {
            if (!$e.isBool) {
                throw new RuntimeException("Type error: assigning int to bool variable " + name);
            }
            v.bvalue = $e.bvalue;
            v.ivalue = null;
        } else {
            throw new RuntimeException("Unknown type for variable " + name);
        }

        v.initialized = true;
      }
    ;

// Combined expressions
expr returns [Integer ivalue, Boolean bvalue, boolean isBool]
    : b=boolExpr   { $isBool = true;  $bvalue = $b.value;  $ivalue = null; }
    | a=arithmExpr { $isBool = false; $ivalue = $a.value;  $bvalue = null; }
    ;

// Arithmetic expressions "  unary - () > * / > + -  "           |         factor  >  term  >  arithmExpr
arithmExpr returns [int value]
    : ADDSUB a=arithmExpr{if ($ADDSUB.getText().equals("-"))
                            $value = -$a.value;
                          else
                              $value = +$a.value;
                        }  // unaryMinus
    | '(' A1=arithmExpr ')'                  { $value = $A1.value;}                // arithParens // parantez değeri yanlızca değeri döndürür
    | A1=arithmExpr MULDIV A2=arithmExpr     { if ($MULDIV.getText().equals("*")) {
                                                   $value = $A1.value * $A2.value;
                                                } else {
                                                  $value = $A1.value / $A2.value;
                                                } 
                                              } // mulDiv 
    | A1=arithmExpr ADDSUB A2=arithmExpr     { if ($ADDSUB.getText().equals("+")) {
                                                   $value = $A1.value + $A2.value;
                                                } else {
                                                  $value = $A1.value - $A2.value;
                                                } 
                                              }   // addSub 
    | id=ID
      {
        String name = $id.getText();
        VarEntry v = symtab.get(name);
        if (v == null) {
            throw new RuntimeException("Undeclared variable: " + name);
        }
        if (!v.initialized) {
            throw new RuntimeException("Uninitialized variable: " + name);
        }
        if (!"int".equals(v.type)) {
            throw new RuntimeException("Type error: " + name + " is not int");
        }
        $value = v.ivalue;
      }
    | ENTIER { $value = Integer.parseInt($ENTIER.getText()); }  // intLiteral
    ;
// Boolean expressions "arithm  >  comparaisons >  not >  and  >  or" 

boolExpr returns [boolean value]
    : 'not' e=boolExpr              { $value = ! $e.value; }
    | '(' e=boolExpr ')'            { $value =  $e.value; }
    | e1=boolExpr 'and' e2=boolExpr { $value =  $e1.value && $e2.value; }
    | e1=boolExpr 'or'  e2=boolExpr { $value =  $e1.value || $e2.value; }
    | a1=arithmExpr LOGICOP a2=arithmExpr { 
        switch ($LOGICOP.getText()) {
            case "==": $value = ($a1.value == $a2.value); break;
            case "<>": $value = ($a1.value != $a2.value); break;
            case "<":  $value = ($a1.value < $a2.value); break;
            case "<=": $value = ($a1.value <= $a2.value); break;
            case ">":  $value = ($a1.value > $a2.value); break;
            case ">=": $value = ($a1.value >= $a2.value); break;
            default:   $value = false; // should not happen
        }
      }
    | id=ID
      {
        String name = $id.getText();
        VarEntry v = symtab.get(name);
        if (v == null) {
            throw new RuntimeException("Undeclared variable: " + name);
        }
        if (!v.initialized) {
            throw new RuntimeException("Uninitialized variable: " + name);
        }
        if (!"bool".equals(v.type)) {
            throw new RuntimeException("Type error: " + name + " is not bool");
        }
        $value = v.bvalue;
      }
    | 'true'                        { $value =  true; }
    | 'false'                       { $value =  false; }
    ;

// ---------------- Lexer rules ----------------
NEWLINE : '\r'? '\n';       // match newlines
SEMICOLON : ';' ;           // match semicolons

MULDIV : ('*' | '/');      // $MULDIV.getText()  | $MULDIV.getType() 
ADDSUB : ('+' | '-');      // $ADDSUB.getText()  | $ADDSUB.getType()
LOGICOP : ('==' | '<>' | '<' | '<=' | '>' | '>='); // $LOGICOP.getText()  | $LOGICOP.getType()

ENTIER : ('0'..'9')+;       // match integers , all sequences of digits

TYPE : 'int' | 'bool' ;    // match types
ID : [a-zA-Z_] [a-zA-Z0-9_]* ;// match identifiers

WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
UNMATCH : . -> skip;        // ignore any other character
