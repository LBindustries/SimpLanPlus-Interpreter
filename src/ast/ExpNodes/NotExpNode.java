package ast.ExpNodes;

import ast.Types.BoolTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;

public class NotExpNode extends BaseExpNode{
    public NotExpNode(Node exp) {
        super(exp);
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        if(! (exp.typeCheck(env).getType().equals("bool"))) {
            System.out.println("No bool in not");
            System.exit(0);
        }
        return new BoolTypeNode();
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = exp.codeGeneration(labgen, localenv);
        asm += ";Logical not\nnot $a0 $a0";
        return asm;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"NotExpNode "+this.exp.toPrint(indent+" ");
    }
}
