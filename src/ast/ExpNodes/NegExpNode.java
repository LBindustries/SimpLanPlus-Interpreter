package ast.ExpNodes;

import ast.Node;

public class NegExpNode extends BaseExpNode{
    public NegExpNode(Node exp) {
        super(exp);
    }

    @Override
    public String toPrint(String indent) {
        return indent+"negExpNode"+this.exp.toPrint(indent+"\t")+"\n";
    }
}
