package ast;

import java.util.ArrayList;
import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

public class VoidTypeNode extends TypeNode {

    public VoidTypeNode () {
        super("void");
    }

    public String toPrint(String s) {
        return s+"VoidType\n";
    }

    //non utilizzato
    public TypeNode typeCheck(SymbolTableManager stm) {
        return null;
    }

    //non utilizzato
    public String codeGeneration() {
        return "";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        return new ArrayList<SemanticError>();
    }

}