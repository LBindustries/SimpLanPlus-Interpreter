package ast.Types;

import java.util.ArrayList;

public class FunctionTypeNode extends TypeNode{

    private ArrayList<ArgTypeNode> args;

    public FunctionTypeNode(String type, ArrayList<ArgTypeNode> args) {
        super(type);
        this.args = args;
    }

    public ArrayList<ArgTypeNode> getArgs() {
        return args;
    }
}
