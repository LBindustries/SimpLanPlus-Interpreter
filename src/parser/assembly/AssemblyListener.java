// Generated from /home/luca/IdeaProjects/SimpLanPlus-Interpreter/src/parser/assembly/Assembly.g4 by ANTLR 4.9.3
package parser.assembly;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AssemblyParser}.
 */
public interface AssemblyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(AssemblyParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(AssemblyParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(AssemblyParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(AssemblyParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#push}.
	 * @param ctx the parse tree
	 */
	void enterPush(AssemblyParser.PushContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#push}.
	 * @param ctx the parse tree
	 */
	void exitPush(AssemblyParser.PushContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#pop}.
	 * @param ctx the parse tree
	 */
	void enterPop(AssemblyParser.PopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#pop}.
	 * @param ctx the parse tree
	 */
	void exitPop(AssemblyParser.PopContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#top}.
	 * @param ctx the parse tree
	 */
	void enterTop(AssemblyParser.TopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#top}.
	 * @param ctx the parse tree
	 */
	void exitTop(AssemblyParser.TopContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#li}.
	 * @param ctx the parse tree
	 */
	void enterLi(AssemblyParser.LiContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#li}.
	 * @param ctx the parse tree
	 */
	void exitLi(AssemblyParser.LiContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#mov}.
	 * @param ctx the parse tree
	 */
	void enterMov(AssemblyParser.MovContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#mov}.
	 * @param ctx the parse tree
	 */
	void exitMov(AssemblyParser.MovContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#lw}.
	 * @param ctx the parse tree
	 */
	void enterLw(AssemblyParser.LwContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#lw}.
	 * @param ctx the parse tree
	 */
	void exitLw(AssemblyParser.LwContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#sw}.
	 * @param ctx the parse tree
	 */
	void enterSw(AssemblyParser.SwContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#sw}.
	 * @param ctx the parse tree
	 */
	void exitSw(AssemblyParser.SwContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#lb}.
	 * @param ctx the parse tree
	 */
	void enterLb(AssemblyParser.LbContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#lb}.
	 * @param ctx the parse tree
	 */
	void exitLb(AssemblyParser.LbContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#sb}.
	 * @param ctx the parse tree
	 */
	void enterSb(AssemblyParser.SbContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#sb}.
	 * @param ctx the parse tree
	 */
	void exitSb(AssemblyParser.SbContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(AssemblyParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(AssemblyParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#addi}.
	 * @param ctx the parse tree
	 */
	void enterAddi(AssemblyParser.AddiContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#addi}.
	 * @param ctx the parse tree
	 */
	void exitAddi(AssemblyParser.AddiContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#sub}.
	 * @param ctx the parse tree
	 */
	void enterSub(AssemblyParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#sub}.
	 * @param ctx the parse tree
	 */
	void exitSub(AssemblyParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#subi}.
	 * @param ctx the parse tree
	 */
	void enterSubi(AssemblyParser.SubiContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#subi}.
	 * @param ctx the parse tree
	 */
	void exitSubi(AssemblyParser.SubiContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#mult}.
	 * @param ctx the parse tree
	 */
	void enterMult(AssemblyParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#mult}.
	 * @param ctx the parse tree
	 */
	void exitMult(AssemblyParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#multi}.
	 * @param ctx the parse tree
	 */
	void enterMulti(AssemblyParser.MultiContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#multi}.
	 * @param ctx the parse tree
	 */
	void exitMulti(AssemblyParser.MultiContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#div}.
	 * @param ctx the parse tree
	 */
	void enterDiv(AssemblyParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#div}.
	 * @param ctx the parse tree
	 */
	void exitDiv(AssemblyParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#divi}.
	 * @param ctx the parse tree
	 */
	void enterDivi(AssemblyParser.DiviContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#divi}.
	 * @param ctx the parse tree
	 */
	void exitDivi(AssemblyParser.DiviContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#lt}.
	 * @param ctx the parse tree
	 */
	void enterLt(AssemblyParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#lt}.
	 * @param ctx the parse tree
	 */
	void exitLt(AssemblyParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#lte}.
	 * @param ctx the parse tree
	 */
	void enterLte(AssemblyParser.LteContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#lte}.
	 * @param ctx the parse tree
	 */
	void exitLte(AssemblyParser.LteContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#gt}.
	 * @param ctx the parse tree
	 */
	void enterGt(AssemblyParser.GtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#gt}.
	 * @param ctx the parse tree
	 */
	void exitGt(AssemblyParser.GtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#gte}.
	 * @param ctx the parse tree
	 */
	void enterGte(AssemblyParser.GteContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#gte}.
	 * @param ctx the parse tree
	 */
	void exitGte(AssemblyParser.GteContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#eq}.
	 * @param ctx the parse tree
	 */
	void enterEq(AssemblyParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#eq}.
	 * @param ctx the parse tree
	 */
	void exitEq(AssemblyParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#neq}.
	 * @param ctx the parse tree
	 */
	void enterNeq(AssemblyParser.NeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#neq}.
	 * @param ctx the parse tree
	 */
	void exitNeq(AssemblyParser.NeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(AssemblyParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(AssemblyParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(AssemblyParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(AssemblyParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(AssemblyParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(AssemblyParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#neg}.
	 * @param ctx the parse tree
	 */
	void enterNeg(AssemblyParser.NegContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#neg}.
	 * @param ctx the parse tree
	 */
	void exitNeg(AssemblyParser.NegContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(AssemblyParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(AssemblyParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#beq}.
	 * @param ctx the parse tree
	 */
	void enterBeq(AssemblyParser.BeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#beq}.
	 * @param ctx the parse tree
	 */
	void exitBeq(AssemblyParser.BeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(AssemblyParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(AssemblyParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#halt}.
	 * @param ctx the parse tree
	 */
	void enterHalt(AssemblyParser.HaltContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#halt}.
	 * @param ctx the parse tree
	 */
	void exitHalt(AssemblyParser.HaltContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#jal}.
	 * @param ctx the parse tree
	 */
	void enterJal(AssemblyParser.JalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#jal}.
	 * @param ctx the parse tree
	 */
	void exitJal(AssemblyParser.JalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AssemblyParser#jr}.
	 * @param ctx the parse tree
	 */
	void enterJr(AssemblyParser.JrContext ctx);
	/**
	 * Exit a parse tree produced by {@link AssemblyParser#jr}.
	 * @param ctx the parse tree
	 */
	void exitJr(AssemblyParser.JrContext ctx);
}