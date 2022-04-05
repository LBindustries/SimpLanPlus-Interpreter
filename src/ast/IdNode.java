package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class IdNode implements Node{

    private String id;

    public IdNode(String id){
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"ID "+this.id;
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    public String getId() {
        return id;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }
}
