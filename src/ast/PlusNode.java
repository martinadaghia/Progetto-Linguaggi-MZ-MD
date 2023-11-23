package ast;

import java.util.ArrayList;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class PlusNode implements Node {
    private Node left;
    private Node right;

    public PlusNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting));
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    public Type typeCheck() {
        if ((left.typeCheck() instanceof IntType) && (right.typeCheck() instanceof IntType))
            return new IntType();
        else {
            System.out.println("Type Error: Non integers in addition");
            return new ErrorType();
        }
    }


    public String codeGeneration() {
        return "//start PlusNode\n" +
                left.codeGeneration() +
                "pushr A0 \n" +
                right.codeGeneration() +
                "popr T1 \n" +
                "add A0 T1 \n" +
                "popr A0 \n" +
                "//end PlusNode\n";
    }

    public String toPrint(String s) {
        return s + "Plus\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
    }

}