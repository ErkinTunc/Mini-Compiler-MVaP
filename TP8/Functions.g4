grammar Functions;

// ----------------- Parser rules ----------------

start: function+;

function: type ID '(' paramlist? ')' '{' functionCode '}';

type: INT | BOOL | RATIONNEL;

paramlist: param (',' param)*;

param: type ID;

code: OB (instruction (SEMICOLON | NEWLINE))* CB;

functionCode: OB (instruction (SEMICOLON | NEWLINE))* retour CB;

retour: RET valeur;

valeur: expr | ID;

instruction: expr; // faut ajouter les decl et assign des var

expr: exprRat | exprBool;
exprRat: num = INT '/' den = INT;

exprBool: bool;

bool: TRUE | FALSE;

// ----------------- Lexer rules ----------------

RET: 'retourner';

TYPE: 'int' | 'bool' | 'rationnel';

INT: [0-9]+;

BOOL: 'boolean';
RATIONNEL: [0-9]+ '/' [0-9]+;

TRUE: 'true';
FALSE: 'false';

OB: '{';
CB: '}';

SEMICOLON: ';';
NEWLINE: '\r?\n';

ID: [a-zA-Z_][a-zA-Z0-9_]*;
WS: [ \t\r\n]+ -> skip;
