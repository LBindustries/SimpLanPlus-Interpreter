package ast;

import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
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
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return exp.checkSemantics(env);
    }
}
