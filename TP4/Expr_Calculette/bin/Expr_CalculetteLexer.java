// Generated from Expr_Calculette.g4 by ANTLR 4.9.2

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
public class Expr_CalculetteLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, NEWLINE=11, SEMICOLON=12, MULDIV=13, ADDSUB=14, LOGICOP=15, ENTIER=16, 
		TYPE=17, ID=18, WS=19, UNMATCH=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", "ENTIER", 
			"TYPE", "ID", "WS", "UNMATCH"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Afficher'", "','", "'='", "'('", "')'", "'not'", "'and'", "'or'", 
			"'true'", "'false'", null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "NEWLINE", 
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


	    static class VarEntry {
	        String type;        // "int" veya "bool"
	        boolean initialized;
	        Integer ivalue;     // sadece int için kullan
	        Boolean bvalue;     // sadece bool için kullan
	    }

	    Map<String, VarEntry> symtab = new HashMap<>(); // symtab : symbole table


	public Expr_CalculetteLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expr_Calculette.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u0089\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\5\fT\n"+
		"\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\5\20h\n\20\3\21\6\21k\n\21\r\21\16\21l\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\5\22v\n\22\3\23\3\23\7\23z\n\23\f\23\16\23}\13"+
		"\23\3\24\6\24\u0080\n\24\r\24\16\24\u0081\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26\3\2\7\4\2,,\61\61\4\2--//\5\2C\\aac"+
		"|\6\2\62;C\\aac|\4\2\13\13\"\"\2\u0092\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\3+\3\2\2\2\5\64\3\2\2\2\7\66\3\2\2\2\t8\3\2\2\2\13:\3\2\2\2\r<"+
		"\3\2\2\2\17@\3\2\2\2\21D\3\2\2\2\23G\3\2\2\2\25L\3\2\2\2\27S\3\2\2\2\31"+
		"W\3\2\2\2\33Y\3\2\2\2\35[\3\2\2\2\37g\3\2\2\2!j\3\2\2\2#u\3\2\2\2%w\3"+
		"\2\2\2\'\177\3\2\2\2)\u0085\3\2\2\2+,\7C\2\2,-\7h\2\2-.\7h\2\2./\7k\2"+
		"\2/\60\7e\2\2\60\61\7j\2\2\61\62\7g\2\2\62\63\7t\2\2\63\4\3\2\2\2\64\65"+
		"\7.\2\2\65\6\3\2\2\2\66\67\7?\2\2\67\b\3\2\2\289\7*\2\29\n\3\2\2\2:;\7"+
		"+\2\2;\f\3\2\2\2<=\7p\2\2=>\7q\2\2>?\7v\2\2?\16\3\2\2\2@A\7c\2\2AB\7p"+
		"\2\2BC\7f\2\2C\20\3\2\2\2DE\7q\2\2EF\7t\2\2F\22\3\2\2\2GH\7v\2\2HI\7t"+
		"\2\2IJ\7w\2\2JK\7g\2\2K\24\3\2\2\2LM\7h\2\2MN\7c\2\2NO\7n\2\2OP\7u\2\2"+
		"PQ\7g\2\2Q\26\3\2\2\2RT\7\17\2\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\f\2"+
		"\2V\30\3\2\2\2WX\7=\2\2X\32\3\2\2\2YZ\t\2\2\2Z\34\3\2\2\2[\\\t\3\2\2\\"+
		"\36\3\2\2\2]^\7?\2\2^h\7?\2\2_`\7>\2\2`h\7@\2\2ah\7>\2\2bc\7>\2\2ch\7"+
		"?\2\2dh\7@\2\2ef\7@\2\2fh\7?\2\2g]\3\2\2\2g_\3\2\2\2ga\3\2\2\2gb\3\2\2"+
		"\2gd\3\2\2\2ge\3\2\2\2h \3\2\2\2ik\4\62;\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2"+
		"\2lm\3\2\2\2m\"\3\2\2\2no\7k\2\2op\7p\2\2pv\7v\2\2qr\7d\2\2rs\7q\2\2s"+
		"t\7q\2\2tv\7n\2\2un\3\2\2\2uq\3\2\2\2v$\3\2\2\2w{\t\4\2\2xz\t\5\2\2yx"+
		"\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|&\3\2\2\2}{\3\2\2\2~\u0080\t\6"+
		"\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3"+
		"\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\b\24\2\2\u0084(\3\2\2\2\u0085\u0086"+
		"\13\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\25\2\2\u0088*\3\2\2\2\t\2"+
		"Sglu{\u0081\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}