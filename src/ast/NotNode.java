package ast;

import java.util.ArrayList;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class NotNode implements Node {
    private Node right;

    public NotNode(Node _right) {
        right = _right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    @Override
    public Type typeCheck() {
        if (right.typeCheck() instanceof BoolType) {
            return new BoolType();
        } else {
            System.out.println("Type Error: Non bool in not exp");
            return new ErrorType();
        }
    }

    public String codeGeneration() {
        String labelTrue = SimpLanlib.freshLabel();
        String labelEnd = SimpLanlib.freshLabel();

        return "//start NotNode\n"
                + right.codeGeneration()
                + "storei T1 1 \n"
                + "beq A0 T1 " + labelTrue + "\n"
                + "storei A0 1 \n"
                + "b " + labelEnd + "\n"
                + labelTrue + ": \n"
                + "storei A0 0 \n"
                + labelEnd + ": \n"
                + "//end NotNode\n";
    }

    public String toPrint(String s) {
        return s + "Not\n" + right.toPrint(s + "  ");
    }
}
