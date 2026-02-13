// Compilation : antlr4 G_Expr_Bool.g4
// Execution   : javac G_Expr_Bool*.java
// grun G_Expr_Bool start -tree
// grun G_Expr_Bool start -tokens
// grun G_Expr_Bool start -gui

/*
f : L(Expr_Bool) → L(G)
her boolean ifade e için, f(e) = G dilinde aynı truth-value’a sahip bir kelime.
 
Bu bir:
    çeviri / traduction
    transducer (grammaire attribuée ile yapılan bir çevirici)
diller arasında fonksiyonel bir dönüşüm. 
 */

grammar G_Expr_Bool;

@header { // { action code }
import java.util.*;
}

// ---------------- Parser rules ----------------

// start yapısını bozmadım, sadece expr'lere isim verip println ekledim
start
    : e1=expr                         { System.out.println($e1.code); }
      ( ( '/' | ';' ) e2=expr         { System.out.println($e2.code); }
      )*
      EOF
    ;

// expr yapısını da bozmadım; sadece return ve action ekledim
expr returns [String code]
    : 'not' e=expr      { $code = "not " + $e.code; }
    | e1=expr 'and' e2=expr { $code = "and " + $e1.code + " " + $e2.code; }
    | e1=expr 'or' e2=expr { $code = "or " + $e1.code + " " + $e2.code; }
    | '(' e=expr ')'    { $code = $e.code; }
    | 'true'            { $code = "true"; }
    | 'false'           { $code = "false"; }
    ;

// ---------------- Lexer rules ----------------

// NEWLINE kuralın zaten hatalıydı ('\n' yok, syntax da bozuk),
// onu tamamen atıyorum; newline'ı WS içine alıyoruz.
WS : (' ' | '\t' | '\r' | '\n')+ -> skip;


//------------------------------------------------------------------------------------------
/* 
grun G_Expr_Bool start -tree
    mot : true and false or true and 
          not true and and true or false true not true

    or and true false and true not true
    
    (start (expr (expr (expr true) and (expr (expr false)
     or (expr true)))and (expr not (expr true))) <EOF>)

Yani:
1. İlk satır: senin System.out.println($e1.code); çıktın
→ or and true false and true not true (G dilindeki kelime)

2. Sonraki satır: parse ağacı (-tree yüzünden)
*/
//------------------------------------------------------------------------------------------
/*

-grun G_Expr_Bool start -tree
    input :true and false; not true or false / true

 
and true false
not or true false
true
(start (expr (expr true) and (expr false)) ; (expr not (expr (expr true) or (expr false))) / (expr true) <EOF>)


1. İlk 3 satır ne?
    Bunlar tamamen senin semantic action’ların:

    and true false
    not or true false
    true

Demek ki parser şu üç ifadeyi görmüş:
    1. true and false
        → kelime: and true false
    2. not true or false
        Parse ağacına göre aslında bu:
        not (true or false)
            çünkü ağaçta ikinci expr şu:
        (expr not (expr (expr true) or (expr false)))
            Yani not dışarıda, (true or false) içeride.
            Senin çevirin: not or true false → bu da tam not (true or false)’un G-dilindeki prefix hali.

    3. / true sonrası üçüncü ifade:
        true
            → kelime: true
            Yani:
                Girişin üç boolean ifadesi var, sen de üç tane L(G) kelimesi üretiyorsun. Bu tam olarak soru 2.3’ün istediği şey.

2. Son satır ne?
(start (expr (expr true) and (expr false)) ; (expr not (expr (expr true) or (expr false))) / (expr true) <EOF>)
Bu -tree seçeneğinin parse ağacı
    - start
        - ilk expr → (expr (expr true) and (expr false))
        - sonra ;
        - ikinci expr → not ( true or false )
        - sonra /
        - üçüncü expr → true
        - sonra <EOF>
    Demek ki grammar artık hatasız parse ediyor ve semantic action’lar doğru çalışıyor.
 */