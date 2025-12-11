// Generated from Rationnel.g4 by ANTLR 4.9.2

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
public class RationnelLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NEWLINE=6, SEMICOLON=7, MULDIV=8, 
		ADDSUB=9, INCDEC=10, LOGICOP=11, ENTIER=12, TYPE=13, ID=14, WS=15, UNMATCH=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "NEWLINE", "SEMICOLON", "MULDIV", 
			"ADDSUB", "INCDEC", "LOGICOP", "ENTIER", "TYPE", "ID", "WS", "UNMATCH"
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


	public RationnelLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Rationnel.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22x\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\5\7=\n\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\5\13K\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fW\n\f"+
		"\3\r\6\rZ\n\r\r\r\16\r[\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16e\n\16"+
		"\3\17\3\17\7\17i\n\17\f\17\16\17l\13\17\3\20\6\20o\n\20\r\20\16\20p\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\2\2\22\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22\3\2\7\4\2,,\61\61\4\2--/"+
		"/\5\2C\\aac|\6\2\62;C\\aac|\4\2\13\13\"\"\2\u0082\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5,\3\2\2\2\7.\3\2"+
		"\2\2\t\60\3\2\2\2\13\65\3\2\2\2\r<\3\2\2\2\17@\3\2\2\2\21B\3\2\2\2\23"+
		"D\3\2\2\2\25J\3\2\2\2\27V\3\2\2\2\31Y\3\2\2\2\33d\3\2\2\2\35f\3\2\2\2"+
		"\37n\3\2\2\2!t\3\2\2\2#$\7C\2\2$%\7h\2\2%&\7h\2\2&\'\7k\2\2\'(\7e\2\2"+
		"()\7j\2\2)*\7g\2\2*+\7t\2\2+\4\3\2\2\2,-\7*\2\2-\6\3\2\2\2./\7+\2\2/\b"+
		"\3\2\2\2\60\61\7v\2\2\61\62\7t\2\2\62\63\7w\2\2\63\64\7g\2\2\64\n\3\2"+
		"\2\2\65\66\7h\2\2\66\67\7c\2\2\678\7n\2\289\7u\2\29:\7g\2\2:\f\3\2\2\2"+
		";=\7\17\2\2<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\f\2\2?\16\3\2\2\2@A\7=\2"+
		"\2A\20\3\2\2\2BC\t\2\2\2C\22\3\2\2\2DE\t\3\2\2E\24\3\2\2\2FG\7-\2\2GK"+
		"\7-\2\2HI\7/\2\2IK\7/\2\2JF\3\2\2\2JH\3\2\2\2K\26\3\2\2\2LM\7?\2\2MW\7"+
		"?\2\2NO\7>\2\2OW\7@\2\2PW\7>\2\2QR\7>\2\2RW\7?\2\2SW\7@\2\2TU\7@\2\2U"+
		"W\7?\2\2VL\3\2\2\2VN\3\2\2\2VP\3\2\2\2VQ\3\2\2\2VS\3\2\2\2VT\3\2\2\2W"+
		"\30\3\2\2\2XZ\4\62;\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\32\3"+
		"\2\2\2]^\7k\2\2^_\7p\2\2_e\7v\2\2`a\7d\2\2ab\7q\2\2bc\7q\2\2ce\7n\2\2"+
		"d]\3\2\2\2d`\3\2\2\2e\34\3\2\2\2fj\t\4\2\2gi\t\5\2\2hg\3\2\2\2il\3\2\2"+
		"\2jh\3\2\2\2jk\3\2\2\2k\36\3\2\2\2lj\3\2\2\2mo\t\6\2\2nm\3\2\2\2op\3\2"+
		"\2\2pn\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\b\20\2\2s \3\2\2\2tu\13\2\2\2uv\3"+
		"\2\2\2vw\b\21\2\2w\"\3\2\2\2\n\2<JV[djp\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}