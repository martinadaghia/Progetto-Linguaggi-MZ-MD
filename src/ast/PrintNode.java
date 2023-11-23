package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class PrintNode implements Node {
    private Node exp;

    public PrintNode (Node exp) {
        this.exp = exp ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        return exp.checkSemantics(ST, _nesting);
    }

    public Type typeCheck() {
        return exp.typeCheck();
    }

    public String codeGeneration() {
        return "//start PrintNode\n" + exp.codeGeneration()+"print\n" + "//end PrintNode\n";
    }

    public String toPrint(String s) {
        return s + "Print\n" +exp.toPrint(s+"  ") ;
    }

}