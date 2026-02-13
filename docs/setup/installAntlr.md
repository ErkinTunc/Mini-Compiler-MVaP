# Installing ANTLR

This document describes how to install ANTLR (version 4.9.2) in a
persistent way so that it does not need to be re-downloaded for each
practical session.

Before starting, make sure you know the location of your permanent home
directory.\
If needed, refer to your UNIX/Shell course documentation.\
You may also consult `memoshell.pdf` for a reminder of basic shell
commands.

------------------------------------------------------------------------

## A) Installation of ANTLR

### 1. Create a Local Bin Directory

Open a terminal and create a `bin` directory in your home directory:

``` bash
mkdir ~/bin
```

------------------------------------------------------------------------

### 2. Download ANTLR

Download the file:

    antlr-4.9.2-complete.jar

Place it inside:

    ~/bin

------------------------------------------------------------------------

## B) Configure the Environment

The ANTLR commands are long. It is recommended to define aliases.

You have two options.

------------------------------------------------------------------------

### Option 1 --- Temporary Configuration (per session)

Each time you open a terminal, run:

``` bash
export CLASSPATH=.:~/bin/antlr-4.9.2-complete.jar:$CLASSPATH
alias antlr4='java -Xmx500M -cp "~/bin/antlr-4.9.2-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java -Xmx500M -cp "~/bin/antlr-4.9.2-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
```

This makes `antlr4` and `grun` available for the current session only.

------------------------------------------------------------------------

### Option 2 --- Permanent Configuration

To avoid repeating the commands at every session, modify your shell
configuration file.

If you are using `bash`, edit:

    ~/.bashrc

Add the following lines:

``` bash
export CLASSPATH=.:~/bin/antlr-4.9.2-complete.jar:$CLASSPATH
alias antlr4='java -Xmx500M -cp "~/bin/antlr-4.9.2-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java -Xmx500M -cp "~/bin/antlr-4.9.2-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
```

Save the file.

If a terminal was already open, reload the configuration:

``` bash
source ~/.bashrc
```

After this, the commands `antlr4` and `grun` will be available
automatically.

------------------------------------------------------------------------

## C) Basic Usage of ANTLR

To compile a grammar file named `Fic.g4`, run:

``` bash
antlr4 Fic.g4
javac Fic*.java
```

This generates and compiles the lexer and parser corresponding to the
grammar.
