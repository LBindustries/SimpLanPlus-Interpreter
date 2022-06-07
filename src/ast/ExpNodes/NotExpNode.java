package ast.ExpNodes;

import ast.BoolTypeNode;
import ast.Node;

public class NotExpNode extends BaseExpNode{
    public NotExpNode(Node exp) {
        super(exp);
    }

    @Override
    public Node typeCheck() {
        if(! (exp.typeCheck().getClass().equals(BoolTypeNode.class))) {
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
