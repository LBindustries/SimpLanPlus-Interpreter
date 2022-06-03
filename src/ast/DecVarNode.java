package ast;

import util.Effect;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class DecVarNode implements Node{

    private TypeNode type;
    private IdNode id;
    private Node exp;

    public DecVarNode(TypeNode type, IdNode id, Node exp){
        this.type = type;
        this.id = id;
        this.exp = exp;
    }

    public DecVarNode(TypeNode type, IdNode id){
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
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        HashMap<String, STentry> st = env.symTable.get(env.nestingLevel);
        if(st.put(this.id.getId(), new STentry(env.nestingLevel, type, env.offset--, new Effect(false))) != null){
            res.add(new SemanticError("Variable id "+this.id.getId()+" already declared."));
        }
        if(this.exp!=null){
            res.addAll(this.exp.checkSemantics(env));
            st.get(this.id.getId()).getEffect().setInitialized(); // "non so se si può fare" -Ale
        }
        return res;
    }
}
