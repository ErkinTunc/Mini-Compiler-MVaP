# TP6 -- Extended MVaP Code Generation and Rational Language Features

## 1. What Was Implemented

This practical session extends the previous work on MVaP code generation
and enriches the Rationnel language with new control structures and
numeric operations.

### Control Flow Programs in MVaP

- Implemented MVaP programs using:
  - Variable assignments
  - Input reading
  - While loops
  - Conditional execution (`if / else`)
- Programs demonstrate:
  - Iterative printing of values.
  - Conditional transformations such as the Collatz-like sequence
    example.
- Manual MVaP programs were written and tested to understand execution
  flow and stack behavior.

### Extension of the Rationnel Language

The Rationnel language was extended to support additional operations on
rational and integer values.

### Rational Number Operations

Added support for:

- Rational simplification:

  sim(r)

  Example:
  - sim(4/2) → 2/1
  - sim(2/8) → 1/4

- Nearest integer operation:

  \[r\]

  Produces the integer closest to rational r.

### Integer Operations

Added integer-only operations:

- Greatest Common Divisor:

  pgcd(a, b)

- Least Common Multiple:

  ppcm(a, b)

These operations are only valid on integer inputs and cannot receive
rational expressions.

### Expression Validation Rules

The language enforces:

- Integer-only operations must receive integer values.
- Arithmetic expressions producing rationals cannot be directly used
  where integers are required.
- Type correctness is preserved during parsing and code generation.

### Grammar and Testing Files

The project includes:

- `Delta.g4` -- grammar extension supporting the new language
  features.
- `Rationnel.g4` -- base grammar for rational language compilation.
- `Rationnel_K.g4` -- implementation and testing grammar version.
- `input.txt` -- example program inputs.
- `out.mvap` -- generated MVaP output program.

These files support experimentation, validation, and execution of the
compiler output.

---

## 2. What Was Learned

### Advanced Code Generation

- Writing low-level programs using MVaP instructions.
- Managing loops and conditional execution on a stack machine.
- Ensuring correct execution flow and stack consistency.

### Language Feature Extension

- Extending grammars without breaking previous functionality.
- Integrating mathematical operations into compiled languages.

### Type Constraints in Languages

- Enforcing integer-only and rational-only operations.
- Detecting invalid expressions during compilation.

### Rational Arithmetic Implementation

- Simplifying rationals using GCD computation.
- Handling conversions between rational and integer forms.

### Compiler Testing Workflow

- Using separate grammars and test files during development.
- Validating generated machine code using simulation tools.

---

## 3. How to Run the Project

1.  Install ANTLR4 and ensure `antlr4` and `grun` are available.

2.  Install the MVaP assembler and simulator.

3.  Generate lexer and parser files:

    ```bash
    antlr4 Delta.g4
    antlr4 Rationnel.g4
    antlr4 Rationnel_K.g4
    ```

4.  Compile generated Java files:

    ```bash
    javac *.java
    ```

5.  Run the compiler with an input program:

    ```bash
    grun Delta start < input.txt
    ```

6.  Assemble and execute the produced MVaP code:

    ```bash
    mvapAssembler out.mvap
    mvapSimulator out.mvap
    ```

---

## 4. Project Structure

    .
    ├── Delta.g4
    ├── Rationnel.g4
    ├── Rationnel_K.g4        # Used for testing and implementation
    ├── input.txt             # Example input programs
    ├── out.mvap              # Generated MVaP code
    ├── *.java                # Generated ANTLR files
    ├── *.class               # Compiled Java files
    └── README.md

---

This TP strengthens the compiler by introducing advanced machine-level
control flow and extending the Rationnel language with new arithmetic
and integer operations required for realistic program execution.
