package compiler;

import ast.Node;
import ast.SimpLanPlusVisitorImpl;
import interpreter.Interpreter;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;
import util.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    // This is the interpreter runner.
    public static void main(String[] args) throws Exception {
        System.out.println("[L] SimpLanPlus Compiler started.");
        String filename = "prova.simplan";
        int i = 0;
        int memsize = 100000;
        ArrayList<Integer> breakpoints = new ArrayList<Integer>();
        // Try to load up interpreter args
        try {
            while (args.length - i > 0) {
                String command = args[i];
                switch (command) {
                    case "-h":
                        // Help menu
                        System.out.println("[L] Syntax: java -jar slp.jar [path_to_program] [-h|-m|-d [lines]].");
                        System.exit(0);
                    case "-m":
                        // Custom stack size
                        memsize = Integer.parseInt(args[i + 1]);
                        i = i + 2;
                        break;
                    case "-d":
                        // Debugger setup
                        int j = 1;
                        Boolean fuse = false;
                        while (!fuse) {
                            try {
                                int line = Integer.parseInt(args[i + j]);
                                breakpoints.add(line);
                                j++;
                            } catch (Exception e) {
                                fuse = true;
                            }
                        }
                        i = i + j;
                        break;
                    default:
                        // Filename
                        if (filename.equals("prova.simplan")) {
                            filename = command;
                        } else {
                            System.out.println("[!] Unknown parameter " + command + ", ignoring...");
                        }
                        i++;
                }
            }
        } catch (Exception e){
            // Fallback if loading goes wrokg
            System.out.println("[!] Error in parameter syntax.");
            System.exit(1);
        }
        FileInputStream is;
        try {
            // Open source file
            is = new FileInputStream(filename);
        } catch (Exception e) {
            // Error in file opening handling
            System.out.println("[!] Something went wrong while accessing the file. Please check the filename.");
            System.exit(1);
            return;
        }
        // Caching functionality

        // Compute sourcefile checksum
        AsmChecksum source = new AsmChecksum(Files.readString(Paths.get(filename)));
        BufferedReader asm_file;
        Boolean compile = true;
        try {
            // Try to read asm_file
            asm_file = new BufferedReader(new FileReader(filename + ".asm"));
            // Force recompile if debug mode is active
            if (breakpoints.size() != 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            asm_file = null;
        }
        if (asm_file != null) {
            // Try to read the checksum embedded in the first line of asm code
            try {
                String value = asm_file.readLine().substring(1);
                if (!value.equals("Program") && source.isEqual(Long.parseLong(value))) {
                    compile = false;
                    System.out.println("[L] An already compiled program has been found, and will be executed.");
                }
            } catch (Exception e) {
                System.out.println("[W] An already compiled program has been found, but its checksum was not verifiable.\n    Hence it will be re-compiled.");
            }
        }
        // Compiler at work
        if (compile) {
            // Create lexer and related listeners and support objects.
            ANTLRInputStream input = new ANTLRInputStream(is);
            SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
            SimpLanPlusErrorHandler handler = new SimpLanPlusErrorHandler();
            lexer.removeErrorListeners();
            lexer.addErrorListener(handler);
            // Create parser and related listener and support objects
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(handler);
            SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();
            // Start parsing
            System.out.println("[L] Parsing...");
            Node ast = visitor.visit(parser.program());
            if (handler.err_list.size() != 0) {
                // Error reporting.
                System.out.println(handler);
                handler.dumpToFile(filename + ".log");
                return;
            }
            System.out.println("[L] Parse completed without issues!");
            System.out.println("[L] Checking for semantic errors...");
            // Start Semantic analysis. Create new environment, semantic error arraylist etc.
            Environment env = new Environment();
            ArrayList<SemanticError> err = ast.checkSemantics(env);
            if (err != null && err.size() > 0) {
                // Write errors on disk
                BufferedWriter wr = new BufferedWriter(new FileWriter(filename + ".log"));
                for (SemanticError e : err) {
                    System.out.println(e);
                    wr.write(e.toString() + "\n");
                }
                wr.close();
                return;
            }
            System.out.println("[L] Environment is good!");
            try {
                // Attempt typechecking
                ast.typeCheck(env);
            } catch (TypeCheckException error) {
                // If TypeCheckException error occurs, dump error on disk and write it on the console.
                System.out.println(error.getMessage());
                BufferedWriter wr = new BufferedWriter(new FileWriter(filename + ".log"));
                wr.write(error.getMessage() + "\n");
                wr.close();
                System.exit(1);
            }

            System.out.println("[L] Program is valid.");
            if (breakpoints.size() > 0) {
                // Breakpoints setup
                System.out.println("[L] Setting up breakpoints...");
                ast.setupBreaks(breakpoints);
                System.out.println("[L] Breakpoints have been set up!");
            }
            // ASM Assembly command.
            System.out.println("[L] Assembling...");
            LabelGenerator labgen = new LabelGenerator();
            // ASM Header with checksum
            String asm = ";" + source.checksum.getValue() + "\n" + ast.codeGeneration(labgen, env);

            System.out.println("[L] Code ready for execution!");
            // Write asm on disk
            BufferedWriter wr = new BufferedWriter(new FileWriter(filename + ".asm"));
            wr.write(asm + "\n");
            wr.close();
        }
        if (Runtime.getRuntime().freeMemory() < memsize) {
            // Check if there's enough free memory available.
            System.out.println("[!] Not enough free system memory. Use -m argument to change allocated memory.");
            System.exit(1);
        }
        // Create new interpreter and let it run
        Interpreter interpreter = new Interpreter(filename + ".asm", memsize, breakpoints.size() > 0);
        interpreter.runVM();
    }
}