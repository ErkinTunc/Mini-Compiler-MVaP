// Generated from Rationnel.g4 by ANTLR 4.9.2

 import java.util.*;
 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RationnelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NEWLINE=6, SEMICOLON=7, MULDIV=8, 
		ADDSUB=9, INCDEC=10, LOGICOP=11, ENTIER=12, TYPE=13, ID=14, WS=15, UNMATCH=16;
	public static final int
		RULE_start = 0, RULE_instruction = 1, RULE_expr = 2, RULE_arithmExpr = 3, 
		RULE_boolExpr = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "instruction", "expr", "arithmExpr", "boolExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Afficher'", "'('", "')'", "'true'", "'false'", null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "NEWLINE", "SEMICOLON", "MULDIV", 
			"ADDSUB", "INCDEC", "LOGICOP", "ENTIER", "TYPE", "ID", "WS", "UNMATCH"
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
	public String getGrammarFileName() { return "Rationnel.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	 // Juste pour le parser // pas pour le lexer

	    // ---------- Runtime representation on MVaP ----------

	    // Booleans: 0 = false, 1 = true
	    // Every bool expression leaves exactly one int (0 or 1) on top of the stack.

	    // Rationals: two consecutive ints on the stack:
	    //   [..., num, den]  with den at the TOP
	    // After compiling a rational expression r:
	    //   stack = [..., num(r), den(r)]

	    // Temporary global cells (P[0..N-1]):
	    // We reserve them at the beginning of every generated MVaP program.
	    // They are used ONLY as scratch for rational operations.
	    static final int G_TMP0 = 0;
	    static final int G_TMP1 = 1;
	    static final int G_TMP2 = 2;
	    static final int G_TMP3 = 3;
	    // If you need more later, add G_TMP4, G_TMP5, etc.

	    // Later: we will generate at program start:
	    // PUSHI 0   ; g0 = G_TMP0
	    // PUSHI 0   ; g1 = G_TMP1
	    // PUSHI 0   ; g2 = G_TMP2
	    // PUSHI 0   ; g3 = G_TMP3
	    // ...

	    StringBuilder code = new StringBuilder();  // holds the MVaP body

	    int labelCounter = 0;
	    String newLabel(String base) {
	        return base + "_" + (labelCounter++);
	    }

	    void emit(String instr) {
	        code.append(instr).append("\n");
	    }


	public RationnelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RationnelParser.EOF, 0); }
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(RationnelParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(RationnelParser.SEMICOLON, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(RationnelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(RationnelParser.NEWLINE, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(10);
				instruction();
				setState(14);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE || _la==SEMICOLON) {
					{
					{
					setState(11);
					_la = _input.LA(1);
					if ( !(_la==NEWLINE || _la==SEMICOLON) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(16);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(22);
			match(EOF);

			        StringBuilder prog = new StringBuilder();

			        // Reserve temp globals (g0..g3)
			        prog.append("PUSHI 0\n"); // g0 = G_TMP0
			        prog.append("PUSHI 0\n"); // g1 = G_TMP1
			        prog.append("PUSHI 0\n"); // g2 = G_TMP2
			        prog.append("PUSHI 0\n"); // g3 = G_TMP3

			        prog.append(code.toString());  // body generated by instructions

			        prog.append("HALT\n");

			        System.out.println(prog.toString());
			      
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
		public ExprContext e;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(T__0);
			setState(26);
			match(T__1);
			setState(27);
			((InstructionContext)_localctx).e = expr();
			setState(28);
			match(T__2);

			        // Generate code for expression
			        emit(((InstructionContext)_localctx).e.code);

			        if (((InstructionContext)_localctx).e.exprType.equals("bool") || ((InstructionContext)_localctx).e.exprType.equals("int")) {
			            // stack: ..., v
			            emit("WRITE");   // print v
			            emit("POP");     // remove v
			        } else if (((InstructionContext)_localctx).e.exprType.equals("rat")) {
			            // stack: ..., num, den (den on top)
			            // Use G_TMP0 to save den while printing num
			            emit("STOREG " + G_TMP0); // g0 = den, stack: ..., num
			            emit("WRITE");            // print num
			            emit("POP");              // pop num
			            emit("PUSHG " + G_TMP0);  // restore den
			            emit("WRITE");            // print den
			            emit("POP");              // pop den
			        } else {
			            throw new RuntimeException("Unknown expr type: " + ((InstructionContext)_localctx).e.exprType);
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

	public static class ExprContext extends ParserRuleContext {
		public String code;
		public String exprType;
		public BoolExprContext b;
		public ArithmExprContext a;
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public ArithmExprContext arithmExpr() {
			return getRuleContext(ArithmExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		try {
			setState(37);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				((ExprContext)_localctx).b = boolExpr();
				((ExprContext)_localctx).code =  ((ExprContext)_localctx).b.code; ((ExprContext)_localctx).exprType =  "bool";
				}
				break;
			case ENTIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				((ExprContext)_localctx).a = arithmExpr();
				((ExprContext)_localctx).code =  ((ExprContext)_localctx).a.code; // For now, treat arithmetic as rationals
				                   ((ExprContext)_localctx).exprType =  "rat";
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ArithmExprContext extends ParserRuleContext {
		public String code;
		public Token ENTIER;
		public TerminalNode ENTIER() { return getToken(RationnelParser.ENTIER, 0); }
		public ArithmExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).enterArithmExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).exitArithmExpr(this);
		}
	}

	public final ArithmExprContext arithmExpr() throws RecognitionException {
		ArithmExprContext _localctx = new ArithmExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arithmExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			((ArithmExprContext)_localctx).ENTIER = match(ENTIER);

			        // literal n is compiled as rational n/1:
			        // stack after this: ..., num=n, den=1
			        String n = ((ArithmExprContext)_localctx).ENTIER.getText();
			        StringBuilder c = new StringBuilder();
			        c.append("PUSHI ").append(n).append("\n"); // num
			        c.append("PUSHI 1\n");                     // den
			        ((ArithmExprContext)_localctx).code =  c.toString();
			      
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

	public static class BoolExprContext extends ParserRuleContext {
		public String code;
		public BoolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).enterBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationnelListener ) ((RationnelListener)listener).exitBoolExpr(this);
		}
	}

	public final BoolExprContext boolExpr() throws RecognitionException {
		BoolExprContext _localctx = new BoolExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_boolExpr);
		try {
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				match(T__3);

				        ((BoolExprContext)_localctx).code =  "PUSHI 1\n";  // true = 1
				      
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				match(T__4);

				        ((BoolExprContext)_localctx).code =  "PUSHI 0\n";  // false = 0
				      
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22\63\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\7\2\17\n\2\f\2\16\2\22\13\2\7\2\24"+
		"\n\2\f\2\16\2\27\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\5\4(\n\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6\61\n\6\3\6\2\2\7"+
		"\2\4\6\b\n\2\3\3\2\b\t\2\61\2\25\3\2\2\2\4\33\3\2\2\2\6\'\3\2\2\2\b)\3"+
		"\2\2\2\n\60\3\2\2\2\f\20\5\4\3\2\r\17\t\2\2\2\16\r\3\2\2\2\17\22\3\2\2"+
		"\2\20\16\3\2\2\2\20\21\3\2\2\2\21\24\3\2\2\2\22\20\3\2\2\2\23\f\3\2\2"+
		"\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\30\3\2\2\2\27\25\3\2\2"+
		"\2\30\31\7\2\2\3\31\32\b\2\1\2\32\3\3\2\2\2\33\34\7\3\2\2\34\35\7\4\2"+
		"\2\35\36\5\6\4\2\36\37\7\5\2\2\37 \b\3\1\2 \5\3\2\2\2!\"\5\n\6\2\"#\b"+
		"\4\1\2#(\3\2\2\2$%\5\b\5\2%&\b\4\1\2&(\3\2\2\2\'!\3\2\2\2\'$\3\2\2\2("+
		"\7\3\2\2\2)*\7\16\2\2*+\b\5\1\2+\t\3\2\2\2,-\7\6\2\2-\61\b\6\1\2./\7\7"+
		"\2\2/\61\b\6\1\2\60,\3\2\2\2\60.\3\2\2\2\61\13\3\2\2\2\6\20\25\'\60";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}