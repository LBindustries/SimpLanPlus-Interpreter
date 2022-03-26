package ast;

import util.Environment;
import util.SemanticError;

import java.util.ArrayList;

public class DecFunNode implements Node {

    private Node type;
    private Node id;
    private ArrayList<Node> args;
    private Node block;

    public DecFunNode(Node type, Node id, ArrayList<Node> args, Node block) {
        this.type = type;
        this.id = id;
        this.args = args;
        this.block = block;
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
        return null;
    }
}
