package ast;

import ast.ExpNodes.*;
import parser.SimpLanPlusBaseVisitor;
import parser.SimpLanPlusParser;

import java.util.ArrayList;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {

    @Override public Node visitBlock(SimpLanPlusParser.BlockContext ctx) {
        BlockNode res;
        ArrayList<Node> declarations = new ArrayList<Node>();
        ArrayList<Node> statements = new ArrayList<Node>();
        // per ogni dichiarazione del blocco...
        for(SimpLanPlusParser.DeclarationContext dc: ctx.declaration()){
            declarations.add(visit(dc));
        }
        // per ogni statement del blocco...
        for(SimpLanPlusParser.StatementContext sc: ctx.statement()){
            statements.add(visit(sc));
        }
        res = new BlockNode(declarations, statements);
        return res;
    }

    @Override public Node visitDeclaration(SimpLanPlusParser.DeclarationContext ctx){
        DeclarationNode res;
        if(ctx.decFun()!=null){
            res = new DeclarationNode(visit(ctx.decFun()));
        }
        else if(ctx.decVar()!=null){
            res = new DeclarationNode(visit(ctx.decVar()));
        }
        else{
            return null;
        }
        //Node next = ctx.children;
        // Todo: Is this a good idea? Better ask Laneve
        return res;
    }

    @Override public Node visitStatement(SimpLanPlusParser.StatementContext ctx){
        Node res;
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
        return new StatementNode(res);
    }

    @Override public Node visitDecVar(SimpLanPlusParser.DecVarContext ctx){
        DecVarNode res;
        if(ctx.exp()!=null){
           res = new DecVarNode(visit(ctx.type()), new IdNode(ctx.ID().getText()), visit(ctx.exp()));
        }
        else{
            res = new DecVarNode(visit(ctx.type()), new IdNode(ctx.ID().getText()));
        }

        return res;
    }

    @Override public Node visitDecFun(SimpLanPlusParser.DecFunContext ctx){
        DecFunNode res;
        ArrayList<Node> args = new ArrayList<Node>();
        for(SimpLanPlusParser.ArgContext atx: ctx.arg()){
            args.add(visit(atx));
        }
        res = new DecFunNode(visit(ctx.type()), visit(ctx.ID()), args, visit(ctx.block()));
        return res;
    }

    @Override public Node visitType(SimpLanPlusParser.TypeContext ctx){
        return new TypeNode(ctx.getText());
    }

    @Override public Node visitArg(SimpLanPlusParser.ArgContext ctx){
        return new ArgNode(visit(ctx.type()), visit(ctx.ID()));
    }

    @Override public Node visitAssignment(SimpLanPlusParser.AssignmentContext ctx){
        return new AssignmentNode(visit(ctx.ID()), visit(ctx.exp()));
    }

    @Override public Node visitPrint(SimpLanPlusParser.PrintContext ctx){
        return new PrintNode(visit(ctx.exp()));
    }

    @Override public Node visitRet(SimpLanPlusParser.RetContext ctx){
        return new ReturnNode(visit(ctx.exp()));
    }

    @Override public Node visitBaseExp(SimpLanPlusParser.BaseExpContext ctx){
        return new BaseExpNode(visit(ctx.exp()));
    }

    @Override public Node visitIte(SimpLanPlusParser.IteContext ctx){
        IteNode res;
        if(ctx.statement().size()==1){
            res = new IteNode(visit(ctx.exp()), visit(ctx.statement().get(0)));
        }
        else if(ctx.statement().size()==2){
            res = new IteNode(visit(ctx.exp()), visit(ctx.statement().get(0)), visit(ctx.statement().get(1)));
        }
        else{
            return null;
        }
        return res;
    }

    @Override public Node visitCall(SimpLanPlusParser.CallContext ctx){
        if(ctx.exp().isEmpty()){
            return new CallNode(visit(ctx.ID()));
        }
        ArrayList<Node> params = new ArrayList<Node>();
        for(SimpLanPlusParser.ExpContext ex: ctx.exp()){
            params.add(visit(ex));
        }
        return new CallNode(visit(ctx.ID()), params);
    }

    @Override public Node visitNegExp(SimpLanPlusParser.NegExpContext ctx){
        return new NegExpNode(visit(ctx.exp()));
    }

    @Override public Node visitNotExp(SimpLanPlusParser.NotExpContext ctx){
        return new NotExpNode(visit(ctx.exp()));
    }

    @Override public Node visitDerExp(SimpLanPlusParser.DerExpContext ctx){
        return new DerExpNode(visit(ctx.ID()));
    }

    @Override public Node visitBinExp(SimpLanPlusParser.BinExpContext ctx){
        return new BinExpNode(ctx.op.getText(), visit(ctx.left), visit(ctx.right));
    }

    @Override public Node visitCallExp(SimpLanPlusParser.CallExpContext ctx){
        return new CallExpNode(visit(ctx.call()));
    }

    @Override public Node visitBoolExp(SimpLanPlusParser.BoolExpContext ctx){
        return new BoolExpNode(Boolean.parseBoolean(ctx.getText()));
    }

    @Override public Node visitValExp(SimpLanPlusParser.ValExpContext ctx){
        return new ValExpNode(Integer.parseInt(ctx.getText()));
    }


}
