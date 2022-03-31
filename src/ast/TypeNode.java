package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class TypeNode implements Node{

    private String type;

    public TypeNode(String type){
        this.type = type;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"Type "+this.type;
    }

    public String getType() {
        return type;
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
