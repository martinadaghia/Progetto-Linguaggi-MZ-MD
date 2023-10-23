package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class BodyNode implements Node {
    private ArrayList<Node> declarations;
    private ArrayList<Node> statements;
    private Node expression;

    public BodyNode(ArrayList<Node> _declarations, ArrayList<Node> _statements, Node _expression) {
        declarations = _declarations;
        statements = _statements;
        expression = _expression;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable _ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<>();

        if (declarations != null) {
            for (Node dec : declarations) {
                errors.addAll(dec.checkSemantics(_ST, _nesting));
            }
        }

        if (statements != null) {
            for (Node stm : statements) {
                errors.addAll(stm.checkSemantics(_ST, _nesting));
            }
        }

        if (expression != null) { //può esserci o no perchè nella grammatica c'è il ?
            errors.addAll(expression.checkSemantics(_ST, _nesting));
        }

        return errors;
    }

    @Override
    public Type typeCheck() {

        if (declarations != null) {
            for (Node dec : declarations) {
                dec.typeCheck();
            }
        }

        if (statements != null) {
            for (Node sta : statements) {
                sta.typeCheck();
            }
        }

        if (expression != null) {
            return expression.typeCheck();
        }

        return new VoidType();
    }

    @Override
    public String codeGeneration() {

        String valutodec = "";
        String valutostm = "";
        String valutoexp = "";

        if (declarations != null) {
            for(Node decc: declarations) {
                valutodec += decc.codeGeneration();
            }
        }

        if (statements != null) {
            for(Node stmm: statements) {
                valutostm += stmm.codeGeneration();
            }
        }

        if (expression != null) {
            valutoexp += expression.codeGeneration();
        }


        return "storei sp maxvalue-n"
        + "store fp maxvalue"
        + valutodec
        + valutostm
        + valutoexp;

    }

    @Override
    public String toPrint(String s) {

        String decstring = "";
        String stmstring = "";
        String expstring = "";

        if (declarations != null) {
            for(Node decS: declarations) {
                decstring += decS.toPrint(s+"  ");
            }
        }

        if (statements != null) {
            for(Node stmS: statements) {
                stmstring += stmS.toPrint(s+"  ");
            }
        }

        if (expression != null) {
            expstring += expression.toPrint(s+"  ");
        }


        return s+"Body\n" + decstring + stmstring + expstring;
    }
}
