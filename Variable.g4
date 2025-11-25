grammar Variable ;

// ---------------- Parser rules ----------------
start
    : varaible
      ( (SEMICOLON | NEWLINE)+ varaible )*
      (SEMICOLON | NEWLINE)*
      EOF
    ;

varaible
    : type ID SEMICOLON
    | 'int' ID ASSIGNE ENTIER SEMICOLON
    | 'bool' ID ASSIGNE BOOL SEMICOLON
    | ID ASSIGNE SEMICOLON
    ;

type : 'int' | 'bool' ;

// ---------------- Lexer rules ----------------
NEWLINE : '\r'? '\n';       // match newlines
SEMICOLON : ';' ;           // match semicolons

MULDIV : ('*' | '/');      // $MULDIV.getText()  | $MULDIV.getType() 
ADDSUB : ('+' | '-');      // $ADDSUB.getText()  | $ADDSUB.getType()
LOGICOP : ('==' | '<>' | '<' | '<=' | '>' | '>='); // $LOGICOP.getText()  | $LOGICOP.getType()

ASSIGNE : '=' ;

ENTIER : ('0'..'9')+;       // match integers , all sequences of digits
BOOL : 'true' | 'false' ;

WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
UNMATCH : . -> skip;        // ignore any other character

ID : ENTIER ID | STRING ID ;
STRING : ('a' .. 'z')+ STRING | ('A' .. 'Z')+ STRING ;