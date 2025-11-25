// Generated from Expr_Calculette.g4 by ANTLR 4.9.2

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
public class Expr_CalculetteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		NEWLINE=10, SEMICOLON=11, MULDIV=12, ADDSUB=13, LOGICOP=14, ENTIER=15, 
		TYPE=16, ID=17, WS=18, UNMATCH=19;
	public static final int
		RULE_start = 0, RULE_instruction = 1, RULE_declInstr = 2, RULE_type = 3, 
		RULE_idList = 4, RULE_assignInstr = 5, RULE_expr = 6, RULE_arithmExpr = 7, 
		RULE_boolExpr = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "instruction", "declInstr", "type", "idList", "assignInstr", 
			"expr", "arithmExpr", "boolExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'='", "'('", "')'", "'not'", "'and'", "'or'", "'true'", 
			"'false'", null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "NEWLINE", 
			"SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", "ENTIER", "TYPE", "ID", "WS", 
			"UNMATCH"
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
	public String getGrammarFileName() { return "Expr_Calculette.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    static class VarEntry {
	        String type;        // "int" veya "bool"
	        boolean initialized;
	        Integer ivalue;     // sadece int için kullan
	        Boolean bvalue;     // sadece bool için kullan
	    }

	    Map<String, VarEntry> symtab = new HashMap<>(); // symtab : symbole table

	public Expr_CalculetteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public TerminalNode EOF() { return getToken(Expr_CalculetteParser.EOF, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(Expr_CalculetteParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(Expr_CalculetteParser.SEMICOLON, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(Expr_CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Expr_CalculetteParser.NEWLINE, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			instruction();
			setState(27);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(20); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(19);
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
						setState(22); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NEWLINE || _la==SEMICOLON );
					setState(24);
					instruction();
					}
					} 
				}
				setState(29);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE || _la==SEMICOLON) {
				{
				{
				setState(30);
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
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			match(EOF);
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
		public DeclInstrContext declInstr() {
			return getRuleContext(DeclInstrContext.class,0);
		}
		public AssignInstrContext assignInstr() {
			return getRuleContext(AssignInstrContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				declInstr();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				assignInstr();
				}
				break;
			case T__2:
			case T__4:
			case T__7:
			case T__8:
			case ADDSUB:
			case ENTIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				((InstructionContext)_localctx).e = expr();

				        if (((InstructionContext)_localctx).e.isBool)
				            System.out.println("Afficher : " + ((InstructionContext)_localctx).e.bvalue);
				        else
				            System.out.println("Afficher : " + ((InstructionContext)_localctx).e.ivalue);
				      
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

	public static class DeclInstrContext extends ParserRuleContext {
		public TypeContext t;
		public IdListContext ids;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public DeclInstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declInstr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterDeclInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitDeclInstr(this);
		}
	}

	public final DeclInstrContext declInstr() throws RecognitionException {
		DeclInstrContext _localctx = new DeclInstrContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declInstr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			((DeclInstrContext)_localctx).t = type();
			setState(46);
			((DeclInstrContext)_localctx).ids = idList();

			        for (String name : ((DeclInstrContext)_localctx).ids.value) {
			            if (symtab.containsKey(name)) {
			                throw new RuntimeException("Variable already declared: " + name);
			            }

			            VarEntry e = new VarEntry();
			            e.type = ((DeclInstrContext)_localctx).t.value;
			            e.initialized = false;
			            e.ivalue = null;
			            e.bvalue = null;
			            
			            symtab.put(name, e);
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

	public static class TypeContext extends ParserRuleContext {
		public String value;
		public Token TYPE;
		public TerminalNode TYPE() { return getToken(Expr_CalculetteParser.TYPE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			((TypeContext)_localctx).TYPE = match(TYPE);
			 ((TypeContext)_localctx).value =  ((TypeContext)_localctx).TYPE.getText(); 
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

	public static class IdListContext extends ParserRuleContext {
		public List<String> value;
		public Token id1;
		public Token idn;
		public List<TerminalNode> ID() { return getTokens(Expr_CalculetteParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(Expr_CalculetteParser.ID, i);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitIdList(this);
		}
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			((IdListContext)_localctx).id1 = match(ID);

			          ((IdListContext)_localctx).value =  new ArrayList<>();
			          _localctx.value.add(((IdListContext)_localctx).id1.getText());
			      
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(54);
				match(T__0);
				setState(55);
				((IdListContext)_localctx).idn = match(ID);
				 _localctx.value.add(((IdListContext)_localctx).idn.getText()); 
				}
				}
				setState(61);
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

	public static class AssignInstrContext extends ParserRuleContext {
		public Token id;
		public ExprContext e;
		public TerminalNode ID() { return getToken(Expr_CalculetteParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignInstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignInstr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterAssignInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitAssignInstr(this);
		}
	}

	public final AssignInstrContext assignInstr() throws RecognitionException {
		AssignInstrContext _localctx = new AssignInstrContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignInstr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			((AssignInstrContext)_localctx).id = match(ID);
			setState(63);
			match(T__1);
			setState(64);
			((AssignInstrContext)_localctx).e = expr();

			        String name = ((AssignInstrContext)_localctx).id.getText();
			        VarEntry v = symtab.get(name);  // v = variable entry
			        if (v == null) {
			            throw new RuntimeException("Undeclared variable: " + name);
			        }

			        // Control type compatibility
			        if (v.type.equals("int")) {
			            if (((AssignInstrContext)_localctx).e.isBool) {
			                throw new RuntimeException("Type error: assigning bool to int variable " + name);
			            }
			            v.ivalue = ((AssignInstrContext)_localctx).e.ivalue;
			            v.bvalue = null;
			        } else if (v.type.equals("bool")) {
			            if (!((AssignInstrContext)_localctx).e.isBool) {
			                throw new RuntimeException("Type error: assigning int to bool variable " + name);
			            }
			            v.bvalue = ((AssignInstrContext)_localctx).e.bvalue;
			            v.ivalue = null;
			        } else {
			            throw new RuntimeException("Unknown type for variable " + name);
			        }

			        v.initialized = true;
			      
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
		public Integer ivalue;
		public Boolean bvalue;
		public boolean isBool;
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
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				((ExprContext)_localctx).b = boolExpr(0);
				 ((ExprContext)_localctx).isBool =  true;  ((ExprContext)_localctx).bvalue =  ((ExprContext)_localctx).b.value;  ((ExprContext)_localctx).ivalue =  null; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				((ExprContext)_localctx).a = arithmExpr(0);
				 ((ExprContext)_localctx).isBool =  false; ((ExprContext)_localctx).ivalue =  ((ExprContext)_localctx).a.value;  ((ExprContext)_localctx).bvalue =  null; 
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

	public static class ArithmExprContext extends ParserRuleContext {
		public int value;
		public ArithmExprContext A1;
		public Token ADDSUB;
		public ArithmExprContext a;
		public Token ENTIER;
		public Token MULDIV;
		public ArithmExprContext A2;
		public TerminalNode ADDSUB() { return getToken(Expr_CalculetteParser.ADDSUB, 0); }
		public List<ArithmExprContext> arithmExpr() {
			return getRuleContexts(ArithmExprContext.class);
		}
		public ArithmExprContext arithmExpr(int i) {
			return getRuleContext(ArithmExprContext.class,i);
		}
		public TerminalNode ENTIER() { return getToken(Expr_CalculetteParser.ENTIER, 0); }
		public TerminalNode MULDIV() { return getToken(Expr_CalculetteParser.MULDIV, 0); }
		public ArithmExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterArithmExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitArithmExpr(this);
		}
	}

	public final ArithmExprContext arithmExpr() throws RecognitionException {
		return arithmExpr(0);
	}

	private ArithmExprContext arithmExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithmExprContext _localctx = new ArithmExprContext(_ctx, _parentState);
		ArithmExprContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_arithmExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADDSUB:
				{
				setState(76);
				((ArithmExprContext)_localctx).ADDSUB = match(ADDSUB);
				setState(77);
				((ArithmExprContext)_localctx).a = arithmExpr(5);
				if (((ArithmExprContext)_localctx).ADDSUB.getText().equals("-"))
				                            ((ArithmExprContext)_localctx).value =  -((ArithmExprContext)_localctx).a.value;
				                          else
				                              ((ArithmExprContext)_localctx).value =  +((ArithmExprContext)_localctx).a.value;
				                        
				}
				break;
			case T__2:
				{
				setState(80);
				match(T__2);
				setState(81);
				((ArithmExprContext)_localctx).A1 = arithmExpr(0);
				setState(82);
				match(T__3);
				 ((ArithmExprContext)_localctx).value =  ((ArithmExprContext)_localctx).A1.value;
				}
				break;
			case ENTIER:
				{
				setState(85);
				((ArithmExprContext)_localctx).ENTIER = match(ENTIER);
				 ((ArithmExprContext)_localctx).value =  Integer.parseInt(((ArithmExprContext)_localctx).ENTIER.getText()); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(99);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmExprContext(_parentctx, _parentState);
						_localctx.A1 = _prevctx;
						_localctx.A1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmExpr);
						setState(89);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(90);
						((ArithmExprContext)_localctx).MULDIV = match(MULDIV);
						setState(91);
						((ArithmExprContext)_localctx).A2 = arithmExpr(4);
						 if (((ArithmExprContext)_localctx).MULDIV.getText().equals("*")) {
						                                                             ((ArithmExprContext)_localctx).value =  ((ArithmExprContext)_localctx).A1.value * ((ArithmExprContext)_localctx).A2.value;
						                                                          } else {
						                                                            ((ArithmExprContext)_localctx).value =  ((ArithmExprContext)_localctx).A1.value / ((ArithmExprContext)_localctx).A2.value;
						                                                          } 
						                                                        
						}
						break;
					case 2:
						{
						_localctx = new ArithmExprContext(_parentctx, _parentState);
						_localctx.A1 = _prevctx;
						_localctx.A1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmExpr);
						setState(94);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(95);
						((ArithmExprContext)_localctx).ADDSUB = match(ADDSUB);
						setState(96);
						((ArithmExprContext)_localctx).A2 = arithmExpr(3);
						 if (((ArithmExprContext)_localctx).ADDSUB.getText().equals("+")) {
						                                                             ((ArithmExprContext)_localctx).value =  ((ArithmExprContext)_localctx).A1.value + ((ArithmExprContext)_localctx).A2.value;
						                                                          } else {
						                                                            ((ArithmExprContext)_localctx).value =  ((ArithmExprContext)_localctx).A1.value - ((ArithmExprContext)_localctx).A2.value;
						                                                          } 
						                                                        
						}
						break;
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BoolExprContext extends ParserRuleContext {
		public boolean value;
		public BoolExprContext e1;
		public BoolExprContext e;
		public ArithmExprContext a1;
		public Token LOGICOP;
		public ArithmExprContext a2;
		public BoolExprContext e2;
		public List<BoolExprContext> boolExpr() {
			return getRuleContexts(BoolExprContext.class);
		}
		public BoolExprContext boolExpr(int i) {
			return getRuleContext(BoolExprContext.class,i);
		}
		public TerminalNode LOGICOP() { return getToken(Expr_CalculetteParser.LOGICOP, 0); }
		public List<ArithmExprContext> arithmExpr() {
			return getRuleContexts(ArithmExprContext.class);
		}
		public ArithmExprContext arithmExpr(int i) {
			return getRuleContext(ArithmExprContext.class,i);
		}
		public BoolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).enterBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Expr_CalculetteListener ) ((Expr_CalculetteListener)listener).exitBoolExpr(this);
		}
	}

	public final BoolExprContext boolExpr() throws RecognitionException {
		return boolExpr(0);
	}

	private BoolExprContext boolExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BoolExprContext _localctx = new BoolExprContext(_ctx, _parentState);
		BoolExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_boolExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(105);
				match(T__4);
				setState(106);
				((BoolExprContext)_localctx).e = boolExpr(7);
				 ((BoolExprContext)_localctx).value =  ! ((BoolExprContext)_localctx).e.value; 
				}
				break;
			case 2:
				{
				setState(109);
				match(T__2);
				setState(110);
				((BoolExprContext)_localctx).e = boolExpr(0);
				setState(111);
				match(T__3);
				 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e.value; 
				}
				break;
			case 3:
				{
				setState(114);
				((BoolExprContext)_localctx).a1 = arithmExpr(0);
				setState(115);
				((BoolExprContext)_localctx).LOGICOP = match(LOGICOP);
				setState(116);
				((BoolExprContext)_localctx).a2 = arithmExpr(0);
				 
				        switch (((BoolExprContext)_localctx).LOGICOP.getText()) {
				            case "==": ((BoolExprContext)_localctx).value =  (((BoolExprContext)_localctx).a1.value == ((BoolExprContext)_localctx).a2.value); break;
				            case "<>": ((BoolExprContext)_localctx).value =  (((BoolExprContext)_localctx).a1.value != ((BoolExprContext)_localctx).a2.value); break;
				            case "<":  ((BoolExprContext)_localctx).value =  (((BoolExprContext)_localctx).a1.value < ((BoolExprContext)_localctx).a2.value); break;
				            case "<=": ((BoolExprContext)_localctx).value =  (((BoolExprContext)_localctx).a1.value <= ((BoolExprContext)_localctx).a2.value); break;
				            case ">":  ((BoolExprContext)_localctx).value =  (((BoolExprContext)_localctx).a1.value > ((BoolExprContext)_localctx).a2.value); break;
				            case ">=": ((BoolExprContext)_localctx).value =  (((BoolExprContext)_localctx).a1.value >= ((BoolExprContext)_localctx).a2.value); break;
				            default:   ((BoolExprContext)_localctx).value =  false; // should not happen
				        }
				      
				}
				break;
			case 4:
				{
				setState(119);
				match(T__7);
				 ((BoolExprContext)_localctx).value =   true; 
				}
				break;
			case 5:
				{
				setState(121);
				match(T__8);
				 ((BoolExprContext)_localctx).value =   false; 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(137);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(135);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new BoolExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
						setState(125);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(126);
						match(T__5);
						setState(127);
						((BoolExprContext)_localctx).e2 = boolExpr(6);
						 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e1.value && ((BoolExprContext)_localctx).e2.value; 
						}
						break;
					case 2:
						{
						_localctx = new BoolExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
						setState(130);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(131);
						match(T__6);
						setState(132);
						((BoolExprContext)_localctx).e2 = boolExpr(5);
						 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e1.value || ((BoolExprContext)_localctx).e2.value; 
						}
						break;
					}
					} 
				}
				setState(139);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return arithmExpr_sempred((ArithmExprContext)_localctx, predIndex);
		case 8:
			return boolExpr_sempred((BoolExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmExpr_sempred(ArithmExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean boolExpr_sempred(BoolExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25\u008f\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\6\2\27\n\2\r\2\16\2\30\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\7\2\"\n"+
		"\2\f\2\16\2%\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3.\n\3\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\7\6<\n\6\f\6\16\6?\13\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5\bL\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\5\tZ\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7"+
		"\tf\n\t\f\t\16\ti\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n~\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\7\n\u008a\n\n\f\n\16\n\u008d\13\n\3\n\2\4\20\22\13\2\4\6\b\n"+
		"\f\16\20\22\2\3\3\2\f\r\2\u0096\2\24\3\2\2\2\4-\3\2\2\2\6/\3\2\2\2\b\63"+
		"\3\2\2\2\n\66\3\2\2\2\f@\3\2\2\2\16K\3\2\2\2\20Y\3\2\2\2\22}\3\2\2\2\24"+
		"\35\5\4\3\2\25\27\t\2\2\2\26\25\3\2\2\2\27\30\3\2\2\2\30\26\3\2\2\2\30"+
		"\31\3\2\2\2\31\32\3\2\2\2\32\34\5\4\3\2\33\26\3\2\2\2\34\37\3\2\2\2\35"+
		"\33\3\2\2\2\35\36\3\2\2\2\36#\3\2\2\2\37\35\3\2\2\2 \"\t\2\2\2! \3\2\2"+
		"\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$&\3\2\2\2%#\3\2\2\2&\'\7\2\2\3\'\3\3"+
		"\2\2\2(.\5\6\4\2).\5\f\7\2*+\5\16\b\2+,\b\3\1\2,.\3\2\2\2-(\3\2\2\2-)"+
		"\3\2\2\2-*\3\2\2\2.\5\3\2\2\2/\60\5\b\5\2\60\61\5\n\6\2\61\62\b\4\1\2"+
		"\62\7\3\2\2\2\63\64\7\22\2\2\64\65\b\5\1\2\65\t\3\2\2\2\66\67\7\23\2\2"+
		"\67=\b\6\1\289\7\3\2\29:\7\23\2\2:<\b\6\1\2;8\3\2\2\2<?\3\2\2\2=;\3\2"+
		"\2\2=>\3\2\2\2>\13\3\2\2\2?=\3\2\2\2@A\7\23\2\2AB\7\4\2\2BC\5\16\b\2C"+
		"D\b\7\1\2D\r\3\2\2\2EF\5\22\n\2FG\b\b\1\2GL\3\2\2\2HI\5\20\t\2IJ\b\b\1"+
		"\2JL\3\2\2\2KE\3\2\2\2KH\3\2\2\2L\17\3\2\2\2MN\b\t\1\2NO\7\17\2\2OP\5"+
		"\20\t\7PQ\b\t\1\2QZ\3\2\2\2RS\7\5\2\2ST\5\20\t\2TU\7\6\2\2UV\b\t\1\2V"+
		"Z\3\2\2\2WX\7\21\2\2XZ\b\t\1\2YM\3\2\2\2YR\3\2\2\2YW\3\2\2\2Zg\3\2\2\2"+
		"[\\\f\5\2\2\\]\7\16\2\2]^\5\20\t\6^_\b\t\1\2_f\3\2\2\2`a\f\4\2\2ab\7\17"+
		"\2\2bc\5\20\t\5cd\b\t\1\2df\3\2\2\2e[\3\2\2\2e`\3\2\2\2fi\3\2\2\2ge\3"+
		"\2\2\2gh\3\2\2\2h\21\3\2\2\2ig\3\2\2\2jk\b\n\1\2kl\7\7\2\2lm\5\22\n\t"+
		"mn\b\n\1\2n~\3\2\2\2op\7\5\2\2pq\5\22\n\2qr\7\6\2\2rs\b\n\1\2s~\3\2\2"+
		"\2tu\5\20\t\2uv\7\20\2\2vw\5\20\t\2wx\b\n\1\2x~\3\2\2\2yz\7\n\2\2z~\b"+
		"\n\1\2{|\7\13\2\2|~\b\n\1\2}j\3\2\2\2}o\3\2\2\2}t\3\2\2\2}y\3\2\2\2}{"+
		"\3\2\2\2~\u008b\3\2\2\2\177\u0080\f\7\2\2\u0080\u0081\7\b\2\2\u0081\u0082"+
		"\5\22\n\b\u0082\u0083\b\n\1\2\u0083\u008a\3\2\2\2\u0084\u0085\f\6\2\2"+
		"\u0085\u0086\7\t\2\2\u0086\u0087\5\22\n\7\u0087\u0088\b\n\1\2\u0088\u008a"+
		"\3\2\2\2\u0089\177\3\2\2\2\u0089\u0084\3\2\2\2\u008a\u008d\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\23\3\2\2\2\u008d\u008b\3\2\2"+
		"\2\16\30\35#-=KYeg}\u0089\u008b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}