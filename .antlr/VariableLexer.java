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
		"\u0004\u0000\r_\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0003\u0002&\b\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006:\b\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0004\b?\b\b\u000b\b\f\b@\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tL\b\t\u0001\n\u0004"+
		"\nO\b\n\u000b\n\f\nP\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0005\f[\b\f\n\f\f\f^\t\f\u0000\u0000\r\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u0001\u0000\u0005"+
		"\u0002\u0000**//\u0002\u0000++--\u0002\u0000\t\t  \u0003\u0000AZ__az\u0004"+
		"\u000009AZ__azh\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0001\u001b\u0001\u0000\u0000"+
		"\u0000\u0003\u001f\u0001\u0000\u0000\u0000\u0005%\u0001\u0000\u0000\u0000"+
		"\u0007)\u0001\u0000\u0000\u0000\t+\u0001\u0000\u0000\u0000\u000b-\u0001"+
		"\u0000\u0000\u0000\r9\u0001\u0000\u0000\u0000\u000f;\u0001\u0000\u0000"+
		"\u0000\u0011>\u0001\u0000\u0000\u0000\u0013K\u0001\u0000\u0000\u0000\u0015"+
		"N\u0001\u0000\u0000\u0000\u0017T\u0001\u0000\u0000\u0000\u0019X\u0001"+
		"\u0000\u0000\u0000\u001b\u001c\u0005i\u0000\u0000\u001c\u001d\u0005n\u0000"+
		"\u0000\u001d\u001e\u0005t\u0000\u0000\u001e\u0002\u0001\u0000\u0000\u0000"+
		"\u001f \u0005b\u0000\u0000 !\u0005o\u0000\u0000!\"\u0005o\u0000\u0000"+
		"\"#\u0005l\u0000\u0000#\u0004\u0001\u0000\u0000\u0000$&\u0005\r\u0000"+
		"\u0000%$\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\'(\u0005\n\u0000\u0000(\u0006\u0001\u0000\u0000\u0000)*\u0005"+
		";\u0000\u0000*\b\u0001\u0000\u0000\u0000+,\u0007\u0000\u0000\u0000,\n"+
		"\u0001\u0000\u0000\u0000-.\u0007\u0001\u0000\u0000.\f\u0001\u0000\u0000"+
		"\u0000/0\u0005=\u0000\u00000:\u0005=\u0000\u000012\u0005<\u0000\u0000"+
		"2:\u0005>\u0000\u00003:\u0005<\u0000\u000045\u0005<\u0000\u00005:\u0005"+
		"=\u0000\u00006:\u0005>\u0000\u000078\u0005>\u0000\u00008:\u0005=\u0000"+
		"\u00009/\u0001\u0000\u0000\u000091\u0001\u0000\u0000\u000093\u0001\u0000"+
		"\u0000\u000094\u0001\u0000\u0000\u000096\u0001\u0000\u0000\u000097\u0001"+
		"\u0000\u0000\u0000:\u000e\u0001\u0000\u0000\u0000;<\u0005=\u0000\u0000"+
		"<\u0010\u0001\u0000\u0000\u0000=?\u000209\u0000>=\u0001\u0000\u0000\u0000"+
		"?@\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000"+
		"\u0000A\u0012\u0001\u0000\u0000\u0000BC\u0005t\u0000\u0000CD\u0005r\u0000"+
		"\u0000DE\u0005u\u0000\u0000EL\u0005e\u0000\u0000FG\u0005f\u0000\u0000"+
		"GH\u0005a\u0000\u0000HI\u0005l\u0000\u0000IJ\u0005s\u0000\u0000JL\u0005"+
		"e\u0000\u0000KB\u0001\u0000\u0000\u0000KF\u0001\u0000\u0000\u0000L\u0014"+
		"\u0001\u0000\u0000\u0000MO\u0007\u0002\u0000\u0000NM\u0001\u0000\u0000"+
		"\u0000OP\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0006\n\u0000\u0000S\u0016\u0001"+
		"\u0000\u0000\u0000TU\t\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0006"+
		"\u000b\u0000\u0000W\u0018\u0001\u0000\u0000\u0000X\\\u0007\u0003\u0000"+
		"\u0000Y[\u0007\u0004\u0000\u0000ZY\u0001\u0000\u0000\u0000[^\u0001\u0000"+
		"\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]\u001a"+
		"\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000\u0007\u0000%9@KP\\"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}