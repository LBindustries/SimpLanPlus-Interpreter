package ast.ExpNodes;

import ast.BoolTypeNode;
import ast.IntTypeNode;
import ast.Node;
import ast.TypeNode;
import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

import java.util.ArrayList;

public class BinExpNode implements Node {

    private String op;
    private Node left;
    private Node right;

    public BinExpNode(String op, Node left, Node right){
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"BinExp "+this.op+this.left.toPrint(indent+" ")+this.right.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(SymbolTableManager stm) {
        switch(op){
            case "==", "!=": {
                if(! (left.typeCheck(stm).getClass().equals(right.typeCheck(stm).getClass()))) {
                    System.out.println("No match of operators type in " + op);
                    System.exit(0);
                }
                return new BoolTypeNode();
            }
            case "+", "-", "*", "/": {
                if(! (left.typeCheck(stm).getClass().equals(IntTypeNode.class) &&
                        right.typeCheck(stm).getClass().equals(IntTypeNode.class))) {
                    System.out.println("No integers in " + op);
                    System.exit(0);
                }
                return new IntTypeNode();
            }
            case ">=", "<=", "<", ">": {
                if(! (left.typeCheck(stm).getClass().equals(IntTypeNode.class) &&
                        right.typeCheck(stm).getClass().equals(IntTypeNode.class))) {
                    System.out.println("No integers in " + op);
                    System.exit(0);
                }
                return new BoolTypeNode();
            }
            case "&&", "||": {
                if(! (left.typeCheck(stm).getClass().equals(BoolTypeNode.class) &&
                        right.typeCheck(stm).getClass().equals(BoolTypeNode.class))) {
                    System.out.println("No booleans in " + op);
                    System.exit(0);
                }
                return new BoolTypeNode();
            }
        }
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        if(this.left!=null) {
            res.addAll(left.checkSemantics(env));
        }
        if(this.right!=null) {
            res.addAll(right.checkSemantics(env));
        }
        return res;
    }
}
