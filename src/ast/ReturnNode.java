package ast;

import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class ReturnNode implements Node {
    // 'return' (exp)?;

    private Node exp;

    public ReturnNode(Node exp) {
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "Return";
        if (this.exp != null)
            return res + exp.toPrint(indent);
        else
            return res;
    }

/*
    public int prova(){
        return 3;
        int a = 5;  // ERRORE!
    }
*/

    @Override
    public TypeNode typeCheck(Environment env) {
        if (this.exp == null) {
            return new VoidTypeNode();
        }
        return exp.typeCheck(env);
    }

    public Node getExp() {
        return exp;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen) {
        return this.exp.codeGeneration(labgen);
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        if(exp != null) {
            return exp.checkSemantics(env);
        }
        return new ArrayList<SemanticError>();
    }
}
