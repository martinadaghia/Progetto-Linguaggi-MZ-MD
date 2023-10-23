package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class DecNode implements Node {
    private String id;
    private Node type;
    private int nesting;

    public DecNode(String _id, Type _type) {
        id = _id;
        type = _type;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<>();
        nesting = _nesting;

        if(ST.top_lookup(id)){ //se non è definita all'interno dello scoope. top_lookup guarda l'ultimo ambiente disponibile
            errors.add(new SemanticError("Var id " + id + " already declared"));
        }else {
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
        return "push 0 \n";
    }

    public String toPrint(String s) {
        return s + "Dec:" + id + type.toPrint(" ") + "\n";
    }
}
