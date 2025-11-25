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
		T__9=10, T__10=11, NEWLINE=12, SEMICOLON=13, MULDIV=14, ADDSUB=15, LOGICOP=16, 
		ENTIER=17, BOOL=18, TYPE=19, ID=20, WS=21, UNMATCH=22;
	public static final int
		RULE_start = 0, RULE_instruction = 1, RULE_declAssigneInstr = 2, RULE_declInstr = 3, 
		RULE_type = 4, RULE_idList = 5, RULE_assignInstr = 6, RULE_expr = 7, RULE_arithmExpr = 8, 
		RULE_boolExpr = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "instruction", "declAssigneInstr", "declInstr", "type", "idList", 
			"assignInstr", "expr", "arithmExpr", "boolExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'='", "'bool'", "','", "'('", "')'", "'not'", "'and'", 
			"'or'", "'true'", "'false'", null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", "ENTIER", "BOOL", 
			"TYPE", "ID", "WS", "UNMATCH"
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
			setState(20);
			instruction();
			setState(29);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(22); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(21);
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
						setState(24); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NEWLINE || _la==SEMICOLON );
					setState(26);
					instruction();
					}
					} 
				}
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE || _la==SEMICOLON) {
				{
				{
				setState(32);
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
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
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
		public DeclAssigneInstrContext declAssigneInstr() {
			return getRuleContext(DeclAssigneInstrContext.class,0);
		}
		public AssignInstrContext assignInstr() {
			return getRuleContext(AssignInstrContext.class,0);
		}
		public DeclInstrContext declInstr() {
			return getRuleContext(DeclInstrContext.class,0);
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
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				declAssigneInstr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				assignInstr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(42);
				declInstr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
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
	public static class DeclAssigneInstrContext extends ParserRuleContext {
		public Token t;
		public Token id;
		public TerminalNode ENTIER() { return getToken(Expr_CalculetteParser.ENTIER, 0); }
		public TerminalNode ID() { return getToken(Expr_CalculetteParser.ID, 0); }
		public TerminalNode BOOL() { return getToken(Expr_CalculetteParser.BOOL, 0); }
		public DeclAssigneInstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declAssigneInstr; }
	}

	public final DeclAssigneInstrContext declAssigneInstr() throws RecognitionException {
		DeclAssigneInstrContext _localctx = new DeclAssigneInstrContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declAssigneInstr);
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				((DeclAssigneInstrContext)_localctx).t = match(T__0);
				setState(49);
				((DeclAssigneInstrContext)_localctx).id = match(ID);
				setState(50);
				match(T__1);
				setState(51);
				match(ENTIER);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				((DeclAssigneInstrContext)_localctx).t = match(T__2);
				setState(53);
				((DeclAssigneInstrContext)_localctx).id = match(ID);
				setState(54);
				match(T__1);
				setState(55);
				match(BOOL);
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
		enterRule(_localctx, 6, RULE_declInstr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((DeclInstrContext)_localctx).t = type();
			setState(59);
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
		enterRule(_localctx, 8, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
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
		enterRule(_localctx, 10, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			((IdListContext)_localctx).id1 = match(ID);

			          ((IdListContext)_localctx).value =  new ArrayList<>();
			          _localctx.value.add(((IdListContext)_localctx).id1.getText());
			      
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(67);
				match(T__3);
				setState(68);
				((IdListContext)_localctx).idn = match(ID);
				 _localctx.value.add(((IdListContext)_localctx).idn.getText()); 
				}
				}
				setState(74);
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
		enterRule(_localctx, 12, RULE_assignInstr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			((AssignInstrContext)_localctx).id = match(ID);
			setState(76);
			match(T__1);
			setState(77);
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
		enterRule(_localctx, 14, RULE_expr);
		try {
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				((ExprContext)_localctx).b = boolExpr(0);
				 ((ExprContext)_localctx).isBool =  true;  ((ExprContext)_localctx).bvalue =  ((ExprContext)_localctx).b.value;  ((ExprContext)_localctx).ivalue =  null; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_arithmExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADDSUB:
				{
				setState(89);
				((ArithmExprContext)_localctx).ADDSUB = match(ADDSUB);
				setState(90);
				((ArithmExprContext)_localctx).a = arithmExpr(6);
				if (((ArithmExprContext)_localctx).ADDSUB.getText().equals("-"))
				                            ((ArithmExprContext)_localctx).value =  -((ArithmExprContext)_localctx).a.value;
				                          else
				                              ((ArithmExprContext)_localctx).value =  +((ArithmExprContext)_localctx).a.value;
				                        
				}
				break;
			case T__4:
				{
				setState(93);
				match(T__4);
				setState(94);
				((ArithmExprContext)_localctx).A1 = arithmExpr(0);
				setState(95);
				match(T__5);
				 ((ArithmExprContext)_localctx).value =  ((ArithmExprContext)_localctx).A1.value;
				}
				break;
			case ID:
				{
				setState(98);
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
				setState(100);
				((ArithmExprContext)_localctx).ENTIER = match(ENTIER);
				 ((ArithmExprContext)_localctx).value =  Integer.parseInt(((ArithmExprContext)_localctx).ENTIER.getText()); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(114);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmExprContext(_parentctx, _parentState);
						_localctx.A1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmExpr);
						setState(104);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(105);
						((ArithmExprContext)_localctx).MULDIV = match(MULDIV);
						setState(106);
						((ArithmExprContext)_localctx).A2 = arithmExpr(5);

						                   if (((ArithmExprContext)_localctx).MULDIV.getText().equals("*")) 
						                   {
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
						setState(109);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(110);
						((ArithmExprContext)_localctx).ADDSUB = match(ADDSUB);
						setState(111);
						((ArithmExprContext)_localctx).A2 = arithmExpr(4);
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
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_boolExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(120);
				match(T__6);
				setState(121);
				((BoolExprContext)_localctx).e = boolExpr(8);
				 ((BoolExprContext)_localctx).value =  ! ((BoolExprContext)_localctx).e.value; 
				}
				break;
			case 2:
				{
				setState(124);
				match(T__4);
				setState(125);
				((BoolExprContext)_localctx).e = boolExpr(0);
				setState(126);
				match(T__5);
				 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e.value; 
				}
				break;
			case 3:
				{
				setState(129);
				((BoolExprContext)_localctx).a1 = arithmExpr(0);
				setState(130);
				((BoolExprContext)_localctx).LOGICOP = match(LOGICOP);
				setState(131);
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
				setState(134);
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
			case 5:
				{
				setState(136);
				match(T__9);
				 ((BoolExprContext)_localctx).value =   true; 
				}
				break;
			case 6:
				{
				setState(138);
				match(T__10);
				 ((BoolExprContext)_localctx).value =   false; 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(154);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(152);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new BoolExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
						setState(142);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(143);
						match(T__7);
						setState(144);
						((BoolExprContext)_localctx).e2 = boolExpr(7);
						 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e1.value && ((BoolExprContext)_localctx).e2.value; 
						}
						break;
					case 2:
						{
						_localctx = new BoolExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
						setState(147);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(148);
						match(T__8);
						setState(149);
						((BoolExprContext)_localctx).e2 = boolExpr(6);
						 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e1.value || ((BoolExprContext)_localctx).e2.value; 
						}
						break;
					}
					} 
				}
				setState(156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
		case 8:
			return arithmExpr_sempred((ArithmExprContext)_localctx, predIndex);
		case 9:
			return boolExpr_sempred((BoolExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmExpr_sempred(ArithmExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean boolExpr_sempred(BoolExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0016\u009e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0001\u0000\u0004\u0000\u0017"+
		"\b\u0000\u000b\u0000\f\u0000\u0018\u0001\u0000\u0005\u0000\u001c\b\u0000"+
		"\n\u0000\f\u0000\u001f\t\u0000\u0001\u0000\u0005\u0000\"\b\u0000\n\u0000"+
		"\f\u0000%\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001/\b\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u00029\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005G\b\u0005\n\u0005"+
		"\f\u0005J\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007W\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\bg\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0005\bs\b\b\n\b\f\bv\t\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u008d\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0005\t\u0099\b\t\n\t\f\t\u009c\t\t\u0001\t\u0000\u0002"+
		"\u0010\u0012\n\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0000\u0001"+
		"\u0001\u0000\f\r\u00a8\u0000\u0014\u0001\u0000\u0000\u0000\u0002.\u0001"+
		"\u0000\u0000\u0000\u00048\u0001\u0000\u0000\u0000\u0006:\u0001\u0000\u0000"+
		"\u0000\b>\u0001\u0000\u0000\u0000\nA\u0001\u0000\u0000\u0000\fK\u0001"+
		"\u0000\u0000\u0000\u000eV\u0001\u0000\u0000\u0000\u0010f\u0001\u0000\u0000"+
		"\u0000\u0012\u008c\u0001\u0000\u0000\u0000\u0014\u001d\u0003\u0002\u0001"+
		"\u0000\u0015\u0017\u0007\u0000\u0000\u0000\u0016\u0015\u0001\u0000\u0000"+
		"\u0000\u0017\u0018\u0001\u0000\u0000\u0000\u0018\u0016\u0001\u0000\u0000"+
		"\u0000\u0018\u0019\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000"+
		"\u0000\u001a\u001c\u0003\u0002\u0001\u0000\u001b\u0016\u0001\u0000\u0000"+
		"\u0000\u001c\u001f\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000"+
		"\u0000\u001d\u001e\u0001\u0000\u0000\u0000\u001e#\u0001\u0000\u0000\u0000"+
		"\u001f\u001d\u0001\u0000\u0000\u0000 \"\u0007\u0000\u0000\u0000! \u0001"+
		"\u0000\u0000\u0000\"%\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000"+
		"#$\u0001\u0000\u0000\u0000$&\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000"+
		"\u0000&\'\u0005\u0000\u0000\u0001\'\u0001\u0001\u0000\u0000\u0000(/\u0003"+
		"\u0004\u0002\u0000)/\u0003\f\u0006\u0000*/\u0003\u0006\u0003\u0000+,\u0003"+
		"\u000e\u0007\u0000,-\u0006\u0001\uffff\uffff\u0000-/\u0001\u0000\u0000"+
		"\u0000.(\u0001\u0000\u0000\u0000.)\u0001\u0000\u0000\u0000.*\u0001\u0000"+
		"\u0000\u0000.+\u0001\u0000\u0000\u0000/\u0003\u0001\u0000\u0000\u0000"+
		"01\u0005\u0001\u0000\u000012\u0005\u0014\u0000\u000023\u0005\u0002\u0000"+
		"\u000039\u0005\u0011\u0000\u000045\u0005\u0003\u0000\u000056\u0005\u0014"+
		"\u0000\u000067\u0005\u0002\u0000\u000079\u0005\u0012\u0000\u000080\u0001"+
		"\u0000\u0000\u000084\u0001\u0000\u0000\u00009\u0005\u0001\u0000\u0000"+
		"\u0000:;\u0003\b\u0004\u0000;<\u0003\n\u0005\u0000<=\u0006\u0003\uffff"+
		"\uffff\u0000=\u0007\u0001\u0000\u0000\u0000>?\u0005\u0013\u0000\u0000"+
		"?@\u0006\u0004\uffff\uffff\u0000@\t\u0001\u0000\u0000\u0000AB\u0005\u0014"+
		"\u0000\u0000BH\u0006\u0005\uffff\uffff\u0000CD\u0005\u0004\u0000\u0000"+
		"DE\u0005\u0014\u0000\u0000EG\u0006\u0005\uffff\uffff\u0000FC\u0001\u0000"+
		"\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001"+
		"\u0000\u0000\u0000I\u000b\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000"+
		"\u0000KL\u0005\u0014\u0000\u0000LM\u0005\u0002\u0000\u0000MN\u0003\u000e"+
		"\u0007\u0000NO\u0006\u0006\uffff\uffff\u0000O\r\u0001\u0000\u0000\u0000"+
		"PQ\u0003\u0012\t\u0000QR\u0006\u0007\uffff\uffff\u0000RW\u0001\u0000\u0000"+
		"\u0000ST\u0003\u0010\b\u0000TU\u0006\u0007\uffff\uffff\u0000UW\u0001\u0000"+
		"\u0000\u0000VP\u0001\u0000\u0000\u0000VS\u0001\u0000\u0000\u0000W\u000f"+
		"\u0001\u0000\u0000\u0000XY\u0006\b\uffff\uffff\u0000YZ\u0005\u000f\u0000"+
		"\u0000Z[\u0003\u0010\b\u0006[\\\u0006\b\uffff\uffff\u0000\\g\u0001\u0000"+
		"\u0000\u0000]^\u0005\u0005\u0000\u0000^_\u0003\u0010\b\u0000_`\u0005\u0006"+
		"\u0000\u0000`a\u0006\b\uffff\uffff\u0000ag\u0001\u0000\u0000\u0000bc\u0005"+
		"\u0014\u0000\u0000cg\u0006\b\uffff\uffff\u0000de\u0005\u0011\u0000\u0000"+
		"eg\u0006\b\uffff\uffff\u0000fX\u0001\u0000\u0000\u0000f]\u0001\u0000\u0000"+
		"\u0000fb\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000gt\u0001\u0000"+
		"\u0000\u0000hi\n\u0004\u0000\u0000ij\u0005\u000e\u0000\u0000jk\u0003\u0010"+
		"\b\u0005kl\u0006\b\uffff\uffff\u0000ls\u0001\u0000\u0000\u0000mn\n\u0003"+
		"\u0000\u0000no\u0005\u000f\u0000\u0000op\u0003\u0010\b\u0004pq\u0006\b"+
		"\uffff\uffff\u0000qs\u0001\u0000\u0000\u0000rh\u0001\u0000\u0000\u0000"+
		"rm\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000"+
		"\u0000tu\u0001\u0000\u0000\u0000u\u0011\u0001\u0000\u0000\u0000vt\u0001"+
		"\u0000\u0000\u0000wx\u0006\t\uffff\uffff\u0000xy\u0005\u0007\u0000\u0000"+
		"yz\u0003\u0012\t\bz{\u0006\t\uffff\uffff\u0000{\u008d\u0001\u0000\u0000"+
		"\u0000|}\u0005\u0005\u0000\u0000}~\u0003\u0012\t\u0000~\u007f\u0005\u0006"+
		"\u0000\u0000\u007f\u0080\u0006\t\uffff\uffff\u0000\u0080\u008d\u0001\u0000"+
		"\u0000\u0000\u0081\u0082\u0003\u0010\b\u0000\u0082\u0083\u0005\u0010\u0000"+
		"\u0000\u0083\u0084\u0003\u0010\b\u0000\u0084\u0085\u0006\t\uffff\uffff"+
		"\u0000\u0085\u008d\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u0014\u0000"+
		"\u0000\u0087\u008d\u0006\t\uffff\uffff\u0000\u0088\u0089\u0005\n\u0000"+
		"\u0000\u0089\u008d\u0006\t\uffff\uffff\u0000\u008a\u008b\u0005\u000b\u0000"+
		"\u0000\u008b\u008d\u0006\t\uffff\uffff\u0000\u008cw\u0001\u0000\u0000"+
		"\u0000\u008c|\u0001\u0000\u0000\u0000\u008c\u0081\u0001\u0000\u0000\u0000"+
		"\u008c\u0086\u0001\u0000\u0000\u0000\u008c\u0088\u0001\u0000\u0000\u0000"+
		"\u008c\u008a\u0001\u0000\u0000\u0000\u008d\u009a\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\n\u0006\u0000\u0000\u008f\u0090\u0005\b\u0000\u0000\u0090"+
		"\u0091\u0003\u0012\t\u0007\u0091\u0092\u0006\t\uffff\uffff\u0000\u0092"+
		"\u0099\u0001\u0000\u0000\u0000\u0093\u0094\n\u0005\u0000\u0000\u0094\u0095"+
		"\u0005\t\u0000\u0000\u0095\u0096\u0003\u0012\t\u0006\u0096\u0097\u0006"+
		"\t\uffff\uffff\u0000\u0097\u0099\u0001\u0000\u0000\u0000\u0098\u008e\u0001"+
		"\u0000\u0000\u0000\u0098\u0093\u0001\u0000\u0000\u0000\u0099\u009c\u0001"+
		"\u0000\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u009b\u0001"+
		"\u0000\u0000\u0000\u009b\u0013\u0001\u0000\u0000\u0000\u009c\u009a\u0001"+
		"\u0000\u0000\u0000\r\u0018\u001d#.8HVfrt\u008c\u0098\u009a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}