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

        int j=env.nestingLevel;
        STentry tmp=null;
        while (j>=0 && tmp==null)
            tmp=(env.symTable.get(j--)).get(this.id.getId());
        if (tmp==null){
            res.add(new SemanticError("Function "+this.id.getId()+" not declared."));
            return res;
        }
        if (!tmp.isFunction()){
            res.add(new SemanticError("ID "+this.id.getId()+" is not a function."));
            return res;
        }
        int argNumber=0;
        if(this.exp!=null){
            argNumber = this.exp.size();
        }
        if(argNumber!=tmp.getNargs()){
            res.add(new SemanticError("Function "+this.id.getId()+" is being called with incorrect number of args. "+tmp.getNargs()+ " expected, "+argNumber+" given."));
            return res;
        }
        if(this.exp != null) {
            for (Node arg : exp)
                res.addAll(arg.checkSemantics(env));
        }
        return res;
    }
}
