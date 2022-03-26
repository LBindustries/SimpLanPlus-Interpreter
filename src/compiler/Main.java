package compiler;

import ast.Node;
import ast.SimpLanPlusVisitorImpl;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        String fileName = "prova.simplan";

        FileInputStream is = new FileInputStream(fileName);
        ANTLRInputStream input = new ANTLRInputStream(is);
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
        SimpLanPlusParserError handler = new SimpLanPlusParserError();

        lexer.addErrorListener(handler);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpLanPlusParser parser = new SimpLanPlusParser(tokens);

        parser.addErrorListener(handler);
        SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();

        Node ast = visitor.visit(parser.block());
        //System.out.println(ast.toPrint(""));
        if(handler.err_list.size() != 0){
            System.out.println(handler);
            handler.dumpToFile(fileName+".log");
        }
    }
}