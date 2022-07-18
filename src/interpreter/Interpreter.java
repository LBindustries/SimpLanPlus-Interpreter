package interpreter;

import interpreter.Nodes.ProgramNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.assembly.AssemblyLexer;
import parser.assembly.AssemblyParser;

import java.io.FileInputStream;

public class Interpreter {

    public ProgramNode code;
    public int MEMSIZE = 10000;
    public int pc = 0;


    public Interpreter(String path) throws Exception{
        FileInputStream is;
        try {
            is = new FileInputStream(path);
        } catch (Exception e){
            System.out.println("Something went wrong while accessing the ASM file. Please check the filename.");
            return;
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
        AssemblyLexer lexer = new AssemblyLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AssemblyParser parser = new AssemblyParser(tokens);
        AssemblyVisitorImpl visitor = new AssemblyVisitorImpl();
        this.code = (ProgramNode) visitor.visit(parser.program());
    }

    public void runVM(){
        while(pc<code.getProgSize()){
            ASMNode instruction = code.getInstruction(pc++);
            System.out.println(instruction.toPrint());
        }
    }

}
