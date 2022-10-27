package ast.ExpNodes;

import ast.Types.IntTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;

import java.util.ArrayList;

public class NegExpNode extends BaseExpNode{
    public NegExpNode(Node exp, int line) {
        super(exp, line);
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        if(! (exp.typeCheck(env).getType().equals("int"))) {
            System.out.println("[!] No integer in neg"+ " at line "+line+".");
            System.exit(0);
        }
        return new IntTypeNode();
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = exp.codeGeneration(labgen, localenv);
        asm += ";Negation\nneg $a0 $a0\n";
        return asm;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"negExpNode"+this.exp.toPrint(indent+" ");
    }

}
