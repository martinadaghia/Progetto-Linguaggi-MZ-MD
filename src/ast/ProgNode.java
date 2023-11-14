package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class ProgNode implements Node {
    private Node exp;

    public ProgNode(Node exp) {
        this.exp = exp;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int nesting) {

        return exp.checkSemantics(ST, nesting);
    }

    @Override
    public Type typeCheck() {
        return exp.typeCheck();
    }

    @Override
    public String codeGeneration() {
        return "//start ProgNode\n" + exp.codeGeneration() + "halt\n" + "//end ProgNode\n";
    }

    @Override
    public String toPrint(String s) {
        return "Prog\n" + exp.toPrint(s + "  ");
    }
}
