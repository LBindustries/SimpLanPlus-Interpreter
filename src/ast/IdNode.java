package ast;

import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class IdNode implements Node{

    private String id;
    private int line;

    public IdNode(String id, int line){
        this.id = id;
        this.line = line;
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
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }
}
