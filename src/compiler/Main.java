package compiler;

import ast.Node;
import ast.SimpLanPlusVisitorImpl;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        String fileName = "prova.simplan";

        FileInputStream is = new FileInputStream(fileName);
        ANTLRInputStream input = new ANTLRInputStream(is);
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
        SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();
        Node ast = visitor.visit(parser.block());
    }
}