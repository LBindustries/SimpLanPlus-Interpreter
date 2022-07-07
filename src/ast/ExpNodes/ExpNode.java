package ast.ExpNodes;

import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class ExpNode implements Node {

    protected Node exp;

    public ExpNode(Node exp){
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"ExpNode "+exp.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        return exp.typeCheck(env);
    }

    @Override
    public String codeGeneration(LabelGenerator labgen) {
        return exp.codeGeneration(labgen);
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return exp.checkSemantics(env);
    }
}
