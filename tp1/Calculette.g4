// Compilation : antlr4 Calculette.g4
// Execution : javac Calculette*.java 

// grun Calculette start -tree
// Testing : Input strings like: 4+6*7 // pour valider le mot taper 2 fois "Control d"

// grun Calculette start -tokens
//   input : 0+9
   
// grun Calculette start -gui
//    input : 3+5*2

// ------------------------------------------------------------------------------------------
grammar Calculette;

// ---------------- Parser rules ----------------
start: expr ((';' | NEWLINE) expr)* EOF;

// Arithmetic expressions "  unary - () > * / > + -  "
/*
Aritmetik ifadeyi üç seviyeye ayırıyorsun:
    arithmExpr   → en üst (zayıf)
    term         → orta (güçlü)
    factor       → en alt (en güçlü)   
Bu bir “güç sırası” değil.
Bu sadece yapısal bir hiyerarşi.

- zayıf operatör için en geniş kapsayıcı kuralı kullanır,
- güçlü operatör için daha derindeki kuralı kullanır.
Yani güçlü operatör üstte olmaz, içte olur.
*/ 

expr
    : '-' expr                         #unaryMinus
    | '(' expr ')'                     #parens
    | expr ('*' | '/') expr            #mulDiv
    | expr ('+' | '-') expr            #addSub
    | ENTIER                           #intLiteral
    ;

// ---------------- Lexer rules ----------------
NEWLINE : '\r'? '\n' -> skip; // ignore newlines , 
WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs
ENTIER : ('0'..'9')+;       // match integers , all sequences of digits
UNMATCH : . -> skip;        // ignore any other character


/* -----------------------------------------------------------------------------------------
## Règles de l’analyseur syntaxique -> miniscule
    Les règles de l’analyseur syntaxique définissent la structure grammaticale des expressions,
    c’est-à-dire comment les symboles se combinent pour former des expressions valides.

    Explication :
        - start : règle principale (point d’entrée).
                Elle attend une expression complète, suivie de la fin de fichier (EOF).

        - expr : décrit une expression arithmétique :
            - expr '*' expr → une multiplication entre deux sous-expressions.
            - expr '+' expr → une addition entre deux sous-expressions.
            - ENTIER → une expression atomique (un nombre entier).
    


------------------------------------------------------------------------------------------
## Règles de l’analyseur lexical -> majiscule  
    Les règles lexicales définissent comment découper le texte en unités lexicales (tokens).

    Explication de chaque règle :
        - NEWLINE : correspond aux retours à la ligne (CRLF ou LF seul) et les ignore (skip).
        - WS : correspond aux espaces et tabulations, également ignorés (skip).
        - ENTIER : correspond à une séquence d’un ou plusieurs chiffres (0-9), représentant un nombre entier.
        - UNMATCH : correspond à tout autre caractère non défini par les règles précédentes et l’ignore (skip).
---------------------------------------------------------------------------------------------

-grun Calculette start -tokens
    input : 42
        ->  [@0,0:1='42',<ENTIER>,1:0]
            [@1,2:1='<EOF>',<EOF>,1:2]

    input : 22+ 20
        ->  [@0,0:1='22',<ENTIER>,1:0]
            [@1,2:2='+',<'+'>,1:2]
            [@2,4:5='20',<ENTIER>,1:4]
            [@3,6:5='<EOF>',<EOF>,1:6]

    input: 5*8+2*   1
        ->  [@0,0:0='5',<ENTIER>,1:0]
            [@1,1:1='*',<'*'>,1:1]
            [@2,2:2='8',<ENTIER>,1:2]
            [@3,3:3='+',<'+'>,1:3]
            [@4,4:4='2',<ENTIER>,1:4]
            [@5,5:5='*',<'*'>,1:5]
            [@6,11:11='1',<ENTIER>,1:11]
            [@7,12:11='<EOF>',<EOF>,1:12]

    input: 5+2 * 8+ 3
        ->  [@0,0:0='5',<ENTIER>,1:0]
            [@1,1:1='+',<'+'>,1:1]
            [@2,2:2='2',<ENTIER>,1:2]
            [@3,4:4='*',<'*'>,1:4]
            [@4,6:6='8',<ENTIER>,1:6]
            [@5,7:7='+',<'+'>,1:7]
            [@6,9:9='3',<ENTIER>,1:9]
            [@7,11:10='<EOF>',<EOF>,2:0]

    input: 6 *4 + 18
        ->  [@0,0:0='6',<ENTIER>,1:0]
            [@1,2:2='*',<'*'>,1:2]
            [@2,3:3='4',<ENTIER>,1:3]
            [@3,5:5='+',<'+'>,1:5]
            [@4,7:8='18',<ENTIER>,1:7]
            [@5,9:8='<EOF>',<EOF>,1:9]


- grun Calculette start -gui
    input : 42

    input : 22+ 20

    input: 5*8+2*   1

    input: 5+2 * 8+ 3

    input: 6 *4 + 18

-------------------------------------------------------------------------------------------
    ## Ambiguïté et gestion des erreurs
    L’analyseur syntaxique peut rencontrer des ambiguïtés ou des erreurs dans les expressions.
    Par exemple, dans l’expression "5 + 2 ∗ 8 + 3 ", l’analyseur peut ne pas savoir comment grouper les opérations
    en raison de l’absence de parenthèses explicites.
    Cela peut entraîner des erreurs de syntaxe ou des interprétations incorrectes.

    input : 5 + 2 ∗ 8 + 3

        grun Calculette start -tokens
        grun Calculette start -gui

        [@0,0:0='5',<ENTIER>,1:0]
        [@1,2:2='+',<'+'>,1:2]
        [@2,4:4='2',<ENTIER>,1:4]
        [@3,8:8='8',<ENTIER>,1:8]
        [@4,10:10='+',<'+'>,1:10]
        [@5,12:12='3',<ENTIER>,1:12]
        [@6,13:12='<EOF>',<EOF>,1:13]
        line 1:8 mismatched input '8' expecting {<EOF>, '*', '+'}

-------------------------------------------------------------------------------------------

42
[@0,0:1='42',<ENTIER>,1:0]
[@1,3:2='<EOF>',<EOF>,2:0]

24+24-6
[@0,0:1='24',<ENTIER>,1:0]
[@1,2:2='+',<'+'>,1:2]
[@2,3:4='24',<ENTIER>,1:3]
[@3,5:5='-',<'-'>,1:5]
[@4,6:6='6',<ENTIER>,1:6]
[@5,8:7='<EOF>',<EOF>,2:0]

5*8+2*1
[@0,0:0='5',<ENTIER>,1:0]
[@1,1:1='*',<'*'>,1:1]
[@2,2:2='8',<ENTIER>,1:2]
[@3,3:3='+',<'+'>,1:3]
[@4,4:4='2',<ENTIER>,1:4]
[@5,5:5='*',<'*'>,1:5]
[@6,6:6='1',<ENTIER>,1:6]
[@7,8:7='<EOF>',<EOF>,2:0]


6*4/5+38
[@0,0:0='6',<ENTIER>,1:0]
[@1,1:1='*',<'*'>,1:1]
[@2,2:2='4',<ENTIER>,1:2]
[@3,3:3='/',<'/'>,1:3]
[@4,4:4='5',<ENTIER>,1:4]
[@5,5:5='+',<'+'>,1:5]
[@6,6:7='38',<ENTIER>,1:6]
[@7,10:9='<EOF>',<EOF>,3:0]
 
42+1+2+-3
[@0,0:1='42',<ENTIER>,1:0]
[@1,2:2='+',<'+'>,1:2]
[@2,3:3='1',<ENTIER>,1:3]
[@3,4:4='+',<'+'>,1:4]
[@4,5:5='2',<ENTIER>,1:5]
[@5,6:6='+',<'+'>,1:6]
[@6,7:7='-',<'-'>,1:7]
[@7,8:8='3',<ENTIER>,1:8]
[@8,10:9='<EOF>',<EOF>,2:0]
 
5*8+2*-1/-1
[@0,0:0='5',<ENTIER>,1:0]
[@1,1:1='*',<'*'>,1:1]
[@2,2:2='8',<ENTIER>,1:2]
[@3,3:3='+',<'+'>,1:3]
[@4,4:4='2',<ENTIER>,1:4]
[@5,5:5='*',<'*'>,1:5]
[@6,6:6='-',<'-'>,1:6]
[@7,7:7='1',<ENTIER>,1:7]
[@8,8:8='/',<'/'>,1:8]
[@9,9:9='-',<'-'>,1:9]
[@10,10:10='1',<ENTIER>,1:10]
[@11,13:12='<EOF>',<EOF>,3:0] 
 
(5*6*7*11 + 2)/11*5-1008
[@0,0:0='(',<'('>,1:0]
[@1,1:1='5',<ENTIER>,1:1]
[@2,2:2='*',<'*'>,1:2]
[@3,3:3='6',<ENTIER>,1:3]
[@4,4:4='*',<'*'>,1:4]
[@5,5:5='7',<ENTIER>,1:5]
[@6,6:6='*',<'*'>,1:6]
[@7,7:8='11',<ENTIER>,1:7]
[@8,10:10='+',<'+'>,1:10]
[@9,12:12='2',<ENTIER>,1:12]
[@10,13:13=')',<')'>,1:13]
[@11,14:14='/',<'/'>,1:14]
[@12,15:16='11',<ENTIER>,1:15]
[@13,17:17='*',<'*'>,1:17]
[@14,18:18='5',<ENTIER>,1:18]
[@15,19:19='-',<'-'>,1:19]
[@16,20:23='1008',<ENTIER>,1:20]
[@17,25:24='<EOF>',<EOF>,2:0]

(5*6*7*11 + 2)/(11*5)
[@0,0:0='(',<'('>,1:0]
[@1,1:1='5',<ENTIER>,1:1]
[@2,2:2='*',<'*'>,1:2]
[@3,3:3='6',<ENTIER>,1:3]
[@4,4:4='*',<'*'>,1:4]
[@5,5:5='7',<ENTIER>,1:5]
[@6,6:6='*',<'*'>,1:6]
[@7,7:8='11',<ENTIER>,1:7]
[@8,10:10='+',<'+'>,1:10]
[@9,12:12='2',<ENTIER>,1:12]
[@10,13:13=')',<')'>,1:13]
[@11,14:14='/',<'/'>,1:14]
[@12,15:15='(',<'('>,1:15]
[@13,16:17='11',<ENTIER>,1:16]
[@14,18:18='*',<'*'>,1:18]
[@15,19:19='5',<ENTIER>,1:19]
[@16,20:20=')',<')'>,1:20]
[@17,22:21='<EOF>',<EOF>,2:0]

(5*6*7*11 + 2)/11/5
[@0,0:0='(',<'('>,1:0]
[@1,1:1='5',<ENTIER>,1:1]
[@2,2:2='*',<'*'>,1:2]
[@3,3:3='6',<ENTIER>,1:3]
[@4,4:4='*',<'*'>,1:4]
[@5,5:5='7',<ENTIER>,1:5]
[@6,6:6='*',<'*'>,1:6]
[@7,7:8='11',<ENTIER>,1:7]
[@8,10:10='+',<'+'>,1:10]
[@9,12:12='2',<ENTIER>,1:12]
[@10,13:13=')',<')'>,1:13]
[@11,14:14='/',<'/'>,1:14]
[@12,15:16='11',<ENTIER>,1:15]
[@13,17:17='/',<'/'>,1:17]
[@14,18:18='5',<ENTIER>,1:18]
[@15,21:20='<EOF>',<EOF>,3:0]
   
(5*6*7*11 + 2)/(11/5)-1114
[@0,0:0='(',<'('>,1:0]
[@1,1:1='5',<ENTIER>,1:1]
[@2,2:2='*',<'*'>,1:2]
[@3,3:3='6',<ENTIER>,1:3]
[@4,4:4='*',<'*'>,1:4]
[@5,5:5='7',<ENTIER>,1:5]
[@6,6:6='*',<'*'>,1:6]
[@7,7:8='11',<ENTIER>,1:7]
[@8,10:10='+',<'+'>,1:10]
[@9,12:12='2',<ENTIER>,1:12]
[@10,13:13=')',<')'>,1:13]
[@11,14:14='/',<'/'>,1:14]
[@12,15:15='(',<'('>,1:15]
[@13,16:17='11',<ENTIER>,1:16]
[@14,18:18='/',<'/'>,1:18]
[@15,19:19='5',<ENTIER>,1:19]
[@16,20:20=')',<')'>,1:20]
[@17,21:21='-',<'-'>,1:21]
[@18,22:25='1114',<ENTIER>,1:22]
[@19,28:27='<EOF>',<EOF>,3:0]
   
-------------------------------------------------------------------------------------------

3+5
4+8;
555
[@0,0:0='3',<ENTIER>,1:0]
[@1,1:1='+',<'+'>,1:1]
[@2,2:2='5',<ENTIER>,1:2]

[@3,3:3='\n',<'
'>,1:3]

[@4,4:4='4',<ENTIER>,2:0]
[@5,5:5='+',<'+'>,2:1]
[@6,6:6='8',<ENTIER>,2:2]
[@7,7:7=';',<';'>,2:3]

[@8,8:8='\n',<'
'>,2:4]

[@9,9:11='555',<ENTIER>,3:0]

## Modified grammar example

    You can change your grammar like this:
    prog : expr ((';' | '\n') expr)* EOF ;

    Explanation:
        expr → the first arithmetic expression.
        ((';' | '\n') expr)* → zero or more repetitions of “a separator (; or newline) followed by another expression”.
        EOF → end of file.

    So your program can now be a sequence of arithmetic expressions separated by newlines or semicolons.
   
   
    */