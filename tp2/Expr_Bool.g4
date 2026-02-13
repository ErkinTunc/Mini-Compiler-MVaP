// Compilation : antlr4 Expr_Bool.g4
// Execution : javac Expr_Bool*.java 

// grun Expr_Bool start -tree
// Testing : Input strings like: true and false // pour valider le mot taper 2 fois "Control d"

// grun Expr_Bool start -tokens
//   input : true and false or true and not true
   
// grun Expr_Bool start -gui
//    input : true and false or true and not true

grammar Expr_Bool;

@header {
import java.util.*;
}

// ---------------- Parser rules ----------------
start: expr (( '/' | ';' | '\n') expr)* EOF;
expr
    : 'not' expr
    | expr 'and' expr
    | expr 'or' expr
    | 'true'
    | 'false'
    | '(' expr ')'
    ;

// Lexer rules
WS : (' '|'\t')+ -> skip;   // ignore spaces and tabs

/**
“Rappelons les priorités not > and > or.” demek istedikleri şey net:
Bu üç operatörün öncelik sırası şöyle olsun diyorlar:
"not" en yüksek öncelik sonra "and" en düşük "or"
Yani bir ifadede parantez yazmasan bile, şu şekilde gruplanmış kabul edilecek:

not her zaman önce uygulanır
sonra and’ler hesaplanır
en son or’lar hesaplanır

- Burada ANTLR4 kuralı yazarken dikkat etmen gereken nokta şu:
  Öncelik sırasını sağlamak için kuralları doğru şekilde yapılandırmalısın.
- En yüksek öncelikli operatör en derin kuralda yer almalı,
  en düşük öncelikli operatör ise en üst kuralda yer almalı.
- Yani not en derin, or en üstte olacak şekilde kuralları yazmalısın.
- Bu sayede ANTLR4, ifadeleri doğru şekilde ayrıştırabilir ve
  öncelik sırasına göre işlemleri gerçekleştirebilir.  


 */

