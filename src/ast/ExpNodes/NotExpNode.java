package ast.ExpNodes;

import ast.Types.BoolTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;

import java.util.ArrayList;

public class NotExpNode extends BaseExpNode{
    public NotExpNode(Node exp, int line) {
        super(exp, line);
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        if(! (exp.typeCheck(env).getType().equals("bool"))) {
            System.out.println("No bool in not"+ " at line "+line+".");
            System.exit(0);
        }
        return new BoolTypeNode();
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = exp.codeGeneration(labgen, localenv);
        asm += ";Logical not\nnot $a0 $a0\n";
        return asm;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"NotExpNode "+this.exp.toPrint(indent+" ");
    }

}
