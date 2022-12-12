package ast.Types;

import java.util.ArrayList;

public class FunctionTypeNode extends TypeNode{
    // The return type of a function, made up of its return type and the type of its args.
    private ArrayList<ArgTypeNode> args;

    public FunctionTypeNode(String type, ArrayList<ArgTypeNode> args) {
        super(type);
        this.args = args;
    }

    public ArrayList<ArgTypeNode> getArgs() {
        return args;
    }
}
