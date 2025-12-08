// Generated from Rationnel.g4 by ANTLR 4.9.2

 import java.util.*;
 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RationnelParser}.
 */
public interface RationnelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RationnelParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(RationnelParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationnelParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(RationnelParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link RationnelParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(RationnelParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationnelParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(RationnelParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RationnelParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(RationnelParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationnelParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(RationnelParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RationnelParser#arithmExpr}.
	 * @param ctx the parse tree
	 */
	void enterArithmExpr(RationnelParser.ArithmExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationnelParser#arithmExpr}.
	 * @param ctx the parse tree
	 */
	void exitArithmExpr(RationnelParser.ArithmExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RationnelParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(RationnelParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationnelParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(RationnelParser.BoolExprContext ctx);
}