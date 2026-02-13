// Compilation : antlr4 Expr_Calculette.g4
// Execution   : javac Expr_Calculette*.java

// grun Expr_Calculette start -tree
//  input : 

// grun Expr_Calculette start -tokens
//  input : 
   
// grun Expr_Calculette start -gui
//  input : 

grammar Expr_Calculette;

// ---------------- Parser rules ----------------
start: 
expr (( SEMICOLON | NEWLINE ) expr)* EOF;

// Combined expressions
expr : boolExpr | arithmExpr ;

// Arithmetic expressions " * / > + - > unary - ()"             |         factor  >  term  >  arithmExpr
arithmExpr
    : '-' arithmExpr                    #unaryMinus
    | '(' arithmExpr ')'            #arithParens
    | arithmExpr ('*' | '/') arithmExpr       #mulDiv
    | arithmExpr ('+' | '-') arithmExpr   #addSub
    | ENTIER                        #intLiteral
    ;
// Boolean expressions "not > and > or"

boolExpr
    : 'not' boolExpr              #notExpr
    | boolExpr 'and' boolExpr     #andExpr
    | boolExpr 'or' boolExpr      #orExpr
    | '(' boolExpr ')'              #boolParens
    | 'true'                        #trueLiteral
    | 'false'                       #falseLiteral
    ;

// ---------------- Lexer rules ----------------
NEWLINE : '\r'? '\n';       // match newlines
SEMICOLON : ';' ;           // match semicolons

ENTIER : ('0'..'9')+;       // match integers , all sequences of digits

WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
UNMATCH : . -> skip;        // ignore any other character


