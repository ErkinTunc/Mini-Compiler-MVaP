# Installing MVaP

This document describes how to install the MVaP virtual machine
simulator (version 3.2).

Prerequisite: ANTLR must already be installed and the `antlr4` command
must be available in your terminal environment.

See: `installAntlr.md`

---

## A) Installation of the MVaP Simulator

### 1. Download the Sources

Download the archive:

    sources-MVaP-3.2.zip

Unzip the archive.

---

### 2. Open a Terminal

Navigate to the extracted directory:

```bash
cd sources-MVaP-3.2
```

---

### 3. Compile the Simulator

Run the following commands:

```bash
antlr4 MVaP.g4
javac *.java
```

---

### 4. Modify the MANIFEST File

Open the file:

    META-INF/MANIFEST.MF

Locate the `Class-Path` line and specify the full path to your ANTLR JAR
file.

If your ANTLR file is located at:

    $HOME/bin/antlr-4.9.2-complete.jar

Modify the line as follows:

    Class-Path: $HOME/bin/antlr-4.9.2-complete.jar

Do not use quotation marks.

---

### 5. Create the MVaP JAR File

From inside `sources-MVaP-3.2`, run:

```bash
jar cfm MVaP.jar META-INF/MANIFEST.MF *.class
```

This creates the file:

    MVaP.jar

---

### 6. Move the JAR File

Move it to your local bin directory:

```bash
mv MVaP.jar $HOME/bin
```

---

### 7. Update CLASSPATH

Add MVaP to your `CLASSPATH`. For example, in `~/.bashrc`:

```bash
export CLASSPATH=.:$HOME/bin/MVaP.jar:$HOME/bin/antlr-4.9.2-complete.jar:$CLASSPATH
```

Reload the configuration:

```bash
source ~/.bashrc
```

---

## B) Using the MVaP Assembler and Simulator

### 1. Assemble MVaP Code

For a source file containing only MVaP instructions (example:
`add.mvap`):

```bash
java -cp $CLASSPATH MVaPAssembler add.mvap
```

This generates:

    add.mvap.cbap

which is the executable bytecode.

---

### 2. Execute the Program

```bash
java -jar $HOME/bin/MVaP.jar add.mvap.cbap
```

---

### Debug Mode

```bash
java -jar $HOME/bin/MVaP.jar -d add.mvap.cbap
```

---

## C) Optional: Define Aliases

To simplify usage, you may define aliases in your `~/.bashrc`:

```bash
alias mvapasm='java -cp $CLASSPATH MVaPAssembler'
alias mvaprun='java -jar $HOME/bin/MVaP.jar'
```

You can then use:

```bash
mvapasm add.mvap
mvaprun add.mvap.cbap
```
