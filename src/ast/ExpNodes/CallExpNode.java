package ast.ExpNodes;

import ast.Node;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class CallExpNode implements Node {

    private Node call;

    public CallExpNode(Node call){
        this.call = call;
    }

    @Override
    public String toPrint(String indent) {
        return indent+"Call"+this.call.toPrint(indent+"\t")+"\n";
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
