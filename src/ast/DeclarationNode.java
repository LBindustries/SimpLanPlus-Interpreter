package ast;

import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

import java.util.ArrayList;

public class DeclarationNode implements Node{

    private Node dec;

    public DeclarationNode(Node dec){
        this.dec = dec;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"Decl"+dec.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(SymbolTableManager stm) {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        if(dec==null){
            return new ArrayList<SemanticError>();
        }
        return this.dec.checkSemantics(env);
    }
}
