package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class IteNode implements Node{

    private Node exp;
    private Node then_statement;
    private Node else_statement;

    public IteNode(Node exp, Node then_statement, Node else_statement){
        this.exp = exp;
        this.then_statement = then_statement;
        this.else_statement = else_statement;
    }

    public IteNode(Node exp, Node then_statement){
        this.exp = exp;
        this.then_statement = then_statement;
    }

    @Override
    public String toPrint(String indent) {
        String res = indent + "ITE:\n" + exp.toString();
        res += indent + " Then:\n" + then_statement.toPrint(indent);

        if(else_statement != null){
            res += indent + " Else:\n" + else_statement.toPrint(indent);
        }

        return res;
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
