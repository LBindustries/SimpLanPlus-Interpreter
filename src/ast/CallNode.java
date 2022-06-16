package ast;

import ast.ExpNodes.DerExpNode;
import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

import java.util.ArrayList;
import java.util.Objects;

public class CallNode implements Node{

    private IdNode id;
    private ArrayList<Node> exp;
    private boolean isExp;

    public CallNode(IdNode id, ArrayList<Node> exp){
        this.id = id;
        this.exp = exp;
        this.isExp = false;
    }

    public CallNode(IdNode id){
        this.id = id;
        this.isExp = false;
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
    public TypeNode typeCheck(SymbolTableManager stm) {

        STentry entry = stm.getLastEntry(id.getId(), 0); // funzioni definite solo a livello 0

        FunctionTypeNode t = (FunctionTypeNode)entry.getType();
        if(t.getArgs().size() != this.exp.size()) // stesso numero di parametri
            return null; // Gestisci errori

        for(int i=0; i<exp.size(); i++){
            if(!Objects.equals(exp.get(i).typeCheck(stm).getType(), t.getArgs().get(i).getType())){ // tipi coerenti alle exp
                return null;
            }

            if(t.getArgs().get(i).isVar()){
                if(! exp.get(i).getClass().equals(DerExpNode.class)){ // se di tipo var deve essere una variabile
                    return null;
                }
            }
        }

        if(isExp)
            return new VoidTypeNode();
        else
            return stm.getLastEntry(id.getId(), 0).getType();

    }

    public void setIsExp(boolean isExp){
        this.isExp = isExp;
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
