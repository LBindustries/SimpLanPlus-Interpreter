package ast.Types;

import java.util.ArrayList;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

public class VoidTypeNode extends TypeNode {

    public VoidTypeNode () {
        super("void");
    }

    public String toPrint(String s) {
        return s+"VoidType\n";
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
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        return new ArrayList<SemanticError>();
    }

}