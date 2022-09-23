package ast;

import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class DeclarationNode implements Node{

    private Node dec;
    private int line;

    public DeclarationNode(Node dec, int line){
        this.dec = dec;
        this.line = line;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent+"Decl"+dec.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        return dec.typeCheck(env);
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        return dec.codeGeneration(labgen, localenv);
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        if(dec==null){
            return new ArrayList<SemanticError>();
        }
        return this.dec.checkSemantics(env);
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        dec.setupBreaks(breaks);
    }
}
