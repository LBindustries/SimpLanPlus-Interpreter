package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class AssignmentNode implements Node{
    // ID '=' exp
    private IdNode id;
    private Node exp;

    private STentry st;

    public AssignmentNode(IdNode ID, Node exp){
        this.id = ID;
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "Assignment " + id.toPrint(indent) + " = " + exp.toPrint(indent+" ");
    }

    @Override
    public Node typeCheck() {
        if(st == null){
            System.out.println("Variable "+this.id.getId()+" not declared");
            System.exit(0);
        }

        if( ! exp.typeCheck().getClass().equals(st.getType().getClass())) {
            System.out.println("Types of variable and value are not compatible");
            System.exit(0);
        }

        st.getEffect().setInitialized();
        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        HashMap<String, STentry> st = env.symTable.get(env.nestingLevel);

        // check for variable with such id in current level and below
        int j=env.nestingLevel;
        STentry tmp=null;
        while (j>=0 && tmp==null)
            tmp=(env.symTable.get(j--)).get(this.id.getId());
        if (tmp==null)
            res.add(new SemanticError("Variable "+this.id.getId()+" not declared"));
        else{   // if variable exists, check the exp
            if(this.exp != null) {
                res.addAll(this.exp.checkSemantics(env));
            }
        }

        this.st = tmp;

        return res;
    }
}
