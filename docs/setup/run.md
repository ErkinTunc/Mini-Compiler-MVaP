# How to Compile and Run the Compiler

This document explains how to compile a grammar and execute a test
program using the Rationnel compiler and the MVaP virtual machine.

Prerequisites:

- ANTLR must be installed and configured.
- MVaP must be installed and added to the CLASSPATH.
- Java (JDK 8+) must be available.

See: - [installAntlr.md](/docs/setup/installAntlr.md) - [installMVaP.md](/docs/setup/installMVaP.md)

---

## 1. Generate the Parser

From inside the TP directory containing the grammar file (for example
`Rationnel.g4`), run:

```bash
antlr4 Rationnel.g4
```

This generates the lexer and parser Java source files.

---

## 2. Compile the Generated Java Files

```bash
javac *.java
```

This produces `.class` files for the parser and related components.

---

## 3. Run the Compiler on a Test Program

Assuming a test program is located in:

    tests/test_program.rat

Run:

```bash
grun Rationnel start < tests/test_program.rat
```

This executes the compiler and generates the corresponding MVaP code
(typically `out.mvap`).

---

## 4. Assemble the Generated MVaP Code

```bash
java -cp $CLASSPATH MVaPAssembler out.mvap
```

This produces:

    out.mvap.cbap

which is the executable bytecode.

---

## 5. Execute the Program

```bash
java -jar $HOME/bin/MVaP.jar out.mvap.cbap
```

---

## Debug Mode

To execute in debug mode:

```bash
java -jar $HOME/bin/MVaP.jar -d out.mvap.cbap
```

This displays step-by-step execution information.

---

## Typical Workflow Summary

1. antlr4 Rationnel.g4\
2. javac \*.java\
3. grun Rationnel start \< tests/test_program.rat\
4. java -cp \$CLASSPATH MVaPAssembler out.mvap\
5. java -jar \$HOME/bin/MVaP.jar out.mvap.cbap

This sequence compiles, assembles, and executes a program using the
compiler.
