package ast.ExpNodes;

import ast.Types.BoolTypeNode;
import ast.Types.IntTypeNode;
import ast.Node;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;
import util.TypeCheckException;

import java.util.ArrayList;

public class BinExpNode implements Node {

    private String op;
    private Node left;
    private Node right;
    private int line;
    // Binary expression node, left op right
    public BinExpNode(String op, Node left, Node right, int line) {
        this.op = op;
        this.left = left;
        this.right = right;
        this.line = line;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "BinExp " + this.op + this.left.toPrint(indent + " ") + this.right.toPrint(indent + " ");
    }

    @Override
    public TypeNode typeCheck(Environment env) throws TypeCheckException {
        switch (op) {
            case "==", "!=": {
                // If operation is an equality one, the types must be equal. Returns a bool.
                if (!(left.typeCheck(env).getType().equals(right.typeCheck(env).getType()))) {
                    throw new TypeCheckException("[!] No match of operators type in " + op + " at line "+line+".");
                }
                return new BoolTypeNode();
            }
            case "+", "-", "*", "/": {
                // If the operation is an arithmetic one, the types must be both integers. Returns an integer.
                if (!(left.typeCheck(env).getType().equals("int") &&
                        right.typeCheck(env).getType().equals("int"))) {
                    throw new TypeCheckException("[!] No integers in " + op+ " at line "+line+".");
                }
                return new IntTypeNode();
            }
            case ">=", "<=", "<", ">": {
                // If the operation is a comparison one, the types must be both integers. Returns a boolean.
                if (!(left.typeCheck(env).getType().equals("int") &&
                        right.typeCheck(env).getType().equals("int"))) {
                    throw new TypeCheckException("[!] No integers in " + op+ " at line "+line+".");
                }
                return new BoolTypeNode();
            }
            case "&&", "||": {
                // If the operation is a boolean one, the types must be both booleans. Returns a boolean.
                if (!(left.typeCheck(env).getType().equals("bool") &&
                        right.typeCheck(env).getType().equals("bool"))) {
                    throw new TypeCheckException("[!] No booleans in \" + op+ \" at line \"+line+\".");
                }
                return new BoolTypeNode();
            }
        }
        return null;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = left.codeGeneration(labgen, localenv);
        asm += "push $a0\n";
        asm += right.codeGeneration(labgen, localenv);
        asm += ";Binary Operation\n";
        // Translates the opcode in a asm instruction
        String operation = switch (this.op) {
            case "+" -> "add";
            case "-" -> "sub";
            case "*" -> "mult";
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
        asm += operation+" $a0 $t1 $a0\n";
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        // Check for semantic errors on left and right.
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        if (this.left != null) {
            res.addAll(left.checkSemantics(env));
        }
        if (this.right != null) {
            res.addAll(right.checkSemantics(env));
        }
        return res;
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        return;
    }
}
