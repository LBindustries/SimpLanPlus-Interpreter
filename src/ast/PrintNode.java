package ast;

import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class PrintNode implements Node{
    //'print' exp;

    private Node exp;

    public PrintNode(Node exp){
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "Print" + exp.toPrint(indent);
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        exp.typeCheck(env);
        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = exp.codeGeneration(labgen, localenv);
        asm += ";Print\nprint $a0\n";
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, int line) {
        return exp.checkSemantics(env, line);
    }
}
