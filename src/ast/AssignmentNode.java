package ast;

import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;
import java.util.Objects;

public class AssignmentNode implements Node{
    // ID '=' exp
    private IdNode id;
    private Node exp;
    private int line;

    private STentry st;

    public AssignmentNode(IdNode ID, Node exp, int line){
        this.id = ID;
        this.exp = exp;
        this.line = line;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "Assignment " + id.toPrint(indent) + " = " + exp.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        if(st == null){
            System.out.println("Variable "+this.id.getId()+" not declared"+ " at line "+line+".");
            System.exit(0);
        }

        if(!Objects.equals(exp.typeCheck(env).getType(), st.getType().getType())) {
            System.out.println("Types of variable and value are not compatible"+ " at line "+line+".");
            System.exit(0);
        }

        st.getEffect().setInitialized();
        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = exp.codeGeneration(labgen, localenv);
        asm += ";Assignment\n";

        asm += "mov $t1 $fp\n";

        for(int i = 0; i < (localenv.getNestingLevel() - localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getNestinglevel()); i++ ){
            asm += "lw $t1 0($t1)\n";
        }

        if(localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getType().getType().equals("int")) {
            asm += "sw $a0 " + localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getOffset() + "($t1)\n";
        } else if (localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getType().getType().equals("bool")) {
            asm += "sb $a0 " + localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getOffset() + "($t1)\n";
        }
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        // check for variable with such id in current level and below
        STentry entry = env.getSymbolTableManager().getLastEntry(id.getId(), env.getNestingLevel());

        if (entry==null)
            res.add(new SemanticError("Variable "+this.id.getId()+" not declared at line "+ line +"."));

        // else, if variable exists, check the exp
        else if(this.exp != null)
                res.addAll(this.exp.checkSemantics(env));

        st = entry;
        return res;
    }
}
