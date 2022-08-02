package ast;

import ast.ExpNodes.DerExpNode;
import ast.Types.FunctionTypeNode;
import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;
import java.util.Objects;

public class CallNode implements Node {

    private IdNode id;
    private ArrayList<Node> exp;
    private boolean isExp;

    public CallNode(IdNode id, ArrayList<Node> exp) {
        this.id = id;
        this.exp = exp;
        this.isExp = false;
    }

    public CallNode(IdNode id) {
        this.id = id;
        this.isExp = false;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "Call" + id.toPrint(indent);
        if (this.exp != null) {
            for (Node e : exp) {
                res += e.toPrint(indent + " ");
            }
        }
        return res;
    }

    @Override
    public TypeNode typeCheck(Environment env) {

        STentry entry = env.getSymbolTableManager().getLastEntry(id.getId(), 0); // funzioni definite solo a livello 0

        if (!(env.getSymbolTableManager().getLastEntry(id.getId(), 0).getType() instanceof FunctionTypeNode)) {
            System.out.println("Trying to call non-function symbol " + this.id.getId());
            System.exit(0);
        }

        FunctionTypeNode t = (FunctionTypeNode) entry.getType();
        if (t.getArgs().size() != this.exp.size()) { // stesso numero di parametri
            System.out.println("Number of parameters for " + this.id.getId() + " is not correct. Expecting " + t.getArgs().size() + ", got " + this.exp.size());
            System.exit(0);
        }
        for (int i = 0; i < exp.size(); i++) {

            if (!Objects.equals(exp.get(i).typeCheck(env).getType(), t.getArgs().get(i).getType())) { // tipi coerenti alle exp
                System.out.println("Parameters type mismatch for " + this.id.getId() + ": expecting " + t.getArgs().get(i).getType() + ", got " + exp.get(i).typeCheck(env).getType()+ " for parameter "+(i+1));
                System.exit(0);
            }

            if (t.getArgs().get(i).isVar()) {
                if (!exp.get(i).getClass().equals(DerExpNode.class)) { // se di tipo var deve essere una variabile
                    System.out.println("Expecting variable in call of symbol " + this.id.getId() + " for parameter "+(i+1));
                    System.exit(0);
                }
            }
        }
        entry.getEffect().setUsed();
        if (isExp)
            return new VoidTypeNode();
        else
            return env.getSymbolTableManager().getLastEntry(id.getId(), 0).getType();

    }

    public void setIsExp(boolean isExp) {
        this.isExp = isExp;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = ";Function Call\n";

        STentry entry = localenv.getSymbolTableManager().getLastEntry(id.getId(), 0);
        FunctionTypeNode t = (FunctionTypeNode) entry.getType();

        for (int i =  (exp.size() - 1); i >= 0; i--) {
            asm += ";Loading arg " + i + "\n";

            if(t.getArgs().get(i).isVar()){
                asm += "mov $t1 $fp\n";
                DerExpNode idName = (DerExpNode) exp.get(i);
                for(int j = 0; j < (localenv.getNestingLevel() - localenv.getSymbolTableManager().getLastEntry(idName.getId().getId(), localenv.getNestingLevel()).getNestinglevel()); j++ ){
                    asm += "lw $t1 0($t1)\n";
                }
                asm += "addi $t1 $t1 " + localenv.getSymbolTableManager().getLastEntry(idName.getId().getId(), localenv.getNestingLevel()).getOffset() + "\n";
                asm += "push $t1\n";
            }

            asm += exp.get(i).codeGeneration(labgen, localenv);

            if (t.getArgs().get(i).getType().equals("int")){
                asm += "push $a0\n";
            } else if (t.getArgs().get(i).getType().equals("bool")) {
                asm += "subi $sp $sp 1\n";
                asm += "sb $a0 0($sp)\n";
            }
        }
        asm += "jal " + id.getId() + "\n";
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        if (env.getSymbolTableManager().getLastEntry(id.getId(), env.getNestingLevel()) == null) {
            res.add(new SemanticError("Function " + this.id.getId() + " not declared."));
            return res;
        }

        if (this.exp != null) {
            for (Node arg : exp)
                res.addAll(arg.checkSemantics(env));
        }
        return res;
    }
}
