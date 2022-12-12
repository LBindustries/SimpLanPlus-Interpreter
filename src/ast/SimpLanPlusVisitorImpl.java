package ast;

import ast.ExpNodes.*;
import ast.Types.TypeNode;
import ast.Types.VoidTypeNode;
import parser.SimpLanPlusBaseVisitor;
import parser.SimpLanPlusParser;

import java.util.ArrayList;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {

    @Override public Node visitProgram(SimpLanPlusParser.ProgramContext ctx){
        ProgramNode res;
        ArrayList<Node> declarations = new ArrayList<Node>();
        ArrayList<Node> statements = new ArrayList<Node>();
        // For each declaration...
        for(SimpLanPlusParser.DeclarationContext dc: ctx.declaration()){
            declarations.add(visit(dc));
        }
        // For each statement...
        for(SimpLanPlusParser.StatementContext sc: ctx.statement()){
            statements.add(visit(sc));
        }
        res = new ProgramNode(declarations, statements);
        return res;
    }

    @Override public Node visitBlock(SimpLanPlusParser.BlockContext ctx) {
        BlockNode res;
        ArrayList<Node> declarations = new ArrayList<Node>();
        ArrayList<Node> statements = new ArrayList<Node>();
        // For each declaration in block...
        for(SimpLanPlusParser.DecVarContext dc: ctx.decVar()){
            declarations.add(visit(dc));
        }
        // For each statement in block...
        for(SimpLanPlusParser.StatementContext sc: ctx.statement()){
            statements.add(visit(sc));
        }
        res = new BlockNode(declarations, statements);
        return res;
    }

    @Override public Node visitDeclaration(SimpLanPlusParser.DeclarationContext ctx){
        DeclarationNode res;
        // Create a new declaration node
        if(ctx.decFun()!=null){
            res = new DeclarationNode(visit(ctx.decFun()), ctx.getStart().getLine());
        }
        else if(ctx.decVar()!=null){
            res = new DeclarationNode(visit(ctx.decVar()), ctx.getStart().getLine());
        }
        else{
            return null;
        }
        return res;
    }

    @Override public Node visitStatement(SimpLanPlusParser.StatementContext ctx){
        Node res;
        // Create a new generic statement node
        if(ctx.block()!=null){
            res = visit(ctx.block());
        }
        else if(ctx.call()!=null){
            res = visit(ctx.call());
        }
        else if(ctx.ite()!=null){
            res = visit(ctx.ite());
        }
        else if(ctx.ret()!=null){
            res = visit(ctx.ret());
        }
        else if(ctx.print()!=null){
            res = visit(ctx.print());
        }
        else if(ctx.assignment()!=null){
            res = visit(ctx.assignment());
        }
        else{
            return null;
        }
        return new StatementNode(res, ctx.getStart().getLine());
    }

    @Override public Node visitDecVar(SimpLanPlusParser.DecVarContext ctx){
        DecVarNode res;
        // Create a decvarnode
        if(ctx.exp()!=null){
           res = new DecVarNode((TypeNode) visit(ctx.type()), new IdNode(ctx.ID().getText(), ctx.getStart().getLine()), visit(ctx.exp()), ctx.getStart().getLine());
        }
        else{
            res = new DecVarNode((TypeNode) visit(ctx.type()), new IdNode(ctx.ID().getText(), ctx.getStart().getLine()), ctx.getStart().getLine());
        }

        return res;
    }

    @Override public Node visitDecFun(SimpLanPlusParser.DecFunContext ctx){
        // Create a decfunnode
        DecFunNode res;
        ArrayList<Node> args = new ArrayList<Node>();
        ArrayList<Node> decs = new ArrayList<Node>();
        ArrayList<Node> stms = new ArrayList<Node>();
        for(SimpLanPlusParser.ArgContext atx: ctx.arg()){
            // For each of the args...
            args.add(visit(atx));
        }
        for(SimpLanPlusParser.DecVarContext dtx: ctx.decVar()){
            // For each of the declarations...
            decs.add(visit(dtx));
        }
        for(SimpLanPlusParser.StatementContext stx: ctx.statement()){
            // For each of the statements...
            stms.add(visit(stx));
        }
        if(ctx.type()!=null){
            res = new DecFunNode(visit(ctx.type()), new IdNode(ctx.ID().getText(), ctx.getStart().getLine()), args, decs, stms, ctx.getStart().getLine());
        }
        else{
            res = new DecFunNode(new VoidTypeNode(), new IdNode(ctx.ID().getText(), ctx.getStart().getLine()), args, decs, stms, ctx.getStart().getLine());
        }

        return res;
    }

    @Override public Node visitType(SimpLanPlusParser.TypeContext ctx){
        // Typenode creation
        return new TypeNode(ctx.getText());
    }

    @Override public Node visitArg(SimpLanPlusParser.ArgContext ctx){
        // Args creation
        boolean isVar = false;
        if(ctx.children.get(0).toString().equals("var")){
            // Set isVar flag
            isVar = true;
        }
        return new ArgNode((TypeNode)visit(ctx.type()), new IdNode(ctx.ID().getText(), ctx.getStart().getLine()), isVar, ctx.getStart().getLine());
    }

    @Override public Node visitAssignment(SimpLanPlusParser.AssignmentContext ctx){
        // Create AssignmentNode
        return new AssignmentNode(new IdNode(ctx.ID().getText(), ctx.getStart().getLine()), visit(ctx.exp()), ctx.getStart().getLine());
    }

    @Override public Node visitPrint(SimpLanPlusParser.PrintContext ctx){
        // Create PrintNode
        return new PrintNode(visit(ctx.exp()), ctx.getStart().getLine());
    }

    @Override public Node visitRet(SimpLanPlusParser.RetContext ctx){
        // Create ReturnNode
        if(ctx.exp()!=null){
            return new ReturnNode(visit(ctx.exp()), ctx.getStart().getLine());
        }
        return new ReturnNode(null, ctx.getStart().getLine());
    }

    @Override public Node visitBaseExp(SimpLanPlusParser.BaseExpContext ctx){
        // Create BaseExpNode
        return new BaseExpNode(visit(ctx.exp()), ctx.getStart().getLine());
    }

    @Override public Node visitIte(SimpLanPlusParser.IteContext ctx){
        // Create ITENode
        IteNode res;
        if(ctx.statement().size()==1){
            res = new IteNode(visit(ctx.exp()), visit(ctx.statement().get(0)), ctx.getStart().getLine());
        }
        else if(ctx.statement().size()==2){
            res = new IteNode(visit(ctx.exp()), visit(ctx.statement().get(0)), visit(ctx.statement().get(1)), ctx.getStart().getLine());
        }
        else{
            return null;
        }
        return res;
    }

    @Override public Node visitCall(SimpLanPlusParser.CallContext ctx){
        // Create CallNode
        if(ctx.exp().isEmpty()){
            return new CallNode(new IdNode(ctx.ID().getText(), ctx.getStart().getLine()),ctx.getStart().getLine());
        }
        ArrayList<Node> params = new ArrayList<Node>();
        for(SimpLanPlusParser.ExpContext ex: ctx.exp()){
            params.add(visit(ex));
        }
        return new CallNode(new IdNode(ctx.ID().getText(), ctx.getStart().getLine()), params, ctx.getStart().getLine());
    }

    @Override public Node visitNegExp(SimpLanPlusParser.NegExpContext ctx){
        // CreateNegExpNode
        return new NegExpNode(visit(ctx.exp()), ctx.getStart().getLine());
    }

    @Override public Node visitNotExp(SimpLanPlusParser.NotExpContext ctx){
        // Create NotExpNode
        return new NotExpNode(visit(ctx.exp()), ctx.getStart().getLine());
    }

    @Override public Node visitDerExp(SimpLanPlusParser.DerExpContext ctx){
        // Create DerExpNode
        return new DerExpNode(new IdNode(ctx.ID().getText(), ctx.getStart().getLine()), ctx.getStart().getLine());
    }

    @Override public Node visitBinExp(SimpLanPlusParser.BinExpContext ctx){
        // Create BinExpNode
        return new BinExpNode(ctx.op.getText(), visit(ctx.left), visit(ctx.right), ctx.getStart().getLine());
    }

    @Override public Node visitCallExp(SimpLanPlusParser.CallExpContext ctx){
        // Create CallExpNode
        return new CallExpNode(visit(ctx.call()), ctx.getStart().getLine());
    }

    @Override public Node visitBoolExp(SimpLanPlusParser.BoolExpContext ctx){
        // Create BoolExpNode
        return new BoolExpNode(Boolean.parseBoolean(ctx.getText()));
    }

    @Override public Node visitValExp(SimpLanPlusParser.ValExpContext ctx){
        // Create ValExpNode
        return new ValExpNode(Integer.parseInt(ctx.getText()));
    }


}
