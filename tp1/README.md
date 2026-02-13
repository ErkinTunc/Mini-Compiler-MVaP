# TP1 -- Introduction to ANTLR and Basic Compiler Construction

## 1. What Was Implemented

This practical session focused on building and testing simple grammars
using ANTLR.

### Mot_Eg Grammar

- Designed a non-ambiguous context-free grammar named `Mot_Eg`.

- The grammar recognizes the language:

  L = { w ∈ {a, b, c}\* \| number of a in w = number of b in w }

- Ensured that:
  - All non-terminals are written in lowercase (ANTLR convention).
  - The start rule ends with `EOF`.

- Compiled the grammar using `antlr4`.

- Generated lexer and parser in Java.

- Tested the grammar using `grun`:
  - Token visualization with `-tokens`
  - Parse tree visualization with `-gui`

- Verified correctness using valid and invalid words.

### Calculette Grammar

- Created `Calculette.g4` for arithmetic expressions.
- Initially supported:
  - Integer literals
  - Addition (+)
  - Multiplication (\*)
- Identified parser rules (`start`, `expr`) and lexer rules (`ENTIER`,
  `WS`, `NEWLINE`).
- Observed whitespace removal via lexer `-> skip`.
- Tested operator precedence behavior.
- Swapped rule order to observe precedence effects.
- Extended grammar to include:
  - Subtraction
  - Division
  - Unary minus
  - Parenthesized expressions
- Enforced correct operator precedence.
- Modified grammar so programs may contain multiple expressions
  separated by newline or semicolon.

---

## 2. What Was Learned

### ANTLR Fundamentals

- Structure of `.g4` grammar files.
- Difference between parser and lexer rules.
- Importance of `EOF` in start rules.
- Automatic generation of lexer and parser code.

### Compilation Workflow

- Grammar creation.
- Code generation with `antlr4`.
- Java compilation.
- Testing with `grun`.

### Context-Free Grammars

- Designing non-ambiguous grammars.
- Managing ambiguity and precedence.
- Understanding left recursion.

### Lexical Analysis

- Token definition using regular expressions.
- Use of `skip` to remove irrelevant tokens.
- Separation between lexical and syntactic analysis.

### Expression Parsing

- Handling unary and binary operators.
- Enforcing precedence and associativity.
- Testing ambiguous vs structured grammars.

---

## 3. How to Run the Project

1.  Install ANTLR4 and ensure `antlr4` and `grun` are available in the
    terminal.

2.  Generate lexer and parser files:

    ```bash
    antlr4 Mot_Eg.g4
    antlr4 Calculette.g4
    ```

3.  Compile generated Java files:

    ```bash
    javac *.java
    ```

4.  Test using `grun`, for example:

    ```bash
    grun Calculette start -gui
    ```

    or

    ```bash
    grun Mot_Eg start -tokens
    ```

---

## 4. Project Structure

    .
    ├── Mot_Eg.g4
    ├── Calculette.g4
    ├── *.java          # Generated ANTLR files
    ├── *.class         # Compiled Java files
    └── README.md

---

This TP established the foundations for future work in syntax analysis,
semantic processing, and code generation.
