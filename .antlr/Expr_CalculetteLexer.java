// Generated from /home/pc/Documents/Compilation/TP4-COMPILATION/Expr_Calculette.g4 by ANTLR 4.13.1

 import java.util.*;
 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class Expr_CalculetteLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, LIRE=13, NEWLINE=14, SEMICOLON=15, MULDIV=16, 
		ADDSUB=17, INCDEC=18, LOGICOP=19, ENTIER=20, TYPE=21, ID=22, WS=23, UNMATCH=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "LIRE", "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", 
			"INCDEC", "LOGICOP", "ENTIER", "TYPE", "ID", "WS", "UNMATCH"
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
		"\u0004\u0000\u0018\u009e\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0003\rc\b\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0003\u0011q\b\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012}\b\u0012\u0001\u0013\u0004\u0013\u0080"+
		"\b\u0013\u000b\u0013\f\u0013\u0081\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u008b\b\u0014"+
		"\u0001\u0015\u0001\u0015\u0005\u0015\u008f\b\u0015\n\u0015\f\u0015\u0092"+
		"\t\u0015\u0001\u0016\u0004\u0016\u0095\b\u0016\u000b\u0016\f\u0016\u0096"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0000\u0000\u0018\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u0018\u0001\u0000\u0005\u0002\u0000**//\u0002\u0000++"+
		"--\u0003\u0000AZ__az\u0004\u000009AZ__az\u0002\u0000\t\t  \u00a8\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00011\u0001\u0000\u0000\u0000\u0003:\u0001\u0000"+
		"\u0000\u0000\u0005<\u0001\u0000\u0000\u0000\u0007>\u0001\u0000\u0000\u0000"+
		"\t@\u0001\u0000\u0000\u0000\u000bB\u0001\u0000\u0000\u0000\rD\u0001\u0000"+
		"\u0000\u0000\u000fF\u0001\u0000\u0000\u0000\u0011J\u0001\u0000\u0000\u0000"+
		"\u0013N\u0001\u0000\u0000\u0000\u0015Q\u0001\u0000\u0000\u0000\u0017V"+
		"\u0001\u0000\u0000\u0000\u0019\\\u0001\u0000\u0000\u0000\u001bb\u0001"+
		"\u0000\u0000\u0000\u001df\u0001\u0000\u0000\u0000\u001fh\u0001\u0000\u0000"+
		"\u0000!j\u0001\u0000\u0000\u0000#p\u0001\u0000\u0000\u0000%|\u0001\u0000"+
		"\u0000\u0000\'\u007f\u0001\u0000\u0000\u0000)\u008a\u0001\u0000\u0000"+
		"\u0000+\u008c\u0001\u0000\u0000\u0000-\u0094\u0001\u0000\u0000\u0000/"+
		"\u009a\u0001\u0000\u0000\u000012\u0005A\u0000\u000023\u0005f\u0000\u0000"+
		"34\u0005f\u0000\u000045\u0005i\u0000\u000056\u0005c\u0000\u000067\u0005"+
		"h\u0000\u000078\u0005e\u0000\u000089\u0005r\u0000\u00009\u0002\u0001\u0000"+
		"\u0000\u0000:;\u0005(\u0000\u0000;\u0004\u0001\u0000\u0000\u0000<=\u0005"+
		")\u0000\u0000=\u0006\u0001\u0000\u0000\u0000>?\u0005,\u0000\u0000?\b\u0001"+
		"\u0000\u0000\u0000@A\u0005=\u0000\u0000A\n\u0001\u0000\u0000\u0000BC\u0005"+
		"{\u0000\u0000C\f\u0001\u0000\u0000\u0000DE\u0005}\u0000\u0000E\u000e\u0001"+
		"\u0000\u0000\u0000FG\u0005n\u0000\u0000GH\u0005o\u0000\u0000HI\u0005t"+
		"\u0000\u0000I\u0010\u0001\u0000\u0000\u0000JK\u0005a\u0000\u0000KL\u0005"+
		"n\u0000\u0000LM\u0005d\u0000\u0000M\u0012\u0001\u0000\u0000\u0000NO\u0005"+
		"o\u0000\u0000OP\u0005r\u0000\u0000P\u0014\u0001\u0000\u0000\u0000QR\u0005"+
		"t\u0000\u0000RS\u0005r\u0000\u0000ST\u0005u\u0000\u0000TU\u0005e\u0000"+
		"\u0000U\u0016\u0001\u0000\u0000\u0000VW\u0005f\u0000\u0000WX\u0005a\u0000"+
		"\u0000XY\u0005l\u0000\u0000YZ\u0005s\u0000\u0000Z[\u0005e\u0000\u0000"+
		"[\u0018\u0001\u0000\u0000\u0000\\]\u0005l\u0000\u0000]^\u0005i\u0000\u0000"+
		"^_\u0005r\u0000\u0000_`\u0005e\u0000\u0000`\u001a\u0001\u0000\u0000\u0000"+
		"ac\u0005\r\u0000\u0000ba\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000"+
		"cd\u0001\u0000\u0000\u0000de\u0005\n\u0000\u0000e\u001c\u0001\u0000\u0000"+
		"\u0000fg\u0005;\u0000\u0000g\u001e\u0001\u0000\u0000\u0000hi\u0007\u0000"+
		"\u0000\u0000i \u0001\u0000\u0000\u0000jk\u0007\u0001\u0000\u0000k\"\u0001"+
		"\u0000\u0000\u0000lm\u0005+\u0000\u0000mq\u0005+\u0000\u0000no\u0005-"+
		"\u0000\u0000oq\u0005-\u0000\u0000pl\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000q$\u0001\u0000\u0000\u0000rs\u0005=\u0000\u0000s}\u0005=\u0000"+
		"\u0000tu\u0005<\u0000\u0000u}\u0005>\u0000\u0000v}\u0005<\u0000\u0000"+
		"wx\u0005<\u0000\u0000x}\u0005=\u0000\u0000y}\u0005>\u0000\u0000z{\u0005"+
		">\u0000\u0000{}\u0005=\u0000\u0000|r\u0001\u0000\u0000\u0000|t\u0001\u0000"+
		"\u0000\u0000|v\u0001\u0000\u0000\u0000|w\u0001\u0000\u0000\u0000|y\u0001"+
		"\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}&\u0001\u0000\u0000\u0000"+
		"~\u0080\u000209\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0081\u0001"+
		"\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001"+
		"\u0000\u0000\u0000\u0082(\u0001\u0000\u0000\u0000\u0083\u0084\u0005i\u0000"+
		"\u0000\u0084\u0085\u0005n\u0000\u0000\u0085\u008b\u0005t\u0000\u0000\u0086"+
		"\u0087\u0005b\u0000\u0000\u0087\u0088\u0005o\u0000\u0000\u0088\u0089\u0005"+
		"o\u0000\u0000\u0089\u008b\u0005l\u0000\u0000\u008a\u0083\u0001\u0000\u0000"+
		"\u0000\u008a\u0086\u0001\u0000\u0000\u0000\u008b*\u0001\u0000\u0000\u0000"+
		"\u008c\u0090\u0007\u0002\u0000\u0000\u008d\u008f\u0007\u0003\u0000\u0000"+
		"\u008e\u008d\u0001\u0000\u0000\u0000\u008f\u0092\u0001\u0000\u0000\u0000"+
		"\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000"+
		"\u0091,\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0093"+
		"\u0095\u0007\u0004\u0000\u0000\u0094\u0093\u0001\u0000\u0000\u0000\u0095"+
		"\u0096\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096"+
		"\u0097\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0006\u0016\u0000\u0000\u0099.\u0001\u0000\u0000\u0000\u009a\u009b"+
		"\t\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u009d\u0006"+
		"\u0017\u0000\u0000\u009d0\u0001\u0000\u0000\u0000\b\u0000bp|\u0081\u008a"+
		"\u0090\u0096\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}