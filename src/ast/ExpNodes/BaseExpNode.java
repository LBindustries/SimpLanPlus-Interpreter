package ast.ExpNodes;

import ast.Node;

public class BaseExpNode extends ExpNode {
    public BaseExpNode(Node exp, int line) {
        super(exp, line);
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"BaseExpNode"+this.exp.toPrint(indent+" ");
    }
}
