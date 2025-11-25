// Compilation : antlr4 Expr_Calculette.g4
// Execution   : javac Expr_Calculette*.java

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

    Map<String, VarEntry> symtab = new HashMap<>(); // systab : sembol tablosu
}

// ---------------- Parser rules ----------------
start
    : instruction
      ( (SEMICOLON | NEWLINE)+ instruction )*
      (SEMICOLON | NEWLINE)*
      EOF
    ;

instruction
    : declInstr                            #declInstruction
    | assignInstr                          #assignInstruction
    |  e=expr
      {
        if ($e.isBool)
            System.out.println("Afficher : " + $e.bvalue);
        else
            System.out.println("Afficher : " + $e.ivalue);
      }
    // İstersen çıplak expr de kabul edebilirsin, ama 3.4'ün ruhu
    // “sadece Afficher yazdırır” olduğu için bunu eklememek daha temiz.
    ;

<<<<<<< HEAD
declInstr
    : t=type ids=idList
      {
        for (String name : $ids.ids) {
            if (symtab.containsKey(name)) {
                throw new RuntimeException("Variable already declared: " + name);
            }

            VarEntry e = new VarEntry();
            e.type = $t.getText();   // "int" veya "bool"
            e.initialized = false;
            e.ivalue = null;
            e.bvalue = null;
            
            symtab.put(name, e);
        }
=======
declInstr // Declaration instruction
    : type idList //TODO 
      { 
        // semantic: her ID için tabloya (type, initialized=false) ekle
>>>>>>> 584e58f (Updateing)
      }
    ;

type // Data type
    : TYPE
    ;

idList returns [List<String> ids]
    : id1=ID
      {
          $ids = new ArrayList<>();
          $ids.add($id1.getText());
      }
      ( ',' idn=ID { $ids.add($idn.getText()); } )*
    ;

assignInstr // Assignment instruction
    : ID '=' e=expr
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
                        } #unaryMinus
    | '(' A1=arithmExpr ')'                  { $value = $A1.value;}                #arithParens // parantez değeri yanlızca değeri döndürür
    | A1=arithmExpr MULDIV A2=arithmExpr     { if ($MULDIV.getText().equals("*")) {
                                                   $value = $A1.value * $A2.value;
                                                } else {
                                                  $value = $A1.value / $A2.value;
                                                } 
                                              }#mulDiv 
    | A1=arithmExpr ADDSUB A2=arithmExpr     { if ($ADDSUB.getText().equals("+")) {
                                                   $value = $A1.value + $A2.value;
                                                } else {
                                                  $value = $A1.value - $A2.value;
                                                } 
                                              }   #addSub 
    | ENTIER { $value = Integer.parseInt($ENTIER.getText()); }  #intLiteral
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
ID : ('0'..'9' | 'a'..'z' | 'A'..'Z' | '_')+
      ;                       // match identifiers

WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
UNMATCH : . -> skip;        // ignore any other character
