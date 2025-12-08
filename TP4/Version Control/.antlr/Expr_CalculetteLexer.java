// Generated from /home/pc/Documents/Compilation/TP4-COMPILATION/Version Control/Expr_Calculette.g4 by ANTLR 4.13.1

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
		"\u0004\u0000\u0014\u0087\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0003\nR\b\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000ef\b\u000e\u0001"+
		"\u000f\u0004\u000fi\b\u000f\u000b\u000f\f\u000fj\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"t\b\u0010\u0001\u0011\u0001\u0011\u0005\u0011x\b\u0011\n\u0011\f\u0011"+
		"{\t\u0011\u0001\u0012\u0004\u0012~\b\u0012\u000b\u0012\f\u0012\u007f\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0000"+
		"\u0000\u0014\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014\u0001\u0000"+
		"\u0005\u0002\u0000**//\u0002\u0000++--\u0003\u0000AZ__az\u0004\u00000"+
		"9AZ__az\u0002\u0000\t\t  \u0090\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0001)\u0001\u0000\u0000\u0000\u00032\u0001\u0000\u0000\u0000\u00054"+
		"\u0001\u0000\u0000\u0000\u00076\u0001\u0000\u0000\u0000\t8\u0001\u0000"+
		"\u0000\u0000\u000b:\u0001\u0000\u0000\u0000\r>\u0001\u0000\u0000\u0000"+
		"\u000fB\u0001\u0000\u0000\u0000\u0011E\u0001\u0000\u0000\u0000\u0013J"+
		"\u0001\u0000\u0000\u0000\u0015Q\u0001\u0000\u0000\u0000\u0017U\u0001\u0000"+
		"\u0000\u0000\u0019W\u0001\u0000\u0000\u0000\u001bY\u0001\u0000\u0000\u0000"+
		"\u001de\u0001\u0000\u0000\u0000\u001fh\u0001\u0000\u0000\u0000!s\u0001"+
		"\u0000\u0000\u0000#u\u0001\u0000\u0000\u0000%}\u0001\u0000\u0000\u0000"+
		"\'\u0083\u0001\u0000\u0000\u0000)*\u0005A\u0000\u0000*+\u0005f\u0000\u0000"+
		"+,\u0005f\u0000\u0000,-\u0005i\u0000\u0000-.\u0005c\u0000\u0000./\u0005"+
		"h\u0000\u0000/0\u0005e\u0000\u000001\u0005r\u0000\u00001\u0002\u0001\u0000"+
		"\u0000\u000023\u0005,\u0000\u00003\u0004\u0001\u0000\u0000\u000045\u0005"+
		"=\u0000\u00005\u0006\u0001\u0000\u0000\u000067\u0005(\u0000\u00007\b\u0001"+
		"\u0000\u0000\u000089\u0005)\u0000\u00009\n\u0001\u0000\u0000\u0000:;\u0005"+
		"n\u0000\u0000;<\u0005o\u0000\u0000<=\u0005t\u0000\u0000=\f\u0001\u0000"+
		"\u0000\u0000>?\u0005a\u0000\u0000?@\u0005n\u0000\u0000@A\u0005d\u0000"+
		"\u0000A\u000e\u0001\u0000\u0000\u0000BC\u0005o\u0000\u0000CD\u0005r\u0000"+
		"\u0000D\u0010\u0001\u0000\u0000\u0000EF\u0005t\u0000\u0000FG\u0005r\u0000"+
		"\u0000GH\u0005u\u0000\u0000HI\u0005e\u0000\u0000I\u0012\u0001\u0000\u0000"+
		"\u0000JK\u0005f\u0000\u0000KL\u0005a\u0000\u0000LM\u0005l\u0000\u0000"+
		"MN\u0005s\u0000\u0000NO\u0005e\u0000\u0000O\u0014\u0001\u0000\u0000\u0000"+
		"PR\u0005\r\u0000\u0000QP\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000"+
		"RS\u0001\u0000\u0000\u0000ST\u0005\n\u0000\u0000T\u0016\u0001\u0000\u0000"+
		"\u0000UV\u0005;\u0000\u0000V\u0018\u0001\u0000\u0000\u0000WX\u0007\u0000"+
		"\u0000\u0000X\u001a\u0001\u0000\u0000\u0000YZ\u0007\u0001\u0000\u0000"+
		"Z\u001c\u0001\u0000\u0000\u0000[\\\u0005=\u0000\u0000\\f\u0005=\u0000"+
		"\u0000]^\u0005<\u0000\u0000^f\u0005>\u0000\u0000_f\u0005<\u0000\u0000"+
		"`a\u0005<\u0000\u0000af\u0005=\u0000\u0000bf\u0005>\u0000\u0000cd\u0005"+
		">\u0000\u0000df\u0005=\u0000\u0000e[\u0001\u0000\u0000\u0000e]\u0001\u0000"+
		"\u0000\u0000e_\u0001\u0000\u0000\u0000e`\u0001\u0000\u0000\u0000eb\u0001"+
		"\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000f\u001e\u0001\u0000\u0000"+
		"\u0000gi\u000209\u0000hg\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000"+
		"jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000k \u0001\u0000\u0000"+
		"\u0000lm\u0005i\u0000\u0000mn\u0005n\u0000\u0000nt\u0005t\u0000\u0000"+
		"op\u0005b\u0000\u0000pq\u0005o\u0000\u0000qr\u0005o\u0000\u0000rt\u0005"+
		"l\u0000\u0000sl\u0001\u0000\u0000\u0000so\u0001\u0000\u0000\u0000t\"\u0001"+
		"\u0000\u0000\u0000uy\u0007\u0002\u0000\u0000vx\u0007\u0003\u0000\u0000"+
		"wv\u0001\u0000\u0000\u0000x{\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000"+
		"\u0000yz\u0001\u0000\u0000\u0000z$\u0001\u0000\u0000\u0000{y\u0001\u0000"+
		"\u0000\u0000|~\u0007\u0004\u0000\u0000}|\u0001\u0000\u0000\u0000~\u007f"+
		"\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f\u0080\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0006"+
		"\u0012\u0000\u0000\u0082&\u0001\u0000\u0000\u0000\u0083\u0084\t\u0000"+
		"\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086\u0006\u0013"+
		"\u0000\u0000\u0086(\u0001\u0000\u0000\u0000\u0007\u0000Qejsy\u007f\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}