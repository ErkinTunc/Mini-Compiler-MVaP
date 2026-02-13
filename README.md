# Compiler Construction

## Project Overview

This repository contains a complete compiler construction project
developed in the _Compilation_ course (Licence Informatique, S5).

The objective is to design and implement a structured compiler using
**ANTLR4**, progressively extending a language from simple expressions
to a fully functional language supporting:

- Arithmetic, boolean, and rational expressions
- Semantic analysis and type checking
- Variables and scoped blocks
- Control flow (conditionals and loops)
- User-defined functions
- Stack-based code generation targeting the **MVaP virtual machine**

The project follows a classical compiler pipeline:

    Grammar → Parser → Semantic Analysis → Code Generation → Virtual Machine Execution

---

## Compiler Architecture

The project evolves through the following stages:

1. Lexical and syntactic analysis (ANTLR grammars)
2. Attributed grammars and interpretation
3. Semantic validation (type checking, scope management)
4. Stack-based code generation (MVaP)
5. Control flow compilation
6. Function calls and activation records

Target machine: **MVaP (version 3.2)**

---

## TP Modules

- [TP1 -- Introduction to ANTLR](./tp1/README.md)
- [TP2 -- Boolean Expressions & Evaluation](./tp2/README.md)
- [TP3 -- Expression Integration](./tp3/README.md)
- [TP4 -- Variables & Semantic Control](./tp4/README.md)
- [TP5 -- MVaP Code Generation](./tp5/README.md)
- [TP6 -- Extended Rational Features](./tp6/README.md)
- [TP7 -- Control Flow Compilation](./tp7/README.md)
- [TP8 -- Functions & Call Mechanism](./tp8/README.md)

Each TP folder contains:

- `.g4` grammar files
- `.rat` test programs
- Optional scripts
- A dedicated README

---

## Learning Outcomes

This project demonstrates:

- Formal language theory applied in practice
- Grammar ambiguity control
- Semantic analysis and type systems
- Stack machine architecture
- Activation record design
- Recursive function execution
- End-to-end compiler pipeline implementation

---

## Setup & Execution

### Environment Requirements

- Java (JDK 8+)
- ANTLR4
- MVaP Assembler and Simulator
- Bash (recommended)

### Installation Guides

- [Installing ANTLR](docs/setup/installAntlr.md)
- [Installing MVaP](docs/setup/installMVaP.md)

### Running the Compiler

- [How to Compile and Run](docs/setup/run.md)

---

## Documentation

Additional documentation and reference materials are available in the
`docs/` directory:

- Setup instructions (ANTLR & MVaP installation guides)
- Bash command memo
- MVaP instruction cheat sheet
- Official TP subject PDFs

These documents provide the theoretical background, machine instruction
reference, and environment setup required to run and understand the
compiler.

---

## Contributors

This project was developed as part of the _Compilation_ course.

**Contributors:**

- [Erkin Tunc BOYA](https://github.com/ErkinTunc)
- [Karam ELNASORY](https://github.com/KaramNS)

---

## Project Status

- TP1--TP6: Fully implemented
- TP7--TP8: Advanced control flow and function mechanism implemented
- Further optimizations possible (symbol table improvements, memory
  model refinement)
