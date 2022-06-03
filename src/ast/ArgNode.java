package ast;

import util.Effect;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class ArgNode implements Node{

    private TypeNode type;
    private IdNode id;

    public ArgNode(TypeNode type, IdNode id){
        this.type = type;
        this.id = id;
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

    @Override   // idk??
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        HashMap<String, STentry> st = env.symTable.get(env.nestingLevel);

        // "Nella DecFun i vari parametri li mettiamo a declared, vero?" -Ale
        if(st.put(this.id.getId(), new STentry(env.nestingLevel, type, env.offset--, new Effect(false))) != null){
            res.add(new SemanticError("Argument id "+this.id.getId()+" already defined for the function."));
        }

        return res;
    }
}
