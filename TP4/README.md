# TP4 -- Variables and Semantic Control with ANTLR

## 1. What Was Implemented

This practical session extended the previous expression interpreter by
adding variables, semantic checks, input handling, and block structures.

### Variable Declarations

-   Added support for variable declarations with two types:

    -   Integer variables
    -   Boolean variables

-   Allowed multiple declarations in a single instruction:

    int x, y, z; bool a, b;

-   Variables are stored for later reuse in expressions.

### Assignment Instructions

-   Added assignment instructions:

    x = expr

-   Expression values are computed and stored in the assigned variable.

-   Variables can then be reused in arithmetic or boolean expressions.

### Variable Usage in Expressions

-   Variables can now appear in:
    -   Arithmetic expressions
    -   Boolean expressions
    -   Display instructions
-   Their interpretation corresponds to their stored value.

### Semantic Checks

The compiler now enforces several semantic constraints:

-   Variables must be declared before use.
-   Variables must have a value before being used in expressions.
-   Boolean variables can only appear in boolean expressions.
-   Integer variables can only appear in arithmetic expressions.

### Increment and Decrement Operators

-   Added support for integer variable updates:

    ++x, --x, x++, x--

-   Implemented both pre and post increment/decrement behavior.

### Input Instruction

-   Added instruction allowing runtime input inside expressions.
-   When used in arithmetic expressions, it reads integers.
-   When used in boolean expressions, it reads boolean values.

### Block Instructions and Local Scope

-   Added block structure using braces:

    { ... }

-   Blocks define local variable scopes.

-   Rules implemented:

    -   Variables declared in a block are only visible inside it and its
        sub-blocks.
    -   Changes inside sub-blocks do not permanently modify outer
        variables.
    -   Local variables can hide global variables with the same name.
    -   Different blocks may reuse the same variable names with
        different types.

------------------------------------------------------------------------

## 2. What Was Learned

### Semantic Analysis

-   Separation between syntax correctness and semantic correctness.
-   Detection of undeclared variables and incorrect usage.
-   Type checking inside expressions.

### Variable Scope Management

-   Handling variable visibility using block scopes.
-   Managing local and global variables.
-   Understanding variable hiding and restoration after blocks.

### Interpreter Extension

-   Integrating variable storage into expression evaluation.
-   Supporting dynamic updates of program state.

### Language Feature Expansion

-   Incrementally extending grammars to support new language features.
-   Combining parsing, evaluation, and semantic checks.

### Runtime Interaction

-   Handling user input inside expression evaluation.
-   Supporting interactive execution.

------------------------------------------------------------------------

## 3. How to Run the Project

1.  Install ANTLR4 and ensure `antlr4` and `grun` are available.

2.  Generate lexer and parser files:

    ``` bash
    antlr4 Expr_Calculette.g4
    ```

3.  Compile generated Java files:

    ``` bash
    javac *.java
    ```

4.  Run the interpreter using:

    ``` bash
    grun Expr_Calculette start -gui
    ```

    or standard execution mode for program input.

------------------------------------------------------------------------

## 4. Project Structure

    .
    ├── Expr_Calculette.g4
    ├── *.java          # Generated ANTLR files
    ├── *.class         # Compiled Java files
    └── README.md

------------------------------------------------------------------------

This TP introduced variables, semantic validation, input handling, and
scoped execution, moving the project closer to a real programming
language interpreter.
