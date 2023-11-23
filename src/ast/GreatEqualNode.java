package ast;

import java.util.ArrayList;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class GreatEqualNode implements Node {
    private Node left;
    private Node right;

    public GreatEqualNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting));
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    @Override
    public Type typeCheck() {
        if (left.typeCheck() instanceof IntType && right.typeCheck() instanceof IntType) {
            return new BoolType();
        } else {
            System.out.println("Type Error: Non integers in greater than or equal to comparison");
            return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String labelTrue = SimpLanlib.freshLabel();
        String labelEnd = SimpLanlib.freshLabel();
        String labelEq = SimpLanlib.freshLabel();


        return "//start GreatNode\n" +
                left.codeGeneration() +
                "pushr A0 \n" +
                right.codeGeneration() +
                "popr T1 \n" +
                "bleq T1 A0 " + labelTrue + " \n" +
                labelEq + ": \n" +
                "storei A0 1 \n" +
                "b " + labelEnd + " \n" +
                labelTrue + ": \n" +
                "beq T1 A0 " + labelEq + " \n" +
                "storei A0 0 \n" +
                labelEnd + ": \n" +
                "//end GreatNode\n";

    }

    @Override
    public String toPrint(String s) {
        return s + "Greatequal\n" +
                left.toPrint(s + "  ") +
                right.toPrint(s + "  ");
    }
}
