// Generated from /Users/martinadaghia/Desktop/ProgettoLinguaggi/src/parser/SimpLanPlus.g4 by ANTLR 4.13.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link parser.SimpLanPlusParser}.
 */
public interface SimpLanPlusListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterSingleExp(parser.SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitSingleExp(parser.SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiDec}
	 * labeled alternative in {@link parser.SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterMultiDec(parser.SimpLanPlusParser.MultiDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiDec}
	 * labeled alternative in {@link parser.SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitMultiDec(parser.SimpLanPlusParser.MultiDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link parser.SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(parser.SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link parser.SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(parser.SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link parser.SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterFunDec(parser.SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link parser.SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitFunDec(parser.SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code para}
	 * labeled alternative in {@link parser.SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void enterPara(parser.SimpLanPlusParser.ParaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code para}
	 * labeled alternative in {@link parser.SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void exitPara(parser.SimpLanPlusParser.ParaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code body1}
	 * labeled alternative in {@link parser.SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody1(parser.SimpLanPlusParser.Body1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code body1}
	 * labeled alternative in {@link parser.SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody1(parser.SimpLanPlusParser.Body1Context ctx);
	/**
	 * Enter a parse tree produced by {@link parser.SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(parser.SimpLanPlusParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link parser.SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(parser.SimpLanPlusParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterAssignExp(parser.SimpLanPlusParser.AssignExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitAssignExp(parser.SimpLanPlusParser.AssignExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterFunExp(parser.SimpLanPlusParser.FunExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitFunExp(parser.SimpLanPlusParser.FunExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link parser.SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterIfStm(parser.SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link parser.SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitIfStm(parser.SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBaseExp(parser.SimpLanPlusParser.BaseExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBaseExp(parser.SimpLanPlusParser.BaseExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andor}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAndor(parser.SimpLanPlusParser.AndorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andor}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAndor(parser.SimpLanPlusParser.AndorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterVarExp(parser.SimpLanPlusParser.VarExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitVarExp(parser.SimpLanPlusParser.VarExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gle}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterGle(parser.SimpLanPlusParser.GleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gle}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitGle(parser.SimpLanPlusParser.GleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIntVal(parser.SimpLanPlusParser.IntValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIntVal(parser.SimpLanPlusParser.IntValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNot(parser.SimpLanPlusParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNot(parser.SimpLanPlusParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExp2}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFunExp2(parser.SimpLanPlusParser.FunExp2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code funExp2}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFunExp2(parser.SimpLanPlusParser.FunExp2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(parser.SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(parser.SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plussub}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPlussub(parser.SimpLanPlusParser.PlussubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plussub}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPlussub(parser.SimpLanPlusParser.PlussubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBoolVal(parser.SimpLanPlusParser.BoolValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBoolVal(parser.SimpLanPlusParser.BoolValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code muldiv}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMuldiv(parser.SimpLanPlusParser.MuldivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code muldiv}
	 * labeled alternative in {@link parser.SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMuldiv(SimpLanPlusParser.MuldivContext ctx);
}