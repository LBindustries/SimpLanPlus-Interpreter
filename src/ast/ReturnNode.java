package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class ReturnNode implements Node{
    // 'return' (exp)?;

    private Node exp;

    public ReturnNode(Node exp){
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Return:\n" + exp.toPrint(indent);
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }
}