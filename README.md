# SimpLanPlus-Interpreter

SimpLanPlus Interpreter developed for the "Interpreters and Compilers" exam @Unibo.

## Project structure

The package is divided in 4 different modules:

* ast, which contains all the node implementations that are used in the exploration of the Abstract Syntax Tree;
* compiler, which contains the runner - and someday the interpreter;
* parser, which contains the SimpLanPlus ANTLR grammar file and the auto-generated classes that allow the lexing and
  subsequent parsing of the program (the grammar was provided as part of the assignment);
* utils, which contains several utilities classes that allow the compiler to perform certain tasks.

## Project usage
The project shall not be used, under any circumstance, as a deliverable for the "I&C" exam by someone external
to the team. It can be used, however, as a reference for other projects, as long as it's not a carbon-copy.
