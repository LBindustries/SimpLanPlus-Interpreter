package ast;

import ast.Types.*;
import util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DecFunNode implements Node {

    private TypeNode type;
    private IdNode id;
    private ArrayList<Node> args;
    private ArrayList<Node> decs;
    private ArrayList<Node> stms;
    private Environment localenv;
    private int line;
    // Function declaration node.
    public DecFunNode(Node type, Node id, ArrayList<Node> args, ArrayList<Node> decs, ArrayList<Node> stms, int line) {
        this.type = (TypeNode) type;
        this.id = (IdNode) id;
        this.args = args;
        this.decs = decs;
        this.stms = stms;
        this.line = line;
    }

    @Override
    public String toPrint(String indent) {
        String t = "void";
        if (this.type != null) {
            t = this.type.toPrint(indent + " ");
        }
        String res = "\n" + indent + "DecFun " + t + this.id.toPrint(indent + " ");
        if (this.args.size() != 0) {
            for (Node arg : this.args) {
                res += arg.toPrint(indent + " ");
            }
        }
        if (this.decs.size() != 0) {
            for (Node dec : this.decs) {
                res += dec.toPrint(indent + " ");
            }
        }
        if (this.stms.size() != 0) {
            for (Node stm : this.stms) {
                res += stm.toPrint(indent + " ");
            }
        }
        return res;
    }

    @Override
    public TypeNode typeCheck(Environment env) throws TypeCheckException {
        if (this.decs != null) {
            for (Node dec : this.decs) {
                // Do declarations typechecking
                dec.typeCheck(localenv);
            }
        }
        // Does this function have a return somewhere?
        boolean fuse = false;
        // Typecheck statements
        if (this.stms != null) {
            for (Node stm : this.stms) {
                TypeNode type = stm.typeCheck(localenv);
                StatementNode tmp = (StatementNode) stm;
                // Return on level 0 of function
                if (tmp.getStatement() instanceof ReturnNode) {
                    fuse = true;
                    if (!Objects.equals(type.getType(), this.type.getType())) {
                        throw new TypeCheckException("[!] Return type mismatch in function " + this.id.getId() + ": expected " + this.type.getType() + ", got " + type.getType()+ " in function declared at line "+line+".");
                    }
                } else if (tmp.getStatement() instanceof BlockNode && !Objects.equals(type.getType(), "void")) {
                    // Return in a nested block with return != void
                    fuse = true;
                    if (!Objects.equals(type.getType(), this.type.getType())) {
                        throw new TypeCheckException("[!] Return type mismatch in function " + this.id.getId() + ": expected " + this.type.getType() + ", got " + type.getType()+ " in function declared at line "+line+".");
                    }
                } else if (tmp.getStatement() instanceof IteNode  && !Objects.equals(type.getType(), "void")) {
                    // Return in a ITE with return != void
                    fuse = true;
                    if (!Objects.equals(type.getType(), this.type.getType())) {
                        throw new TypeCheckException("[!] Return type mismatch in function " + this.id.getId() + ": expected " + this.type.getType() + ", got " + type.getType()+ " in function declared at line "+line+".");
                    }
                }
            }
        }
        for (String id : localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).keySet()) {
            // Deal with unused symbols.
            if (!localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).get(id).getEffect().isUsed() && !Objects.equals(id, this.id.getId())) {
                if(!localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).get(id).isFn()){
                    System.out.println("[W] Symbol " + id + " is unused in function that starts at line "+line+".");
                }
            }
            // Set functions as used if used inside of themselves.
            if(localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).get(id).getEffect().isUsed() &&
                    localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).get(id).isFn()){
                env.getSymbolTableManager().getLevel(env.getNestingLevel()).get(id).getEffect().setUsed();
            }
        }
        if (!fuse && !Objects.equals(this.type.getType(), "void")) {
            // If function is not type void but no non-void returns have been detected
            throw new TypeCheckException("[!] Return type mismatch in function " + this.id.getId() + ": expected " + this.type.getType() + ", got void in function declared at line "+line+".");
        } else {
            // Return function type
            switch (type.getType()) {
                case "int":
                    return new IntTypeNode();
                case "bool":
                    return new BoolTypeNode();
                case "void":
                    return new VoidTypeNode();
            }
        }
        return null;
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv1) {
        String asm = ";Function\n";
        String jump_label = labgen.new_label("JUMP_FUNC");
        asm += "jal " + jump_label + "\n";
        //asm += id.getId() + ":\n";
        asm += "label " + id.getId() + ":\n";

        // Arg space that needs to be allocated
        int DecSpace = localenv.getDecSpace();
        int nVar = 0;
        if (this.args.size() > 0) {
            for (Node arg : this.args) {
                ArgNode t = (ArgNode) arg;
                DecSpace -= ( t.getType().getType().equals("int")? 4 : 1 );
                if(t.isVar()){
                    nVar++;
                }
            }
        }

        // Declarations
        if (this.decs != null && this.decs.size() > 0) {
            asm += ";Variable Declaration\n";
            //li $t1 " + localenv.getDecSpace() + "\n";
            asm += "subi $sp $sp " + DecSpace + "\n";
            asm += "mov $t1 $ra\n";
            asm += "jal " + id.getId() + "_end\n";
            asm += "label " + id.getId() + "_start :\n";
            asm += "push $ra\n";
            asm += "push $fp\n";
            asm += "mov $fp $sp\n";
            asm += "push $t1\n";
            for (Node declaration : this.decs) {
                asm += declaration.codeGeneration(labgen, this.localenv);
            }
        }else {
            asm += "mov $t1 $ra\n";
            asm += "jal " + id.getId() + "_end\n";
            asm += "label " + id.getId() + "_start :\n";
            asm += "push $ra\n";
            asm += "push $fp\n";
            asm += "mov $fp $sp\n";
            asm += "push $t1\n";
        }
        if (this.stms != null) {
            for (Node statement : this.stms) {
                // statements codegen
                asm += statement.codeGeneration(labgen, this.localenv);
            }
        }

        asm += "lw $t1 4($fp)\n";
        asm += "jr $t1\n";
        asm += "label " + id.getId() + "_end :\n";
        asm += "jal " + id.getId() + "_start\n";
        // Arguments asm
        if (this.args != null) {
            for (Node arg : this.args) {
                ArgNode argNode = (ArgNode) arg;
                if(argNode.isVar()){
                    if(argNode.getType().getType().equals("int")){
                        asm += "lw $t1 " + localenv.getSymbolTableManager().getLastEntry(argNode.getId().getId(), localenv.getNestingLevel()).getOffset() + "($fp)\n";
                        asm += "lw $t2 " + (localenv.getSymbolTableManager().getLastEntry(argNode.getId().getId(), localenv.getNestingLevel()).getOffset()+4) + "($fp)\n";
                        asm += "sw $t1 0($t2)\n";
                    } else if (argNode.getType().getType().equals("bool")) {
                        asm += "lb $t1 " + localenv.getSymbolTableManager().getLastEntry(argNode.getId().getId(), localenv.getNestingLevel()).getOffset() + "($fp)\n";
                        asm += "lw $t2 " + (localenv.getSymbolTableManager().getLastEntry(argNode.getId().getId(), localenv.getNestingLevel()).getOffset()+1) + "($fp)\n";
                        asm += "sb $t1 0($t2)\n";
                    }

                }
            }
        }
        // Function cleanup
        asm += "pop $ra\n";
        asm += "pop $fp\n";
        asm += "pop $t1\n";
        //asm += "li $t1 " + localenv.getDecSpace() + "\n";
        asm += "addi $sp $sp " + (localenv.getDecSpace() + (nVar * 4)) + "\n";
        asm += "jr $ra\n";
        asm += "label " + jump_label + ":\n";
        //asm += jump_label + ":\n";
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        HashMap<String, STentry> st = env.getSymbolTableManager().getLevel(env.getNestingLevel());
        // Check if function is not already declared


        ArrayList<ArgTypeNode> argTypeNodes = new ArrayList<ArgTypeNode>();

        if (this.args != null) {
            for (Node arg : this.args) {
                ArgNode argNode = (ArgNode) arg;
                argTypeNodes.add(new ArgTypeNode(argNode.getType().getType(), argNode.isVar()));
            }
        }

        FunctionTypeNode t = new FunctionTypeNode(type.getType(), argTypeNodes);
        if (st.put(this.id.getId(), new STentry(env.getNestingLevel(), t, 0, new Effect(true), true)) != null) {
            res.add(new SemanticError("[!] Function id " + this.id.getId() + " already declared."));
            return res;
        }

        // Begin analyzing args

        st = new HashMap<String, STentry>();
        Environment localenv = new Environment();
        // Add function name to the new env
        for( String key : env.getSymbolTableManager().getSymbolTable().get(0).keySet()){
            if(env.getSymbolTableManager().getSymbolTable().get(0).get(key).isFn()){
                st.put(key, new STentry(env.getNestingLevel(), env.getSymbolTableManager().getSymbolTable().get(0).get(key).getType(), 0, new Effect(true), true));
            }
        }
        localenv.incNestingLevel(1);
        localenv.getSymbolTableManager().addLevel(st);

        // Declarations offset calculations
        int offsetDecs = 8;
        if (this.decs.size() > 0) {
            for (Node decNode : this.decs) {
                DecVarNode dec = (DecVarNode) decNode;
                offsetDecs += (dec.getType().getType().equals("int")? 4 : 1);
            }
        }

        localenv.setOffset(offsetDecs);
        localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).put(this.id.getId(), new STentry(localenv.getNestingLevel(), t, 0, new Effect(true), true));
        if (this.args.size() > 0) {
            for (Node arg : this.args) {
                res.addAll(arg.checkSemantics(localenv));
            }
        }
        localenv.setOffset(8);
        if (this.decs.size() > 0) {
            for (Node dec : this.decs) {
                res.addAll(dec.checkSemantics(localenv));
            }
        }
        if (this.stms.size() > 0) {
            for (Node stm : this.stms) {
                res.addAll(stm.checkSemantics(localenv));
            }
        }
        this.localenv = localenv;
        return res;
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        if(this.stms!=null && this.stms.size()>0){
            for(Node n:this.stms){
                n.setupBreaks(breaks);
            }
        }
    }
}
