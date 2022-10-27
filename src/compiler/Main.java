package compiler;

import ast.Node;
import ast.SimpLanPlusVisitorImpl;
import interpreter.Interpreter;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;
import util.AsmChecksum;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO: Ricordarsi di mettere tutti i messaggi di log dentro il file .log
        System.out.println("[L] SimpLanPlus Compiler started.");
        String filename = "prova.simplan";
        int i = 0;
        int memsize = 100000;
        ArrayList<Integer> breakpoints = new ArrayList<Integer>();
        while(args.length-i>0){
            String command = args[i];
            switch (command){
                case "-m":
                    memsize = Integer.parseInt(args[i+1]);
                    i=i+2;
                    break;
                case "-d":
                    int j = 1;
                    Boolean fuse = false;
                    while(!fuse){
                        try{
                            int line = Integer.parseInt(args[i+j]);
                            breakpoints.add(line);
                            j++;
                        }
                        catch(Exception e){
                            fuse = true;
                        }
                    }
                    i=i+j;
                    break;
                default:
                    if(filename.equals("prova.simplan")){
                        filename=command;
                    }
                    else{
                        System.out.println("[!] Unknown parameter "+ command+", ignoring...");
                    }
                    i++;
            }
        }
        FileInputStream is;
        try {
            is = new FileInputStream(filename);
        } catch (Exception e){
            System.out.println("[!] Something went wrong while accessing the file. Please check the filename.");
            System.exit(1);
            return;
        }

        AsmChecksum source = new AsmChecksum(Files.readString(Paths.get(filename)));
        BufferedReader asm_file;
        Boolean compile = true;
        try{
            asm_file = new BufferedReader(new FileReader(filename +".asm"));
            if(breakpoints.size()!=0){
                throw new Exception();
            }
        } catch (Exception e){
            asm_file = null;
        }
        if(asm_file!=null){
            String value = asm_file.readLine().substring(1);
            if(!value.equals("Program") && source.isEqual(Long.parseLong(value))){
                compile = false;
                System.out.println("[L] An already compiled program has been found, and will be executed.");
            }

        }
        if(compile) {
            ANTLRInputStream input = new ANTLRInputStream(is);
            SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
            SimpLanPlusErrorHandler handler = new SimpLanPlusErrorHandler();
            lexer.removeErrorListeners();
            lexer.addErrorListener(handler);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(handler);
            SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();

            System.out.println("[L] Parsing...");
            Node ast = visitor.visit(parser.program());
            if (handler.err_list.size() != 0) {
                System.out.println(handler);
                handler.dumpToFile(filename + ".log");
                return;
            }
            System.out.println("[L] Parse completed without issues!");
            System.out.println("[L] Checking for semantic errors...");
            // Start Semantic analysis
            Environment env = new Environment();
            ArrayList<SemanticError> err = ast.checkSemantics(env);
            if (err != null && err.size() > 0) {
                BufferedWriter wr = new BufferedWriter(new FileWriter(filename + ".log"));
                for (SemanticError e : err) {
                    System.out.println(e);
                    wr.write(e.toString() + "\n");
                }
                wr.close();
                return;
            }
            System.out.println("[L] Environment is good!");
            ast.typeCheck(env);
            System.out.println("[L] Program is valid.");
            if(breakpoints.size()>0){
                System.out.println("[L] Setting up breakpoints...");
                ast.setupBreaks(breakpoints);
                System.out.println("[L] Breakpoints have been set up!");
            }
            System.out.println("[L] Assembling...");
            LabelGenerator labgen = new LabelGenerator();
            String asm = ";" + source.checksum.getValue() + "\n" + ast.codeGeneration(labgen, env);

            System.out.println("[L] Code ready for execution!");

            BufferedWriter wr = new BufferedWriter(new FileWriter(filename + ".asm"));
            wr.write(asm + "\n");
            wr.close();
        }
        if(Runtime.getRuntime().freeMemory()<memsize){
            System.out.println("[!] Not enough free system memory. Use -m argument to change allocated memory.");
            System.exit(1);
        }
        Interpreter interpreter = new Interpreter(filename+".asm", memsize, breakpoints.size()>0);
        interpreter.runVM();
    }
}