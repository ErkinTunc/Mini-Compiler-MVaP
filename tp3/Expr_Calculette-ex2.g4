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

// ---------------- Parser rules ----------------
start: 
  e1=expr
    {
      if ($e1.isBool) System.out.println($e1.bvalue);
        else System.out.println($e1.ivalue);
      }
  (( SEMICOLON | NEWLINE ) e2=expr
    {
      if ($e2.isBool) System.out.println($e2.bvalue);
          else            System.out.println($e2.ivalue);
      }

   )* EOF
    
    
    ;

// Combined expressions
expr returns [Integer ivalue, Boolean bvalue, boolean isBool]
    : b=boolExpr   { $isBool = true;  $bvalue = $b.value;  $ivalue = null; }
    | a=arithmExpr { $isBool = false; $ivalue = $a.value;  $bvalue = null; }
    ;

// Arithmetic expressions "  unary - () > * / > + -  "           |         factor  >  term  >  arithmExpr
arithmExpr returns [int value]
    : '-' A1=arithmExpr                      { $value = -1 * $A1.value; }          #unaryMinus
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
// Boolean expressions "not > and > or"

boolExpr returns [boolean value]
    : 'not' e=boolExpr              { $value = ! $e.value; }
    | e1=boolExpr 'and' e2=boolExpr { $value =  $e1.value && $e2.value; }
    | e1=boolExpr 'or'  e2=boolExpr { $value =  $e1.value || $e2.value; }
    | '(' e=boolExpr ')'            { $value =  $e.value; }
    | 'true'                        { $value =  true; }
    | 'false'                       { $value =  false; }
    ;

// ---------------- Lexer rules ----------------
NEWLINE : '\r'? '\n';       // match newlines
SEMICOLON : ';' ;           // match semicolons

MULDIV : ('*' | '/');      // $MULDIV.getText()  | $MULDIV.getType() 
ADDSUB : ('+' | '-');      // $ADDSUB.getText()  | $ADDSUB.getType()

ENTIER : ('0'..'9')+;       // match integers , all sequences of digits

WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
UNMATCH : . -> skip;        // ignore any other character
