# TP3 -- Expression Handling and Interpretation with ANTLR

## 1. What Was Implemented

This practical session focused on integrating arithmetic and boolean
expressions within a compiler generated using ANTLR and enabling their
interpretation.

### Combined Expression Grammar

- Built a grammar `Expr_Calculette` capable of parsing:
  - Arithmetic expressions
  - Boolean expressions
- Allowed programs to contain multiple expressions separated by
  newline or semicolon.
- Ensured compatibility between arithmetic and boolean components.

### Expression Interpretation

- Decorated the grammar so that each expression is interpreted during
  execution.
- Arithmetic expressions evaluate to numeric values.
- Boolean expressions evaluate to logical values.
- Output corresponds to evaluated results.

### Comparison Operators

- Extended boolean expressions to support comparisons between
  arithmetic expressions.
- Implemented comparison operators:
  - `==`
  - `<>`
  - `<`
  - `>`
  - `<=`
  - `>=`
- Comparison results produce boolean values usable inside logical
  expressions.

### Display Instruction

- Added instruction:

  Afficher(expr)

- Allows explicit output of evaluated expressions.

- Supports both arithmetic and boolean expressions.

- Interpretation prints evaluation results when invoked.

---

## 2. What Was Learned

### Expression Integration

- Combining arithmetic and boolean grammars.
- Handling expressions of different semantic types.
- Managing precedence across arithmetic and logical operators.

### Grammar Decoration for Interpretation

- Evaluating expressions directly from grammar rules.
- Propagating semantic values through attributes.
- Integrating syntax parsing with runtime evaluation.

### Comparison Semantics

- Converting arithmetic comparisons into boolean expressions.
- Supporting comparisons inside logical operations.

### Language Feature Extension

- Incrementally extending grammars with new instructions.
- Adding output instructions similar to programming languages.

### Interpreter Construction

- Using ANTLR grammars to construct interpreters.
- Executing expression programs directly via parsing.

---

## 3. How to Run the Project

1.  Install ANTLR4 and ensure `antlr4` and `grun` are available in the
    terminal.

2.  Generate lexer and parser files:

    ```bash
    antlr4 Expr_Calculette-ex1.g4
    antlr4 Expr_Calculette-ex2.g4
    antlr4 Expr_Calculette-ex3.g4
    antlr4 Expr_Calculette-ex4.g4
    ```

3.  Compile generated Java files:

    ```bash
    javac *.java
    ```

4.  Run tests using:

    ```bash
    grun Expr_Calculette start -gui
    ```

---

## 4. Project Structure

    .
    ├── Expr_Calculette-ex1.g4
    ├── Expr_Calculette-ex2.g4
    ├── Expr_Calculette-ex3.g4
    ├── Expr_Calculette-ex4.g4
    ├── *.java          # Generated ANTLR files
    ├── *.class         # Compiled Java files
    └── README.md

---

This TP demonstrated how grammars evolve from parsing expressions to
building interpreters capable of evaluating mixed arithmetic and boolean
programs.
