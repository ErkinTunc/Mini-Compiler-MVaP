// Generated from /home/pc/Documents/Compilation/TP4-COMPILATION/Variable.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class VariableLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NEWLINE=3, SEMICOLON=4, MULDIV=5, ADDSUB=6, LOGICOP=7, 
		ASSIGNE=8, ENTIER=9, BOOL=10, WS=11, UNMATCH=12, ID=13, STRING=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", 
			"ASSIGNE", "ENTIER", "BOOL", "WS", "UNMATCH", "ID", "STRING"
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
			"ASSIGNE", "ENTIER", "BOOL", "WS", "UNMATCH", "ID", "STRING"
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
		"\u0004\u0000\u000ep\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0003\u0002(\b\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006<\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0004\bA\b\b\u000b\b\f\bB\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tN\b\t\u0001"+
		"\n\u0004\nQ\b\n\u000b\n\f\nR\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\fa\b\f\u0001\r\u0004\rd\b\r\u000b\r\f\re\u0001\r\u0001\r\u0004\rj\b\r"+
		"\u000b\r\f\rk\u0001\r\u0003\ro\b\r\u0000\u0000\u000e\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u0001\u0000\u0003\u0002"+
		"\u0000**//\u0002\u0000++--\u0002\u0000\t\t  |\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0001\u001d\u0001\u0000\u0000\u0000"+
		"\u0003!\u0001\u0000\u0000\u0000\u0005\'\u0001\u0000\u0000\u0000\u0007"+
		"+\u0001\u0000\u0000\u0000\t-\u0001\u0000\u0000\u0000\u000b/\u0001\u0000"+
		"\u0000\u0000\r;\u0001\u0000\u0000\u0000\u000f=\u0001\u0000\u0000\u0000"+
		"\u0011@\u0001\u0000\u0000\u0000\u0013M\u0001\u0000\u0000\u0000\u0015P"+
		"\u0001\u0000\u0000\u0000\u0017V\u0001\u0000\u0000\u0000\u0019`\u0001\u0000"+
		"\u0000\u0000\u001bn\u0001\u0000\u0000\u0000\u001d\u001e\u0005i\u0000\u0000"+
		"\u001e\u001f\u0005n\u0000\u0000\u001f \u0005t\u0000\u0000 \u0002\u0001"+
		"\u0000\u0000\u0000!\"\u0005b\u0000\u0000\"#\u0005o\u0000\u0000#$\u0005"+
		"o\u0000\u0000$%\u0005l\u0000\u0000%\u0004\u0001\u0000\u0000\u0000&(\u0005"+
		"\r\u0000\u0000\'&\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000("+
		")\u0001\u0000\u0000\u0000)*\u0005\n\u0000\u0000*\u0006\u0001\u0000\u0000"+
		"\u0000+,\u0005;\u0000\u0000,\b\u0001\u0000\u0000\u0000-.\u0007\u0000\u0000"+
		"\u0000.\n\u0001\u0000\u0000\u0000/0\u0007\u0001\u0000\u00000\f\u0001\u0000"+
		"\u0000\u000012\u0005=\u0000\u00002<\u0005=\u0000\u000034\u0005<\u0000"+
		"\u00004<\u0005>\u0000\u00005<\u0005<\u0000\u000067\u0005<\u0000\u0000"+
		"7<\u0005=\u0000\u00008<\u0005>\u0000\u00009:\u0005>\u0000\u0000:<\u0005"+
		"=\u0000\u0000;1\u0001\u0000\u0000\u0000;3\u0001\u0000\u0000\u0000;5\u0001"+
		"\u0000\u0000\u0000;6\u0001\u0000\u0000\u0000;8\u0001\u0000\u0000\u0000"+
		";9\u0001\u0000\u0000\u0000<\u000e\u0001\u0000\u0000\u0000=>\u0005=\u0000"+
		"\u0000>\u0010\u0001\u0000\u0000\u0000?A\u000209\u0000@?\u0001\u0000\u0000"+
		"\u0000AB\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000"+
		"\u0000\u0000C\u0012\u0001\u0000\u0000\u0000DE\u0005t\u0000\u0000EF\u0005"+
		"r\u0000\u0000FG\u0005u\u0000\u0000GN\u0005e\u0000\u0000HI\u0005f\u0000"+
		"\u0000IJ\u0005a\u0000\u0000JK\u0005l\u0000\u0000KL\u0005s\u0000\u0000"+
		"LN\u0005e\u0000\u0000MD\u0001\u0000\u0000\u0000MH\u0001\u0000\u0000\u0000"+
		"N\u0014\u0001\u0000\u0000\u0000OQ\u0007\u0002\u0000\u0000PO\u0001\u0000"+
		"\u0000\u0000QR\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000RS\u0001"+
		"\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0006\n\u0000\u0000U\u0016"+
		"\u0001\u0000\u0000\u0000VW\t\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XY\u0006\u000b\u0000\u0000Y\u0018\u0001\u0000\u0000\u0000Z[\u0003\u0011"+
		"\b\u0000[\\\u0003\u0019\f\u0000\\a\u0001\u0000\u0000\u0000]^\u0003\u001b"+
		"\r\u0000^_\u0003\u0019\f\u0000_a\u0001\u0000\u0000\u0000`Z\u0001\u0000"+
		"\u0000\u0000`]\u0001\u0000\u0000\u0000a\u001a\u0001\u0000\u0000\u0000"+
		"bd\u0002az\u0000cb\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000e"+
		"c\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000"+
		"\u0000go\u0003\u001b\r\u0000hj\u0002AZ\u0000ih\u0001\u0000\u0000\u0000"+
		"jk\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000mo\u0003\u001b\r\u0000nc\u0001\u0000\u0000"+
		"\u0000ni\u0001\u0000\u0000\u0000o\u001c\u0001\u0000\u0000\u0000\n\u0000"+
		"\';BMR`ekn\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}