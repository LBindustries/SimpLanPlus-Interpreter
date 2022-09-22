package ast;

import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class StatementNode implements Node {

    private Node statement;
    private int line;

    public StatementNode(Node statement, int line) {
        this.statement = statement;
        this.line = line;
    }

    public Node getStatement() {
        return statement;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "Statement" + statement.toPrint(indent + " ");
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        return statement.typeCheck(env);
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        return statement.codeGeneration(labgen, localenv);
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
