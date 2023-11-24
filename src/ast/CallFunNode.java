package ast;

import java.util.ArrayList;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class CallFunNode implements Node {
    private String id;
    private STentry entry;
    private ArrayList<Node> parameters; //della funzione
    private int nesting;

    public CallFunNode(String id, ArrayList<Node> parameters) {
        this.id = id;
        this.parameters = parameters;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        this.nesting = nesting;
        STentry tmp = ST.lookup(id);
        if (tmp != null) {
            entry = tmp;
            for (Node par : parameters)
                errors.addAll(par.checkSemantics(ST, nesting));
        } else {
            errors.add(new SemanticError("Id " + id + " not declared"));
        }
        return errors;
    }

    public Type typeCheck() {
        Type _type = entry.gettype();

        if (_type instanceof ArrowType) {
            ArrayList<Type> _partype = ((ArrowType) _type).get_inputtype(); // lista dei tipi di input

            if (_partype.size() != parameters.size()) { // se il num di parametri passati non coincide con quelli attesi
                System.out.println("Wrong number of parameters in the invocation of " + id);
                return new ErrorType();

            } else { // verifichiamo i tipi dei singoli parametri
                boolean verificaTipo = true;
                for (int i = 0; i < parameters.size(); i++) {
                    Type par_i = (parameters.get(i)).typeCheck();

                    // se il tipo del parametro attuale non coincide con quello atteso
                    if (!(par_i.getClass().equals(_partype.get(i).getClass()))) {
                        System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + id);
                        verificaTipo = false;
                    }
                }

                // tutti i tipi sono corretti --> restituisci il tipo di output della funzione
                if (verificaTipo) return ((ArrowType) _type).get_outputtype();
                else return new ErrorType();
            }
        } else {
            // se la funzione chiamata non è di tipo ArrowType --> errore
            System.out.println("Invocation of a non-function " + id);
            return new ErrorType();
        }
    }

    public String codeGeneration() {
        String parCode = "";
        for (int i = 0; i < parameters.size(); i = i + 1)
            parCode += parameters.get(i).codeGeneration() + "pushr A0\n";

        // per risalire la catena statica
        String getAR = "";
        for (int i = 0; i < nesting - entry.getnesting(); i++)
            getAR += "store T1 0(T1) \n";
        // formato AR: control_link + access link + parameters + indirizzo di ritorno + dich_locali

        return "//start CallFunNode\n"
                + "pushr FP \n" // carico il frame pointer
                + "move SP FP \n" // inizializzo FP
                + "addi FP 1 \n" // salvo in FP il puntatore all'indirizzo del frame pointer caricato
                + "move AL T1\n" // risalgo la catena statica
                + getAR // carico l'access link statico
                + "pushr T1 \n" // salvo sulla pila l'access link statico
                + parCode // calcolo i parametri attuali con l'access link del chiamante
                + "move FP AL \n" // imposto il FP al nuovo access link
                + "subi AL 1 \n" // imposto l'access link al livello corretto
                + "jsub " + entry.getlabel() + "\n" // chiamata alla funzione
                + "//end CallFunNode\n";
    }

    public String toPrint(String s) {  //
        String parlstr = "";
        for (Node par : parameters)
            parlstr += par.toPrint(s + "  ");
        return s + "Call:" + id + " at nestlev " + nesting + "\n" + entry.gettype().toPrint(s + "  ") + parlstr;
    }
}