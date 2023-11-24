package semanticanalysis;

import ast.BoolType;
import ast.IntType;
import ast.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    private ArrayList<HashMap<String, STentry>> symbol_table; // mappa (HashMap) che associa gli id alle relative voci nella ST(oggetti STentry)
    private ArrayList<Integer> offset; // posizione dell'id nella memoria

    public SymbolTable() {
        symbol_table = new ArrayList<HashMap<String, STentry>>();
        offset = new ArrayList<Integer>();
    }

    // restituisce il livello di annidamento corrente
    public Integer nesting() {
        return symbol_table.size() - 1;
    }

    // cerca un id nella ST
    public STentry lookup(String id) {
        int n = symbol_table.size() - 1; // livello più esterno della tabella
        boolean found = false;
        STentry T = null;
        while ((n >= 0) && !found) { //scorre i livelli della tabella dal più esterno al più interno
            HashMap<String, STentry> H = symbol_table.get(n); // rappresenta l'ambiente corrente
            T = H.get(id); // cerco l'id nell'ambiente H
            if (T != null)
                found = true; // trovato
            else
                n = n - 1; // cerchiamo l'id nel livello più interno
        }
        return T; // contiene tutte le info di id (tipo, offset, nesting, label)
    }

    // verifica se id è presente nell'ambiente corrente della tabella
    public boolean top_lookup(String id) {
        int n = symbol_table.size() - 1;
        STentry T = null;
        HashMap<String, STentry> H = symbol_table.get(n);
        T = H.get(id); //assegno a T le info di id se è stato trovato
        return (T != null); //true o false
    }

    // cerca un id specificato nella tabella e restituisce il nesting level in cui viene trovato
    public Integer nslookup(String id) {
        int n = symbol_table.size() - 1;
        boolean found = false;
        while ((n >= 0) && !found) {
            HashMap<String, STentry> H = symbol_table.get(n);
            if (H.get(id) != null)
                found = true;
            else
                n = n - 1;
        }
        return n; // = livello in cui viene trovato l'id, se l'id non viene trovato restituisce -1
    }

    // aggiunge un nuovo ambiente alla tabella
    public void add(HashMap<String, STentry> H) {
        symbol_table.add(H);
        offset.add(1); // si inizia da 2 perchè prima ci sono FP e AL
    }

    // rimuove l'ultimo ambiente della tabella
    public void remove() {
        int x = symbol_table.size();
        symbol_table.remove(x - 1);
        offset.remove(x - 1);
    }

    // inserisce un nuovo id nella ST corrente
    public void insert(String id, Type type, int _nesting, String _label) {
        int n = symbol_table.size() - 1; // indice dell'ambiente corrente

        HashMap<String, STentry> H = symbol_table.get(n); // ambiente corrente dalla ST
        symbol_table.remove(n);

        int offs = offset.get(n); // offset corrente associato all'ambiente corrente
        offset.remove(n);

        STentry idtype = new STentry(type, offs, _nesting, _label); // nuovo oggetto STentry con le informazioni dell'identificatore
        H.put(id, idtype); // id inserito nell'ambiente corrente
        symbol_table.add(H); // ambiente aggiornato alla ST

        // incrementa l'offset in base al tipo dell'id
        if (type.getClass().equals((new BoolType()).getClass())) { // tipo bool
            offs = offs + 1; // incrementiamo sempre l'offset di 1, altrimenti avremmo bisogno di operazioni bytecode ad hoc
        } else if (type.getClass().equals((new IntType()).getClass())) { // tipo intero
            offs = offs + 1;
        } else { // tipo void
            offs = offs + 1;
        }

        offset.add(offs); // aggiunto nuovo offset alla lista
    }


    public void printST() {
        System.out.println("****** Symbol Table ******");
        for (int i = 0; i < symbol_table.size(); i++) {
            symbol_table.get(i).forEach((key, value) -> System.out.println(key + ": " + value.gettype().getClass() + ", " + value.getnesting()));
            if (i != symbol_table.size() - 1) System.out.println("-------");
        }
        System.out.println("************************** \n");
    }

    // GETTER
    public ArrayList<HashMap<String, STentry>> getSymbol_table() {
        return this.symbol_table;
    }

    public ArrayList<Integer> getOffset() {
        return this.offset;
    }

    // SETTER
    // se in input riceve un'ArrayList appartenente a un'altra ST, non referenziamo la ST precedente, ma ne creiamo una copia assestante
    public void setSymbol_table(ArrayList<HashMap<String, STentry>> newST) {
        // variabili temporanee per copiare gli elementi della nuova tabella
        String currString = "";
        STentry currSTentry;
        HashMap<String, STentry> currHM = new HashMap<String, STentry>();
        ArrayList<HashMap<String, STentry>> currAL = new ArrayList<HashMap<String, STentry>>();

        for (int i = 0; i < newST.size(); i++) {
            HashMap<String, STentry> newHM = new HashMap<String, STentry>(newST.get(i)); // copia dell'HashMap corrente della nuova ST
            for (HashMap.Entry<String, STentry> e : newHM.entrySet()) {
                currString = e.getKey();
                //  nuovo oggetto STentry con le info della voce corrente
                if (e.getValue().getlabel() != null) {
                    currSTentry = new STentry(e.getValue().gettype(), e.getValue().getoffset(), e.getValue().getnesting(), e.getValue().getlabel());
                } else {
                    currSTentry = new STentry(e.getValue().gettype(), e.getValue().getoffset(), e.getValue().getnesting(), e.getValue().getInitialized());
                }
                // imposta lo stato di inizializzazione e aggiunge l'oggetto STentry all'HashMap temporanea
                currSTentry.setInitialized(e.getValue().getInitialized());
                currHM.put(currString, currSTentry);
            }
            currAL.add(currHM); // aggiunge l'HashMap temporanea all'ArrayList delle HashMap temporanee
        }
        this.symbol_table = currAL; // imposta la tabella dei simboli alla copia creata
    }

    public void setOffset(ArrayList<Integer> newOffset) {
        this.offset = new ArrayList<Integer>(newOffset);
    }

    // setta la symbol_table e l'offset con i valori ricevuti in input
    public void restore(ArrayList<HashMap<String, STentry>> oldST, ArrayList<Integer> oldOff) {
        this.setSymbol_table(oldST);
        this.setOffset(oldOff);
    }

    public void union(SymbolTable ST1, SymbolTable ST2) {
        // cicliamo sulla prima ST
        for (HashMap<String, STentry> hm1 : ST1.symbol_table) {
            for (HashMap.Entry<String, STentry> e1 : hm1.entrySet()) {
                // cicliamo sulla seconda ST
                for (HashMap<String, STentry> hm2 : ST2.symbol_table) {
                    for (HashMap.Entry<String, STentry> e2 : hm2.entrySet()) {
                        // controlliamo se inizializzano la stessa variabile
                        if (e1.getKey().equals(e2.getKey())) {
                            // ottieni l'entry corrente nella tabella corrente
                            STentry currEntry = this.lookup(e1.getKey());
                            // se entrambe le tabelle inizializzano la variabile, la segna come inizializzata
                            if (currEntry != null && e1.getValue().getInitialized() && e2.getValue().getInitialized()) {
                                currEntry.setInitialized(true);
                            }
                        }
                    }
                }
            }
        }
    }

    // incrementa l'offset corrente nell'ambiente corrente della tabella
    public void increaseoffset() {
        int n = offset.size() - 1;
        int offs = offset.get(n);
        offset.remove(n);
        offs = offs + 1;
        offset.add(offs);
    }

    /* utilizzato per ottenere il tipo di un id dalla tabella
    public Type getType(String id, int nesting) {
        int n = nesting;
        boolean found = false; //id trovato oppure no
        while (n >= 0 && !found) {
            HashMap<String, STentry> H = symbol_table.get(n);
            STentry entry = H.get(id);
            if (entry != null) { //se entry non è null --> l'ho trovato e restituisco il tipo
                found = true;
                return entry.getType();
            }
            n--; //decremento e ripeto il while finchè non esaurisco tutti i livelli
        }

        // L'identificatore non è stato trovato nella tabella dei simboli
        // Gesto l'errore o restituisco un tipo di default
        System.out.println("Type Error: Variable " + id + " not found in symbol table");
        return new ErrorType(); //se non è stato trovato l'id ritorna un errore
    }
*/

}
