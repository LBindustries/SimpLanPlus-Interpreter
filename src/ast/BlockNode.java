package ast;

import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockNode implements Node {

    /*
    Nella sintassi di SLP, le dichiarazioni delle variabili / funzioni devono venire fatte prima
    degli statement.
     */
    private ArrayList<Node> declarations;
    private ArrayList<Node> statements;
    private Environment localenv;

    public BlockNode(ArrayList<Node> declarations, ArrayList<Node> statements) {
        this.declarations = declarations;
        this.statements = statements;
    }

    @Override
    public String toPrint(String indent) {
        String res = "";
        if (this.declarations != null) {
            for (Node dec : declarations) {
                res += dec.toPrint(indent + " ");
            }
        }
        if (this.statements != null) {
            for (Node dec : statements) {
                res += dec.toPrint(indent + " ");
            }
        }
        return "\n"+indent + "Block" + res;
    }

    @Override
    public TypeNode typeCheck(Environment env) {
        this.checkSemantics(env);
        if(this.declarations!=null){
            for(Node dec: declarations){
                dec.typeCheck(localenv);
            }
        }
        TypeNode T = new VoidTypeNode();
        int counter = 1; // TODO: check if actually correct
        if(this.statements!=null){
            for(Node s: statements){
                StatementNode tmp = (StatementNode) s;
                T = s.typeCheck(localenv);
                if(tmp.getStatement() instanceof ReturnNode){
                    break;
                }
                if(tmp.getStatement() instanceof IteNode){
                    if(!T.getType().equals("void")){
                        break;
                    }
                }
                counter++;
            }
        }
        for(String id: localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).keySet()){
            if(!localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).get(id).getEffect().isUsed()){
                System.out.println("Warning: symbol "+id+" is unused.");
            }
        }
        return T;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv2) {
        String asm = ";Block\n";
        if (this.declarations != null) {
            asm += ";Variable Declaration\nli $t1 " + localenv.getDecSpace() + "\n";
            asm += "sub $sp $sp $t1\n";
            asm += "push $fp\n";
            asm += "mov $fp $sp\n";
            for (Node declaration : this.declarations) {
                asm += declaration.codeGeneration(labgen, this.localenv);
            }

        }else {
            asm += "push $fp\n";
            asm += "mov $fp $sp\n";
        }
        if (this.statements != null) {
            for (Node statement : this.statements) {
                asm += statement.codeGeneration(labgen, this.localenv);
            }
        }
        asm += "pop $fp\n";
        asm += "li $t1 " + localenv.getDecSpace() + "\n";
        asm += "add $sp $sp $t1\n";
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        env.incNestingLevel(1);
        HashMap<String, STentry> level = new HashMap<String, STentry>();
        env.getSymbolTableManager().addLevel(level);
        env.setOffset(4);

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        if(this.declarations!=null && this.declarations.size()>0){
            for(Node n: this.declarations){
                res.addAll(n.checkSemantics(env));
            }
        }
        if(this.statements!=null && this.statements.size()>0){
            for(Node n: this.statements){
                res.addAll(n.checkSemantics(env));
            }
        }
        this.localenv = new Environment(env);
        env.getSymbolTableManager().removeLevel(env.decNestingLevel(1));

        return res;
    }
}
