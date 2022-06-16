package ast.ExpNodes;

import ast.IdNode;
import ast.Node;
import ast.STentry;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class DerExpNode implements Node {

    private IdNode id; // "Perché non c'è una stringa e basta T.T" -Ale "Perchè sì" -Balu
    private STentry st;

    public DerExpNode(IdNode id){
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "DerExpNode" + this.id.toPrint(indent+" ");
    }

    @Override
    public Node typeCheck() {
        if(st == null){
            System.out.println("Variable "+this.id.getId()+" not declared"); // "Vogliamo ristamparlo?" -Ale
            System.exit(0);
        }
        if (! st.getEffect().isInitialized()){
            System.out.println("Variable "+this.id.getId()+" not initialized");
            System.exit(0);
        }

        st.getEffect().setUsed(); // "Idk se va bene" -Ale
        return st.getType();
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        STentry entry = env.getSymbolTableManager().getLastEntry(id.getId(), env.getNestingLevel());

        if (entry==null)
            res.add(new SemanticError("Variable "+this.id.getId()+" not declared"));

        st = entry;
        return res;
    }
}
