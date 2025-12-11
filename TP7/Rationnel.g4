/*
  Compilation : antlr4 ./Rationnel.g4 -o ./build/ & javac ./build/*.java
  
  Exécution : grun Rationnel 'start' -gui 
  */


/**
— Utilisation de variables globales : déclaration, affectation et utilisation dans des expressions.
— Blocs.
— Structures conditionnelles.
— Boucles.
 */


grammar Rationnel;

@header
{
 import java.util.*;
}

@parser::members
{ 
  static class VarEntry
  {
    String type;        // "int" or "bool"
    boolean initialized;
    Integer ivalue;    
    Boolean bvalue;  
    Double dvalue ; // Rationnel

    int address;     // address in MVaP memory   
  }

  HashMap < String, VarEntry > varTab = new HashMap<>(); 
  // holds variable names and types and addresses in MVaP


  StringBuilder code = new StringBuilder();  // holds the MVaP code

  int labelCounter = 0;

  String newLabel(String base)
  {
    return base + "_" + (labelCounter++);
  }

  void emit(String instr)
  {
    code.append(instr).append("\n");
  }
}

// ---------------- Parser rules ----------------

start
  : code 
  ;

code
  : (instruction (SEMICOLON | NEWLINE)*)* EOF
  | ( bloc NEWLINE)* EOF
  ;

bloc 
  : '{' (instruction (SEMICOLON | NEWLINE)*)* '}'
  ;

instruction
    : declInstr
    | assignInstr
    |  e=expr
    | 'Afficher' e=expr
    | struc_conditionnel
    | boucle
    ;

declInstr
  : t=type ids=idList

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
    ;


// Nous allons reprendre les régles des TPs précédents pour les expressions arithmétiques et booléennes
// Cela nous permettra de passer au tests
expr  
  :  e1=arithmexpr
  | e2=boolexpr
  ;

arithmexpr  
  : e1=ENTIER
  ;

boolexpr returns [Boolean value]
  : b1='true'
    {
      $value = true;
    }
  | b2='false'
    {
      $value = false;
    }
  ;

/*
On accepte les instructions conditionnelles, possiblement imbri-
quées, de la forme suivante : exprC? A : S où exprC est une expression booléenne et la séman-
tique est celle classique : Si exprC est vraie, alors on n’exécute que A, sinon on n’exécute que S. Le
: S est facultatif. De plus, l’ensemble des instructions A (ou S) peut être soit une seule instruction,
soit un bloc d’instructions. Par exemple le code source suivant devrait être accepté :
*/

struc_conditionnel
  : cond=boolexpr '?' thenInstr=instruction ( ':' elseInstr=instruction )
  {
    // Generate code for conditional structure
    String elseLabel = newLabel("else");
    String endLabel = newLabel("end_if");

    // Evaluate condition
    if ( !$cond.value )
    {
        // Jump to else part if condition is false
        emit("JUMP " + elseLabel);
    }

    // Then part
    // (Generate code for thenInstr here)

    emit("JUMP " + endLabel);

    // Else part
    emit(elseLabel + ":");

    // (Generate code for elseInstr here)

    emit(endLabel + ":");
  }
  | cond=boolexpr '?' thenInstr=instruction 
  {
    // Generate code for conditional structure
    String endLabel = newLabel("end_if");

    // Evaluate condition
    if ( !$cond.value )
    {
        // Jump to end if condition is false
        emit("JUMP " + endLabel);
    }

    // Then part
    // (Generate code for thenInstr here)

    emit(endLabel + ":");
  }
  ;

/* 
Boucles. On accepte deux types de structures itératives :

  — Les instructions itératives, possiblement imbriquées de la forme,
  Pour index=debut .. fin Faire instr
  où index est un identifiant d’une variable globale déjà déclarée, debut et fin sont des
  expressions de type entier, et instr est soit une instruction, soit un bloc d’instructions. Le
  sens d’une telle instruction est la répétition fin-debut fois de instr. La valeur de index
  à la fin de la boucle est égale à fin.

  — Les instructions itératives, possiblement imbriquées de la forme suivante :
  repeter instr jusque exprC
  où instr est soit une seule instruction soit un bloc d’instructions et exprC est une expression
  booléenne. Le sens d’une telle instruction itérative est l’exécution de instr au moins une
  fois et tant que la condition exprC n’est pas satisfaite.
*/
boucle 
  : 'repeter' code 'jusque' boolexpr
  {
    // Generate code for repeat-until loop

    String startLabel = newLabel("repeat_start");
    String endLabel = newLabel("repeat_end");

    emit(startLabel + ":");

    // Loop body
    // (Generate code for code here)

    // Evaluate condition
    if ( $boolexpr.value )
    {
      // Exit loop if condition is true
      emit("JUMP " + endLabel);
    }

    // Jump back to start
    emit("JUMP " + startLabel);

    emit(endLabel + ":");
  }
  | 'Pour' 'index=' index= ENTIER '..' fin= ENTIER 'Faire' body=code
  {
    // Generate code for for loop
    String startLabel = newLabel("for_start");
    String endLabel = newLabel("for_end");

    // Initialize index
    emit("PUSHI " + $index.getText());
    emit("STORE index"); // assuming 'index' is the variable name

    emit(startLabel + ":");

    // Check loop condition
    emit("LOAD index");
    emit("PUSHI " + $fin.getText());
    emit("GT"); // if index > fin
    emit("JUMP " + endLabel);

    // Loop body
    // (Generate code for body here)

    // Increment index
    emit("LOAD index");
    emit("PUSHI 1");
    emit("ADD");
    emit("STORE index");

    // Jump back to start
    emit("JUMP " + startLabel);

    emit(endLabel + ":");
  }
  ; 

// ---------------- Lexer rules ----------------
NEWLINE : '\r'? '\n';       // match newlines
SEMICOLON : ';' ;           // match semicolons

MULDIV : ('*' | '/');      // $MULDIV.getText()  | $MULDIV.getType() 
ADDSUB : ('+' | '-');      // $ADDSUB.getText()  | $ADDSUB.getType()
INCDEC : '++' | '--' ;     // $INCDEC.getText()  | $INCDEC.getType()
LOGICOP : ('==' | '<>' | '<' | '<=' | '>' | '>='); // $LOGICOP.getText()  | $LOGICOP.getType()

ENTIER : ('0'..'9')+;       // match integers , all sequences of digits

TYPE : 'int' | 'bool' ;    // match types
ID : [a-zA-Z_] [a-zA-Z0-9_]* ;// match identifiers

WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
//UNMATCH : . -> skip;        // ignore any other character 

