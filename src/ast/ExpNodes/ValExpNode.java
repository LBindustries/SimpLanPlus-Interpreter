package ast.ExpNodes;

import ast.Types.IntTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class ValExpNode implements Node {

    private int value;

    public ValExpNode(int value){
        this.value=value;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"ValueExpNode "+this.value;
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        return new IntTypeNode();
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
