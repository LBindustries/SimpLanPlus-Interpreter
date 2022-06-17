package ast;

import ast.Types.*;
import util.Effect;
import util.Environment;
import util.SemanticError;
import util.SymbolTableManager;

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

    public DecFunNode(Node type, Node id, ArrayList<Node> args, ArrayList<Node> decs, ArrayList<Node> stms) {
        this.type = (TypeNode) type;
        this.id = (IdNode) id;
        this.args = args;
        this.decs = decs;
        this.stms = stms;
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
    public TypeNode typeCheck(Environment env) {
        if (this.decs != null) {
            for (Node dec : this.decs) {
                dec.typeCheck(localenv);
            }
        }
        boolean fuse = false;
        if (this.stms != null) {
            for (Node stm : this.stms) {
                TypeNode type = stm.typeCheck(localenv);
                StatementNode tmp = (StatementNode) stm;
                if (tmp.getStatement() instanceof ReturnNode) {
                    fuse = true;
                    if (!Objects.equals(type.getType(), this.type.getType())) {
                        System.out.println("Return type mismatch in function " + this.id.getId() + ": expected " + this.type.getType() + ", got " + type.getType());
                        System.exit(0);
                    }
                }
            }
        }
        for(String id: localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).keySet()){
            if(!localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).get(id).getEffect().isUsed() && !Objects.equals(id, this.id.getId())){
                System.out.println("Warning: symbol "+id+" is unused.");
            }
        }
        if (!fuse && !Objects.equals(this.type.getType(), "void")) {
            System.out.println("Return type mismatch in function " + this.id.getId() + ": expected " + this.type.getType() + ", got void");
            System.exit(0);
        } else {
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
    public String codeGeneration() {
        return null;
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

        if (st.put(this.id.getId(), new STentry(env.getNestingLevel(), t, env.decOffset(1), new Effect(true))) != null) {
            res.add(new SemanticError("Function id " + this.id.getId() + " already declared."));
            return res;
        }
        // Begin analyzing args

        st = new HashMap<String, STentry>();
        Environment localenv = new Environment();
        localenv.incNestingLevel(1);
        localenv.getSymbolTableManager().addLevel(st);
        localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).put(this.id.getId(), new STentry(localenv.getNestingLevel(), type, localenv.decOffset(1), new Effect(true)));
        if (this.args.size() > 0) {
            for (Node arg : this.args) {
                res.addAll(arg.checkSemantics(localenv));
            }
        }
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
}
