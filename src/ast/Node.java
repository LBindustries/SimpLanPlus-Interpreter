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

}