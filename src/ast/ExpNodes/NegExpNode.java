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
        if(! (exp.typeCheck(env).getType().equals("int"))) {
            System.out.println("No integer in neg");
            System.exit(0);
        }
        return new IntTypeNode();
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"negExpNode"+this.exp.toPrint(indent+" ");
    }
}
