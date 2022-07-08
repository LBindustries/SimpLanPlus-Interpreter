package ast.ExpNodes;

import ast.Types.BoolTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class BoolExpNode implements Node {

    private boolean bool;

    public BoolExpNode(boolean bool){
        this.bool = bool;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "Bool " + bool;
    }

    @Override
    public TypeNode typeCheck(Environment env) { return new BoolTypeNode(); }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        return "li $a0 "+(bool ? "1" : "0")+"\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }
}
