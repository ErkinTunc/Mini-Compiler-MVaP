# TP5 -- MVaP Code Generation for Rational and Boolean Expressions

## 1. What Was Implemented

This practical session focused on generating executable code for the
MVaP virtual machine from expressions involving integers, rational
numbers, and boolean values.

### MVaP Code Generation

- Implemented code generation targeting the MVaP virtual machine.
- Generated programs always terminate with a `HALT` instruction.
- Ensured generated programs correctly manage stack behavior and
  intermediate storage.

### Rational Number Support

- Implemented rational numbers represented as:

  numerator / denominator

- Supported rational arithmetic operations:
  - Addition
  - Subtraction
  - Multiplication
  - Division

- Implemented exponentiation using the operator `**`, where the
  exponent is an integer.

- Enabled extraction of numerator and denominator using dedicated
  operations.

### Expression Handling

The language supports:

- Rational expressions with standard operators:
  - `+`, `-`, `*`, `/`
  - Unary and binary subtraction
- Proper operator precedence and parenthesis handling.
- Automatic interpretation of integers as rational numbers (e.g., `3`
  becomes `3/1`).

### Boolean Expressions

- Implemented boolean operators:
  - `and`
  - `or`
  - `not`
- Enforced operator precedence: not \> and \> or.
- Implemented lazy (short-circuit) evaluation in generated MVaP code.

### Comparisons

- Supported comparisons between rational expressions:
  - `<`, `<=`, `>`, `>=`, `==`, `<>`
- Comparison results produce boolean values.

### Input Instruction

- Implemented input operation allowing expressions to read values at
  runtime.
- Behavior depends on context:
  - Reads integers when required.
  - Reads rationals as two integers.
  - Reads booleans as integers (0 = false, positive = true).

### Display Instruction

- Implemented instruction:

  Afficher(expr);

- Output depends on expression type:
  - Integers printed directly.
  - Booleans printed as 1 (true) or 0 (false).
  - Rationals printed as numerator followed by denominator.

### Implementation Grammars

The project includes several grammar versions:

- `Rationnel.g4` -- main grammar implementing the language.
- `Rationnel-ex2.g4` -- intermediate or extended version used for
  development.
- `RationnelK.g4` -- grammar version used for testing and
  experimentation during implementation.

These grammars were used incrementally during development and validation
of code generation.

---

## 2. What Was Learned

### Code Generation Concepts

- Translating high-level expressions into virtual machine
  instructions.
- Managing stack operations and temporary storage.
- Ensuring correct program termination.

### Rational Arithmetic Implementation

- Representing rational values using numerator/denominator pairs.
- Implementing arithmetic operations at machine level.

### Lazy Evaluation

- Implementing short-circuit boolean evaluation in generated code.
- Avoiding unnecessary execution branches.

### Compiler Construction Workflow

- Incremental grammar development.
- Testing grammars before integrating code generation.
- Linking parsing, semantic processing, and machine code output.

### Virtual Machine Programming

- Understanding execution behavior of stack machines.
- Generating correct instruction sequences for evaluation.

---

## 3. How to Run the Project

1.  Install ANTLR4 and ensure `antlr4` and `grun` are available.

2.  Install the MVaP simulator and assembler.

3.  Generate lexer and parser files:

    ```bash
    antlr4 Rationnel.g4
    antlr4 Rationnel-ex2.g4
    antlr4 RationnelK.g4
    ```

4.  Compile generated Java files:

    ```bash
    javac *.java
    ```

5.  Run the compiler to generate MVaP code and assemble it using the
    MVaP tools.

6.  Execute the assembled program using the MVaP simulator.

---

## 4. Project Structure

    .
    ├── Rationnel.g4
    ├── Rationnel-ex2.g4
    ├── RationnelK.g4        # Used for testing and implementation
    ├── *.java               # Generated ANTLR files
    ├── *.class              # Compiled Java files
    └── README.md

---

This TP completes the transition from expression parsing to full code
generation targeting a virtual machine, enabling execution of rational
and boolean programs compiled from ANTLR grammars.
