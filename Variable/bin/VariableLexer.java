// Generated from Variable.g4 by ANTLR 4.9.2

 import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VariableLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NEWLINE=3, SEMICOLON=4, MULDIV=5, ADDSUB=6, LOGICOP=7, 
		ASSIGNE=8, ENTIER=9, BOOL=10, WS=11, UNMATCH=12, ID=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", 
			"ASSIGNE", "ENTIER", "BOOL", "WS", "UNMATCH", "ID"
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


	    static class VarEntry 
	    {
	        String type;        // "int" or "bool"
	        boolean initialized;
	        Integer ivalue;     // sadece int için kullan
	        Boolean bvalue;     // sadece bool için kullan
	    }

	    Map<String, VarEntry> symtab = new HashMap<>();


	public VariableLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Variable.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17a\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\5\4(\n\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\5\b<\n\b\3\t\3\t\3\n\6\nA\n\n\r\n\16\nB\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\5\13N\n\13\3\f\6\fQ\n\f\r\f\16\fR\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\7\16]\n\16\f\16\16\16`\13\16\2\2\17\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\3\2\7\4\2,,\61"+
		"\61\4\2--//\4\2\13\13\"\"\5\2C\\aac|\6\2\62;C\\aac|\2j\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\3\35\3\2\2\2\5!\3\2\2\2\7\'\3\2\2\2\t+\3\2\2\2\13-\3\2\2\2\r"+
		"/\3\2\2\2\17;\3\2\2\2\21=\3\2\2\2\23@\3\2\2\2\25M\3\2\2\2\27P\3\2\2\2"+
		"\31V\3\2\2\2\33Z\3\2\2\2\35\36\7k\2\2\36\37\7p\2\2\37 \7v\2\2 \4\3\2\2"+
		"\2!\"\7d\2\2\"#\7q\2\2#$\7q\2\2$%\7n\2\2%\6\3\2\2\2&(\7\17\2\2\'&\3\2"+
		"\2\2\'(\3\2\2\2()\3\2\2\2)*\7\f\2\2*\b\3\2\2\2+,\7=\2\2,\n\3\2\2\2-.\t"+
		"\2\2\2.\f\3\2\2\2/\60\t\3\2\2\60\16\3\2\2\2\61\62\7?\2\2\62<\7?\2\2\63"+
		"\64\7>\2\2\64<\7@\2\2\65<\7>\2\2\66\67\7>\2\2\67<\7?\2\28<\7@\2\29:\7"+
		"@\2\2:<\7?\2\2;\61\3\2\2\2;\63\3\2\2\2;\65\3\2\2\2;\66\3\2\2\2;8\3\2\2"+
		"\2;9\3\2\2\2<\20\3\2\2\2=>\7?\2\2>\22\3\2\2\2?A\4\62;\2@?\3\2\2\2AB\3"+
		"\2\2\2B@\3\2\2\2BC\3\2\2\2C\24\3\2\2\2DE\7v\2\2EF\7t\2\2FG\7w\2\2GN\7"+
		"g\2\2HI\7h\2\2IJ\7c\2\2JK\7n\2\2KL\7u\2\2LN\7g\2\2MD\3\2\2\2MH\3\2\2\2"+
		"N\26\3\2\2\2OQ\t\4\2\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2"+
		"\2TU\b\f\2\2U\30\3\2\2\2VW\13\2\2\2WX\3\2\2\2XY\b\r\2\2Y\32\3\2\2\2Z^"+
		"\t\5\2\2[]\t\6\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\34\3\2\2"+
		"\2`^\3\2\2\2\t\2\';BMR^\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}