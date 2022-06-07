package ast;

import java.util.ArrayList;
import java.util.Collection;

import util.Environment;
import util.SemanticError;

public interface Node {

    String toPrint(String indent);

    Node typeCheck();

    String codeGeneration();

    ArrayList<SemanticError> checkSemantics(Environment env);

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