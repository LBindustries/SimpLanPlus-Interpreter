package ast;

import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;
import util.TypeCheckException;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockNode implements Node {
    private ArrayList<Node> declarations;
    private ArrayList<Node> statements;
    private Environment localenv;
    private int line;
    // Code block inside program
    public BlockNode(ArrayList<Node> declarations, ArrayList<Node> statements, int line) {
        this.declarations = declarations;
        this.statements = statements;
        this.line = line;
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
        return "\n" + indent + "Block" + res;
    }

    @Override
    public TypeNode typeCheck(Environment env) throws TypeCheckException {
        this.checkSemantics(env);
        if (this.declarations != null) {
            for (Node dec : declarations) {
                dec.typeCheck(localenv);
            }
        }
        TypeNode T = new VoidTypeNode();
        TypeNode first = null;
        int counter = 1;
        // Typecheck the statements inside the block. If different return types are detected, an error is raised.
        if (this.statements != null) {
            for (Node s : statements) {
                StatementNode tmp = (StatementNode) s;
                T = s.typeCheck(localenv);
                if(tmp.getStatement() instanceof ReturnNode && first == null){
                    first = T;
                }
                else if(tmp.getStatement() instanceof ReturnNode){
                    if(!T.getType().equals(first.getType())){
                        throw new TypeCheckException("[!] Found returns of mismatched type in block");
                    }
                }
                /* if(tmp.getStatement() instanceof IteNode){
                    if(!T.getType().equals("void")){
                        break;
                    }
                 }*/
                counter++;
            }
        }
        // Block-related unused variables detection
        for (String id : localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).keySet()) {
            if (!localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).get(id).getEffect().isUsed()) {
                System.out.println("[W] Symbol " + id + " is unused in block that starts at line "+this.line+".");
            }
        }
        if(first == null){
            // If no returns are inside block, returns void.
            first = new VoidTypeNode();
        }
        return first;
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks) {
        if (this.statements != null && this.statements.size() > 0) {
            for (Node stm : this.statements) {
                stm.setupBreaks(breaks);
            }
        }
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv2) {
        String asm = ";Block\n";
        if (this.declarations != null && this.declarations.size() > 0) {
            asm += ";Variable Declaration\n";
            //li $t1 " + localenv.getDecSpace() + "\n";
            asm += "subi $sp $sp " + localenv.getDecSpace() + "\n";
            asm += "push $fp\n";
            asm += "mov $fp $sp\n";
            for (Node declaration : this.declarations) {
                asm += declaration.codeGeneration(labgen, this.localenv);
            }

        } else {
            asm += "push $fp\n";
            asm += "mov $fp $sp\n";
        }
        if (this.statements != null) {
            for (Node statement : this.statements) {
                asm += statement.codeGeneration(labgen, this.localenv);
            }
        }
        asm += "pop $fp\n";
        //asm += "li $t1 " + localenv.getDecSpace() + "\n";
        asm += "addi $sp $sp " + localenv.getDecSpace() + "\n";
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        env.incNestingLevel(1);
        HashMap<String, STentry> level = new HashMap<String, STentry>();
        env.getSymbolTableManager().addLevel(level);
        env.setOffset(4);

        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        if (this.declarations != null && this.declarations.size() > 0) {
            for (Node n : this.declarations) {
                res.addAll(n.checkSemantics(env));
            }
        }
        if (this.statements != null && this.statements.size() > 0) {
            for (Node n : this.statements) {
                res.addAll(n.checkSemantics(env));
            }
        }
        this.localenv = new Environment(env);
        env.getSymbolTableManager().removeLevel(env.decNestingLevel(1));

        return res;
    }
}
