#!/bin/bash
# Compile grammar (ensure it's up to date)
# antlr4 -o ./build/ Rationnel.g4 && javac ./build/*.java

export CLASSPATH=".:./build:$CLASSPATH"

# Create output dir
OUTPUT_DIR="./build/MVaP_machine"
mkdir -p "$OUTPUT_DIR"

# Run tests
for file in tests/*.rat; do
    filename=$(basename -- "$file")
    name="${filename%.*}"
    echo "Running test: $name"
    
    # Run parsing and save output (MVaP code) to file
    # We pipe input into TestRig. TestRig output (stdout) goes to file.
    # Note: Our grammar prints code to stdout.
    java org.antlr.v4.gui.TestRig Rationnel start < "$file" > "$OUTPUT_DIR/${name}.mvap" 2>&1
    
    echo "Generated $OUTPUT_DIR/${name}.mvap"
done
