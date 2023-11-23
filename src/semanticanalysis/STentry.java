package semanticanalysis;

import ast.Type;

/* STentry rappresenta una voce nella tabella dei simboli durante l'analisi semantica */
public class STentry {

    private Type type; //tipo associato all'id (=variabile)
    private int offset; //offset associato all'id. offset = posizione dell'identificatore nella memoria
    private int nesting; //= a quale livello di scope l'id appartiene
    private String label; //etichetta associata all'id

    private boolean initialized = false;

    //costruttore per le variabili
    public STentry(Type type, int offset, int nesting, boolean initialized) {
        this.type = type;
        this.offset = offset;
        this.nesting = nesting;
        this.initialized = initialized;
    }

    //costruttore per la funzione perchè le label le hanno solo loro
    public STentry(Type type, int offset, int nesting, String label) {
        this.type = type;
        this.offset = offset;
        this.nesting = nesting;
        this.label = label;
    }

    public Type gettype() {
        return type;
    }

    public int getoffset() {
        return offset;
    }

    public int getnesting() {
        return nesting;
    }

    public String getlabel() {
        return label;
    }

    public Boolean getInitialized() {
        return initialized;
    }

    public void setInitialized(Boolean val) {
        initialized = val;
    }

}
