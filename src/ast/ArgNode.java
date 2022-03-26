package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class ArgNode implements Node{

    private Node type;
    private Node id;

    public ArgNode(Node type, Node id){
        this.type = type;
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return indent+"Arg"+this.type.toPrint(indent+"\t")+this.id.toPrint(indent+"\t");
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
