package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class ParamNode implements Node {
    private String id;
    private Type type;
    private int nesting;

    public ParamNode(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        nesting = _nesting;
        return new ArrayList<SemanticError>();
    }

    public Type typeCheck() {
        return null;
    }

    public String codeGeneration() {
        return "//start ParamNode\n" + "" + "//end ParamNode\n";
    }

    public String toPrint(String s) {
        return s + "Par " + id + ":" + type.toPrint(s);
    }

}