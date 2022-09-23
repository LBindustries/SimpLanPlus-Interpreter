package ast;

import ast.Types.TypeNode;
import util.Effect;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DecVarNode implements Node{


    private TypeNode type;
    private IdNode id;
    private Node exp;
    private int line;

    public DecVarNode(TypeNode type, IdNode id, Node exp, int line){
        this.type = type;
        this.id = id;
        this.exp = exp;
        this.line = line;
    }

    public DecVarNode(TypeNode type, IdNode id, int line){
        this.type = type;
        this.id = id;
        this.line = line;
    }

    public TypeNode getType() {
        return type;
    }

    @Override
    public String toPrint(String indent) {
        if(this.exp!=null){
            return "\n"+indent+"DecVar "+this.type.toPrint(indent+" ")+this.id.toPrint(indent+" ")+this.exp.toPrint(indent+" ");
        }
        return "\n"+indent+"DecVar "+this.type.toPrint(indent+" ")+this.id.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        if(this.exp!=null && !Objects.equals(this.type.getType(), exp.typeCheck(env).getType())){
            System.out.println("Type mismatch: symbol "+id.getId()+" and expression are not matching types at line "+line+".");
            System.exit(0);
        }
        if(this.exp!=null){
            env.getSymbolTableManager().getLastEntry(id.getId(), env.getNestingLevel()).getEffect().setInitialized();
        }
        return null;
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        return;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = "";
        if(exp!=null){
            asm = exp.codeGeneration(labgen, localenv);
            if( type.getType().equals("int")) {
                asm += "sw $a0 " + localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getOffset() + "($fp)\n";
            } else if (type.getType().equals("bool")) {
                asm += "sb $a0 " + localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getOffset() + "($fp)\n";
            }
        }
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        HashMap<String, STentry> st = env.getSymbolTableManager().getLevel(env.getNestingLevel());
        if(st.put(this.id.getId(), new STentry(env.getNestingLevel(), type, env.decOffset((Objects.equals(type.getType(), "int")? 4:1)), new Effect(false), false)) != null){
            res.add(new SemanticError("Variable id "+this.id.getId()+" already declared at line "+ this.line +"."));
        }
        if(this.exp!=null){
            res.addAll(this.exp.checkSemantics(env));
            st.get(this.id.getId()).getEffect().setInitialized();
        }
        return res;
    }
}
