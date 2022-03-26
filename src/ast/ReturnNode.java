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
        String res = indent + "Return\n";
        if(this.exp != null)
            return res + exp.toPrint(indent);
        else
            return res;
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
