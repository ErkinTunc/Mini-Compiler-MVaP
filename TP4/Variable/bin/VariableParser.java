// Generated from Variable.g4 by ANTLR 4.9.2

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
public class VariableParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NEWLINE=3, SEMICOLON=4, MULDIV=5, ADDSUB=6, LOGICOP=7, 
		ASSIGNE=8, ENTIER=9, BOOL=10, WS=11, UNMATCH=12, ID=13;
	public static final int
		RULE_start = 0, RULE_variable = 1, RULE_type = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "variable", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'bool'", null, "';'", null, null, null, "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", 
			"ASSIGNE", "ENTIER", "BOOL", "WS", "UNMATCH", "ID"
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
	public String getGrammarFileName() { return "Variable.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    static class VarEntry 
	    {
	        String type;        // "int" or "bool"
	        boolean initialized;
	        Integer ivalue;     // sadece int için kullan
	        Boolean bvalue;     // sadece bool için kullan
	    }

	    Map<String, VarEntry> symtab = new HashMap<>();

	public VariableParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode EOF() { return getToken(VariableParser.EOF, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(VariableParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(VariableParser.SEMICOLON, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(VariableParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(VariableParser.NEWLINE, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableListener ) ((VariableListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableListener ) ((VariableListener)listener).exitStart(this);
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
			setState(6);
			variable();
			setState(15);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(8); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(7);
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
						setState(10); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NEWLINE || _la==SEMICOLON );
					setState(12);
					variable();
					}
					} 
				}
				setState(17);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE || _la==SEMICOLON) {
				{
				{
				setState(18);
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
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(24);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(VariableParser.ID, 0); }
		public TerminalNode ASSIGNE() { return getToken(VariableParser.ASSIGNE, 0); }
		public TerminalNode ENTIER() { return getToken(VariableParser.ENTIER, 0); }
		public TerminalNode BOOL() { return getToken(VariableParser.BOOL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableListener ) ((VariableListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableListener ) ((VariableListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_variable);
		try {
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				match(T__0);
				setState(27);
				match(ID);
				setState(28);
				match(ASSIGNE);
				setState(29);
				match(ENTIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				match(T__1);
				setState(31);
				match(ID);
				setState(32);
				match(ASSIGNE);
				setState(33);
				match(BOOL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(34);
				type();
				setState(35);
				match(ID);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(37);
				match(ID);
				setState(38);
				match(ASSIGNE);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableListener ) ((VariableListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableListener ) ((VariableListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17.\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\6\2\13\n\2\r\2\16\2\f\3\2\7\2\20\n\2\f\2\16\2\23\13"+
		"\2\3\2\7\2\26\n\2\f\2\16\2\31\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3*\n\3\3\4\3\4\3\4\2\2\5\2\4\6\2\4\3\2\5\6"+
		"\3\2\3\4\2\60\2\b\3\2\2\2\4)\3\2\2\2\6+\3\2\2\2\b\21\5\4\3\2\t\13\t\2"+
		"\2\2\n\t\3\2\2\2\13\f\3\2\2\2\f\n\3\2\2\2\f\r\3\2\2\2\r\16\3\2\2\2\16"+
		"\20\5\4\3\2\17\n\3\2\2\2\20\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22"+
		"\27\3\2\2\2\23\21\3\2\2\2\24\26\t\2\2\2\25\24\3\2\2\2\26\31\3\2\2\2\27"+
		"\25\3\2\2\2\27\30\3\2\2\2\30\32\3\2\2\2\31\27\3\2\2\2\32\33\7\2\2\3\33"+
		"\3\3\2\2\2\34\35\7\3\2\2\35\36\7\17\2\2\36\37\7\n\2\2\37*\7\13\2\2 !\7"+
		"\4\2\2!\"\7\17\2\2\"#\7\n\2\2#*\7\f\2\2$%\5\6\4\2%&\7\17\2\2&*\3\2\2\2"+
		"\'(\7\17\2\2(*\7\n\2\2)\34\3\2\2\2) \3\2\2\2)$\3\2\2\2)\'\3\2\2\2*\5\3"+
		"\2\2\2+,\t\3\2\2,\7\3\2\2\2\6\f\21\27)";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}