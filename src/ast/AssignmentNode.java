package ast;

import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

import java.util.ArrayList;

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
    public TypeNode typeCheck(SymbolTableManager stm) {
        if(st == null){
            System.out.println("Variable "+this.id.getId()+" not declared");
            System.exit(0);
        }

        if( ! exp.typeCheck(stm).getClass().equals(st.getType().getClass())) {
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

        // check for variable with such id in current level and below
        STentry entry = env.getSymbolTableManager().getLastEntry(id.getId(), env.getNestingLevel());

        if (entry==null)
            res.add(new SemanticError("Variable "+this.id.getId()+" not declared"));

        // else, if variable exists, check the exp
        else if(this.exp != null)
                res.addAll(this.exp.checkSemantics(env));

        st = entry;
        return res;
    }
}
