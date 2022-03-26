package ast.ExpNodes;

import ast.Node;

public class BaseExpNode extends ExpNode {
    public BaseExpNode(Node exp) {
        super(exp);
    }

    @Override
    public String toPrint(String indent) {
        return indent+"BaseExpNode"+this.exp.toPrint(indent+"\t")+"\n";
    }
}
