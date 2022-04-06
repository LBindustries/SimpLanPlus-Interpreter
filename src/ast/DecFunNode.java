package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class DecFunNode implements Node {

    private TypeNode type;
    private IdNode id;
    private ArrayList<Node> args;
    private BlockNode block;

    public DecFunNode(Node type, Node id, ArrayList<Node> args, Node block) {
        this.type = (TypeNode) type;
        this.id = (IdNode) id;
        this.args = args;
        this.block = (BlockNode) block;
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
        res += block.toPrint(indent + " ");
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
        HashMap<String, STentry> st = env.symTable.get(env.nestingLevel);
        // Check if function is not already declared
        if(env.insideFunction){
            res.add(new SemanticError("Function id "+this.id.getId()+" is being declared inside a function. This is not allowed."));
            return res;
        }
        int argNumber = 0;
        if(this.args!=null){
            argNumber = this.args.size();
        }
        if(st.put(this.id.getId(), new STentry(env.nestingLevel, type, env.offset--, true, argNumber)) != null){
            res.add(new SemanticError("Function id "+this.id.getId()+" already declared."));
            return res;
        }
        env.insideFunction=true;
        // Begin analyzing args
        env.nestingLevel++;
        st = new HashMap<String, STentry>();
        env.symTable.add(st);
        if(this.args.size() >0){
            for(Node arg:this.args){
                res.addAll(arg.checkSemantics(env));
            }
        }
        if(this.block!=null){
            res.addAll(this.block.checkSemanticsFunction(env));
        }
        env.symTable.remove(env.nestingLevel--);
        env.insideFunction=false;
        return res;
    }
}
