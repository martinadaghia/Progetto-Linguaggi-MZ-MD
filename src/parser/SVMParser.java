// Generated from /Users/martinadaghia/Desktop/ProgettoLinguaggi/src/parser/SVM.g4 by ANTLR 4.13.1
package parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SVMParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, LOAD=4, STORE=5, STOREI=6, MOVE=7, ADD=8, ADDI=9, 
		SUB=10, SUBI=11, MUL=12, MULI=13, DIV=14, DIVI=15, PUSH=16, PUSHR=17, 
		POP=18, POPR=19, BRANCH=20, BRANCHEQ=21, BRANCHLESSEQ=22, JUMPSUB=23, 
		RETURNSUB=24, BGTE=25, BL=26, HALT=27, REG=28, LABEL=29, NUMBER=30, WHITESP=31, 
		LINECOMENTS=32, ERR=33;
	public static final int
		RULE_assembly = 0, RULE_instruction = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"assembly", "instruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "':'", "'load'", "'store'", "'storei'", "'move'", 
			"'add'", "'addi'", "'sub'", "'subi'", "'mul'", "'muli'", "'div'", "'divi'", 
			"'push'", "'pushr'", "'pop'", "'popr'", "'b'", "'beq'", "'bleq'", "'jsub'", 
			"'rsub'", "'bgte'", "'bl'", "'halt'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "LOAD", "STORE", "STOREI", "MOVE", "ADD", "ADDI", 
			"SUB", "SUBI", "MUL", "MULI", "DIV", "DIVI", "PUSH", "PUSHR", "POP", 
			"POPR", "BRANCH", "BRANCHEQ", "BRANCHLESSEQ", "JUMPSUB", "RETURNSUB", 
			"BGTE", "BL", "HALT", "REG", "LABEL", "NUMBER", "WHITESP", "LINECOMENTS", 
			"ERR"
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
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SVMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssemblyContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public AssemblyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assembly; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterAssembly(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitAssembly(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAssembly(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssemblyContext assembly() throws RecognitionException {
		AssemblyContext _localctx = new AssemblyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assembly);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 805306352L) != 0)) {
				{
				{
				setState(4);
				instruction();
				}
				}
				setState(9);
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

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionContext extends ParserRuleContext {
		public Token n;
		public Token l;
		public TerminalNode LOAD() { return getToken(SVMParser.LOAD, 0); }
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public TerminalNode STORE() { return getToken(SVMParser.STORE, 0); }
		public TerminalNode STOREI() { return getToken(SVMParser.STOREI, 0); }
		public TerminalNode MOVE() { return getToken(SVMParser.MOVE, 0); }
		public TerminalNode ADD() { return getToken(SVMParser.ADD, 0); }
		public TerminalNode ADDI() { return getToken(SVMParser.ADDI, 0); }
		public TerminalNode SUB() { return getToken(SVMParser.SUB, 0); }
		public TerminalNode SUBI() { return getToken(SVMParser.SUBI, 0); }
		public TerminalNode MUL() { return getToken(SVMParser.MUL, 0); }
		public TerminalNode MULI() { return getToken(SVMParser.MULI, 0); }
		public TerminalNode DIV() { return getToken(SVMParser.DIV, 0); }
		public TerminalNode DIVI() { return getToken(SVMParser.DIVI, 0); }
		public TerminalNode PUSH() { return getToken(SVMParser.PUSH, 0); }
		public TerminalNode PUSHR() { return getToken(SVMParser.PUSHR, 0); }
		public TerminalNode POP() { return getToken(SVMParser.POP, 0); }
		public TerminalNode POPR() { return getToken(SVMParser.POPR, 0); }
		public TerminalNode BRANCH() { return getToken(SVMParser.BRANCH, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public TerminalNode BRANCHEQ() { return getToken(SVMParser.BRANCHEQ, 0); }
		public TerminalNode BRANCHLESSEQ() { return getToken(SVMParser.BRANCHLESSEQ, 0); }
		public TerminalNode JUMPSUB() { return getToken(SVMParser.JUMPSUB, 0); }
		public TerminalNode RETURNSUB() { return getToken(SVMParser.RETURNSUB, 0); }
		public TerminalNode BGTE() { return getToken(SVMParser.BGTE, 0); }
		public TerminalNode BL() { return getToken(SVMParser.BL, 0); }
		public TerminalNode HALT() { return getToken(SVMParser.HALT, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOAD:
				{
				setState(10);
				match(LOAD);
				setState(11);
				match(REG);
				setState(12);
				match(NUMBER);
				setState(13);
				match(T__0);
				setState(14);
				match(REG);
				setState(15);
				match(T__1);
				}
				break;
			case STORE:
				{
				setState(16);
				match(STORE);
				setState(17);
				match(REG);
				setState(18);
				match(NUMBER);
				setState(19);
				match(T__0);
				setState(20);
				match(REG);
				setState(21);
				match(T__1);
				}
				break;
			case STOREI:
				{
				setState(22);
				match(STOREI);
				setState(23);
				match(REG);
				setState(24);
				match(NUMBER);
				}
				break;
			case MOVE:
				{
				setState(25);
				match(MOVE);
				setState(26);
				match(REG);
				setState(27);
				match(REG);
				}
				break;
			case ADD:
				{
				setState(28);
				match(ADD);
				setState(29);
				match(REG);
				setState(30);
				match(REG);
				}
				break;
			case ADDI:
				{
				setState(31);
				match(ADDI);
				setState(32);
				match(REG);
				setState(33);
				match(NUMBER);
				}
				break;
			case SUB:
				{
				setState(34);
				match(SUB);
				setState(35);
				match(REG);
				setState(36);
				match(REG);
				}
				break;
			case SUBI:
				{
				setState(37);
				match(SUBI);
				setState(38);
				match(REG);
				setState(39);
				match(NUMBER);
				}
				break;
			case MUL:
				{
				setState(40);
				match(MUL);
				setState(41);
				match(REG);
				setState(42);
				match(REG);
				}
				break;
			case MULI:
				{
				setState(43);
				match(MULI);
				setState(44);
				match(REG);
				setState(45);
				match(NUMBER);
				}
				break;
			case DIV:
				{
				setState(46);
				match(DIV);
				setState(47);
				match(REG);
				setState(48);
				match(REG);
				}
				break;
			case DIVI:
				{
				setState(49);
				match(DIVI);
				setState(50);
				match(REG);
				setState(51);
				match(NUMBER);
				}
				break;
			case PUSH:
				{
				setState(52);
				match(PUSH);
				setState(55);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NUMBER:
					{
					setState(53);
					((InstructionContext)_localctx).n = match(NUMBER);
					}
					break;
				case LABEL:
					{
					setState(54);
					((InstructionContext)_localctx).l = match(LABEL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case PUSHR:
				{
				setState(57);
				match(PUSHR);
				setState(58);
				match(REG);
				}
				break;
			case POP:
				{
				setState(59);
				match(POP);
				}
				break;
			case POPR:
				{
				setState(60);
				match(POPR);
				setState(61);
				match(REG);
				}
				break;
			case BRANCH:
				{
				setState(62);
				match(BRANCH);
				setState(63);
				match(LABEL);
				}
				break;
			case BRANCHEQ:
				{
				setState(64);
				match(BRANCHEQ);
				setState(65);
				match(REG);
				setState(66);
				match(REG);
				setState(67);
				match(LABEL);
				}
				break;
			case BRANCHLESSEQ:
				{
				setState(68);
				match(BRANCHLESSEQ);
				setState(69);
				match(REG);
				setState(70);
				match(REG);
				setState(71);
				match(LABEL);
				}
				break;
			case JUMPSUB:
				{
				setState(72);
				match(JUMPSUB);
				setState(73);
				match(LABEL);
				}
				break;
			case RETURNSUB:
				{
				setState(74);
				match(RETURNSUB);
				setState(75);
				match(REG);
				}
				break;
			case BGTE:
				{
				setState(76);
				match(BGTE);
				setState(77);
				match(REG);
				setState(78);
				match(REG);
				setState(79);
				match(LABEL);
				}
				break;
			case BL:
				{
				setState(80);
				match(BL);
				setState(81);
				match(REG);
				setState(82);
				match(REG);
				setState(83);
				match(LABEL);
				}
				break;
			case LABEL:
				{
				setState(84);
				((InstructionContext)_localctx).l = match(LABEL);
				setState(85);
				match(T__2);
				}
				break;
			case HALT:
				{
				setState(86);
				match(HALT);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static final String _serializedATN =
		"\u0004\u0001!Z\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001\u0000"+
		"\u0005\u0000\u0006\b\u0000\n\u0000\f\u0000\t\t\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u00018\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001X\b\u0001\u0001"+
		"\u0001\u0000\u0000\u0002\u0000\u0002\u0000\u0000q\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0002W\u0001\u0000\u0000\u0000\u0004\u0006\u0003\u0002\u0001"+
		"\u0000\u0005\u0004\u0001\u0000\u0000\u0000\u0006\t\u0001\u0000\u0000\u0000"+
		"\u0007\u0005\u0001\u0000\u0000\u0000\u0007\b\u0001\u0000\u0000\u0000\b"+
		"\u0001\u0001\u0000\u0000\u0000\t\u0007\u0001\u0000\u0000\u0000\n\u000b"+
		"\u0005\u0004\u0000\u0000\u000b\f\u0005\u001c\u0000\u0000\f\r\u0005\u001e"+
		"\u0000\u0000\r\u000e\u0005\u0001\u0000\u0000\u000e\u000f\u0005\u001c\u0000"+
		"\u0000\u000fX\u0005\u0002\u0000\u0000\u0010\u0011\u0005\u0005\u0000\u0000"+
		"\u0011\u0012\u0005\u001c\u0000\u0000\u0012\u0013\u0005\u001e\u0000\u0000"+
		"\u0013\u0014\u0005\u0001\u0000\u0000\u0014\u0015\u0005\u001c\u0000\u0000"+
		"\u0015X\u0005\u0002\u0000\u0000\u0016\u0017\u0005\u0006\u0000\u0000\u0017"+
		"\u0018\u0005\u001c\u0000\u0000\u0018X\u0005\u001e\u0000\u0000\u0019\u001a"+
		"\u0005\u0007\u0000\u0000\u001a\u001b\u0005\u001c\u0000\u0000\u001bX\u0005"+
		"\u001c\u0000\u0000\u001c\u001d\u0005\b\u0000\u0000\u001d\u001e\u0005\u001c"+
		"\u0000\u0000\u001eX\u0005\u001c\u0000\u0000\u001f \u0005\t\u0000\u0000"+
		" !\u0005\u001c\u0000\u0000!X\u0005\u001e\u0000\u0000\"#\u0005\n\u0000"+
		"\u0000#$\u0005\u001c\u0000\u0000$X\u0005\u001c\u0000\u0000%&\u0005\u000b"+
		"\u0000\u0000&\'\u0005\u001c\u0000\u0000\'X\u0005\u001e\u0000\u0000()\u0005"+
		"\f\u0000\u0000)*\u0005\u001c\u0000\u0000*X\u0005\u001c\u0000\u0000+,\u0005"+
		"\r\u0000\u0000,-\u0005\u001c\u0000\u0000-X\u0005\u001e\u0000\u0000./\u0005"+
		"\u000e\u0000\u0000/0\u0005\u001c\u0000\u00000X\u0005\u001c\u0000\u0000"+
		"12\u0005\u000f\u0000\u000023\u0005\u001c\u0000\u00003X\u0005\u001e\u0000"+
		"\u000047\u0005\u0010\u0000\u000058\u0005\u001e\u0000\u000068\u0005\u001d"+
		"\u0000\u000075\u0001\u0000\u0000\u000076\u0001\u0000\u0000\u00008X\u0001"+
		"\u0000\u0000\u00009:\u0005\u0011\u0000\u0000:X\u0005\u001c\u0000\u0000"+
		";X\u0005\u0012\u0000\u0000<=\u0005\u0013\u0000\u0000=X\u0005\u001c\u0000"+
		"\u0000>?\u0005\u0014\u0000\u0000?X\u0005\u001d\u0000\u0000@A\u0005\u0015"+
		"\u0000\u0000AB\u0005\u001c\u0000\u0000BC\u0005\u001c\u0000\u0000CX\u0005"+
		"\u001d\u0000\u0000DE\u0005\u0016\u0000\u0000EF\u0005\u001c\u0000\u0000"+
		"FG\u0005\u001c\u0000\u0000GX\u0005\u001d\u0000\u0000HI\u0005\u0017\u0000"+
		"\u0000IX\u0005\u001d\u0000\u0000JK\u0005\u0018\u0000\u0000KX\u0005\u001c"+
		"\u0000\u0000LM\u0005\u0019\u0000\u0000MN\u0005\u001c\u0000\u0000NO\u0005"+
		"\u001c\u0000\u0000OX\u0005\u001d\u0000\u0000PQ\u0005\u001a\u0000\u0000"+
		"QR\u0005\u001c\u0000\u0000RS\u0005\u001c\u0000\u0000SX\u0005\u001d\u0000"+
		"\u0000TU\u0005\u001d\u0000\u0000UX\u0005\u0003\u0000\u0000VX\u0005\u001b"+
		"\u0000\u0000W\n\u0001\u0000\u0000\u0000W\u0010\u0001\u0000\u0000\u0000"+
		"W\u0016\u0001\u0000\u0000\u0000W\u0019\u0001\u0000\u0000\u0000W\u001c"+
		"\u0001\u0000\u0000\u0000W\u001f\u0001\u0000\u0000\u0000W\"\u0001\u0000"+
		"\u0000\u0000W%\u0001\u0000\u0000\u0000W(\u0001\u0000\u0000\u0000W+\u0001"+
		"\u0000\u0000\u0000W.\u0001\u0000\u0000\u0000W1\u0001\u0000\u0000\u0000"+
		"W4\u0001\u0000\u0000\u0000W9\u0001\u0000\u0000\u0000W;\u0001\u0000\u0000"+
		"\u0000W<\u0001\u0000\u0000\u0000W>\u0001\u0000\u0000\u0000W@\u0001\u0000"+
		"\u0000\u0000WD\u0001\u0000\u0000\u0000WH\u0001\u0000\u0000\u0000WJ\u0001"+
		"\u0000\u0000\u0000WL\u0001\u0000\u0000\u0000WP\u0001\u0000\u0000\u0000"+
		"WT\u0001\u0000\u0000\u0000WV\u0001\u0000\u0000\u0000X\u0003\u0001\u0000"+
		"\u0000\u0000\u0003\u00077W";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}