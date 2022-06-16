package ast.ExpNodes;

import ast.BoolTypeNode;
import ast.Node;
import ast.TypeNode;
import util.SymbolTableManager;

public class NotExpNode extends BaseExpNode{
    public NotExpNode(Node exp) {
        super(exp);
    }

    @Override
    public TypeNode typeCheck(SymbolTableManager stm) {
        if(! (exp.typeCheck(stm).getClass().equals(BoolTypeNode.class))) {
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
