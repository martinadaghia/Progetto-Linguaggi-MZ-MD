package ast;

import evaluator.SimpLanlib;
import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;

public class FunNode implements Node {
    private String id;
    private Type returntype;
    private ArrayList<ParamNode> parlist;
    private Node body;
    private ArrowType type;
    private int nesting;
    private String flabel;

    public FunNode(String id, Type type, ArrayList<ParamNode> parlist, Node body) {
        this.id = id;
        this.returntype = type;
        this.parlist = parlist;
        this.body = body;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        this.nesting = nesting;

        // salva la vecchia ST per ripristinarla dopo la verifica del corpo
        SymbolTable oldST = new SymbolTable();
        oldST.setSymbol_table(ST.getSymbol_table());
        oldST.setOffset(ST.getOffset());

        if (ST.lookup(id) != null) {
            errors.add(new SemanticError("Identifier " + id + " already declared"));
        } else {
            // crea un nuovo ambiente nella tabella dei simboli per la funzione
            HashMap<String, STentry> HM = new HashMap<String, STentry>();
            ArrayList<Type> partypes = new ArrayList<Type>();

            ST.add(HM);

            // controlla i parametri della funzione
            for (ParamNode arg : parlist) {
                partypes.add(arg.getType());
                // controlla se il parametro è già dichiarato
                if (ST.top_lookup(arg.getId())) {
                    errors.add(new SemanticError("Parameter id " + arg.getId() + " already declared"));
                } else {
                    // inserisce il parametro nella ST e lo assume già inizializzato
                    ST.insert(arg.getId(), arg.getType(), nesting + 1, "");
                    STentry a = ST.lookup(arg.getId());
                    a.setInitialized(true);
                }
            }

            type = new ArrowType(partypes, returntype);// crea il tipo della funzione
            flabel = SimpLanlib.freshFunLabel(); // nuova etichetta per la funzione
            ST.insert(id, type, nesting, flabel);

            // controlla il corpo della funzione
            if (body != null) {
                errors.addAll(body.checkSemantics(ST, nesting + 1));
            }

            ST.remove(); // rimuove l'ambiente della funzione dalla ST
            ST.restore(oldST.getSymbol_table(), oldST.getOffset()); // ripristina la vecchia ST
            ST.insert(id, type, nesting, flabel); // reinserisce la funzione nella ST
        }

        return errors; // lista errori semantici
    }


    public Type typeCheck() {

        if (body == null && returntype.getClass().equals(ast.VoidType.class)) {
            return new VoidType();
        } else if (body != null && body.typeCheck().getClass().equals(returntype.getClass())) {
            return returntype;
        } else if (body != null && body.typeCheck() instanceof ErrorType) {
            return new ErrorType();
        } else {
            System.out.println("Type Error: Function return type doesn't match statement/expression type");
            return new ErrorType();
        }

    }

    public String codeGeneration() {
        String bodyCode = "";
        if (body != null) {
            bodyCode = body.codeGeneration();
        }
        // aggiunge il codice bytecode della funzione alla libreria di codice
        SimpLanlib.putCode(
                flabel + ":\n"
                        + "pushr RA \n" // salva il registro RA nello stack
                        + bodyCode // codice del corpo della funzione
                        + "popr RA \n" // ripristina il registro RA dallo stack
                        + "addi SP " + parlist.size() + "\n"// aggiorna lo stack pointer (SP) aggiungendo la dimensione dei parametri
                        + "pop \n" // toglie l'indirizzo di ritorno dallo stack
                        + "store FP 0(FP) \n" // salva il frame pointer (FP) nel suo indirizzo
                        + "move FP AL \n" // muove l'access link (AL) al frame pointer (FP)
                        + "subi AL 1 \n" // sottrae 1 da AL (Dimensione del frame pointer)
                        + "pop \n" // toglie il valore di AL dallo stack
                        + "rsub RA \n" // ritorna al chiamante
        );

        return "//start FunNode\n" + "push " + flabel + "\n" + "//end FunNode\n";
    }


    public String toPrint(String s) {
        StringBuilder parlstr = new StringBuilder();
        for (Node par : parlist) {
            parlstr.append(par.toPrint(s));
        }
        if (body != null) {
            return s + "Fun:" + id + "\n"
                    + parlstr
                    + "\n"
                    + body.toPrint(s + "  ");
        } else {
            return s + "Fun:" + id + "\n"
                    + parlstr
                    + "\n"
                    + " (no body) ";
        }
    }

}