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

    /*
     *   La funzione ritorna:
     *       - 0 se i tipi sono uguali;
     *       - 1 se va tenuto il tipo del then, in quanto l'else è void
     *       - 2 se va tenuto il tipo dell'else, in quanto il then è void
     *       - -1 se sono diversi fra loro (int - bool)
     */


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