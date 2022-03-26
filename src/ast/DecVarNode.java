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
            return "\n"+indent+"DecVar "+this.type.toPrint(indent+" ")+this.id.toPrint(indent+" ")+this.exp.toPrint(indent+" ");
        }
        return "\n"+indent+"DecVar "+this.type.toPrint(indent+" ")+this.id.toPrint(indent+" ");
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
