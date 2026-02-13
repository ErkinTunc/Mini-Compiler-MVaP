# TP8 -- Functions and MVaP Code Generation

## 1. What Was Implemented

This stage extends the Rationnel language with user-defined functions
and full call semantics while keeping MVaP as the target virtual
machine.

### Function Support

The language now supports:

- Function declarations with return types:
  - entier
  - booleen
  - rationnel
- Parameter lists (one or more parameters)
- Local variables inside functions
- Mandatory `retourner value` instruction
- No nested function definitions

### Function Calls

Functions can be:

- Called inside expressions
- Used in arithmetic or boolean contexts
- Recursive (e.g., factorial, fibonacci)

The compiler enforces:

- Correct number of arguments
- Correct argument types
- Correct return type usage in expressions
- Mandatory return instruction in each function

### Code Generation

- Implemented CALL / RETURN mechanism for MVaP
- Managed stack frames and activation records
- Supported recursive function calls
- Ensured proper stack cleanup after function execution

---

## 2. What Was Learned

- Designing activation records for stack-based machines
- Implementing call-by-value parameter passing
- Type checking across function boundaries
- Managing recursion at machine level
- Structuring a compiler with multi-phase logic
- Maintaining separation between grammar, tests, and generated code

---

## 3. How to Run the Project

### Step 1 -- Generate ANTLR Files

```bash
antlr4 Functions.g4
antlr4 Rationnel.g4
```

### Step 2 -- Compile Java Files

```bash
javac *.java
```

### Step 3 -- Run Test Program

```bash
grun Rationnel start < tests/test_functions.rat
```

### Step 4 -- Execute Generated MVaP Code

Generated `.mvap` files (e.g., `code.mvap`, `ex1.mvap`) can be assembled
and executed using the MVaP assembler and simulator.

---

## 4. Project Structure

    project-root/
    │
    ├── Functions.g4
    ├── Rationnel.g4
    │
    ├── tests/
    │   └── test_functions.rat
    │
    ├── code.mvap
    ├── ex1.mvap
    │
    ├── tp8.pdf
    │
    └── README.md

---

## 5. Notes

- `.mvap` files are generated machine code outputs.
- For clean repository management, generated files should ideally be
  moved to a `build/` directory and excluded from version control.
- Only grammar files, test programs, and documentation should be
  tracked long-term.

---

This TP completes the transition from structured control flow to full
function support with proper stack-based execution on the MVaP virtual
machine.
