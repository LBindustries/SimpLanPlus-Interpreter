package ast;

import ast.Types.BoolTypeNode;
import ast.Types.TypeNode;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class IteNode implements Node{

    private Node exp;
    private Node then_statement;
    private Node else_statement;
    private ArrayList<HashMap<String, STentry>> localSymbolTable;

    public IteNode(Node exp, Node then_statement, Node else_statement){
        this.exp = exp;
        this.then_statement = then_statement;
        this.else_statement = else_statement;
    }

    public IteNode(Node exp, Node then_statement){
        this.exp = exp;
        this.then_statement = then_statement;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n"+indent + "ITE ";
        res += "\n"+indent+ " Condition " + exp.toPrint(indent+"  ");
        res += "\n"+indent + " Then " + then_statement.toPrint(indent+"  ");

        if(else_statement != null){
            res += "\n"+indent + " Else " + else_statement.toPrint(indent+ "  ");
        }

        return res;
    }


    @Override
    public TypeNode typeCheck(Environment env) {

        if(! exp.typeCheck(env).getClass().equals(BoolTypeNode.class)){
            System.out.println("Condition of if statement not boolean");
            System.exit(0);
        }

        TypeNode then_node = then_statement.typeCheck(env);
        if(else_statement!=null) {
            TypeNode else_node = else_statement.typeCheck(env);
            if(! then_node.getClass().equals(else_node.getClass())) {
                System.out.println("Then and else have different types");
                System.exit(0);
            }
        }

        return then_node;
        // Il max degli effetti lo sta già facendo il typecheck di then_node e else_node

    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        if(this.exp!=null){
            res.addAll(this.exp.checkSemantics(env));
        }
        if(this.then_statement!=null){
            res.addAll(this.then_statement.checkSemantics(env));
        }
        if(this.else_statement!=null){
            res.addAll(this.else_statement.checkSemantics(env));
        }
        this.localSymbolTable = env.getSymbolTableManager().getSymbolTable();
        return res;
    }
}
