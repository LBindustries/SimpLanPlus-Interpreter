package ast.ExpNodes;

import ast.Types.BoolTypeNode;
import ast.Types.IntTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class BinExpNode implements Node {

    private String op;
    private Node left;
    private Node right;

    public BinExpNode(String op, Node left, Node right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "BinExp " + this.op + this.left.toPrint(indent + " ") + this.right.toPrint(indent + " ");
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        switch (op) {
            case "==", "!=": {
                if (!(left.typeCheck(env).getType().equals(right.typeCheck(env).getType()))) {
                    System.out.println("No match of operators type in " + op);
                    System.exit(0);
                }
                return new BoolTypeNode();
            }
            case "+", "-", "*", "/": {
                if (!(left.typeCheck(env).getType().equals("int") &&
                        right.typeCheck(env).getType().equals("int"))) {
                    System.out.println("No integers in " + op);
                    System.exit(0);
                }
                return new IntTypeNode();
            }
            case ">=", "<=", "<", ">": {
                if (!(left.typeCheck(env).getType().equals("int") &&
                        right.typeCheck(env).getType().equals("int"))) {
                    System.out.println("No integers in " + op);
                    System.exit(0);
                }
                return new BoolTypeNode();
            }
            case "&&", "||": {
                if (!(left.typeCheck(env).getType().equals("bool") &&
                        right.typeCheck(env).getType().equals("bool"))) {
                    System.out.println("No booleans in " + op);
                    System.exit(0);
                }
                return new BoolTypeNode();
            }
        }
        return null;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen) {
        String asm = left.codeGeneration(labgen);
        asm += "push $a0\n";
        asm += right.codeGeneration(labgen);
        String operation = switch (this.op) {
            case "+" -> "add";
            case "-" -> "sub";
            case "*" -> "mul";
            case "/" -> "div";
            case "<" -> "lt";
            case ">" -> "gt";
            case "<=" -> "lte";
            case ">=" -> "gte";
            case "==" -> "eq";
            case "!=" -> "neq";
            case "&&" -> "and";
            case "||" -> "or";
            default -> "err";
        };
        asm += "pop $t1\n";
        asm += operation+" $a0 $a0 $t1\n";
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        if (this.left != null) {
            res.addAll(left.checkSemantics(env));
        }
        if (this.right != null) {
            res.addAll(right.checkSemantics(env));
        }
        return res;
    }
}
