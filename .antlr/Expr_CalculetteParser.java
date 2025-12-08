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
		T__9=10, T__10=11, T__11=12, LIRE=13, NEWLINE=14, SEMICOLON=15, MULDIV=16, 
		ADDSUB=17, INCDEC=18, LOGICOP=19, ENTIER=20, TYPE=21, ID=22, WS=23, UNMATCH=24;
	public static final int
		RULE_start = 0, RULE_instruction = 1, RULE_declInstr = 2, RULE_type = 3, 
		RULE_idList = 4, RULE_assignInstr = 5, RULE_blockInstr = 6, RULE_expr = 7, 
		RULE_arithmExpr = 8, RULE_boolExpr = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "instruction", "declInstr", "type", "idList", "assignInstr", 
			"blockInstr", "expr", "arithmExpr", "boolExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Afficher'", "'('", "')'", "','", "'='", "'{'", "'}'", "'not'", 
			"'and'", "'or'", "'true'", "'false'", "'lire'", null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "LIRE", "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "INCDEC", "LOGICOP", 
			"ENTIER", "TYPE", "ID", "WS", "UNMATCH"
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

	 // Juste pour le parser // pas pour le lexer
	    static class VarEntry {
	        String type;
	        boolean initialized;
	        Integer ivalue;
	        Boolean bvalue;
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
		public DeclInstrContext declInstr() {
			return getRuleContext(DeclInstrContext.class,0);
		}
		public AssignInstrContext assignInstr() {
			return getRuleContext(AssignInstrContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockInstrContext blockInstr() {
			return getRuleContext(BlockInstrContext.class,0);
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
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				declInstr();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				assignInstr();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(42);
				match(T__0);
				setState(43);
				match(T__1);
				setState(44);
				((InstructionContext)_localctx).e = expr();
				setState(45);
				match(T__2);

				        if (((InstructionContext)_localctx).e.isBool)
				            System.out.println(" Afficher : " + ((InstructionContext)_localctx).e.bvalue);
				        else
				            System.out.println(" Afficher : " + ((InstructionContext)_localctx).e.ivalue);
				      
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(48);
				blockInstr();
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
		enterRule(_localctx, 4, RULE_declInstr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			((DeclInstrContext)_localctx).t = type();
			setState(52);
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

			            symtab.put(name, e); // mevcut girdinin üzerine yazar
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
			setState(55);
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
			setState(58);
			((IdListContext)_localctx).id1 = match(ID);

			          ((IdListContext)_localctx).value =  new ArrayList<>();
			          _localctx.value.add(((IdListContext)_localctx).id1.getText());
			      
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(60);
				match(T__3);
				setState(61);
				((IdListContext)_localctx).idn = match(ID);
				 _localctx.value.add(((IdListContext)_localctx).idn.getText()); 
				}
				}
				setState(67);
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
			setState(68);
			((AssignInstrContext)_localctx).id = match(ID);
			setState(69);
			match(T__4);
			setState(70);
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
	public static class BlockInstrContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(Expr_CalculetteParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(Expr_CalculetteParser.SEMICOLON, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(Expr_CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Expr_CalculetteParser.NEWLINE, i);
		}
		public BlockInstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockInstr; }
	}

	public final BlockInstrContext blockInstr() throws RecognitionException {
		BlockInstrContext _localctx = new BlockInstrContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_blockInstr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__5);

			          // Bloka girerken: snapshot + yeni tablo
			          symtabStack.push(symtab);         // eski referansı stack'e koy
			          symtab = cloneSymtab(symtab);     // mevcut tabloyu kopyala ve kopya ile çalış
			      
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE || _la==SEMICOLON) {
						{
						{
						setState(75);
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
						setState(80);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(81);
					instruction();
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE || _la==SEMICOLON) {
				{
				{
				setState(87);
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
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93);
			match(T__6);

			          // Bloktan çıkarken: eski tabloya geri dön
			          symtab = symtabStack.pop();
			      
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
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				((ExprContext)_localctx).b = boolExpr(0);
				 ((ExprContext)_localctx).isBool =  true;  ((ExprContext)_localctx).bvalue =  ((ExprContext)_localctx).b.value;  ((ExprContext)_localctx).ivalue =  null; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
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
		public Token op;
		public Token id;
		public Token ENTIER;
		public Token ADDSUB;
		public ArithmExprContext a;
		public Token MULDIV;
		public ArithmExprContext A2;
		public TerminalNode LIRE() { return getToken(Expr_CalculetteParser.LIRE, 0); }
		public TerminalNode INCDEC() { return getToken(Expr_CalculetteParser.INCDEC, 0); }
		public TerminalNode ID() { return getToken(Expr_CalculetteParser.ID, 0); }
		public TerminalNode ENTIER() { return getToken(Expr_CalculetteParser.ENTIER, 0); }
		public TerminalNode ADDSUB() { return getToken(Expr_CalculetteParser.ADDSUB, 0); }
		public List<ArithmExprContext> arithmExpr() {
			return getRuleContexts(ArithmExprContext.class);
		}
		public ArithmExprContext arithmExpr(int i) {
			return getRuleContext(ArithmExprContext.class,i);
		}
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
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(105);
				match(LIRE);
				setState(106);
				match(T__1);
				setState(107);
				match(T__2);

				        System.out.print("Entrez un entier : ");
				        ((ArithmExprContext)_localctx).value =  in.nextInt();
				      
				}
				break;
			case 2:
				{
				setState(109);
				((ArithmExprContext)_localctx).op = match(INCDEC);
				setState(110);
				((ArithmExprContext)_localctx).id = match(ID);

				        String name = ((ArithmExprContext)_localctx).id.getText();
				        VarEntry v = symtab.get(name);
				        if (v == null)  throw new RuntimeException("Undeclared variable: " + name);
				        if (!v.initialized) throw new RuntimeException("Uninitialized variable: " + name);
				        if (!"int".equals(v.type)) throw new RuntimeException("Type error: " + name + " is not int");

				        int old = v.ivalue;
				        if (((ArithmExprContext)_localctx).op.getText().equals("++")) v.ivalue = old + 1;
				        else                            v.ivalue = old - 1;
				        ((ArithmExprContext)_localctx).value =  old; // eski değer kullanılıyor
				      
				}
				break;
			case 3:
				{
				setState(112);
				((ArithmExprContext)_localctx).id = match(ID);
				setState(113);
				((ArithmExprContext)_localctx).op = match(INCDEC);

				        String name = ((ArithmExprContext)_localctx).id.getText();
				        VarEntry v = symtab.get(name);
				        if (v == null)  throw new RuntimeException("Undeclared variable: " + name);
				        if (!v.initialized) throw new RuntimeException("Uninitialized variable: " + name);
				        if (!"int".equals(v.type)) throw new RuntimeException("Type error: " + name + " is not int");

				        int old = v.ivalue; // ancien valueur 
				        if (((ArithmExprContext)_localctx).op.getText().equals("++")) v.ivalue = old + 1; // nouveau valeur
				        else                            v.ivalue = old - 1; // nouveau valeur
				        ((ArithmExprContext)_localctx).value =  v.ivalue; // yeni değer kullanılıyor
				      
				}
				break;
			case 4:
				{
				setState(115);
				if (!(isIntVarToken())) throw new FailedPredicateException(this, "isIntVarToken()");
				setState(116);
				((ArithmExprContext)_localctx).id = match(ID);

				        String name = ((ArithmExprContext)_localctx).id.getText();
				        VarEntry v = symtab.get(name);
				        if (v == null) {
				            throw new RuntimeException("Undeclared variable: " + name);
				        }
				        if (!v.initialized) {
				            throw new RuntimeException("Uninitialized variable: " + name);
				        }
				        ((ArithmExprContext)_localctx).value =  v.ivalue;
				      
				}
				break;
			case 5:
				{
				setState(118);
				((ArithmExprContext)_localctx).ENTIER = match(ENTIER);
				 ((ArithmExprContext)_localctx).value =  Integer.parseInt(((ArithmExprContext)_localctx).ENTIER.getText()); 
				}
				break;
			case 6:
				{
				setState(120);
				((ArithmExprContext)_localctx).ADDSUB = match(ADDSUB);
				setState(121);
				((ArithmExprContext)_localctx).a = arithmExpr(7);
				if (((ArithmExprContext)_localctx).ADDSUB.getText().equals("-"))
				                            ((ArithmExprContext)_localctx).value =  -((ArithmExprContext)_localctx).a.value;
				                          else
				                              ((ArithmExprContext)_localctx).value =  +((ArithmExprContext)_localctx).a.value;
				                        
				}
				break;
			case 7:
				{
				setState(124);
				match(T__1);
				setState(125);
				((ArithmExprContext)_localctx).A1 = arithmExpr(0);
				setState(126);
				match(T__2);
				 ((ArithmExprContext)_localctx).value =  ((ArithmExprContext)_localctx).A1.value;
				}
				break;
			case 8:
				{
				setState(129);
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
			case 9:
				{
				setState(131);
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
			case 10:
				{
				setState(133);
				((ArithmExprContext)_localctx).ENTIER = match(ENTIER);
				 ((ArithmExprContext)_localctx).value =  Integer.parseInt(((ArithmExprContext)_localctx).ENTIER.getText()); 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(147);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmExprContext(_parentctx, _parentState);
						_localctx.A1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmExpr);
						setState(137);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(138);
						((ArithmExprContext)_localctx).MULDIV = match(MULDIV);
						setState(139);
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
						setState(142);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(143);
						((ArithmExprContext)_localctx).ADDSUB = match(ADDSUB);
						setState(144);
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
				setState(151);
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_boolExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(153);
				match(T__7);
				setState(154);
				((BoolExprContext)_localctx).e = boolExpr(9);
				 ((BoolExprContext)_localctx).value =  ! ((BoolExprContext)_localctx).e.value; 
				}
				break;
			case 2:
				{
				setState(157);
				match(T__1);
				setState(158);
				((BoolExprContext)_localctx).e = boolExpr(0);
				setState(159);
				match(T__2);
				 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e.value; 
				}
				break;
			case 3:
				{
				setState(162);
				((BoolExprContext)_localctx).a1 = arithmExpr(0);
				setState(163);
				((BoolExprContext)_localctx).LOGICOP = match(LOGICOP);
				setState(164);
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
				setState(167);
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
				setState(169);
				((BoolExprContext)_localctx).id = match(ID);

				        String name = ((BoolExprContext)_localctx).id.getText();
				        VarEntry v = symtab.get(name);
				        if (v == null) {
				            throw new RuntimeException("Undeclared variable: " + name);
				        }
				        if (!v.initialized) {
				            throw new RuntimeException("Uninitialized variable: " + name);
				        }
				        ((BoolExprContext)_localctx).value =  v.bvalue;
				      
				}
				break;
			case 6:
				{
				setState(171);
				match(T__10);
				 ((BoolExprContext)_localctx).value =   true; 
				}
				break;
			case 7:
				{
				setState(173);
				match(T__11);
				 ((BoolExprContext)_localctx).value =   false; 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(189);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(187);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new BoolExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
						setState(177);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(178);
						match(T__8);
						setState(179);
						((BoolExprContext)_localctx).e2 = boolExpr(8);
						 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e1.value && ((BoolExprContext)_localctx).e2.value; 
						}
						break;
					case 2:
						{
						_localctx = new BoolExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
						setState(182);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(183);
						match(T__9);
						setState(184);
						((BoolExprContext)_localctx).e2 = boolExpr(7);
						 ((BoolExprContext)_localctx).value =   ((BoolExprContext)_localctx).e1.value || ((BoolExprContext)_localctx).e2.value; 
						}
						break;
					}
					} 
				}
				setState(191);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
			return isIntVarToken();
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean boolExpr_sempred(BoolExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 7);
		case 4:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0018\u00c1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0001\u0000\u0004\u0000\u0017"+
		"\b\u0000\u000b\u0000\f\u0000\u0018\u0001\u0000\u0005\u0000\u001c\b\u0000"+
		"\n\u0000\f\u0000\u001f\t\u0000\u0001\u0000\u0005\u0000\"\b\u0000\n\u0000"+
		"\f\u0000%\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u00012\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004@\b\u0004\n\u0004\f\u0004C\t"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0005\u0006M\b\u0006\n\u0006\f\u0006P\t"+
		"\u0006\u0001\u0006\u0005\u0006S\b\u0006\n\u0006\f\u0006V\t\u0006\u0001"+
		"\u0006\u0005\u0006Y\b\u0006\n\u0006\f\u0006\\\t\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007g\b\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u0088\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0005\b\u0094\b\b\n\b\f\b\u0097\t\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00b0\b\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u00bc"+
		"\b\t\n\t\f\t\u00bf\t\t\u0001\t\u0000\u0002\u0010\u0012\n\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0000\u0001\u0001\u0000\u000e\u000f\u00d4"+
		"\u0000\u0014\u0001\u0000\u0000\u0000\u00021\u0001\u0000\u0000\u0000\u0004"+
		"3\u0001\u0000\u0000\u0000\u00067\u0001\u0000\u0000\u0000\b:\u0001\u0000"+
		"\u0000\u0000\nD\u0001\u0000\u0000\u0000\fI\u0001\u0000\u0000\u0000\u000e"+
		"f\u0001\u0000\u0000\u0000\u0010\u0087\u0001\u0000\u0000\u0000\u0012\u00af"+
		"\u0001\u0000\u0000\u0000\u0014\u001d\u0003\u0002\u0001\u0000\u0015\u0017"+
		"\u0007\u0000\u0000\u0000\u0016\u0015\u0001\u0000\u0000\u0000\u0017\u0018"+
		"\u0001\u0000\u0000\u0000\u0018\u0016\u0001\u0000\u0000\u0000\u0018\u0019"+
		"\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u001c"+
		"\u0003\u0002\u0001\u0000\u001b\u0016\u0001\u0000\u0000\u0000\u001c\u001f"+
		"\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001e"+
		"\u0001\u0000\u0000\u0000\u001e#\u0001\u0000\u0000\u0000\u001f\u001d\u0001"+
		"\u0000\u0000\u0000 \"\u0007\u0000\u0000\u0000! \u0001\u0000\u0000\u0000"+
		"\"%\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000"+
		"\u0000$&\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000&\'\u0005\u0000"+
		"\u0000\u0001\'\u0001\u0001\u0000\u0000\u0000(2\u0003\u0004\u0002\u0000"+
		")2\u0003\n\u0005\u0000*+\u0005\u0001\u0000\u0000+,\u0005\u0002\u0000\u0000"+
		",-\u0003\u000e\u0007\u0000-.\u0005\u0003\u0000\u0000./\u0006\u0001\uffff"+
		"\uffff\u0000/2\u0001\u0000\u0000\u000002\u0003\f\u0006\u00001(\u0001\u0000"+
		"\u0000\u00001)\u0001\u0000\u0000\u00001*\u0001\u0000\u0000\u000010\u0001"+
		"\u0000\u0000\u00002\u0003\u0001\u0000\u0000\u000034\u0003\u0006\u0003"+
		"\u000045\u0003\b\u0004\u000056\u0006\u0002\uffff\uffff\u00006\u0005\u0001"+
		"\u0000\u0000\u000078\u0005\u0015\u0000\u000089\u0006\u0003\uffff\uffff"+
		"\u00009\u0007\u0001\u0000\u0000\u0000:;\u0005\u0016\u0000\u0000;A\u0006"+
		"\u0004\uffff\uffff\u0000<=\u0005\u0004\u0000\u0000=>\u0005\u0016\u0000"+
		"\u0000>@\u0006\u0004\uffff\uffff\u0000?<\u0001\u0000\u0000\u0000@C\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000"+
		"B\t\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000DE\u0005\u0016\u0000"+
		"\u0000EF\u0005\u0005\u0000\u0000FG\u0003\u000e\u0007\u0000GH\u0006\u0005"+
		"\uffff\uffff\u0000H\u000b\u0001\u0000\u0000\u0000IJ\u0005\u0006\u0000"+
		"\u0000JT\u0006\u0006\uffff\uffff\u0000KM\u0007\u0000\u0000\u0000LK\u0001"+
		"\u0000\u0000\u0000MP\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000"+
		"NO\u0001\u0000\u0000\u0000OQ\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000"+
		"\u0000QS\u0003\u0002\u0001\u0000RN\u0001\u0000\u0000\u0000SV\u0001\u0000"+
		"\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UZ\u0001"+
		"\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000WY\u0007\u0000\u0000\u0000"+
		"XW\u0001\u0000\u0000\u0000Y\\\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000"+
		"\u0000Z[\u0001\u0000\u0000\u0000[]\u0001\u0000\u0000\u0000\\Z\u0001\u0000"+
		"\u0000\u0000]^\u0005\u0007\u0000\u0000^_\u0006\u0006\uffff\uffff\u0000"+
		"_\r\u0001\u0000\u0000\u0000`a\u0003\u0012\t\u0000ab\u0006\u0007\uffff"+
		"\uffff\u0000bg\u0001\u0000\u0000\u0000cd\u0003\u0010\b\u0000de\u0006\u0007"+
		"\uffff\uffff\u0000eg\u0001\u0000\u0000\u0000f`\u0001\u0000\u0000\u0000"+
		"fc\u0001\u0000\u0000\u0000g\u000f\u0001\u0000\u0000\u0000hi\u0006\b\uffff"+
		"\uffff\u0000ij\u0005\r\u0000\u0000jk\u0005\u0002\u0000\u0000kl\u0005\u0003"+
		"\u0000\u0000l\u0088\u0006\b\uffff\uffff\u0000mn\u0005\u0012\u0000\u0000"+
		"no\u0005\u0016\u0000\u0000o\u0088\u0006\b\uffff\uffff\u0000pq\u0005\u0016"+
		"\u0000\u0000qr\u0005\u0012\u0000\u0000r\u0088\u0006\b\uffff\uffff\u0000"+
		"st\u0004\b\u0000\u0000tu\u0005\u0016\u0000\u0000u\u0088\u0006\b\uffff"+
		"\uffff\u0000vw\u0005\u0014\u0000\u0000w\u0088\u0006\b\uffff\uffff\u0000"+
		"xy\u0005\u0011\u0000\u0000yz\u0003\u0010\b\u0007z{\u0006\b\uffff\uffff"+
		"\u0000{\u0088\u0001\u0000\u0000\u0000|}\u0005\u0002\u0000\u0000}~\u0003"+
		"\u0010\b\u0000~\u007f\u0005\u0003\u0000\u0000\u007f\u0080\u0006\b\uffff"+
		"\uffff\u0000\u0080\u0088\u0001\u0000\u0000\u0000\u0081\u0082\u0005\r\u0000"+
		"\u0000\u0082\u0088\u0006\b\uffff\uffff\u0000\u0083\u0084\u0005\u0016\u0000"+
		"\u0000\u0084\u0088\u0006\b\uffff\uffff\u0000\u0085\u0086\u0005\u0014\u0000"+
		"\u0000\u0086\u0088\u0006\b\uffff\uffff\u0000\u0087h\u0001\u0000\u0000"+
		"\u0000\u0087m\u0001\u0000\u0000\u0000\u0087p\u0001\u0000\u0000\u0000\u0087"+
		"s\u0001\u0000\u0000\u0000\u0087v\u0001\u0000\u0000\u0000\u0087x\u0001"+
		"\u0000\u0000\u0000\u0087|\u0001\u0000\u0000\u0000\u0087\u0081\u0001\u0000"+
		"\u0000\u0000\u0087\u0083\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000"+
		"\u0000\u0000\u0088\u0095\u0001\u0000\u0000\u0000\u0089\u008a\n\u0005\u0000"+
		"\u0000\u008a\u008b\u0005\u0010\u0000\u0000\u008b\u008c\u0003\u0010\b\u0006"+
		"\u008c\u008d\u0006\b\uffff\uffff\u0000\u008d\u0094\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\n\u0004\u0000\u0000\u008f\u0090\u0005\u0011\u0000\u0000\u0090"+
		"\u0091\u0003\u0010\b\u0005\u0091\u0092\u0006\b\uffff\uffff\u0000\u0092"+
		"\u0094\u0001\u0000\u0000\u0000\u0093\u0089\u0001\u0000\u0000\u0000\u0093"+
		"\u008e\u0001\u0000\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095"+
		"\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096"+
		"\u0011\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0006\t\uffff\uffff\u0000\u0099\u009a\u0005\b\u0000\u0000\u009a"+
		"\u009b\u0003\u0012\t\t\u009b\u009c\u0006\t\uffff\uffff\u0000\u009c\u00b0"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0005\u0002\u0000\u0000\u009e\u009f"+
		"\u0003\u0012\t\u0000\u009f\u00a0\u0005\u0003\u0000\u0000\u00a0\u00a1\u0006"+
		"\t\uffff\uffff\u0000\u00a1\u00b0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0003"+
		"\u0010\b\u0000\u00a3\u00a4\u0005\u0013\u0000\u0000\u00a4\u00a5\u0003\u0010"+
		"\b\u0000\u00a5\u00a6\u0006\t\uffff\uffff\u0000\u00a6\u00b0\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a8\u0005\r\u0000\u0000\u00a8\u00b0\u0006\t\uffff"+
		"\uffff\u0000\u00a9\u00aa\u0005\u0016\u0000\u0000\u00aa\u00b0\u0006\t\uffff"+
		"\uffff\u0000\u00ab\u00ac\u0005\u000b\u0000\u0000\u00ac\u00b0\u0006\t\uffff"+
		"\uffff\u0000\u00ad\u00ae\u0005\f\u0000\u0000\u00ae\u00b0\u0006\t\uffff"+
		"\uffff\u0000\u00af\u0098\u0001\u0000\u0000\u0000\u00af\u009d\u0001\u0000"+
		"\u0000\u0000\u00af\u00a2\u0001\u0000\u0000\u0000\u00af\u00a7\u0001\u0000"+
		"\u0000\u0000\u00af\u00a9\u0001\u0000\u0000\u0000\u00af\u00ab\u0001\u0000"+
		"\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00bd\u0001\u0000"+
		"\u0000\u0000\u00b1\u00b2\n\u0007\u0000\u0000\u00b2\u00b3\u0005\t\u0000"+
		"\u0000\u00b3\u00b4\u0003\u0012\t\b\u00b4\u00b5\u0006\t\uffff\uffff\u0000"+
		"\u00b5\u00bc\u0001\u0000\u0000\u0000\u00b6\u00b7\n\u0006\u0000\u0000\u00b7"+
		"\u00b8\u0005\n\u0000\u0000\u00b8\u00b9\u0003\u0012\t\u0007\u00b9\u00ba"+
		"\u0006\t\uffff\uffff\u0000\u00ba\u00bc\u0001\u0000\u0000\u0000\u00bb\u00b1"+
		"\u0001\u0000\u0000\u0000\u00bb\u00b6\u0001\u0000\u0000\u0000\u00bc\u00bf"+
		"\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be"+
		"\u0001\u0000\u0000\u0000\u00be\u0013\u0001\u0000\u0000\u0000\u00bf\u00bd"+
		"\u0001\u0000\u0000\u0000\u000f\u0018\u001d#1ANTZf\u0087\u0093\u0095\u00af"+
		"\u00bb\u00bd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}