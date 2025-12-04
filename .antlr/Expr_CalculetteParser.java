// Generated from /home/pc/Documents/Compilation/TP4-COMPILATION/Expr_Calculette.g4 by ANTLR 4.13.1

 import java.util.*;
 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Expr_CalculetteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, LIRE=11, NEWLINE=12, SEMICOLON=13, MULDIV=14, ADDSUB=15, LOGICOP=16, 
		ENTIER=17, TYPE=18, ID=19, WS=20, UNMATCH=21;
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
			null, "'Afficher'", "','", "'='", "'('", "')'", "'not'", "'and'", "'or'", 
			"'true'", "'false'", "'lire'", null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "LIRE", 
			"NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", "ENTIER", "TYPE", 
			"ID", "WS", "UNMATCH"
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

	    Scanner scanner = new Scanner(System.in); // Permet de lire des entrées pour l'instruction lire

	public Expr_CalculetteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				declInstr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				assignInstr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				((InstructionContext)_localctx).e = expr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				match(T__0);
				setState(42);
				((InstructionContext)_localctx).e = expr();

				        if (((InstructionContext)_localctx).e.isBool)
				            System.out.println("Afficher : " + ((InstructionContext)_localctx).e.bvalue);
				        else
				            System.out.println("Afficher : " + ((InstructionContext)_localctx).e.ivalue);
				      
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

	@SuppressWarnings("CheckReturnValue")
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
	}

	public final DeclInstrContext declInstr() throws RecognitionException {
		DeclInstrContext _localctx = new DeclInstrContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declInstr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			((DeclInstrContext)_localctx).t = type();
			setState(48);
			((DeclInstrContext)_localctx).ids = idList();

			        for (String name : ((DeclInstrContext)_localctx).ids.value)
			        {
			            if (symtab.containsKey(name))
			            {
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public String value;
		public Token TYPE;
		public TerminalNode TYPE() { return getToken(Expr_CalculetteParser.TYPE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
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

	@SuppressWarnings("CheckReturnValue")
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
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			((IdListContext)_localctx).id1 = match(ID);

			          ((IdListContext)_localctx).value =  new ArrayList<>();
			          _localctx.value.add(((IdListContext)_localctx).id1.getText());
			      
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(56);
				match(T__1);
				setState(57);
				((IdListContext)_localctx).idn = match(ID);
				 _localctx.value.add(((IdListContext)_localctx).idn.getText()); 
				}
				}
				setState(63);
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
	}

	public final AssignInstrContext assignInstr() throws RecognitionException {
		AssignInstrContext _localctx = new AssignInstrContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignInstr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			((AssignInstrContext)_localctx).id = match(ID);
			setState(65);
			match(T__2);
			setState(66);
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

	@SuppressWarnings("CheckReturnValue")
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
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				((ExprContext)_localctx).b = boolExpr(0);
				 ((ExprContext)_localctx).isBool =  true;  ((ExprContext)_localctx).bvalue =  ((ExprContext)_localctx).b.value;  ((ExprContext)_localctx).ivalue =  null; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArithmExprContext extends ParserRuleContext {
		public int value;
		public ArithmExprContext A1;
		public Token ADDSUB;
		public ArithmExprContext a;
		public Token id;
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
		public TerminalNode LIRE() { return getToken(Expr_CalculetteParser.LIRE, 0); }
		public TerminalNode ID() { return getToken(Expr_CalculetteParser.ID, 0); }
		public TerminalNode ENTIER() { return getToken(Expr_CalculetteParser.ENTIER, 0); }
		public TerminalNode MULDIV() { return getToken(Expr_CalculetteParser.MULDIV, 0); }
		public ArithmExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmExpr; }
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
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADDSUB:
				{
				setState(78);
				((ArithmExprContext)_localctx).ADDSUB = match(ADDSUB);
				setState(79);
				((ArithmExprContext)_localctx).a = arithmExpr(7);
				if (((ArithmExprContext)_localctx).ADDSUB.getText().equals("-"))
				                            ((ArithmExprContext)_localctx).value =  -((ArithmExprContext)_localctx).a.value;
				                          else
				                              ((ArithmExprContext)_localctx).value =  +((ArithmExprContext)_localctx).a.value;
				                        
				}
				break;
			case T__3:
				{
				setState(82);
				match(T__3);
				setState(83);
				((ArithmExprContext)_localctx).A1 = arithmExpr(0);
				setState(84);
				match(T__4);
				 ((ArithmExprContext)_localctx).value =  ((ArithmExprContext)_localctx).A1.value;
				}
				break;
			case LIRE:
				{
				setState(87);
				match(LIRE);

				          try
				          {
				              ((ArithmExprContext)_localctx).value =  scanner.nextInt();
				          } 
				          catch (Exception ex)
				          {
				              throw new RuntimeException("Expected int from lire()");
				          }
				      
				}
				break;
			case ID:
				{
				setState(89);
				((ArithmExprContext)_localctx).id = match(ID);

				        String name = ((ArithmExprContext)_localctx).id.getText();
				        VarEntry v = symtab.get(name);
				        if (v == null) {
				            throw new RuntimeException("Undeclared variable: " + name);
				        }
				        if (!v.initialized) {
				            throw new RuntimeException("Uninitialized variable: " + name);
				        }
				        if (!"int".equals(v.type)) {
				            throw new RuntimeException("Type error: " + name + " is not int");
				        }
				        ((ArithmExprContext)_localctx).value =  v.ivalue;
				      
				}
				break;
			case ENTIER:
				{
				setState(91);
				((ArithmExprContext)_localctx).ENTIER = match(ENTIER);
				 ((ArithmExprContext)_localctx).value =  Integer.parseInt(((ArithmExprContext)_localctx).ENTIER.getText()); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(107);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(105);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmExprContext(_parentctx, _parentState);
						_localctx.A1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmExpr);
						setState(95);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(96);
						((ArithmExprContext)_localctx).MULDIV = match(MULDIV);
						setState(97);
						((ArithmExprContext)_localctx).A2 = arithmExpr(6);
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
						pushNewRecursionContext(_localctx, _startState, RULE_arithmExpr);
						setState(100);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(101);
						((ArithmExprContext)_localctx).ADDSUB = match(ADDSUB);
						setState(102);
						((ArithmExprContext)_localctx).A2 = arithmExpr(5);
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
				setState(109);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BoolExprContext extends ParserRuleContext {
		public boolean value;
		public BoolExprContext e1;
		public BoolExprContext e;
		public ArithmExprContext a1;
		public Token LOGICOP;
		public ArithmExprContext a2;
		public Token id;
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
		public TerminalNode LIRE() { return getToken(Expr_CalculetteParser.LIRE, 0); }
		public TerminalNode ID() { return getToken(Expr_CalculetteParser.ID, 0); }
		public BoolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExpr; }
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
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(111);
				match(T__5);
				setState(112);
				((BoolExprContext)_localctx).e = boolExpr(9);
				 ((BoolExprContext)_localctx).value =  ! ((BoolExprContext)_localctx).e.value; 
				}
				break;
			case 2:
				{
				setState(115);
				match(T__3);
				setState(116);
				((BoolExprContext)_localctx).e = boolExpr(0);
				setState(117);
				match(T__4);
				 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e.value; 
				}
				break;
			case 3:
				{
				setState(120);
				((BoolExprContext)_localctx).a1 = arithmExpr(0);
				setState(121);
				((BoolExprContext)_localctx).LOGICOP = match(LOGICOP);
				setState(122);
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
				setState(125);
				match(LIRE);

				          try {
				              ((BoolExprContext)_localctx).value =  scanner.nextBoolean();
				          } catch (Exception ex) {
				              throw new RuntimeException("Expected bool from lire()");
				          }
				      
				}
				break;
			case 5:
				{
				setState(127);
				((BoolExprContext)_localctx).id = match(ID);

				        String name = ((BoolExprContext)_localctx).id.getText();
				        VarEntry v = symtab.get(name);
				        if (v == null) {
				            throw new RuntimeException("Undeclared variable: " + name);
				        }
				        if (!v.initialized) {
				            throw new RuntimeException("Uninitialized variable: " + name);
				        }
				        if (!"bool".equals(v.type)) {
				            throw new RuntimeException("Type error: " + name + " is not bool");
				        }
				        ((BoolExprContext)_localctx).value =  v.bvalue;
				      
				}
				break;
			case 6:
				{
				setState(129);
				match(T__8);
				 ((BoolExprContext)_localctx).value =   true; 
				}
				break;
			case 7:
				{
				setState(131);
				match(T__9);
				 ((BoolExprContext)_localctx).value =   false; 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(147);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(145);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new BoolExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
						setState(135);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(136);
						match(T__6);
						setState(137);
						((BoolExprContext)_localctx).e2 = boolExpr(8);
						 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e1.value && ((BoolExprContext)_localctx).e2.value; 
						}
						break;
					case 2:
						{
						_localctx = new BoolExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
						setState(140);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(141);
						match(T__7);
						setState(142);
						((BoolExprContext)_localctx).e2 = boolExpr(7);
						 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e1.value || ((BoolExprContext)_localctx).e2.value; 
						}
						break;
					}
					} 
				}
				setState(149);
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
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean boolExpr_sempred(BoolExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0015\u0097\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0001\u0000\u0001\u0000\u0004\u0000\u0015\b\u0000\u000b"+
		"\u0000\f\u0000\u0016\u0001\u0000\u0005\u0000\u001a\b\u0000\n\u0000\f\u0000"+
		"\u001d\t\u0000\u0001\u0000\u0005\u0000 \b\u0000\n\u0000\f\u0000#\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001.\b\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"<\b\u0004\n\u0004\f\u0004?\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006L\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007^\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007j\b\u0007\n\u0007\f\u0007m\t\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0086\b\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005"+
		"\b\u0092\b\b\n\b\f\b\u0095\t\b\u0001\b\u0000\u0002\u000e\u0010\t\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0000\u0001\u0001\u0000\f\r\u00a3"+
		"\u0000\u0012\u0001\u0000\u0000\u0000\u0002-\u0001\u0000\u0000\u0000\u0004"+
		"/\u0001\u0000\u0000\u0000\u00063\u0001\u0000\u0000\u0000\b6\u0001\u0000"+
		"\u0000\u0000\n@\u0001\u0000\u0000\u0000\fK\u0001\u0000\u0000\u0000\u000e"+
		"]\u0001\u0000\u0000\u0000\u0010\u0085\u0001\u0000\u0000\u0000\u0012\u001b"+
		"\u0003\u0002\u0001\u0000\u0013\u0015\u0007\u0000\u0000\u0000\u0014\u0013"+
		"\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0014"+
		"\u0001\u0000\u0000\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0018"+
		"\u0001\u0000\u0000\u0000\u0018\u001a\u0003\u0002\u0001\u0000\u0019\u0014"+
		"\u0001\u0000\u0000\u0000\u001a\u001d\u0001\u0000\u0000\u0000\u001b\u0019"+
		"\u0001\u0000\u0000\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c!\u0001"+
		"\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001e \u0007\u0000"+
		"\u0000\u0000\u001f\u001e\u0001\u0000\u0000\u0000 #\u0001\u0000\u0000\u0000"+
		"!\u001f\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"$\u0001\u0000"+
		"\u0000\u0000#!\u0001\u0000\u0000\u0000$%\u0005\u0000\u0000\u0001%\u0001"+
		"\u0001\u0000\u0000\u0000&.\u0003\u0004\u0002\u0000\'.\u0003\n\u0005\u0000"+
		"(.\u0003\f\u0006\u0000)*\u0005\u0001\u0000\u0000*+\u0003\f\u0006\u0000"+
		"+,\u0006\u0001\uffff\uffff\u0000,.\u0001\u0000\u0000\u0000-&\u0001\u0000"+
		"\u0000\u0000-\'\u0001\u0000\u0000\u0000-(\u0001\u0000\u0000\u0000-)\u0001"+
		"\u0000\u0000\u0000.\u0003\u0001\u0000\u0000\u0000/0\u0003\u0006\u0003"+
		"\u000001\u0003\b\u0004\u000012\u0006\u0002\uffff\uffff\u00002\u0005\u0001"+
		"\u0000\u0000\u000034\u0005\u0012\u0000\u000045\u0006\u0003\uffff\uffff"+
		"\u00005\u0007\u0001\u0000\u0000\u000067\u0005\u0013\u0000\u00007=\u0006"+
		"\u0004\uffff\uffff\u000089\u0005\u0002\u0000\u00009:\u0005\u0013\u0000"+
		"\u0000:<\u0006\u0004\uffff\uffff\u0000;8\u0001\u0000\u0000\u0000<?\u0001"+
		"\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000"+
		">\t\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@A\u0005\u0013\u0000"+
		"\u0000AB\u0005\u0003\u0000\u0000BC\u0003\f\u0006\u0000CD\u0006\u0005\uffff"+
		"\uffff\u0000D\u000b\u0001\u0000\u0000\u0000EF\u0003\u0010\b\u0000FG\u0006"+
		"\u0006\uffff\uffff\u0000GL\u0001\u0000\u0000\u0000HI\u0003\u000e\u0007"+
		"\u0000IJ\u0006\u0006\uffff\uffff\u0000JL\u0001\u0000\u0000\u0000KE\u0001"+
		"\u0000\u0000\u0000KH\u0001\u0000\u0000\u0000L\r\u0001\u0000\u0000\u0000"+
		"MN\u0006\u0007\uffff\uffff\u0000NO\u0005\u000f\u0000\u0000OP\u0003\u000e"+
		"\u0007\u0007PQ\u0006\u0007\uffff\uffff\u0000Q^\u0001\u0000\u0000\u0000"+
		"RS\u0005\u0004\u0000\u0000ST\u0003\u000e\u0007\u0000TU\u0005\u0005\u0000"+
		"\u0000UV\u0006\u0007\uffff\uffff\u0000V^\u0001\u0000\u0000\u0000WX\u0005"+
		"\u000b\u0000\u0000X^\u0006\u0007\uffff\uffff\u0000YZ\u0005\u0013\u0000"+
		"\u0000Z^\u0006\u0007\uffff\uffff\u0000[\\\u0005\u0011\u0000\u0000\\^\u0006"+
		"\u0007\uffff\uffff\u0000]M\u0001\u0000\u0000\u0000]R\u0001\u0000\u0000"+
		"\u0000]W\u0001\u0000\u0000\u0000]Y\u0001\u0000\u0000\u0000][\u0001\u0000"+
		"\u0000\u0000^k\u0001\u0000\u0000\u0000_`\n\u0005\u0000\u0000`a\u0005\u000e"+
		"\u0000\u0000ab\u0003\u000e\u0007\u0006bc\u0006\u0007\uffff\uffff\u0000"+
		"cj\u0001\u0000\u0000\u0000de\n\u0004\u0000\u0000ef\u0005\u000f\u0000\u0000"+
		"fg\u0003\u000e\u0007\u0005gh\u0006\u0007\uffff\uffff\u0000hj\u0001\u0000"+
		"\u0000\u0000i_\u0001\u0000\u0000\u0000id\u0001\u0000\u0000\u0000jm\u0001"+
		"\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000"+
		"l\u000f\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000no\u0006\b\uffff"+
		"\uffff\u0000op\u0005\u0006\u0000\u0000pq\u0003\u0010\b\tqr\u0006\b\uffff"+
		"\uffff\u0000r\u0086\u0001\u0000\u0000\u0000st\u0005\u0004\u0000\u0000"+
		"tu\u0003\u0010\b\u0000uv\u0005\u0005\u0000\u0000vw\u0006\b\uffff\uffff"+
		"\u0000w\u0086\u0001\u0000\u0000\u0000xy\u0003\u000e\u0007\u0000yz\u0005"+
		"\u0010\u0000\u0000z{\u0003\u000e\u0007\u0000{|\u0006\b\uffff\uffff\u0000"+
		"|\u0086\u0001\u0000\u0000\u0000}~\u0005\u000b\u0000\u0000~\u0086\u0006"+
		"\b\uffff\uffff\u0000\u007f\u0080\u0005\u0013\u0000\u0000\u0080\u0086\u0006"+
		"\b\uffff\uffff\u0000\u0081\u0082\u0005\t\u0000\u0000\u0082\u0086\u0006"+
		"\b\uffff\uffff\u0000\u0083\u0084\u0005\n\u0000\u0000\u0084\u0086\u0006"+
		"\b\uffff\uffff\u0000\u0085n\u0001\u0000\u0000\u0000\u0085s\u0001\u0000"+
		"\u0000\u0000\u0085x\u0001\u0000\u0000\u0000\u0085}\u0001\u0000\u0000\u0000"+
		"\u0085\u007f\u0001\u0000\u0000\u0000\u0085\u0081\u0001\u0000\u0000\u0000"+
		"\u0085\u0083\u0001\u0000\u0000\u0000\u0086\u0093\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\n\u0007\u0000\u0000\u0088\u0089\u0005\u0007\u0000\u0000\u0089"+
		"\u008a\u0003\u0010\b\b\u008a\u008b\u0006\b\uffff\uffff\u0000\u008b\u0092"+
		"\u0001\u0000\u0000\u0000\u008c\u008d\n\u0006\u0000\u0000\u008d\u008e\u0005"+
		"\b\u0000\u0000\u008e\u008f\u0003\u0010\b\u0007\u008f\u0090\u0006\b\uffff"+
		"\uffff\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091\u0087\u0001\u0000"+
		"\u0000\u0000\u0091\u008c\u0001\u0000\u0000\u0000\u0092\u0095\u0001\u0000"+
		"\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000"+
		"\u0000\u0000\u0094\u0011\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000"+
		"\u0000\u0000\f\u0016\u001b!-=K]ik\u0085\u0091\u0093";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}