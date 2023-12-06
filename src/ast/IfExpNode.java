package ast;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class IfExpNode implements Node {

    private Node guard;
    private Node thenExp;
    private Node elseExp;
    private ArrayList<Node> thenStms;
    private ArrayList<Node> elseStms;

    public IfExpNode(Node condExp, ArrayList<Node> thenStms, Node thenExp, ArrayList<Node> elseStms, Node elseExp) {
        this.guard = condExp;
        this.thenExp = thenExp;
        this.elseExp = elseExp;
        this.thenStms = thenStms;
        this.elseStms = elseStms;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(guard.checkSemantics(ST, _nesting));

        // salviamo la ST iniziale per restorarla dopo l'if
        SymbolTable oldST = new SymbolTable();
        oldST.setSymbol_table(ST.getSymbol_table());
        oldST.setOffset(ST.getOffset());
        //  salviamo le ST generate nel then e nell'else per confrontarle in seguito
        SymbolTable thenST = new SymbolTable();
        SymbolTable elseST = new SymbolTable();

        if (!thenStms.isEmpty()) {
            for (Node then : thenStms) {
                errors.addAll(then.checkSemantics(ST, _nesting));
            }
        }
        // salviamo la ST aggiornata del then
        thenST.setSymbol_table(ST.getSymbol_table());
        thenST.setOffset(ST.getOffset());
        errors.addAll(thenExp.checkSemantics(ST, _nesting));

        ST.restore(oldST.getSymbol_table(), oldST.getOffset());

        if (!elseStms.isEmpty()) {
            for (Node i : elseStms) {
                errors.addAll(i.checkSemantics(ST, _nesting));
            }
        }
        // salviamo la ST aggiornata dell'else
        elseST.setSymbol_table(ST.getSymbol_table());
        elseST.setOffset(ST.getOffset());
        errors.addAll(elseExp.checkSemantics(ST, _nesting));

        ST.restore(oldST.getSymbol_table(), oldST.getOffset());

        //  facciamo il caso pessimo tra le due ST
        ST.union(thenST, elseST);

        return errors;
    }

    public Type typeCheck() {
        if (guard.typeCheck() instanceof BoolType) {

            for (Node a : thenStms) {
                if (a.typeCheck() instanceof ErrorType) {
                    return new ErrorType();
                }
            }

            for (Node b : elseStms) {
                if (b.typeCheck() instanceof ErrorType) {
                    return new ErrorType();
                }
            }

            Type thenType = thenExp.typeCheck();
            Type elseType = elseExp.typeCheck();

            if (thenType.getClass().equals(elseType.getClass())) {
                return thenType;
            } else {
                System.out.println("Type Error: incompatible types in then and else branches");
                return new ErrorType();
            }
        } else {
            System.out.println("Type Error: non boolean condition in if");
            return new ErrorType();
        }
    }

    public String codeGeneration() {
        String labelthen = SimpLanlib.freshLabel();
        String labelend = SimpLanlib.freshLabel();
        String thencode = "";
        String elsecode = "";

        for (Node thenC : thenStms) {
            thencode += thenC.codeGeneration();
        }

        for (Node elseC : elseStms) {
            elsecode += elseC.codeGeneration();
        }

        return "//start IfExpNode\n" +
                guard.codeGeneration() +
                "storei T1 1 \n" +
                "beq A0 T1 " + labelthen + "\n" +
                elsecode + elseExp.codeGeneration() +
                "b " + labelend + "\n" +
                labelthen + ":\n" +
                thencode + thenExp.codeGeneration() +
                labelend + ":\n"
                + "//end IfExpNode\n";
    }

    public String toPrint(String s) {
        StringBuilder thenStmString = new StringBuilder();
        StringBuilder elseStmString = new StringBuilder();

        for (Node a : thenStms)
            thenStmString.append(a.toPrint("  "));
        for (Node b : elseStms)
            elseStmString.append(b.toPrint("  "));

        String thenString = thenExp.toPrint(s + "  ");
        String elseString = elseExp.toPrint(s + "  ");
        return s + "If\n" + guard.toPrint(s + "  ") + thenStmString + thenString + elseStmString + elseString;
    }

}