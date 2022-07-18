package interpreter.Nodes;

import interpreter.ASMNode;

import java.util.ArrayList;

public class InstructionNode implements ASMNode {
    public String opcode, param1, param2, param3;
    public String classname;

    public InstructionNode(String opcode, String param1, String param2, String param3, String classname){
        this.opcode = opcode;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.classname = classname;
    }

    public InstructionNode(ArrayList<String> contents, String classname){
        switch (contents.size()){
            case 4:
                this.param3 = contents.get(3);
            case 3:
                this.param2 = contents.get(2);
            case 2:
                this.param1 = contents.get(1);
            case 1:
                this.opcode = contents.get(0);
                break;
            default:
                this.param1 = this.param2 = this.param3 = this.opcode = "";
        }
        this.classname = classname;

    }

    @Override
    public String toPrint() {
        return (this.opcode!=null ? (this.opcode):(""))+(this.param1!=null ? (" "+this.param1):(""))
                +(this.param2!=null ? (" "+this.param2):(""))+(this.param3!=null ? (" "+this.param3):(""));
    }
}
