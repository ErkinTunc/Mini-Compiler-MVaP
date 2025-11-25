// Generated from /home/pc/Documents/Compilation/TP4-COMPILATION/Variable.g4 by ANTLR 4.13.1

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
public class VariableLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, NEWLINE=4, SEMICOLON=5, MULDIV=6, ADDSUB=7, LOGICOP=8, 
		ASSIGNE=9, ENTIER=10, BOOL=11, WS=12, UNMATCH=13, ID=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", 
			"ASSIGNE", "ENTIER", "BOOL", "WS", "UNMATCH", "ID"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Afficher'", "'int'", "'bool'", null, "';'", null, null, null, 
			"'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "NEWLINE", "SEMICOLON", "MULDIV", "ADDSUB", "LOGICOP", 
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
		"\u0004\u0000\u000ej\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0003\u00031\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007E\b\u0007\u0001\b\u0001\b\u0001\t\u0004\tJ\b\t\u000b"+
		"\t\f\tK\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0003\nW\b\n\u0001\u000b\u0004\u000bZ\b\u000b\u000b\u000b\f"+
		"\u000b[\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0005\rf\b\r\n\r\f\ri\t\r\u0000\u0000\u000e\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u0001\u0000\u0005\u0002"+
		"\u0000**//\u0002\u0000++--\u0002\u0000\t\t  \u0003\u0000AZ__az\u0004\u0000"+
		"09AZ__azs\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0001\u001d\u0001\u0000\u0000\u0000\u0003&\u0001\u0000\u0000\u0000\u0005"+
		"*\u0001\u0000\u0000\u0000\u00070\u0001\u0000\u0000\u0000\t4\u0001\u0000"+
		"\u0000\u0000\u000b6\u0001\u0000\u0000\u0000\r8\u0001\u0000\u0000\u0000"+
		"\u000fD\u0001\u0000\u0000\u0000\u0011F\u0001\u0000\u0000\u0000\u0013I"+
		"\u0001\u0000\u0000\u0000\u0015V\u0001\u0000\u0000\u0000\u0017Y\u0001\u0000"+
		"\u0000\u0000\u0019_\u0001\u0000\u0000\u0000\u001bc\u0001\u0000\u0000\u0000"+
		"\u001d\u001e\u0005A\u0000\u0000\u001e\u001f\u0005f\u0000\u0000\u001f "+
		"\u0005f\u0000\u0000 !\u0005i\u0000\u0000!\"\u0005c\u0000\u0000\"#\u0005"+
		"h\u0000\u0000#$\u0005e\u0000\u0000$%\u0005r\u0000\u0000%\u0002\u0001\u0000"+
		"\u0000\u0000&\'\u0005i\u0000\u0000\'(\u0005n\u0000\u0000()\u0005t\u0000"+
		"\u0000)\u0004\u0001\u0000\u0000\u0000*+\u0005b\u0000\u0000+,\u0005o\u0000"+
		"\u0000,-\u0005o\u0000\u0000-.\u0005l\u0000\u0000.\u0006\u0001\u0000\u0000"+
		"\u0000/1\u0005\r\u0000\u00000/\u0001\u0000\u0000\u000001\u0001\u0000\u0000"+
		"\u000012\u0001\u0000\u0000\u000023\u0005\n\u0000\u00003\b\u0001\u0000"+
		"\u0000\u000045\u0005;\u0000\u00005\n\u0001\u0000\u0000\u000067\u0007\u0000"+
		"\u0000\u00007\f\u0001\u0000\u0000\u000089\u0007\u0001\u0000\u00009\u000e"+
		"\u0001\u0000\u0000\u0000:;\u0005=\u0000\u0000;E\u0005=\u0000\u0000<=\u0005"+
		"<\u0000\u0000=E\u0005>\u0000\u0000>E\u0005<\u0000\u0000?@\u0005<\u0000"+
		"\u0000@E\u0005=\u0000\u0000AE\u0005>\u0000\u0000BC\u0005>\u0000\u0000"+
		"CE\u0005=\u0000\u0000D:\u0001\u0000\u0000\u0000D<\u0001\u0000\u0000\u0000"+
		"D>\u0001\u0000\u0000\u0000D?\u0001\u0000\u0000\u0000DA\u0001\u0000\u0000"+
		"\u0000DB\u0001\u0000\u0000\u0000E\u0010\u0001\u0000\u0000\u0000FG\u0005"+
		"=\u0000\u0000G\u0012\u0001\u0000\u0000\u0000HJ\u000209\u0000IH\u0001\u0000"+
		"\u0000\u0000JK\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000KL\u0001"+
		"\u0000\u0000\u0000L\u0014\u0001\u0000\u0000\u0000MN\u0005t\u0000\u0000"+
		"NO\u0005r\u0000\u0000OP\u0005u\u0000\u0000PW\u0005e\u0000\u0000QR\u0005"+
		"f\u0000\u0000RS\u0005a\u0000\u0000ST\u0005l\u0000\u0000TU\u0005s\u0000"+
		"\u0000UW\u0005e\u0000\u0000VM\u0001\u0000\u0000\u0000VQ\u0001\u0000\u0000"+
		"\u0000W\u0016\u0001\u0000\u0000\u0000XZ\u0007\u0002\u0000\u0000YX\u0001"+
		"\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000"+
		"[\\\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]^\u0006\u000b\u0000"+
		"\u0000^\u0018\u0001\u0000\u0000\u0000_`\t\u0000\u0000\u0000`a\u0001\u0000"+
		"\u0000\u0000ab\u0006\f\u0000\u0000b\u001a\u0001\u0000\u0000\u0000cg\u0007"+
		"\u0003\u0000\u0000df\u0007\u0004\u0000\u0000ed\u0001\u0000\u0000\u0000"+
		"fi\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000h\u001c\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000\u0007"+
		"\u00000DKV[g\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}