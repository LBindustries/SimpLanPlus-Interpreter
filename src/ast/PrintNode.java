package ast;

import ast.Types.FunctionTypeNode;
import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;
import java.util.Objects;

public class PrintNode implements Node{
    //'print' exp;

    private Node exp;
    private int line;

    public PrintNode(Node exp, int line){
        this.exp = exp;
        this.line = line;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "Print" + exp.toPrint(indent);
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        TypeNode type = exp.typeCheck(env);
        if(type instanceof VoidTypeNode || (type instanceof FunctionTypeNode && Objects.equals(type.getType(), "void"))){
            System.out.println("[!] Attempt to print void type at line "+line+".");
            System.exit(1);
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
