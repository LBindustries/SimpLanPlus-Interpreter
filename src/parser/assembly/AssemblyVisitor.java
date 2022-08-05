// Generated from /home/luca/IdeaProjects/SimpLanPlus-Interpreter/src/parser/assembly/Assembly.g4 by ANTLR 4.9.3
package parser.assembly;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AssemblyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AssemblyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(AssemblyParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(AssemblyParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#push}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(AssemblyParser.PushContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(AssemblyParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#top}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTop(AssemblyParser.TopContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#li}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLi(AssemblyParser.LiContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#mov}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMov(AssemblyParser.MovContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#lw}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLw(AssemblyParser.LwContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#sw}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSw(AssemblyParser.SwContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#lb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLb(AssemblyParser.LbContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#sb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSb(AssemblyParser.SbContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(AssemblyParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#addi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddi(AssemblyParser.AddiContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#sub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(AssemblyParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#subi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubi(AssemblyParser.SubiContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#mult}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(AssemblyParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#multi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti(AssemblyParser.MultiContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(AssemblyParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#divi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivi(AssemblyParser.DiviContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#lt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(AssemblyParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#lte}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLte(AssemblyParser.LteContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#gt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGt(AssemblyParser.GtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#gte}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGte(AssemblyParser.GteContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#eq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq(AssemblyParser.EqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#neq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeq(AssemblyParser.NeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(AssemblyParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(AssemblyParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(AssemblyParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#neg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeg(AssemblyParser.NegContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(AssemblyParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#beq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeq(AssemblyParser.BeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(AssemblyParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#halt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHalt(AssemblyParser.HaltContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#jal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJal(AssemblyParser.JalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblyParser#jr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJr(AssemblyParser.JrContext ctx);
}