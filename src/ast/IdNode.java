package ast;

import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
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
    public TypeNode typeCheck(Environment env) {
        return null;
    }

    public String getId() {
        return id;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, int line) {
        return new ArrayList<SemanticError>();
    }
}
