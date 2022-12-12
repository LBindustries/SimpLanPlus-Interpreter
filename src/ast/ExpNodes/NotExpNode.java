package ast.ExpNodes;

import ast.Types.BoolTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.TypeCheckException;

import java.util.ArrayList;

public class NotExpNode extends BaseExpNode{
    // Logical not operation, !a
    public NotExpNode(Node exp, int line) {
        super(exp, line);
    }

    @Override
    public TypeNode typeCheck(Environment env) throws TypeCheckException {
        if(! (exp.typeCheck(env).getType().equals("bool"))) {
            // Exp must be a boolean for this operation
            throw new TypeCheckException("[!] No bool in not"+ " at line "+line+".");
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
