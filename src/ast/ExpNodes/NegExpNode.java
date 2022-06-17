package ast.ExpNodes;

import ast.Types.IntTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;

public class NegExpNode extends BaseExpNode{
    public NegExpNode(Node exp) {
        super(exp);
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        if(! (exp.typeCheck(env).getClass().equals(IntTypeNode.class))) {
            System.out.println("No integer in neg");
            System.exit(0);
        }
        // TODO: Make typecheck functions carry arraylist of errors.
        return new IntTypeNode();
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"negExpNode"+this.exp.toPrint(indent+" ");
    }
}
