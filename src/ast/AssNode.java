package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class AssNode implements Node {
    private String id;
    private STentry stentry;
    private Node exp;
    private int nesting;

    public AssNode(String id, Node exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        nesting = _nesting;

        errors.addAll(exp.checkSemantics(ST, _nesting));

        STentry var = ST.lookup(id);

        // Controlliamo che la variabile esista
        if (var == null) {
            errors.add(new SemanticError("Var " + id + " never declared"));
        } else {
            stentry = ST.lookup(id);
            var.setInitialized(true);
        }

        return errors;

    }

    @Override
    public Type typeCheck() {
        if (!stentry.gettype().getClass().equals(exp.typeCheck().getClass())) {
            System.out.println("Asg: " + id + " doesn't match expression type ");
            return new ErrorType();
        } else {
            return exp.typeCheck();
        }
    }

    @Override
    public String codeGeneration() {
        String getAR = "";
        for (int i = 0; i < nesting - stentry.getnesting(); i++)
            getAR += "store T1 0(T1) \n";
        return "//start AssNode\n"
                + exp.codeGeneration()
                + "move AL T1 \n" // sposta AL nel registro T1
                + getAR  // risalgo la catena statica posizionando T1 nel contesto corretto
                + "subi T1 " + stentry.getoffset() + "\n" // sottrae l'offset dell'id da T1 per ottenere l'indirizzo di memoria corrispondente
                + "load A0 0(T1) \n"// carica il valore attuale dell'id nella posizione di memoria ottenuta da T1, il valore viene caricato in A0
                + "//and AssNode\n";
    }

    @Override
    public String toPrint(String s) {
        return s + "Asg: Var " + id + " = " + exp.toPrint(s) + "\t";
    }
}
