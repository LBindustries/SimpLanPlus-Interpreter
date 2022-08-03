package interpreter;

import interpreter.Nodes.ProgramNode;
import interpreter.Nodes.InstructionNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.assembly.AssemblyLexer;
import parser.assembly.AssemblyParser;

import java.io.FileInputStream;
import java.util.HashMap;

public class Interpreter {

    public ProgramNode code;
    public int MEMSIZE = 10000;
    public int pc = 0;

    public byte[] stack = new byte[MEMSIZE];
    public int sp = MEMSIZE;
    public int fp = MEMSIZE;
    public int a0, t1, t2, ra;

    HashMap<String, Integer> labels;

    public Interpreter(String path) throws Exception{
        labels = new HashMap<>();
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



    private int fromByteArray(byte[] bytes, int start) {
        return ((bytes[start] & 0xFF) << 24) |
                ((bytes[start + 1] & 0xFF) << 16) |
                ((bytes[start + 2] & 0xFF) << 8 ) |
                ((bytes[start + 3] & 0xFF) << 0 );
    }

    private byte[] toByteArray(int value) {
        return new byte[] {
                (byte)(value >> 24),
                (byte)(value >> 16),
                (byte)(value >> 8),
                (byte)value };
    }

    public void runVM(){
        while(pc<code.getProgSize()){
            ASMNode instruction = code.getInstruction(pc++);
            InstructionNode ist = (InstructionNode) instruction;

            System.out.println(ist.toPrint());
        }

        pc = 0;

        System.out.println("\n--------------------\n");

        while(pc<code.getProgSize()){
            ASMNode instruction = code.getInstruction(pc++);
            InstructionNode ist = (InstructionNode) instruction;

            switch(ist.getOpcode()){
                case "push","pop","top","li","mov","lw","sw","lb","sb","add","addi","sub","subi","mult","multi","div","divi","lt","lte","gt","gte","eq","neq","and","or","not","neg","print","jal","jr","beq","halt": break;
                default:
                    if(ist.getClassname().contains("Label")) {
                        pc -= 1;
                        labels.put(ist.getOpcode(), pc);
                        code.delIst(pc);
                        System.out.println(ist.getOpcode() + " = " + pc);
                    }
            }
        }
        System.out.println("\n--------------------\n");
        pc = 0;
        while(pc<code.getProgSize()){
            ASMNode instruction = code.getInstruction(pc++);
            InstructionNode ist = (InstructionNode) instruction;

            System.out.println(ist.toPrint());
        }
    }

}