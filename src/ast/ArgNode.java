package ast;

import util.Effect;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class ArgNode implements Node{

    private TypeNode type;
    private IdNode id;
    private boolean isVar = false;

    public ArgNode(TypeNode type, IdNode id, boolean isVar){
        this.type = type;
        this.id = id;
        this.isVar = isVar;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"Arg"+this.type.toPrint(indent+" ")+this.id.toPrint(indent+" ");
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
        HashMap<String, STentry> st = env.getSymbolTableManager().getLevel(env.getNestingLevel());

        // "Nella DecFun i vari parametri li mettiamo a declared, vero?" -Ale
        if(st.put(this.id.getId(), new STentry(env.getNestingLevel(), type, env.decOffset(1), new Effect(false))) != null){
            res.add(new SemanticError("Argument id "+this.id.getId()+" already defined for the function."));
        }

        return res;
    }
}
