package ast;

import java.util.ArrayList;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class BoolNode implements Node {

    private boolean val;

    public BoolNode (boolean val) {
        this.val = val ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        return new ArrayList<SemanticError>();
    }

    public Type typeCheck() {
        return new BoolType();
    }

    public String codeGeneration() {
        return "//start BoolNode\n" + "storei A0 "+(val?1:0)+"\n" + "//and BoolNode\n";
    }

    public String toPrint(String s) {
        return s + String.valueOf(val) +"\n";
    }

}