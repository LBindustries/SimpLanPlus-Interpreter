package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class DecVarNode implements Node{

    private Node type;
    private Node id;
    private Node exp;

    public DecVarNode(Node type, Node id, Node exp){
        this.type = type;
        this.id = id;
        this.exp = exp;
    }

    public DecVarNode(Node type, Node id){
        this.type = type;
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        if(this.exp!=null){
            return indent+"DecVar "+this.type.toPrint(indent+" ")+this.id.toPrint(indent+" ")+this.exp.toPrint(indent+" ")+"\n";
        }
        return indent+"DecVar "+this.type.toPrint(indent+" ")+this.id.toPrint(indent+" ")+"\n";
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
