package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class DecNode implements Node {
    private String id;
    private Node type;
    private int nesting;

    public DecNode(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<>();
        nesting = _nesting;

        if (ST.top_lookup(id)) {
            errors.add(new SemanticError("Var id " + id + " already declared"));
        } else {
            ST.insert(id, (Type) type, nesting, "");
        }

        return errors;
    }

    @Override
    public Type typeCheck() {
        return (Type) type;
    }

    @Override
    public String codeGeneration() {
        return "//start DecNode\n" + "push 0 \n" + "//and DecNode\n";
    }

    public String toPrint(String s) {
        return s + "Dec:" + id + type.toPrint(" ") + "\n";
    }
}
