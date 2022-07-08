package ast;

import ast.Types.BoolTypeNode;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class IteNode implements Node {

    private Node exp;
    private Node then_statement;
    private Node else_statement;
    private ArrayList<HashMap<String, STentry>> localSymbolTable;

    public IteNode(Node exp, Node then_statement, Node else_statement) {
        this.exp = exp;
        this.then_statement = then_statement;
        this.else_statement = else_statement;
    }

    public IteNode(Node exp, Node then_statement) {
        this.exp = exp;
        this.then_statement = then_statement;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "ITE ";
        res += "\n" + indent + " Condition " + exp.toPrint(indent + "  ");
        res += "\n" + indent + " Then " + then_statement.toPrint(indent + "  ");

        if (else_statement != null) {
            res += "\n" + indent + " Else " + else_statement.toPrint(indent + "  ");
        }

        return res;
    }


    @Override
    public TypeNode typeCheck(Environment env) {

        if (!exp.typeCheck(env).getClass().equals(BoolTypeNode.class)) {
            System.out.println("Condition of if statement not boolean");
            System.exit(0);
        }

        Environment envOld = new Environment(env, true);

        TypeNode then_node = then_statement.typeCheck(env);

        if (else_statement != null) {
            TypeNode else_node = else_statement.typeCheck(envOld);
            if (!then_node.getType().equals(else_node.getType())) {
                System.out.println("Then and else have different types");
                System.exit(0);
            }
        }

        for (int i = 0; i < env.getSymbolTableManager().getSymbolTable().size(); i++) {
            HashMap<String, STentry> tmp = env.getSymbolTableManager().getSymbolTable().get(i);
            HashMap<String, STentry> tmp1 = envOld.getSymbolTableManager().getSymbolTable().get(i);
            for (String key : tmp.keySet()) {
                tmp.get(key).getEffect().join(tmp1.get(key).getEffect().getStatus());
            }

        }

        return then_node;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = ";Ite\n"+exp.codeGeneration(labgen, localenv);
        String then_label = labgen.new_label("ITE_THEN");
        String exit_label = labgen.new_label("ITE_EXIT");
        asm += "li $t1 1\nbeq $a0 $t1 " + then_label + "\n" +
                (else_statement != null ? else_statement.codeGeneration(labgen, localenv) : "") + "jmp " + exit_label + "\n" +
                then_label + ":\n" + then_statement.codeGeneration(labgen, localenv) + exit_label + ":\n";
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        if (this.exp != null) {
            res.addAll(this.exp.checkSemantics(env));
        }
        if (this.then_statement != null) {
            res.addAll(this.then_statement.checkSemantics(env));
        }
        if (this.else_statement != null) {
            res.addAll(this.else_statement.checkSemantics(env));
        }
        this.localSymbolTable = env.getSymbolTableManager().getSymbolTable();
        return res;
    }
}
