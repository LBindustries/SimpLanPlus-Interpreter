package ast;

import ast.Types.FunctionTypeNode;
import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;
import util.TypeCheckException;

import java.util.ArrayList;
import java.util.Objects;

public class PrintNode implements Node{

    private Node exp;
    private int line;
    // Print instruction
    public PrintNode(Node exp, int line){
        this.exp = exp;
        this.line = line;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "Print" + exp.toPrint(indent);
    }

    @Override
    public TypeNode typeCheck(Environment env) throws TypeCheckException {
        TypeNode type = exp.typeCheck(env);
        // Check for type != void or function that returns void
        if(type instanceof VoidTypeNode || (type instanceof FunctionTypeNode && Objects.equals(type.getType(), "void"))){
            throw new TypeCheckException("[!] Attempt to print void type at line "+line+".");
        }
        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = exp.codeGeneration(labgen, localenv);
        asm += ";Print\nprint $a0\n";
        return asm;
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        return;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return exp.checkSemantics(env);
    }
}
