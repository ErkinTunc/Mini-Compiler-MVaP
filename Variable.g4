// Compilation : antlr4 Variable.g4 -o ./Variable/bin
// Execution   : javac -d ./Variable/bin/ ./Variable/bin/*.java

// grun Variable start -gui

grammar Variable ;
@header
{
 import java.util.*;
}

@members
{
    static class VarEntry 
    {
        String type;        // "int" or "bool"
        boolean initialized;
        Integer ivalue;     // sadece int için kullan
        Boolean bvalue;     // sadece bool için kullan
    }

    Map<String, VarEntry> symtab = new HashMap<>();
}

// ---------------- Parser rules ----------------
start
    : instruction
      ( (SEMICOLON | NEWLINE)+ instruction )*
      (SEMICOLON | NEWLINE)*
      EOF
    ;

instruction 
    : variable
    | 'Afficher' variable
    ; 

variable
         // { $VariableParser.symtab.put(
                //                 $id.getText(), 
                //                 new VarEntry() 
                //                 {
                //                     { type = $t.getText(); 
                //                         initialized = false; 
                // *                   }
                //                 }
                //                     ); 
                // } 
    : 'int' ID ASSIGNE ENTIER
    | 'bool' ID ASSIGNE BOOL
    | type ID
    | ID ASSIGNE
    ;

type : 'int' | 'bool';

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

ID : [a-zA-Z_] [a-zA-Z0-9_]* ;// match identifiers