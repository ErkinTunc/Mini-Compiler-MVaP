# TP7 -- Control Flow and Code Generation (MVaP)

## 1. What Was Implemented

This stage extends the Rationnel compiler with control flow structures
and prepares the project for full program compilation.

The grammar supports:

- Global variable declarations
- Blocks using `{ ... }`
- Conditional instructions
- Loop instructions
- Arithmetic and boolean expressions
- Code generation targeting the MVaP virtual machine

The compiler generates valid MVaP programs that terminate correctly and
respect stack discipline.

Note: TP7 and TP8 are still in progress. The current version focuses on
control structures and program-level compilation.

---

## 2. What Was Learned

- Structuring a compiler project beyond single-expression evaluation.
- Managing control flow in a stack-based virtual machine.
- Generating correct jump instructions for:
  - Conditionals
  - Loops
- Organizing grammar rules to distinguish:
  - Declarations
  - Instructions
- Maintaining clean separation between source files, tests, and
  generated artifacts.

---

## 3. How to Run the Project

### Step 1 -- Generate ANTLR Files

```bash
antlr4 src/Rationnel.g4
```

### Step 2 -- Compile Java Files

```bash
javac *.java
```

### Step 3 -- Run Tests

```bash
./scripts/run_tests.sh
```

Test programs are located in the `tests/` directory.

### Step 4 -- Execute Generated MVaP Code

Generated `.mvap` files are placed in the `build/` directory and can be
executed using the MVaP assembler and simulator.

---

## 4. Project Structure

    project-root/
    │
    ├── src/
    │   └── Rationnel.g4
    ├── drafts/                 # Development / experimental versions
    ├── tests/                  # .rat test programs
    ├── scripts/
    │   └── run_tests.sh
    ├── build/                  # Generated files (ignored in git)
    │── tp7.pdf
    ├── README.md

---

## 5. Repository Policy

- Only source files, tests, scripts, and documentation are tracked.
- Generated files (`*.java`, `*.class`, `*.mvap`, `*.cbap`) must not
  be committed.
- The `build/` directory is reserved for compiler outputs.

---

This TP represents the transition from expression compilation to full
program compilation with structured control flow.
