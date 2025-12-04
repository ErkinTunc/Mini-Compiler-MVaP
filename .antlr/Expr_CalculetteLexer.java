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
		T__9=10, LIRE=11, NEWLINE=12, SEMICOLON=13, MULDIV=14, ADDSUB=15, LOGICOP=16, 
		ENTIER=17, TYPE=18, ID=19, WS=20, UNMATCH=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "LIRE", "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", 
			"ENTIER", "TYPE", "ID", "WS", "UNMATCH"
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


	    static class VarEntry {
	        String type;        // "int" veya "bool"
	        boolean initialized;
	        Integer ivalue;     // sadece int için kullan
	        Boolean bvalue;     // sadece bool için kullan
	    }

	    Map<String, VarEntry> symtab = new HashMap<>(); // symtab : symbole table

	    Scanner scanner = new Scanner(System.in); // Permet de lire des entrées pour l'instruction lire


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
		"\u0004\u0000\u0015\u008e\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0003\u000bY\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000fm\b\u000f"+
		"\u0001\u0010\u0004\u0010p\b\u0010\u000b\u0010\f\u0010q\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011{\b\u0011\u0001\u0012\u0001\u0012\u0005\u0012\u007f\b\u0012\n\u0012"+
		"\f\u0012\u0082\t\u0012\u0001\u0013\u0004\u0013\u0085\b\u0013\u000b\u0013"+
		"\f\u0013\u0086\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0000\u0000\u0015\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0014)\u0015\u0001\u0000\u0005\u0002\u0000**//\u0002\u0000++--\u0003"+
		"\u0000AZ__az\u0004\u000009AZ__az\u0002\u0000\t\t  \u0097\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0001+\u0001\u0000"+
		"\u0000\u0000\u00034\u0001\u0000\u0000\u0000\u00056\u0001\u0000\u0000\u0000"+
		"\u00078\u0001\u0000\u0000\u0000\t:\u0001\u0000\u0000\u0000\u000b<\u0001"+
		"\u0000\u0000\u0000\r@\u0001\u0000\u0000\u0000\u000fD\u0001\u0000\u0000"+
		"\u0000\u0011G\u0001\u0000\u0000\u0000\u0013L\u0001\u0000\u0000\u0000\u0015"+
		"R\u0001\u0000\u0000\u0000\u0017X\u0001\u0000\u0000\u0000\u0019\\\u0001"+
		"\u0000\u0000\u0000\u001b^\u0001\u0000\u0000\u0000\u001d`\u0001\u0000\u0000"+
		"\u0000\u001fl\u0001\u0000\u0000\u0000!o\u0001\u0000\u0000\u0000#z\u0001"+
		"\u0000\u0000\u0000%|\u0001\u0000\u0000\u0000\'\u0084\u0001\u0000\u0000"+
		"\u0000)\u008a\u0001\u0000\u0000\u0000+,\u0005A\u0000\u0000,-\u0005f\u0000"+
		"\u0000-.\u0005f\u0000\u0000./\u0005i\u0000\u0000/0\u0005c\u0000\u0000"+
		"01\u0005h\u0000\u000012\u0005e\u0000\u000023\u0005r\u0000\u00003\u0002"+
		"\u0001\u0000\u0000\u000045\u0005,\u0000\u00005\u0004\u0001\u0000\u0000"+
		"\u000067\u0005=\u0000\u00007\u0006\u0001\u0000\u0000\u000089\u0005(\u0000"+
		"\u00009\b\u0001\u0000\u0000\u0000:;\u0005)\u0000\u0000;\n\u0001\u0000"+
		"\u0000\u0000<=\u0005n\u0000\u0000=>\u0005o\u0000\u0000>?\u0005t\u0000"+
		"\u0000?\f\u0001\u0000\u0000\u0000@A\u0005a\u0000\u0000AB\u0005n\u0000"+
		"\u0000BC\u0005d\u0000\u0000C\u000e\u0001\u0000\u0000\u0000DE\u0005o\u0000"+
		"\u0000EF\u0005r\u0000\u0000F\u0010\u0001\u0000\u0000\u0000GH\u0005t\u0000"+
		"\u0000HI\u0005r\u0000\u0000IJ\u0005u\u0000\u0000JK\u0005e\u0000\u0000"+
		"K\u0012\u0001\u0000\u0000\u0000LM\u0005f\u0000\u0000MN\u0005a\u0000\u0000"+
		"NO\u0005l\u0000\u0000OP\u0005s\u0000\u0000PQ\u0005e\u0000\u0000Q\u0014"+
		"\u0001\u0000\u0000\u0000RS\u0005l\u0000\u0000ST\u0005i\u0000\u0000TU\u0005"+
		"r\u0000\u0000UV\u0005e\u0000\u0000V\u0016\u0001\u0000\u0000\u0000WY\u0005"+
		"\r\u0000\u0000XW\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001"+
		"\u0000\u0000\u0000Z[\u0005\n\u0000\u0000[\u0018\u0001\u0000\u0000\u0000"+
		"\\]\u0005;\u0000\u0000]\u001a\u0001\u0000\u0000\u0000^_\u0007\u0000\u0000"+
		"\u0000_\u001c\u0001\u0000\u0000\u0000`a\u0007\u0001\u0000\u0000a\u001e"+
		"\u0001\u0000\u0000\u0000bc\u0005=\u0000\u0000cm\u0005=\u0000\u0000de\u0005"+
		"<\u0000\u0000em\u0005>\u0000\u0000fm\u0005<\u0000\u0000gh\u0005<\u0000"+
		"\u0000hm\u0005=\u0000\u0000im\u0005>\u0000\u0000jk\u0005>\u0000\u0000"+
		"km\u0005=\u0000\u0000lb\u0001\u0000\u0000\u0000ld\u0001\u0000\u0000\u0000"+
		"lf\u0001\u0000\u0000\u0000lg\u0001\u0000\u0000\u0000li\u0001\u0000\u0000"+
		"\u0000lj\u0001\u0000\u0000\u0000m \u0001\u0000\u0000\u0000np\u000209\u0000"+
		"on\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000"+
		"\u0000qr\u0001\u0000\u0000\u0000r\"\u0001\u0000\u0000\u0000st\u0005i\u0000"+
		"\u0000tu\u0005n\u0000\u0000u{\u0005t\u0000\u0000vw\u0005b\u0000\u0000"+
		"wx\u0005o\u0000\u0000xy\u0005o\u0000\u0000y{\u0005l\u0000\u0000zs\u0001"+
		"\u0000\u0000\u0000zv\u0001\u0000\u0000\u0000{$\u0001\u0000\u0000\u0000"+
		"|\u0080\u0007\u0002\u0000\u0000}\u007f\u0007\u0003\u0000\u0000~}\u0001"+
		"\u0000\u0000\u0000\u007f\u0082\u0001\u0000\u0000\u0000\u0080~\u0001\u0000"+
		"\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081&\u0001\u0000\u0000"+
		"\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083\u0085\u0007\u0004\u0000"+
		"\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000"+
		"\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0006\u0013\u0000"+
		"\u0000\u0089(\u0001\u0000\u0000\u0000\u008a\u008b\t\u0000\u0000\u0000"+
		"\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008d\u0006\u0014\u0000\u0000"+
		"\u008d*\u0001\u0000\u0000\u0000\u0007\u0000Xlqz\u0080\u0086\u0001\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}