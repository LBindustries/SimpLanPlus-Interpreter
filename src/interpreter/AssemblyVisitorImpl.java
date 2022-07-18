package interpreter;

import interpreter.Nodes.InstructionNode;
import interpreter.Nodes.ProgramNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import parser.assembly.AssemblyBaseVisitor;
import interpreter.ASMNode;
import parser.assembly.AssemblyParser;

import java.util.ArrayList;

public class AssemblyVisitorImpl extends AssemblyBaseVisitor<ASMNode> {
    @Override public ASMNode visitProgram(AssemblyParser.ProgramContext ctx){
        ProgramNode program = new ProgramNode();
        for(AssemblyParser.InstructionContext ins: ctx.instruction()){
            ArrayList<String> contents = new ArrayList<>();
            for(int i = 0; i<ins.children.get(0).getChildCount(); i++){
                contents.add(ins.children.get(0).getChild(i).toString());
            }
            if(contents.size()>4){
                StringBuilder coll = new StringBuilder(contents.get(2));
                for(int i=3; i<contents.size(); i++){
                    coll.append(contents.get(i));
                }
                contents.set(2, coll.toString());
                contents.subList(3, contents.size()).clear();
            }
            program.appendCode(new InstructionNode(contents, ins.children.get(0).getClass().toString()));
        }
        return program;
    }
}
