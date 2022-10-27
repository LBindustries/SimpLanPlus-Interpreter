package ast.ExpNodes;

import ast.Node;

import java.util.ArrayList;

public class BaseExpNode extends ExpNode {
    public BaseExpNode(Node exp, int line) {
        super(exp, line);
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"BaseExpNode"+this.exp.toPrint(indent+" ");
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        return;
    }
}
