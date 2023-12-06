package ast;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class IfStmNode implements Node {
    private Node guard;
    private ArrayList<Node> thenbranches;
    private ArrayList<Node> elsebranches;

    public IfStmNode(Node guard, ArrayList<Node> thenbranches, ArrayList<Node> elsebranches) {
        this.guard = guard;
        this.thenbranches = thenbranches;
        this.elsebranches = elsebranches;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(guard.checkSemantics(ST, _nesting));

        // salviamo la ST iniziale per restorarla dopo l'if
        SymbolTable oldST = new SymbolTable();
        oldST.setSymbol_table(ST.getSymbol_table());
        oldST.setOffset(ST.getOffset());
        // salviamo le ST generate nel then e nell'else per confrontarle in seguito
        SymbolTable thenST = new SymbolTable();
        SymbolTable elseST = new SymbolTable();

        if (thenbranches != null) {
            // visitiamo tutto il ramo then
            for (Node tb : thenbranches) {
                errors.addAll(tb.checkSemantics(ST, _nesting));
            }
            // salviamo la ST e l'offset aggiornati del then
            thenST.setSymbol_table(ST.getSymbol_table());
            thenST.setOffset(ST.getOffset());

            ST.restore(oldST.getSymbol_table(), oldST.getOffset());
        }

        if (elsebranches != null) {
            // visitiamo tutto il ramo else
            for (Node eb : elsebranches) {
                errors.addAll(eb.checkSemantics(ST, _nesting));
            }
            // Salviamo la ST aggiornata dell'else
            elseST.setSymbol_table(ST.getSymbol_table());
            elseST.setOffset(ST.getOffset());

            ST.restore(oldST.getSymbol_table(), oldST.getOffset());
        }

        // facciamo il caso pessimo tra le due ST
        ST.union(thenST, elseST);

        return errors;
    }

    public Type typeCheck() {
        if (guard.typeCheck() instanceof BoolType) {

            Type thenType = new Type();
            Type elseType = new Type();

            if (thenbranches != null) {
                for (Node tb : thenbranches) {
                    thenType = tb.typeCheck();
                    if (thenType instanceof ErrorType)
                        return new ErrorType();
                }
            }

            if (elsebranches != null) {
                for (Node eb : elsebranches) {
                    elseType = eb.typeCheck();
                    if (elseType instanceof ErrorType)
                        return new ErrorType();
                }
            }

            return new VoidType();

        } else {
            System.out.println("Type Error: non boolean condition in if");
            return new ErrorType();
        }
    }

    public String codeGeneration() {
        String lelse = SimpLanlib.freshLabel();
        String lend = SimpLanlib.freshLabel();
        StringBuilder thenB = new StringBuilder();
        StringBuilder elseB = new StringBuilder();

        for (Node tb : thenbranches)
            thenB.append(tb.codeGeneration());

        for (Node eb : elsebranches)
            elseB.append(eb.codeGeneration());

        return "//start IfStmNode\n"
                + guard.codeGeneration()
                + "storei T1 1 \n"
                + "beq A0 T1 " + lelse + "\n"
                + elseB
                + "b " + lend + "\n"
                + lelse + ":\n"
                + thenB
                + lend + ":\n"
                + "//end IfStmNode\n";
    }

    public String toPrint(String s) {
        String thenString = "  ";
        String elseString = "  ";
        if (thenbranches != null) {
            for (Node tb : thenbranches)
                thenString = tb.toPrint(s + "  ");
        }
        if (elsebranches != null) {
            for (Node eb : elsebranches)
                elseString = eb.toPrint(s + "  ");
        }
        return s + "If\n" + guard.toPrint(s + "  ") + thenString + elseString;
    }

}