package ast.ExpNodes;

import ast.CallNode;
import ast.Node;
import ast.TypeNode;
import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

import java.util.ArrayList;

public class CallExpNode implements Node {

    private CallNode call;

    public CallExpNode(Node call){
        this.call = (CallNode) call;
        this.call.setIsExp(true);
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"Call "+this.call.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(SymbolTableManager stm) {
        return call.typeCheck(stm);
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return call.checkSemantics(env);
    }
}
