package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class CallNode implements Node{

    private Node id;
    private Node exp;

    public CallNode(Node id, Node exp){
        this.id = id;
        this.exp = exp;
    }

    public CallNode(Node id){
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return null;
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
