package ast.ExpNodes;

import ast.Types.BoolTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;

public class NotExpNode extends BaseExpNode{
    public NotExpNode(Node exp) {
        super(exp);
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        if(! (exp.typeCheck(env).getClass().equals(BoolTypeNode.class))) {
            System.out.println("No bool in not");
            System.exit(0);
        }
        return new BoolTypeNode();
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"NotExpNode "+this.exp.toPrint(indent+" ");
    }
}
