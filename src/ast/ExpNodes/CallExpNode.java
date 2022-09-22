package ast.ExpNodes;

import ast.CallNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class CallExpNode implements Node {

    private CallNode call;

    public CallExpNode(Node call){
        this.call = (CallNode) call;
        this.call.setIsExp(true);
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"Call "+this.call.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        return call.typeCheck(env);
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        return this.call.codeGeneration(labgen,localenv);
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, int line) {
        return call.checkSemantics(env, line);
    }
}
