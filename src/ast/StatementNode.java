package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class StatementNode implements Node {

    private Node statement;

    public StatementNode(Node statement) {
        this.statement = statement;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "Statement" + statement.toPrint(indent + " ");
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
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        if (statement != null) {
            res.addAll(this.statement.checkSemantics(env));
        }
        return res;
    }
}
