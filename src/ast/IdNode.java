package ast;

import java.util.ArrayList;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class IdNode implements Node {
    private String id ;
    private STentry type ;
    private int nesting ;

    public IdNode (String _id) {
        id = _id ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        nesting = _nesting ;

        STentry st_type = ST.lookup(id) ;
        if (st_type == null) {
            errors.add(new SemanticError("Id " + id + " not declared"));
        }else {
            // Se è dichiarata nello scope corrente ma non è inizializzata ritorno errore
            if(ST.top_lookup(id) && !st_type.getInitialized()) {
                errors.add(new SemanticError("Var " + id + " has not been initialised"));
            }
            // Se è dichiarata in un altro scope e non è ancora stata inizializzata -> warning
            else if(!ST.top_lookup(id) && !st_type.getInitialized()) {
                System.out.println("\u001B[33m" +"WARNING: var " + id + " (nestlvl: " + st_type.getnesting() + ") might not be initialised" +"\u001B[0m");
            }
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
        return
                "move AL T1 \n"
                + getAR  //risalgo la catena statica
                + "subi T1 " + type.getoffset() +"\n" //metto offset sullo stack
                + "store A0 0(T1) \n" ; //carico sullo stack il valore all'indirizzo ottenuto
    }

    public String toPrint(String s) {
        return s+"Id:" + id + " at nestlev " + type.getnesting() +"\n" ;
    }

}