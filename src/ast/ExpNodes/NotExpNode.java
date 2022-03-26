package ast.ExpNodes;

import ast.Node;

public class NotExpNode extends BaseExpNode{
    public NotExpNode(Node exp) {
        super(exp);
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"NotExpNode "+this.exp.toPrint(indent+" ");
    }
}
