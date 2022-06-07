package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class IteNode implements Node{

    private Node exp;
    private Node then_statement;
    private Node else_statement;

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

    /*
    *   La funzione ritorna:
    *       - 0 se i tipi sono uguali;
    *       - 1 se va tenuto il tipo del then, in quanto l'else è void
    *       - 2 se va tenuto il tipo dell'else, in quanto il then è void
    *       - -1 se sono diversi fra loro (int - bool)
    */




    @Override
    public Node typeCheck() {

        if(! exp.typeCheck().getClass().equals(BoolTypeNode.class)){
            System.out.println("Condition of if statement not boolean");
            System.exit(0);
        }

        Node then_node = then_statement.typeCheck();
        if(else_statement!=null) {
            Node else_node = else_statement.typeCheck();
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
        return res;
    }
}
