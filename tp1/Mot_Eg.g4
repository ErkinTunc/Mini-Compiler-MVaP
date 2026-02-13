grammar Mot_Eg;

// Compilation : antlr4 Mot_Eg.g4 
// Execution : javac Mot_Eg*.java 
// grun Mot_Eg s -tree

// Testing : Input strings like: abccba // pour valider le mot taper 2 fois "Control d"

// file: Mot_Eg.g4
// ----------------------------------------------------------------------------------------------------
// Parser rules
start: s EOF; // point d'entrée

s:
	A s B s // wrap two sub-strings with 'a' ... 'b' 
	| B s A s // or 'b' ... 'a' 
	| C;

A: 'a';
B: 'b';
C: 'c';

// ----------------------------------------------------------------------------------------------------

/*
 l’outil grun pour avoir la liste des tokens ou pour visualiser l’arbre syntaxique produit par
 l’analyseur
 
 Testez votre grammaire avec des mots de L et des mots qui ne sont pas dans L. On termine chaque
 entrée par "control d"
 
 - grun Mot_Eg start -tokens
   input : abb
 
 [@0,0:0='a',<'a'>,1:0] [@1,1:1='b',<'b'>,1:1] [@2,2:2='b',<'b'>,1:2] [@3,3:2='<EOF>',<EOF>,1:3] No
 method for rule start or it has arguments
 
 - grun Mot_Eg start -gui
   input : abccba

    ababline 1:4 mismatched input '<EOF>' expecting {'a', 'b', 'c'}
    Nov 06, 2025 12:45:14 PM java.util.prefs.FileSystemPreferences$1 run
    INFO: Created user preferences directory.

 */





/* CM1 Exemple
 
 grammer Ex_parser; //regles de parseur expr: (NOMBRE | ID) suite_expr; suite_expr: OP (NOMBRE | ID)
 suite_expr | MotVide; //et aussi mot vide MotVide: "";
 
 // regles analyseur lexical NOMBRE: [0-9]+; OP: '+' | '-' | '*' | '/'; ID: [a-zA-Z]+;
 

 

 */