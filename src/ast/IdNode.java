package ast;

import java.util.ArrayList;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class IdNode implements Node {
    private String id ;
    private STentry type ;
    private int nesting ;

    public IdNode (String id) {
        this.id = id ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        nesting = _nesting ;
        STentry st_type = ST.lookup(id) ;
        if (st_type == null) {
            errors.add(new SemanticError("Id " + id + " not declared"));
        } else {
            // Se è dichiarata nello scope corrente ma non è inizializzata ritono errore
            if (ST.top_lookup(id) && !st_type.getInitialized()) {

                // Verifica che la variabile sia dichiarata nell'ambiente corrente
                if (ST.lookup(id).getnesting() == nesting) {
                    // Aggiungi un errore se la variabile non è stata inizializzata
                    errors.add(new SemanticError("Var " + id + " has not been initialized"));
                } else {
                    // Verifica che la variabile sia dichiarata in un'altro scope ed è sempre non inizializzata
                    System.out.println("WARNING: var " + id + " (nestlvl: " + st_type.getnesting() + ") might not be initialised");
                }
            }
            /*Se è dichiarata in un altro scope e non è ancora stata inizializzata -> warning
            } else if(!ST.top_lookup(id) && !st_type.getInitialized()) {

                System.out.println("WARNING: var " + id + " (nestlvl: " + st_type.getnesting() + ") might not be initialised");
            }*/
            type = st_type ;
        }
        return errors;
    }

    public Type typeCheck () {
        if (type.gettype() instanceof ArrowType) { //
            System.out.println("Wrong usage of function identifier");
            return new ErrorType() ;
        } else return type.gettype() ;
    }

    public String codeGeneration() {
        String getAR="";
        for (int i=0; i < nesting - type.getnesting(); i++)
            getAR += "store T1 0(T1) \n";
        return "//start IdNode\n"
                + "move AL T1 \n"
                + getAR  //risalgo la catena statica
                + "subi T1 " + type.getoffset() +"\n" //metto offset sullo stack
                + "store A0 0(T1) \n"
                + "//end IdNode\n" ; //carico sullo stack il valore all'indirizzo ottenuto
    }

    public String toPrint(String s) {
        return s+"Id:" + id + " at nestlev " + type.getnesting() +"\n" ;
    }

}