package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;
/*QUESTA è CORRETTA*/
public class ProgNode implements Node {
    private Node exp;

    public ProgNode(Node _exp) {
        exp = _exp;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {

        return exp.checkSemantics(ST, _nesting);
    }

    @Override
    public Type typeCheck() {
        return exp.typeCheck();
    }

    @Override
    public String codeGeneration() {
        return exp.codeGeneration() + "halt\n";
    }

    @Override
    public String toPrint(String s) {
        return "Prog\n" + exp.toPrint(s + "  ");
    }
}
