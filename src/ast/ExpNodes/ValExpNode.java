package ast.ExpNodes;

import ast.Types.IntTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
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
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        return ";Integer Load Immediate\nli $a0 "+this.value+"\n";
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
