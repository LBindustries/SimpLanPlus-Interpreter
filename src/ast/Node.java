package ast;

import java.util.ArrayList;

import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;
import util.TypeCheckException;

public interface Node {

    String toPrint(String indent);

    TypeNode typeCheck(Environment env) throws TypeCheckException;

    String codeGeneration(LabelGenerator labgen, Environment localenv);

    ArrayList<SemanticError> checkSemantics(Environment env);

    void setupBreaks(ArrayList<Integer> breaks);

    private int myMax(Node s1, Node s2){
        if(s1.getClass().equals(VoidTypeNode.class))
            return 2;
        if(s2.getClass().equals(VoidTypeNode.class))
            return 1;
        if(s1.getClass().equals(s2.getClass()))
            return 0;

        return -1;
    }
}