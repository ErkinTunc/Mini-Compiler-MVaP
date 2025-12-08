// Generated from Variable.g4 by ANTLR 4.9.2

 import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VariableParser}.
 */
public interface VariableListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link VariableParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(VariableParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(VariableParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link VariableParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(VariableParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(VariableParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link VariableParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(VariableParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(VariableParser.TypeContext ctx);
}