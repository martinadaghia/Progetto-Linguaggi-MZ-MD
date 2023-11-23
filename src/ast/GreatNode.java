package ast;

import java.util.ArrayList;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class GreatNode implements Node {
    private Node left;
    private Node right;

    public GreatNode(Node left, Node right) {
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
        if (left.typeCheck() instanceof IntType && right.typeCheck() instanceof IntType)
            return new BoolType();
        else {
            System.out.println("Type Error: Non integers in great than to comparison");
            return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String labelTrue = SimpLanlib.freshLabel();
        String labelEnd = SimpLanlib.freshLabel();

        return "//start GreatNode\n" +
                left.codeGeneration() +
                "pushr A0 \n" +
                right.codeGeneration() +
                "popr T1 \n" +
                "bleq T1 A0 " + labelTrue + " \n" + //ragionare al contrario:  se left è <= di right allora false (> == !(<=))
                "storei A0 1 \n" +
                "b " + labelEnd + " \n" +
                labelTrue + ": \n" +
                "storei A0 0 \n" +
                labelEnd + ": \n" +
                "//end GreatNode\n";
    }

    @Override
    public String toPrint(String s) {
        return s + "Great\n" +
                left.toPrint(s + "  ") +
                right.toPrint(s + "  ");
    }
}
