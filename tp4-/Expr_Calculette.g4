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

@parser::members { // Juste pour le parser // pas pour le lexer
    static class VarEntry {
        String type;
        boolean initialized;
        Integer ivalue;
        Boolean bvalue;
    }

    Map<String, VarEntry> symtab = new HashMap<>();
    Deque<Map<String, VarEntry>> symtabStack = new ArrayDeque<>(); // Stack for symbol tables

    static Scanner in = new Scanner(System.in);

    // Deep copy: VarEntry'leri de kopyalıyoruz
    Map<String, VarEntry> cloneSymtab(Map<String, VarEntry> src) {
        Map<String, VarEntry> copy = new HashMap<>();  // "copy" -> nouvelle table des symboles (table copiee)
        for (Map.Entry<String, VarEntry> e : src.entrySet()) {
            VarEntry v = e.getValue();
            VarEntry nv = new VarEntry();   // On creé un nouvelle objet VarEntry
            nv.type = v.type;               // On affecte les valuers et types
            nv.initialized = v.initialized;
            nv.ivalue = v.ivalue;
            nv.bvalue = v.bvalue;
            copy.put(e.getKey(), nv);
        }
        return copy;
    }

    // Next token ID ise ve bool değişken ise true
    boolean isBoolVarToken() {
        org.antlr.v4.runtime.Token t = _input.LT(1);
        if (t.getType() != ID) return false;      //Le prochain token n'est pas un ID
        VarEntry v = symtab.get(t.getText());
        return v != null && "bool".equals(v.type); // Le type de la variable est bool
    }

    // Next token ID ise ve int değişken ise true
    boolean isIntVarToken() {
        org.antlr.v4.runtime.Token t = _input.LT(1);
        if (t.getType() != ID) return false;     //Le prochain token n'est pas un ID
        VarEntry v = symtab.get(t.getText());
        return v != null && "int".equals(v.type); // Le type de la variable est int
    }
}

// ---------------- Parser rules ----------------
start
    : instruction
      ( (SEMICOLON | NEWLINE)+ instruction )*
      (SEMICOLON | NEWLINE)*
      EOF
    ;

instruction
    : declInstr                            // declInstruction
    | assignInstr                          // assignInstruction
    | 'Afficher' '(' e=expr ')'
      {
        if ($e.isBool)
            System.out.println(" Afficher : " + $e.bvalue);
        else
            System.out.println(" Afficher : " + $e.ivalue);
      }
    | blockInstr
    ;

declInstr
    : t=type ids=idList
      {
        for (String name : $ids.value) {
            // Shadowing'e izin veriyoruz: aynı isim varsa bile üzerine yazarız
            VarEntry e = new VarEntry();
            e.type = $t.value;
            e.initialized = false;
            e.ivalue = null;
            e.bvalue = null;

            symtab.put(name, e); // mevcut girdinin üzerine yazar
        }
      }
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
      {
        String name = $id.getText();
        VarEntry v = symtab.get(name);  // v = variable entry
        if (v == null) {
            throw new RuntimeException("Undeclared variable: " + name);
        }

        // Control type compatibility
        if (v.type.equals("int")) {
            if ($e.isBool) {
                throw new RuntimeException("Type error: assigning bool to int variable " + name);
            }
            v.ivalue = $e.ivalue;
            v.bvalue = null;
        } else if (v.type.equals("bool")) {
            if (!$e.isBool) {
                throw new RuntimeException("Type error: assigning int to bool variable " + name);
            }
            v.bvalue = $e.bvalue;
            v.ivalue = null;
        } else {
            throw new RuntimeException("Unknown type for variable " + name);
        }

        v.initialized = true;
      }
    ;

blockInstr
    : '{'
      {
          // Bloka girerken: snapshot + yeni tablo
          symtabStack.push(symtab);         // eski referansı stack'e koy
          symtab = cloneSymtab(symtab);     // mevcut tabloyu kopyala ve kopya ile çalış
      }
      ( (SEMICOLON | NEWLINE)* instruction )*
      (SEMICOLON | NEWLINE)*
      '}'
      {
          // Bloktan çıkarken: eski tabloya geri dön
          symtab = symtabStack.pop();
      }
    ;

// Combined expressions
expr returns [Integer ivalue, Boolean bvalue, boolean isBool]
    : b=boolExpr   { $isBool = true;  $bvalue = $b.value;  $ivalue = null; }
    | a=arithmExpr { $isBool = false; $ivalue = $a.value;  $bvalue = null; }
    ;

// Arithmetic expressions "  unary - () > * / > + -  "           |         factor  >  term  >  arithmExpr
arithmExpr returns [int value]
    : 'lire' '(' ')'
      {
        System.out.print("Entrez un entier : ");
        $value = in.nextInt();
      }
    | op=INCDEC id=ID // ++x / --x  : önce eski değeri kullan, sonra değiştir
      {
        String name = $id.getText();
        VarEntry v = symtab.get(name);
        if (v == null)  throw new RuntimeException("Undeclared variable: " + name);
        if (!v.initialized) throw new RuntimeException("Uninitialized variable: " + name);
        if (!"int".equals(v.type)) throw new RuntimeException("Type error: " + name + " is not int");

        int old = v.ivalue;
        if ($op.getText().equals("++")) v.ivalue = old + 1;
        else                            v.ivalue = old - 1;
        $value = old; // eski değer kullanılıyor
      }
    | id=ID op=INCDEC // x++ / x-- : önce değiştir, sonra yeni değeri kullan
      {
        String name = $id.getText();
        VarEntry v = symtab.get(name);
        if (v == null)  throw new RuntimeException("Undeclared variable: " + name);
        if (!v.initialized) throw new RuntimeException("Uninitialized variable: " + name);
        if (!"int".equals(v.type)) throw new RuntimeException("Type error: " + name + " is not int");

        int old = v.ivalue; // ancien valueur 
        if ($op.getText().equals("++")) v.ivalue = old + 1; // nouveau valeur
        else                            v.ivalue = old - 1; // nouveau valeur
        $value = v.ivalue; // yeni değer kullanılıyor
      }
    | {isIntVarToken()}? id=ID    // fonction isIntVarToken -> si token est ID et variable de type int 
      {
        String name = $id.getText();
        VarEntry v = symtab.get(name);
        if (v == null) {
            throw new RuntimeException("Undeclared variable: " + name);
        }
        if (!v.initialized) {
            throw new RuntimeException("Uninitialized variable: " + name);
        }
        $value = v.ivalue;
      }
    | ENTIER { $value = Integer.parseInt($ENTIER.getText()); }  // intLiteral
    | ADDSUB a=arithmExpr{if ($ADDSUB.getText().equals("-"))
                            $value = -$a.value;
                          else
                              $value = +$a.value;
                        }  // unaryMinus
    | '(' A1=arithmExpr ')'                  { $value = $A1.value;}                // arithParens // parantez değeri yanlızca değeri döndürür

    | A1=arithmExpr MULDIV A2=arithmExpr     { if ($MULDIV.getText().equals("*")) {
                                                   $value = $A1.value * $A2.value;
                                                } else {
                                                  $value = $A1.value / $A2.value;
                                                } 
                                              } // mulDiv 
    | A1=arithmExpr ADDSUB A2=arithmExpr     { if ($ADDSUB.getText().equals("+")) {
                                                   $value = $A1.value + $A2.value;
                                                } else {
                                                  $value = $A1.value - $A2.value;
                                                } 
                                              }   // addSub 
    ;

// Boolean expressions "arithm  >  comparaisons >  not >  and  >  or" 
boolExpr returns [boolean value]
    : 'lire' '(' ')'
      {
        System.out.print("Entrez un booléen (true/false) : ");
        $value = in.nextBoolean();
      }
    | 'not' e=boolExpr              { $value = ! $e.value; }
    | '(' e=boolExpr ')'            { $value =  $e.value; }
    | e1=boolExpr 'and' e2=boolExpr { $value =  $e1.value && $e2.value; }
    | e1=boolExpr 'or'  e2=boolExpr { $value =  $e1.value || $e2.value; }
    | a1=arithmExpr LOGICOP a2=arithmExpr { 
        switch ($LOGICOP.getText()) {
            case "==": $value = ($a1.value == $a2.value); break;
            case "<>": $value = ($a1.value != $a2.value); break;
            case "<":  $value = ($a1.value < $a2.value); break;
            case "<=": $value = ($a1.value <= $a2.value); break;
            case ">":  $value = ($a1.value > $a2.value); break;
            case ">=": $value = ($a1.value >= $a2.value); break;
            default:   $value = false; // should not happen
        }
      }
     | {isBoolVarToken()}? id=ID   // fonction isBoolVarToken -> si token est ID et variable de type bool
      {
        String name = $id.getText();
        VarEntry v = symtab.get(name);
        if (v == null) {
            throw new RuntimeException("Undeclared variable: " + name);
        }
        if (!v.initialized) {
            throw new RuntimeException("Uninitialized variable: " + name);
        }
        $value = v.bvalue;
      }
    | 'true'                        { $value =  true; }
    | 'false'                       { $value =  false; }
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
UNMATCH : . -> skip;        // ignore any other character

