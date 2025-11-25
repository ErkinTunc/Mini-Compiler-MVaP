// Generated from Expr_Calculette.g4 by ANTLR 4.9.2

 import java.util.*;
 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Expr_CalculetteParser}.
 */
public interface Expr_CalculetteListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(Expr_CalculetteParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(Expr_CalculetteParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(Expr_CalculetteParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(Expr_CalculetteParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#declInstr}.
	 * @param ctx the parse tree
	 */
	void enterDeclInstr(Expr_CalculetteParser.DeclInstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#declInstr}.
	 * @param ctx the parse tree
	 */
	void exitDeclInstr(Expr_CalculetteParser.DeclInstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(Expr_CalculetteParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(Expr_CalculetteParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(Expr_CalculetteParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(Expr_CalculetteParser.IdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#assignInstr}.
	 * @param ctx the parse tree
	 */
	void enterAssignInstr(Expr_CalculetteParser.AssignInstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#assignInstr}.
	 * @param ctx the parse tree
	 */
	void exitAssignInstr(Expr_CalculetteParser.AssignInstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Expr_CalculetteParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Expr_CalculetteParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#arithmExpr}.
	 * @param ctx the parse tree
	 */
	void enterArithmExpr(Expr_CalculetteParser.ArithmExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#arithmExpr}.
	 * @param ctx the parse tree
	 */
	void exitArithmExpr(Expr_CalculetteParser.ArithmExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Expr_CalculetteParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(Expr_CalculetteParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Expr_CalculetteParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(Expr_CalculetteParser.BoolExprContext ctx);
}