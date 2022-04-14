package compiler;

import ast.Node;
import ast.SimpLanPlusVisitorImpl;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;
import util.Environment;
import util.SemanticError;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        String fileName = "prova.simplan";
        if(args.length>0){
            fileName = args[0];
        }
        FileInputStream is;
        try {
            is = new FileInputStream(fileName);
        } catch (Exception e){
            System.out.println("Something went wrong while accessing the file. Please check the filename.");
            return;
        }
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

        System.out.println("Parsing...");
        Node ast = visitor.visit(parser.program());
        if(handler.err_list.size() != 0){
            System.out.println(handler);
            handler.dumpToFile(fileName+".log");
            return;
        }
        System.out.println("Parse completed without issues!");
        System.out.println(ast.toPrint(""));
        System.out.println("Checking for semantic errors...");
        // Start Semantic analysis
        Environment env = new Environment();
        ArrayList<SemanticError> err = ast.checkSemantics(env);
        if(err!=null && err.size()>0){
            for(SemanticError e: err){
                System.out.println(e);
            }
            return;
        }
        System.out.println("Environment is good!");
        System.out.println("Program is valid.");
    }
}