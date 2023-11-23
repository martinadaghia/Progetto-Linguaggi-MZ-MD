package ast;

import java.util.ArrayList;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class AndNode implements Node {
    private Node left;
    private Node right;

    public AndNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting)); // verifica semantica espressione a sx
        errors.addAll(right.checkSemantics(ST, _nesting)); // verifica semantica espressione a dx

        return errors;
    }

    @Override
    public Type typeCheck() {
        if (left.typeCheck() instanceof BoolType && right.typeCheck() instanceof BoolType) {
            return new BoolType();
        } else {
            System.out.println("Type Error: Non-boolean operands in logical AND");
            return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String labelTrue = SimpLanlib.freshLabel();
        String labelEnd = SimpLanlib.freshLabel();

        return "//start andNode\n" +
                left.codeGeneration() + //left in A0
                "storei T1 0 \n" + // in T1 salviamo 0
                "beq A0 T1 \n" + "b " + labelEnd + "\n" + // A0 = 0 (falso)
                right.codeGeneration() +
                "beq A0 T1 \n" + "b " + labelEnd + "\n" +
                "storei A0 1 \n" + "b " + labelTrue + "\n" +
                labelEnd + ": \n" +
                "storei A0 0 \n" +
                labelTrue + ": \n" +
                "//end andNode\n" ;
    }

    @Override
    public String toPrint(String s) {
        return s + "And\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
    }
}
