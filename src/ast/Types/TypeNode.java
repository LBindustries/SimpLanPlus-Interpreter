package ast.Types;

import ast.Node;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class TypeNode implements Node {

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
    public TypeNode typeCheck(Environment env) {
        return null;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        return;
    }
}
