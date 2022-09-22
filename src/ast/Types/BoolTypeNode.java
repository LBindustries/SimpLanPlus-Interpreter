package ast.Types;

import java.util.ArrayList;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

public class BoolTypeNode extends TypeNode {

    public BoolTypeNode () {
        super("bool");
    }

    public String toPrint(String s) {
        return s+"BoolType\n";
    }

    //non utilizzato
    public TypeNode typeCheck(Environment env) {
        return null;
    }

    //non utilizzato
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        return "";
    }


    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env, int line) {

        return new ArrayList<SemanticError>();
    }

}