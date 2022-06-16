package ast;

import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

import java.util.ArrayList;

public class ReturnNode implements Node{
    // 'return' (exp)?;

    private Node exp;

    public ReturnNode(Node exp){
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n"+indent + "Return";
        if(this.exp != null)
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
    public TypeNode typeCheck(SymbolTableManager stm) {
        return new VoidTypeNode();
    }

    public Node getExp(){
        return exp;
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
