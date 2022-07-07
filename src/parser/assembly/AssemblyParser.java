// Generated from C:/Users/loren/Documents/GitHub/SimpLanPlus-Interpreter/src/parser/assembly\Assembly.g4 by ANTLR 4.9.2
package parser.assembly;

import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AssemblyParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, PUSH=3, POP=4, TOP=5, LI=6, MOV=7, LW=8, SW=9, LB=10, 
		SB=11, ADD=12, ADDI=13, SUB=14, SUBI=15, MULT=16, MULTI=17, DIV=18, DIVI=19, 
		LT=20, LTE=21, GT=22, GTE=23, EQ=24, NEQ=25, AND=26, OR=27, NOT=28, NEG=29, 
		PRINT=30, BEQ=31, HALT=32, JAL=33, JR=34, SYMBOLS=35, LABEL=36, REG=37, 
		STRING=38, COL=39, NUMBER=40, WHITESP=41, LINECOMMENTS=42;
	public static final int
		RULE_program = 0, RULE_instruction = 1, RULE_push = 2, RULE_pop = 3, RULE_top = 4, 
		RULE_li = 5, RULE_mov = 6, RULE_lw = 7, RULE_sw = 8, RULE_lb = 9, RULE_sb = 10, 
		RULE_add = 11, RULE_addi = 12, RULE_sub = 13, RULE_subi = 14, RULE_mult = 15, 
		RULE_multi = 16, RULE_div = 17, RULE_divi = 18, RULE_lt = 19, RULE_lte = 20, 
		RULE_gt = 21, RULE_gte = 22, RULE_eq = 23, RULE_neq = 24, RULE_and = 25, 
		RULE_or = 26, RULE_not = 27, RULE_neg = 28, RULE_print = 29, RULE_beq = 30, 
		RULE_label = 31, RULE_halt = 32, RULE_jal = 33, RULE_jr = 34;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "instruction", "push", "pop", "top", "li", "mov", "lw", "sw", 
			"lb", "sb", "add", "addi", "sub", "subi", "mult", "multi", "div", "divi", 
			"lt", "lte", "gt", "gte", "eq", "neq", "and", "or", "not", "neg", "print", 
			"beq", "label", "halt", "jal", "jr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'push'", "'pop'", "'top'", "'li'", "'mov'", "'lw'", 
			"'sw'", "'lb'", "'sb'", "'add'", "'addi'", "'sub'", "'subi'", "'mult'", 
			"'multi'", "'div'", "'divi'", "'lt'", "'lte'", "'gt'", "'gte'", "'eq'", 
			"'neq'", "'and'", "'or'", "'not'", "'neg'", "'print'", "'beq'", "'halt'", 
			"'jal'", "'jr'", "'_'", null, null, null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "PUSH", "POP", "TOP", "LI", "MOV", "LW", "SW", "LB", 
			"SB", "ADD", "ADDI", "SUB", "SUBI", "MULT", "MULTI", "DIV", "DIVI", "LT", 
			"LTE", "GT", "GTE", "EQ", "NEQ", "AND", "OR", "NOT", "NEG", "PRINT", 
			"BEQ", "HALT", "JAL", "JR", "SYMBOLS", "LABEL", "REG", "STRING", "COL", 
			"NUMBER", "WHITESP", "LINECOMMENTS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Assembly.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AssemblyParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUSH) | (1L << POP) | (1L << TOP) | (1L << LI) | (1L << MOV) | (1L << LW) | (1L << SW) | (1L << LB) | (1L << ADD) | (1L << ADDI) | (1L << SUB) | (1L << SUBI) | (1L << MULT) | (1L << MULTI) | (1L << DIV) | (1L << DIVI) | (1L << LT) | (1L << LTE) | (1L << GT) | (1L << GTE) | (1L << EQ) | (1L << NEQ) | (1L << AND) | (1L << OR) | (1L << NOT) | (1L << NEG) | (1L << PRINT) | (1L << BEQ) | (1L << HALT) | (1L << JAL) | (1L << JR) | (1L << LABEL))) != 0)) {
				{
				{
				setState(70);
				instruction();
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public PushContext push() {
			return getRuleContext(PushContext.class,0);
		}
		public PopContext pop() {
			return getRuleContext(PopContext.class,0);
		}
		public TopContext top() {
			return getRuleContext(TopContext.class,0);
		}
		public LiContext li() {
			return getRuleContext(LiContext.class,0);
		}
		public MovContext mov() {
			return getRuleContext(MovContext.class,0);
		}
		public LwContext lw() {
			return getRuleContext(LwContext.class,0);
		}
		public SwContext sw() {
			return getRuleContext(SwContext.class,0);
		}
		public LbContext lb() {
			return getRuleContext(LbContext.class,0);
		}
		public SbContext sb() {
			return getRuleContext(SbContext.class,0);
		}
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public AddiContext addi() {
			return getRuleContext(AddiContext.class,0);
		}
		public SubContext sub() {
			return getRuleContext(SubContext.class,0);
		}
		public SubiContext subi() {
			return getRuleContext(SubiContext.class,0);
		}
		public MultContext mult() {
			return getRuleContext(MultContext.class,0);
		}
		public MultiContext multi() {
			return getRuleContext(MultiContext.class,0);
		}
		public DivContext div() {
			return getRuleContext(DivContext.class,0);
		}
		public DiviContext divi() {
			return getRuleContext(DiviContext.class,0);
		}
		public LtContext lt() {
			return getRuleContext(LtContext.class,0);
		}
		public LteContext lte() {
			return getRuleContext(LteContext.class,0);
		}
		public GtContext gt() {
			return getRuleContext(GtContext.class,0);
		}
		public GteContext gte() {
			return getRuleContext(GteContext.class,0);
		}
		public EqContext eq() {
			return getRuleContext(EqContext.class,0);
		}
		public NeqContext neq() {
			return getRuleContext(NeqContext.class,0);
		}
		public AndContext and() {
			return getRuleContext(AndContext.class,0);
		}
		public OrContext or() {
			return getRuleContext(OrContext.class,0);
		}
		public NotContext not() {
			return getRuleContext(NotContext.class,0);
		}
		public NegContext neg() {
			return getRuleContext(NegContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public JalContext jal() {
			return getRuleContext(JalContext.class,0);
		}
		public JrContext jr() {
			return getRuleContext(JrContext.class,0);
		}
		public BeqContext beq() {
			return getRuleContext(BeqContext.class,0);
		}
		public HaltContext halt() {
			return getRuleContext(HaltContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				label();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				push();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				pop();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				top();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(80);
				li();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(81);
				mov();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(82);
				lw();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(83);
				sw();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(84);
				lb();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(85);
				sb();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(86);
				add();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(87);
				addi();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(88);
				sub();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(89);
				subi();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(90);
				mult();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(91);
				multi();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(92);
				div();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(93);
				divi();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(94);
				lt();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(95);
				lte();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(96);
				gt();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(97);
				gte();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(98);
				eq();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(99);
				neq();
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(100);
				and();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(101);
				or();
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(102);
				not();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(103);
				neg();
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(104);
				print();
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(105);
				jal();
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 31);
				{
				setState(106);
				jr();
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 32);
				{
				setState(107);
				beq();
				}
				break;
			case 33:
				enterOuterAlt(_localctx, 33);
				{
				setState(108);
				halt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PushContext extends ParserRuleContext {
		public Token src;
		public TerminalNode PUSH() { return getToken(AssemblyParser.PUSH, 0); }
		public TerminalNode REG() { return getToken(AssemblyParser.REG, 0); }
		public PushContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_push; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterPush(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitPush(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitPush(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PushContext push() throws RecognitionException {
		PushContext _localctx = new PushContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_push);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(PUSH);
			setState(112);
			((PushContext)_localctx).src = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PopContext extends ParserRuleContext {
		public Token dest;
		public TerminalNode POP() { return getToken(AssemblyParser.POP, 0); }
		public TerminalNode REG() { return getToken(AssemblyParser.REG, 0); }
		public PopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterPop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitPop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitPop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopContext pop() throws RecognitionException {
		PopContext _localctx = new PopContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_pop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(POP);
			setState(115);
			((PopContext)_localctx).dest = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopContext extends ParserRuleContext {
		public Token dest;
		public TerminalNode TOP() { return getToken(AssemblyParser.TOP, 0); }
		public TerminalNode REG() { return getToken(AssemblyParser.REG, 0); }
		public TopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_top; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterTop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitTop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitTop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopContext top() throws RecognitionException {
		TopContext _localctx = new TopContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_top);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(TOP);
			setState(118);
			((TopContext)_localctx).dest = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiContext extends ParserRuleContext {
		public Token dest;
		public Token n;
		public TerminalNode LI() { return getToken(AssemblyParser.LI, 0); }
		public TerminalNode REG() { return getToken(AssemblyParser.REG, 0); }
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public LiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_li; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitLi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiContext li() throws RecognitionException {
		LiContext _localctx = new LiContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_li);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(LI);
			setState(121);
			((LiContext)_localctx).dest = match(REG);
			setState(122);
			((LiContext)_localctx).n = match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MovContext extends ParserRuleContext {
		public Token dest;
		public Token src;
		public TerminalNode MOV() { return getToken(AssemblyParser.MOV, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public MovContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mov; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterMov(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitMov(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitMov(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MovContext mov() throws RecognitionException {
		MovContext _localctx = new MovContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_mov);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(MOV);
			setState(125);
			((MovContext)_localctx).dest = match(REG);
			setState(126);
			((MovContext)_localctx).src = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LwContext extends ParserRuleContext {
		public Token reg1;
		public Token offset;
		public Token reg2;
		public TerminalNode LW() { return getToken(AssemblyParser.LW, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public LwContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lw; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLw(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLw(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitLw(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LwContext lw() throws RecognitionException {
		LwContext _localctx = new LwContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_lw);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(LW);
			setState(129);
			((LwContext)_localctx).reg1 = match(REG);
			setState(130);
			((LwContext)_localctx).offset = match(NUMBER);
			setState(131);
			match(T__0);
			setState(132);
			((LwContext)_localctx).reg2 = match(REG);
			setState(133);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwContext extends ParserRuleContext {
		public Token reg1;
		public Token offset;
		public Token reg2;
		public TerminalNode SW() { return getToken(AssemblyParser.SW, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public SwContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sw; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterSw(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitSw(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitSw(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwContext sw() throws RecognitionException {
		SwContext _localctx = new SwContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_sw);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(SW);
			setState(136);
			((SwContext)_localctx).reg1 = match(REG);
			setState(137);
			((SwContext)_localctx).offset = match(NUMBER);
			setState(138);
			match(T__0);
			setState(139);
			((SwContext)_localctx).reg2 = match(REG);
			setState(140);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LbContext extends ParserRuleContext {
		public Token reg1;
		public Token offset;
		public Token reg2;
		public TerminalNode LB() { return getToken(AssemblyParser.LB, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public LbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLb(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitLb(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LbContext lb() throws RecognitionException {
		LbContext _localctx = new LbContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lb);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(LB);
			setState(143);
			((LbContext)_localctx).reg1 = match(REG);
			setState(144);
			((LbContext)_localctx).offset = match(NUMBER);
			setState(145);
			match(T__0);
			setState(146);
			((LbContext)_localctx).reg2 = match(REG);
			setState(147);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SbContext extends ParserRuleContext {
		public Token reg1;
		public Token offset;
		public Token reg2;
		public TerminalNode SW() { return getToken(AssemblyParser.SW, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public SbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterSb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitSb(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitSb(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SbContext sb() throws RecognitionException {
		SbContext _localctx = new SbContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_sb);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(SW);
			setState(150);
			((SbContext)_localctx).reg1 = match(REG);
			setState(151);
			((SbContext)_localctx).offset = match(NUMBER);
			setState(152);
			match(T__0);
			setState(153);
			((SbContext)_localctx).reg2 = match(REG);
			setState(154);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode ADD() { return getToken(AssemblyParser.ADD, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitAdd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		AddContext _localctx = new AddContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_add);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(ADD);
			setState(157);
			((AddContext)_localctx).dest = match(REG);
			setState(158);
			((AddContext)_localctx).reg1 = match(REG);
			setState(159);
			((AddContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddiContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public TerminalNode ADDI() { return getToken(AssemblyParser.ADDI, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public AddiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterAddi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitAddi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitAddi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddiContext addi() throws RecognitionException {
		AddiContext _localctx = new AddiContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_addi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(ADDI);
			setState(162);
			((AddiContext)_localctx).dest = match(REG);
			setState(163);
			((AddiContext)_localctx).reg1 = match(REG);
			setState(164);
			((AddiContext)_localctx).val = match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode SUB() { return getToken(AssemblyParser.SUB, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public SubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubContext sub() throws RecognitionException {
		SubContext _localctx = new SubContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_sub);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(SUB);
			setState(167);
			((SubContext)_localctx).dest = match(REG);
			setState(168);
			((SubContext)_localctx).reg1 = match(REG);
			setState(169);
			((SubContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubiContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public TerminalNode SUBI() { return getToken(AssemblyParser.SUBI, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public SubiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterSubi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitSubi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitSubi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubiContext subi() throws RecognitionException {
		SubiContext _localctx = new SubiContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_subi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(SUBI);
			setState(172);
			((SubiContext)_localctx).dest = match(REG);
			setState(173);
			((SubiContext)_localctx).reg1 = match(REG);
			setState(174);
			((SubiContext)_localctx).val = match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode MULT() { return getToken(AssemblyParser.MULT, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public MultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mult; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterMult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitMult(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitMult(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultContext mult() throws RecognitionException {
		MultContext _localctx = new MultContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_mult);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(MULT);
			setState(177);
			((MultContext)_localctx).dest = match(REG);
			setState(178);
			((MultContext)_localctx).reg1 = match(REG);
			setState(179);
			((MultContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public TerminalNode MULTI() { return getToken(AssemblyParser.MULTI, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public MultiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterMulti(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitMulti(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitMulti(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiContext multi() throws RecognitionException {
		MultiContext _localctx = new MultiContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_multi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(MULTI);
			setState(182);
			((MultiContext)_localctx).dest = match(REG);
			setState(183);
			((MultiContext)_localctx).reg1 = match(REG);
			setState(184);
			((MultiContext)_localctx).val = match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DivContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode DIV() { return getToken(AssemblyParser.DIV, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public DivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_div; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivContext div() throws RecognitionException {
		DivContext _localctx = new DivContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_div);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(DIV);
			setState(187);
			((DivContext)_localctx).dest = match(REG);
			setState(188);
			((DivContext)_localctx).reg1 = match(REG);
			setState(189);
			((DivContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DiviContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public TerminalNode DIVI() { return getToken(AssemblyParser.DIVI, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(AssemblyParser.NUMBER, 0); }
		public DiviContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterDivi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitDivi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitDivi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DiviContext divi() throws RecognitionException {
		DiviContext _localctx = new DiviContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_divi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(DIVI);
			setState(192);
			((DiviContext)_localctx).dest = match(REG);
			setState(193);
			((DiviContext)_localctx).reg1 = match(REG);
			setState(194);
			((DiviContext)_localctx).val = match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LtContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode LT() { return getToken(AssemblyParser.LT, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public LtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitLt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LtContext lt() throws RecognitionException {
		LtContext _localctx = new LtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_lt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(LT);
			setState(197);
			((LtContext)_localctx).dest = match(REG);
			setState(198);
			((LtContext)_localctx).reg1 = match(REG);
			setState(199);
			((LtContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LteContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode LTE() { return getToken(AssemblyParser.LTE, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public LteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lte; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLte(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLte(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitLte(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LteContext lte() throws RecognitionException {
		LteContext _localctx = new LteContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_lte);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(LTE);
			setState(202);
			((LteContext)_localctx).dest = match(REG);
			setState(203);
			((LteContext)_localctx).reg1 = match(REG);
			setState(204);
			((LteContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GtContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode GT() { return getToken(AssemblyParser.GT, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public GtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterGt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitGt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitGt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GtContext gt() throws RecognitionException {
		GtContext _localctx = new GtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_gt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(GT);
			setState(207);
			((GtContext)_localctx).dest = match(REG);
			setState(208);
			((GtContext)_localctx).reg1 = match(REG);
			setState(209);
			((GtContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GteContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode GTE() { return getToken(AssemblyParser.GTE, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public GteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gte; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterGte(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitGte(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitGte(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GteContext gte() throws RecognitionException {
		GteContext _localctx = new GteContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_gte);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(GTE);
			setState(212);
			((GteContext)_localctx).dest = match(REG);
			setState(213);
			((GteContext)_localctx).reg1 = match(REG);
			setState(214);
			((GteContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode EQ() { return getToken(AssemblyParser.EQ, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public EqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterEq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitEq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitEq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqContext eq() throws RecognitionException {
		EqContext _localctx = new EqContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_eq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(EQ);
			setState(217);
			((EqContext)_localctx).dest = match(REG);
			setState(218);
			((EqContext)_localctx).reg1 = match(REG);
			setState(219);
			((EqContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NeqContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode NEQ() { return getToken(AssemblyParser.NEQ, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public NeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_neq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterNeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitNeq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitNeq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NeqContext neq() throws RecognitionException {
		NeqContext _localctx = new NeqContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_neq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(NEQ);
			setState(222);
			((NeqContext)_localctx).dest = match(REG);
			setState(223);
			((NeqContext)_localctx).reg1 = match(REG);
			setState(224);
			((NeqContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode AND() { return getToken(AssemblyParser.AND, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public AndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndContext and() throws RecognitionException {
		AndContext _localctx = new AndContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_and);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(AND);
			setState(227);
			((AndContext)_localctx).dest = match(REG);
			setState(228);
			((AndContext)_localctx).reg1 = match(REG);
			setState(229);
			((AndContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public TerminalNode OR() { return getToken(AssemblyParser.OR, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public OrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrContext or() throws RecognitionException {
		OrContext _localctx = new OrContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_or);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(OR);
			setState(232);
			((OrContext)_localctx).dest = match(REG);
			setState(233);
			((OrContext)_localctx).reg1 = match(REG);
			setState(234);
			((OrContext)_localctx).reg2 = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotContext extends ParserRuleContext {
		public Token dest;
		public Token src;
		public TerminalNode NOT() { return getToken(AssemblyParser.NOT, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public NotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotContext not() throws RecognitionException {
		NotContext _localctx = new NotContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_not);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(NOT);
			setState(237);
			((NotContext)_localctx).dest = match(REG);
			setState(238);
			((NotContext)_localctx).src = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegContext extends ParserRuleContext {
		public Token dest;
		public Token src;
		public TerminalNode NEG() { return getToken(AssemblyParser.NEG, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public NegContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_neg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterNeg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitNeg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitNeg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NegContext neg() throws RecognitionException {
		NegContext _localctx = new NegContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_neg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(NEG);
			setState(241);
			((NegContext)_localctx).dest = match(REG);
			setState(242);
			((NegContext)_localctx).src = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintContext extends ParserRuleContext {
		public Token src;
		public TerminalNode PRINT() { return getToken(AssemblyParser.PRINT, 0); }
		public TerminalNode REG() { return getToken(AssemblyParser.REG, 0); }
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(PRINT);
			setState(245);
			((PrintContext)_localctx).src = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BeqContext extends ParserRuleContext {
		public Token reg1;
		public Token reg2;
		public Token lab;
		public TerminalNode BEQ() { return getToken(AssemblyParser.BEQ, 0); }
		public List<TerminalNode> REG() { return getTokens(AssemblyParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AssemblyParser.REG, i);
		}
		public TerminalNode LABEL() { return getToken(AssemblyParser.LABEL, 0); }
		public BeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterBeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitBeq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitBeq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeqContext beq() throws RecognitionException {
		BeqContext _localctx = new BeqContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_beq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(BEQ);
			setState(248);
			((BeqContext)_localctx).reg1 = match(REG);
			setState(249);
			((BeqContext)_localctx).reg2 = match(REG);
			setState(250);
			((BeqContext)_localctx).lab = match(LABEL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public Token lab;
		public TerminalNode COL() { return getToken(AssemblyParser.COL, 0); }
		public TerminalNode LABEL() { return getToken(AssemblyParser.LABEL, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			((LabelContext)_localctx).lab = match(LABEL);
			setState(253);
			match(COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HaltContext extends ParserRuleContext {
		public TerminalNode HALT() { return getToken(AssemblyParser.HALT, 0); }
		public HaltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_halt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterHalt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitHalt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitHalt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HaltContext halt() throws RecognitionException {
		HaltContext _localctx = new HaltContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_halt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(HALT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JalContext extends ParserRuleContext {
		public Token lab;
		public TerminalNode JAL() { return getToken(AssemblyParser.JAL, 0); }
		public TerminalNode LABEL() { return getToken(AssemblyParser.LABEL, 0); }
		public JalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterJal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitJal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitJal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JalContext jal() throws RecognitionException {
		JalContext _localctx = new JalContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_jal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(JAL);
			setState(258);
			((JalContext)_localctx).lab = match(LABEL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JrContext extends ParserRuleContext {
		public Token dest;
		public TerminalNode JR() { return getToken(AssemblyParser.JR, 0); }
		public TerminalNode REG() { return getToken(AssemblyParser.REG, 0); }
		public JrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).enterJr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssemblyListener ) ((AssemblyListener)listener).exitJr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssemblyVisitor ) return ((AssemblyVisitor<? extends T>)visitor).visitJr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JrContext jr() throws RecognitionException {
		JrContext _localctx = new JrContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_jr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(JR);
			setState(261);
			((JrContext)_localctx).dest = match(REG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u010a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\7\2J\n\2\f\2\16\2M\13\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3p\n\3\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3$\3$\2\2%\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF\2\2\2\u0107\2K\3\2\2\2"+
		"\4o\3\2\2\2\6q\3\2\2\2\bt\3\2\2\2\nw\3\2\2\2\fz\3\2\2\2\16~\3\2\2\2\20"+
		"\u0082\3\2\2\2\22\u0089\3\2\2\2\24\u0090\3\2\2\2\26\u0097\3\2\2\2\30\u009e"+
		"\3\2\2\2\32\u00a3\3\2\2\2\34\u00a8\3\2\2\2\36\u00ad\3\2\2\2 \u00b2\3\2"+
		"\2\2\"\u00b7\3\2\2\2$\u00bc\3\2\2\2&\u00c1\3\2\2\2(\u00c6\3\2\2\2*\u00cb"+
		"\3\2\2\2,\u00d0\3\2\2\2.\u00d5\3\2\2\2\60\u00da\3\2\2\2\62\u00df\3\2\2"+
		"\2\64\u00e4\3\2\2\2\66\u00e9\3\2\2\28\u00ee\3\2\2\2:\u00f2\3\2\2\2<\u00f6"+
		"\3\2\2\2>\u00f9\3\2\2\2@\u00fe\3\2\2\2B\u0101\3\2\2\2D\u0103\3\2\2\2F"+
		"\u0106\3\2\2\2HJ\5\4\3\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\3\3"+
		"\2\2\2MK\3\2\2\2Np\5@!\2Op\5\6\4\2Pp\5\b\5\2Qp\5\n\6\2Rp\5\f\7\2Sp\5\16"+
		"\b\2Tp\5\20\t\2Up\5\22\n\2Vp\5\24\13\2Wp\5\26\f\2Xp\5\30\r\2Yp\5\32\16"+
		"\2Zp\5\34\17\2[p\5\36\20\2\\p\5 \21\2]p\5\"\22\2^p\5$\23\2_p\5&\24\2`"+
		"p\5(\25\2ap\5*\26\2bp\5,\27\2cp\5.\30\2dp\5\60\31\2ep\5\62\32\2fp\5\64"+
		"\33\2gp\5\66\34\2hp\58\35\2ip\5:\36\2jp\5<\37\2kp\5D#\2lp\5F$\2mp\5> "+
		"\2np\5B\"\2oN\3\2\2\2oO\3\2\2\2oP\3\2\2\2oQ\3\2\2\2oR\3\2\2\2oS\3\2\2"+
		"\2oT\3\2\2\2oU\3\2\2\2oV\3\2\2\2oW\3\2\2\2oX\3\2\2\2oY\3\2\2\2oZ\3\2\2"+
		"\2o[\3\2\2\2o\\\3\2\2\2o]\3\2\2\2o^\3\2\2\2o_\3\2\2\2o`\3\2\2\2oa\3\2"+
		"\2\2ob\3\2\2\2oc\3\2\2\2od\3\2\2\2oe\3\2\2\2of\3\2\2\2og\3\2\2\2oh\3\2"+
		"\2\2oi\3\2\2\2oj\3\2\2\2ok\3\2\2\2ol\3\2\2\2om\3\2\2\2on\3\2\2\2p\5\3"+
		"\2\2\2qr\7\5\2\2rs\7\'\2\2s\7\3\2\2\2tu\7\6\2\2uv\7\'\2\2v\t\3\2\2\2w"+
		"x\7\7\2\2xy\7\'\2\2y\13\3\2\2\2z{\7\b\2\2{|\7\'\2\2|}\7*\2\2}\r\3\2\2"+
		"\2~\177\7\t\2\2\177\u0080\7\'\2\2\u0080\u0081\7\'\2\2\u0081\17\3\2\2\2"+
		"\u0082\u0083\7\n\2\2\u0083\u0084\7\'\2\2\u0084\u0085\7*\2\2\u0085\u0086"+
		"\7\3\2\2\u0086\u0087\7\'\2\2\u0087\u0088\7\4\2\2\u0088\21\3\2\2\2\u0089"+
		"\u008a\7\13\2\2\u008a\u008b\7\'\2\2\u008b\u008c\7*\2\2\u008c\u008d\7\3"+
		"\2\2\u008d\u008e\7\'\2\2\u008e\u008f\7\4\2\2\u008f\23\3\2\2\2\u0090\u0091"+
		"\7\f\2\2\u0091\u0092\7\'\2\2\u0092\u0093\7*\2\2\u0093\u0094\7\3\2\2\u0094"+
		"\u0095\7\'\2\2\u0095\u0096\7\4\2\2\u0096\25\3\2\2\2\u0097\u0098\7\13\2"+
		"\2\u0098\u0099\7\'\2\2\u0099\u009a\7*\2\2\u009a\u009b\7\3\2\2\u009b\u009c"+
		"\7\'\2\2\u009c\u009d\7\4\2\2\u009d\27\3\2\2\2\u009e\u009f\7\16\2\2\u009f"+
		"\u00a0\7\'\2\2\u00a0\u00a1\7\'\2\2\u00a1\u00a2\7\'\2\2\u00a2\31\3\2\2"+
		"\2\u00a3\u00a4\7\17\2\2\u00a4\u00a5\7\'\2\2\u00a5\u00a6\7\'\2\2\u00a6"+
		"\u00a7\7*\2\2\u00a7\33\3\2\2\2\u00a8\u00a9\7\20\2\2\u00a9\u00aa\7\'\2"+
		"\2\u00aa\u00ab\7\'\2\2\u00ab\u00ac\7\'\2\2\u00ac\35\3\2\2\2\u00ad\u00ae"+
		"\7\21\2\2\u00ae\u00af\7\'\2\2\u00af\u00b0\7\'\2\2\u00b0\u00b1\7*\2\2\u00b1"+
		"\37\3\2\2\2\u00b2\u00b3\7\22\2\2\u00b3\u00b4\7\'\2\2\u00b4\u00b5\7\'\2"+
		"\2\u00b5\u00b6\7\'\2\2\u00b6!\3\2\2\2\u00b7\u00b8\7\23\2\2\u00b8\u00b9"+
		"\7\'\2\2\u00b9\u00ba\7\'\2\2\u00ba\u00bb\7*\2\2\u00bb#\3\2\2\2\u00bc\u00bd"+
		"\7\24\2\2\u00bd\u00be\7\'\2\2\u00be\u00bf\7\'\2\2\u00bf\u00c0\7\'\2\2"+
		"\u00c0%\3\2\2\2\u00c1\u00c2\7\25\2\2\u00c2\u00c3\7\'\2\2\u00c3\u00c4\7"+
		"\'\2\2\u00c4\u00c5\7*\2\2\u00c5\'\3\2\2\2\u00c6\u00c7\7\26\2\2\u00c7\u00c8"+
		"\7\'\2\2\u00c8\u00c9\7\'\2\2\u00c9\u00ca\7\'\2\2\u00ca)\3\2\2\2\u00cb"+
		"\u00cc\7\27\2\2\u00cc\u00cd\7\'\2\2\u00cd\u00ce\7\'\2\2\u00ce\u00cf\7"+
		"\'\2\2\u00cf+\3\2\2\2\u00d0\u00d1\7\30\2\2\u00d1\u00d2\7\'\2\2\u00d2\u00d3"+
		"\7\'\2\2\u00d3\u00d4\7\'\2\2\u00d4-\3\2\2\2\u00d5\u00d6\7\31\2\2\u00d6"+
		"\u00d7\7\'\2\2\u00d7\u00d8\7\'\2\2\u00d8\u00d9\7\'\2\2\u00d9/\3\2\2\2"+
		"\u00da\u00db\7\32\2\2\u00db\u00dc\7\'\2\2\u00dc\u00dd\7\'\2\2\u00dd\u00de"+
		"\7\'\2\2\u00de\61\3\2\2\2\u00df\u00e0\7\33\2\2\u00e0\u00e1\7\'\2\2\u00e1"+
		"\u00e2\7\'\2\2\u00e2\u00e3\7\'\2\2\u00e3\63\3\2\2\2\u00e4\u00e5\7\34\2"+
		"\2\u00e5\u00e6\7\'\2\2\u00e6\u00e7\7\'\2\2\u00e7\u00e8\7\'\2\2\u00e8\65"+
		"\3\2\2\2\u00e9\u00ea\7\35\2\2\u00ea\u00eb\7\'\2\2\u00eb\u00ec\7\'\2\2"+
		"\u00ec\u00ed\7\'\2\2\u00ed\67\3\2\2\2\u00ee\u00ef\7\36\2\2\u00ef\u00f0"+
		"\7\'\2\2\u00f0\u00f1\7\'\2\2\u00f19\3\2\2\2\u00f2\u00f3\7\37\2\2\u00f3"+
		"\u00f4\7\'\2\2\u00f4\u00f5\7\'\2\2\u00f5;\3\2\2\2\u00f6\u00f7\7 \2\2\u00f7"+
		"\u00f8\7\'\2\2\u00f8=\3\2\2\2\u00f9\u00fa\7!\2\2\u00fa\u00fb\7\'\2\2\u00fb"+
		"\u00fc\7\'\2\2\u00fc\u00fd\7&\2\2\u00fd?\3\2\2\2\u00fe\u00ff\7&\2\2\u00ff"+
		"\u0100\7)\2\2\u0100A\3\2\2\2\u0101\u0102\7\"\2\2\u0102C\3\2\2\2\u0103"+
		"\u0104\7#\2\2\u0104\u0105\7&\2\2\u0105E\3\2\2\2\u0106\u0107\7$\2\2\u0107"+
		"\u0108\7\'\2\2\u0108G\3\2\2\2\4Ko";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}