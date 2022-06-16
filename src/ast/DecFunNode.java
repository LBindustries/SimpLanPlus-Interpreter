package ast;

import util.Effect;
import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class DecFunNode implements Node {

    private TypeNode type;
    private IdNode id;
    private ArrayList<Node> args;
    private ArrayList<Node> decs;
    private ArrayList<Node> stms;

    public DecFunNode(Node type, Node id, ArrayList<Node> args, ArrayList<Node> decs, ArrayList<Node> stms) {
        this.type = (TypeNode) type;
        this.id = (IdNode) id;
        this.args = args;
        this.decs = decs;
        this.stms = stms;
    }

    @Override
    public String toPrint(String indent) {
        String t="void";
        if(this.type!=null){
            t = this.type.toPrint(indent + " ");
        }
        String res = "\n"+indent + "DecFun "+t+this.id.toPrint(indent+" ");
        if (this.args.size() != 0) {
            for (Node arg : this.args) {
                res += arg.toPrint(indent + " ");
            }
        }
        if (this.decs.size() != 0) {
            for (Node dec : this.decs) {
                res += dec.toPrint(indent + " ");
            }
        }
        if (this.stms.size() != 0) {
            for (Node stm : this.stms) {
                res += stm.toPrint(indent + " ");
            }
        }
        return res;
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        HashMap<String, STentry> st = env.getSymbolTableManager().getLevel(env.getNestingLevel());
        // Check if function is not already declared
        int argNumber = 0;
        if(this.args!=null){
            argNumber = this.args.size();
        }
        if(st.put(this.id.getId(), new STentry(env.getNestingLevel(), type, env.decOffset(1), new Effect(true))) != null){
            res.add(new SemanticError("Function id "+this.id.getId()+" already declared."));
            return res;
        }
        // Begin analyzing args
        env.incNestingLevel(1);
        st = new HashMap<String, STentry>();
        env.getSymbolTableManager().addLevel(st);
        env.getSymbolTableManager().getLevel(env.getNestingLevel()).put(this.id.getId(), new STentry(env.getNestingLevel(), type, env.decOffset(1), new Effect(true)));
        if(this.args.size() >0){
            for(Node arg:this.args){
                res.addAll(arg.checkSemantics(env));
            }
        }
        if(this.decs.size()>0){
            for(Node dec:this.decs){
                res.addAll(dec.checkSemantics(env));
            }
        }
        if(this.stms.size()>0){
            for(Node stm:this.stms){
                res.addAll(stm.checkSemantics(env));
            }
        }
        env.getSymbolTableManager().removeLevel(env.decNestingLevel(1));
        return res;
    }
}
