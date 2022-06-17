package ast.ExpNodes;

import ast.IdNode;
import ast.Node;
import ast.STentry;
import ast.Types.FunctionTypeNode;
import ast.Types.TypeNode;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class DerExpNode implements Node {

    private IdNode id; // "Perché non c'è una stringa e basta T.T" -Ale "Perchè sì" -Balu

    public DerExpNode(IdNode id){
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "DerExpNode" + this.id.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        if(env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()) == null){
            System.out.println("Variable "+this.id.getId()+" not declared"); // "Vogliamo ristamparlo?" -Ale
            System.exit(0);
        }
        if(env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getType() instanceof FunctionTypeNode){
            System.out.println("Trying to use function "+this.id.getId()+" as a variable.");
            System.exit(0);
        }
        if (! env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getEffect().isInitialized() && ! env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getEffect().isUsed()){
            System.out.println("Variable "+this.id.getId()+" not initialized");
            System.exit(0);
        }

        env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getEffect().setUsed();
        return env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getType();
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
        return res;
    }
}
