package ast.ExpNodes;

import ast.Node;
import ast.STentry;
import ast.TypeNode;
import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ProgramNode implements Node {

    /*
    Nella sintassi di SLP, le dichiarazioni delle variabili / funzioni devono venire fatte prima
    degli statement.
     */
    private ArrayList<Node> declarations;
    private ArrayList<Node> statements;

    public ProgramNode(ArrayList<Node> declarations, ArrayList<Node> statements) {
        this.declarations = declarations;
        this.statements = statements;
    }

    @Override
    public String toPrint(String indent) {
        String res = "";
        if (this.declarations != null) {
            for (Node dec : declarations) {
                res += dec.toPrint(indent + " ");
            }
        }
        if (this.statements != null) {
            for (Node dec : statements) {
                res += dec.toPrint(indent + " ");
            }
        }
        return "\n"+indent + "Program" + res;
    }

    @Override
    public TypeNode typeCheck(SymbolTableManager stm) {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        env.incNestingLevel(1);
        HashMap<String, STentry> st = new HashMap<String, STentry>();
        env.getSymbolTableManager().addLevel(st);

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        if(this.declarations!=null && this.declarations.size()>0){
            env.setOffset(-2); // Why?
            for(Node n: this.declarations){
                res.addAll(n.checkSemantics(env));
            }
        }
        if(this.statements!=null && this.statements.size()>0){
            env.setOffset(-2); // Why?
            for(Node n: this.statements){
                res.addAll(n.checkSemantics(env));
            }
        }

        env.getSymbolTableManager().removeLevel(env.decNestingLevel(1));
        return res;
    }

}
