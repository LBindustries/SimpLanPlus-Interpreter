package interpreter.Nodes;

import interpreter.ASMNode;

import java.util.ArrayList;

public class ProgramNode implements ASMNode {

    private ArrayList<ASMNode> code;

    public ProgramNode(){
        this.code = new ArrayList<>();
    }

    public void appendCode(ASMNode line){
        this.code.add(line);
    }

    public int getProgSize(){
        return code.size();
    }

    public ASMNode getInstruction(int pc){
        return code.get(pc);
    }

    public void delIst(int pc) {
        code.remove(pc);
    }

    @Override
    public String toPrint() {
        return null;
    }
}
