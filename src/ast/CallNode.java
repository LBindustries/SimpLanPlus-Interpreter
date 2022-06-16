package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class CallNode implements Node{

    private IdNode id;
    private ArrayList<Node> exp;

    public CallNode(IdNode id, ArrayList<Node> exp){
        this.id = id;
        this.exp = exp;
    }

    public CallNode(IdNode id){
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n"+indent + "Call" + id.toPrint(indent);
        if (this.exp != null) {
            for (Node e : exp) {
                res += e.toPrint(indent + " ");
            }
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
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        if (env.getSymbolTableManager().getLastEntry(id.getId(), env.getNestingLevel())==null){
            res.add(new SemanticError("Function "+this.id.getId()+" not declared."));
            return res;
        }

        if(this.exp != null) {
            for (Node arg : exp)
                res.addAll(arg.checkSemantics(env));
        }
        return res;
    }
}
