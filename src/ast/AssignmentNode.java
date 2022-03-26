package ast;

import ast.ExpNodes.ValExpNode;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class AssignmentNode implements Node{
    // ID '=' exp
    private Node ID;
    private Node exp;

    public AssignmentNode(Node ID, Node exp){
        this.ID = ID;
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Assignment:\n" + ID.toPrint(indent) + " = " + exp.toPrint("");
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
