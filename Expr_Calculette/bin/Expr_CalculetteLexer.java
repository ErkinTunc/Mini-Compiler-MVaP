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
		NEWLINE=10, SEMICOLON=11, MULDIV=12, ADDSUB=13, LOGICOP=14, ENTIER=15, 
		TYPE=16, ID=17, WS=18, UNMATCH=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", "ENTIER", "TYPE", 
			"ID", "WS", "UNMATCH"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25~\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\5\13I\n\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17]\n\17\3\20\6\20`\n\20\r\20\16\20"+
		"a\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21k\n\21\3\22\3\22\7\22o\n\22\f"+
		"\22\16\22r\13\22\3\23\6\23u\n\23\r\23\16\23v\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\2\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25\3\2\7\4\2,,\61\61\4\2--//\5\2C\\a"+
		"ac|\6\2\62;C\\aac|\4\2\13\13\"\"\2\u0087\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)"+
		"\3\2\2\2\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\r\65\3\2\2\2\17"+
		"9\3\2\2\2\21<\3\2\2\2\23A\3\2\2\2\25H\3\2\2\2\27L\3\2\2\2\31N\3\2\2\2"+
		"\33P\3\2\2\2\35\\\3\2\2\2\37_\3\2\2\2!j\3\2\2\2#l\3\2\2\2%t\3\2\2\2\'"+
		"z\3\2\2\2)*\7.\2\2*\4\3\2\2\2+,\7?\2\2,\6\3\2\2\2-.\7*\2\2.\b\3\2\2\2"+
		"/\60\7+\2\2\60\n\3\2\2\2\61\62\7p\2\2\62\63\7q\2\2\63\64\7v\2\2\64\f\3"+
		"\2\2\2\65\66\7c\2\2\66\67\7p\2\2\678\7f\2\28\16\3\2\2\29:\7q\2\2:;\7t"+
		"\2\2;\20\3\2\2\2<=\7v\2\2=>\7t\2\2>?\7w\2\2?@\7g\2\2@\22\3\2\2\2AB\7h"+
		"\2\2BC\7c\2\2CD\7n\2\2DE\7u\2\2EF\7g\2\2F\24\3\2\2\2GI\7\17\2\2HG\3\2"+
		"\2\2HI\3\2\2\2IJ\3\2\2\2JK\7\f\2\2K\26\3\2\2\2LM\7=\2\2M\30\3\2\2\2NO"+
		"\t\2\2\2O\32\3\2\2\2PQ\t\3\2\2Q\34\3\2\2\2RS\7?\2\2S]\7?\2\2TU\7>\2\2"+
		"U]\7@\2\2V]\7>\2\2WX\7>\2\2X]\7?\2\2Y]\7@\2\2Z[\7@\2\2[]\7?\2\2\\R\3\2"+
		"\2\2\\T\3\2\2\2\\V\3\2\2\2\\W\3\2\2\2\\Y\3\2\2\2\\Z\3\2\2\2]\36\3\2\2"+
		"\2^`\4\62;\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2b \3\2\2\2cd\7k\2"+
		"\2de\7p\2\2ek\7v\2\2fg\7d\2\2gh\7q\2\2hi\7q\2\2ik\7n\2\2jc\3\2\2\2jf\3"+
		"\2\2\2k\"\3\2\2\2lp\t\4\2\2mo\t\5\2\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2pq"+
		"\3\2\2\2q$\3\2\2\2rp\3\2\2\2su\t\6\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2v"+
		"w\3\2\2\2wx\3\2\2\2xy\b\23\2\2y&\3\2\2\2z{\13\2\2\2{|\3\2\2\2|}\b\24\2"+
		"\2}(\3\2\2\2\t\2H\\ajpv\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}