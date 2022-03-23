package ast;

import parser.SimpLanPlusBaseVisitor;
import parser.SimpLanPlusParser;
import parser.*;

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
        else{
            res = new DeclarationNode(visit(ctx.decVar()));
        }
        //Node next = ctx.children;
        // Todo: Is this a good idea? Better ask Laneve
        return res;
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

    @Override public Node visitType(SimpLanPlusParser.TypeContext ctx){
        return new TypeNode(ctx.getText());
    }


}
