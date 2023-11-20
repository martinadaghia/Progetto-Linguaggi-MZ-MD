package semanticanalysis;

import ast.BoolType;
import ast.IntType;
import ast.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    private ArrayList<HashMap<String,STentry>>  symbol_table ; // elemento = uno scope (ambiente) e contiene una mappa che associa gli identificatori alle relative STentry (voci nella tabella dei simboli).
    private ArrayList<Integer> offset; //offset = posizione dell'id nella memoria

    public SymbolTable() {
        symbol_table = new ArrayList<HashMap<String,STentry>>() ;
        offset = new ArrayList<Integer>() ;
    }

    /*nesting() restituisce il livello di imbricazione corrente*/
    public Integer nesting() {
        return symbol_table.size() -1 ; //-1 perchè gli indici dell'ArrayList partono da 0 --> serve per ottenere il num corretto di livelli
    }

    /*
     * lookup(String id)  mi cerca un id nella tabella dei simboli
     * Prende come parametro una stringa id --> rappresenta l'identificatore da cercare
     * Tabella dei simboli rappresentata come una lista di HashMap
     * Ogni HashMap rappresenta uno scope (=ambiente)
     * Ogni voce della tabella è rappresentata da una STentry (Symbol Table Entry) che contiene info (tipo, offset, nesting, label)
     */
    public STentry lookup(String id) {
        int n = symbol_table.size() - 1 ; //livello più esterno della tabella (indice dell'ultimo elemento)
        boolean found = false ; //tiene traccia se l'id è stato trovato nella tabella o no
        STentry T = null ; //conterrà il risultato della ricerca dell'id
        while ((n >= 0) && !found) { //scorre i livelli della tabella dal più esterno al più interno
            HashMap<String,STentry> H = symbol_table.get(n) ; //rappresenta l'ambiente (scope) corrente
            T = H.get(id) ; //cerco id all'interno dell'ambiente corrente e il risultato assegnato a T
            if (T != null) found = true ; //se non è null vuol dire che ho trovato l'id
            else n = n-1 ; //se non è stato trovato l'id passiamo a cercarlo nel livello precedente (più interno)
        }
        return T ; //ritorno T che mi contiene tutte le info di id
    }


    /* nslookup(String id) cerca un id specificato nella tabella e restituisce il nesting level in cui viene trovato l'id*/
    public Integer nslookup(String id) {
        int n = symbol_table.size() - 1 ;
        boolean found = false ;
        while ((n >= 0) && !found) {
            HashMap<String,STentry> H = symbol_table.get(n) ; //rappresenta l'ambiente (scope) corrente
            if (H.get(id) != null) found = true ; //se id trovato e il suo valore è diverso da null --> l'id esiste nella tabella
            else n = n-1 ;
        }
        return n ; //torna il livello in cui viene trovato l'id. se l'id non viene trovato restituisce -1
    }

    /* aggiunge un nuovo ambiente alla tabella */
    public void add(HashMap<String,STentry> H) { //parametro che rappresenta un nuovo ambiente da aggiungere alla tabella
        symbol_table.add(H) ; //aggiunto alla tabellla
        offset.add(1) ;	// si inizia da 2 perche` prima ci sono FP e AL
        //offset associato al nuovo ambiente, utilizzato per tenere traccia della posizione delle variabili all'interno dell'ambiente
    }

    /* rimuove l'ultimo ambiente della tabella */
    public void remove() {
        int x = symbol_table.size() ;
        symbol_table.remove(x-1) ; //rimuove l'ultimo ambiente aggiunto
        offset.remove(x-1) ; //rimuovere l'ultimo elemento della lista = offset associato all'ultimo ambiente rimosso
    }

    /*verifica se id è presente nell'ambiente corrente della tabella*/
    public boolean top_lookup(String id) {
        int n = symbol_table.size() - 1 ; //ultimo ambiente aggiunto alla tabella (= ambiente corrente)
        STentry T = null ; //conterrà il risultato della ricerca dell'id
        HashMap<String,STentry> H = symbol_table.get(n) ; //rappresenta l'ambiente (scope) corrente
        T = H.get(id) ; //assegno a T le info di id se è stato trovato
        return (T != null) ; //se T != null --> id è presente nell'ambiente corrente della tabella dei simboli (true). Altrimenti non è presente (false)
    }

    /* inserisco nuovo id nella tabella dei simboli corrente*/
    public void insert(String id, Type type, int _nesting, String _label) {
        int n = symbol_table.size() - 1 ; //ambiente corrente (-1 perchè inizia da 0)
        HashMap<String,STentry> H = symbol_table.get(n) ;
        symbol_table.remove(n) ; //viene rimosso l'ambiente corrente dalla tabella
        int offs = offset.get(n) ; //prendo l'offset che si riferisce all'ambiente corrente e me lo salvo
        offset.remove(n) ; //rimuovo l'offset dalla sua lista
        STentry idtype = new STentry(type,offs,_nesting, _label) ; //creo nuovo oggetto STentry passandogli i parametri (=info del simbolo da inserire nella tabella)
        H.put(id,idtype) ; //inserisco idtype nell'ambiente corrente
        symbol_table.add(H) ; //inserisco l'ambiente aggiornato nella tabella
        if (type.getClass().equals((new BoolType()).getClass())) //controllo sul tipo type dell'id se è bool else se è int else void
            offs = offs + 1 ; // we always increment the offset by 1 otherwise we need ad-hoc
            // bytecode operations
        else if (type.getClass().equals((new IntType()).getClass())) //controllo il tipo se è int
            offs = offs + 1 ;
        else offs = offs + 1 ; //controllo il tipo se è void
        offset.add(offs) ; //aggiorno la lista degli offset
    }

    public void printST() {
        System.out.println("****** Symbol Table ******");
        for(int i = 0; i < symbol_table.size(); i++) {
            symbol_table.get(i).forEach((key, value) -> System.out.println(key + ": " + value.gettype().getClass() + ", " + value.getnesting()));
            if(i != symbol_table.size() - 1) System.out.println("-------");
        }
        System.out.println("************************** \n");
    }

    // GETTER
    public ArrayList<HashMap<String,STentry>> getSymbol_table() {
        return this.symbol_table;
    }
    public ArrayList<Integer> getOffset() {
        return this.offset;
    }

    // SETTER
	/*
		Se in input riceve un'ArrayList appartenente a un'altra ST non
		referenziamo la ST precedente, ma ne creiamo una copia assestante
	 */

    public void setSymbol_table(ArrayList<HashMap<String,STentry>> newST) {
        String currString = "";
        STentry currSTentry;
        HashMap<String, STentry> currHM = new HashMap<String, STentry>();
        ArrayList<HashMap<String,STentry>> currAL = new ArrayList<HashMap<String,STentry>>();

        for(int i = 0; i < newST.size(); i++) {
            HashMap<String, STentry> newHM = new HashMap<String, STentry>(newST.get(i));
            for (HashMap.Entry<String, STentry> e : newHM.entrySet()) {
                currString = e.getKey();
                if(e.getValue().getlabel() != null){
                    currSTentry = new STentry(e.getValue().gettype(), e.getValue().getoffset(), e.getValue().getnesting(), e.getValue().getlabel());
                }else{
                    currSTentry = new STentry(e.getValue().gettype(), e.getValue().getoffset(), e.getValue().getnesting(), e.getValue().getInitialized());
                }
                currSTentry.setInitialized(e.getValue().getInitialized());
                currHM.put(currString, currSTentry);

            }
            currAL.add(currHM);
        }

        this.symbol_table = currAL;
    }


    public void setOffset(ArrayList<Integer> newOffset) {
        this.offset = new ArrayList<Integer>(newOffset);
    }

    // Setta la symbol_table e l'offset ai valori ricevuti in input
    public void restore(ArrayList<HashMap<String,STentry>> oldST, ArrayList<Integer> oldOff){
        this.setSymbol_table(oldST);
        this.setOffset(oldOff);
    }

    public void union(SymbolTable ST1, SymbolTable ST2) {
        // Cicliamo sulla prima ST
        for(HashMap<String,STentry> hm1 : ST1.symbol_table) {
            for(HashMap.Entry<String,STentry> e1 : hm1.entrySet()) {
                // Cicliamo sulla seconda ST
                for(HashMap<String,STentry> hm2 : ST2.symbol_table) {
                    for(HashMap.Entry<String,STentry> e2 : hm2.entrySet()) {
                        // Controlliamo se inizializzano la stessa var
                        if(e1.getKey().equals(e2.getKey())) {
                            STentry currEntry = this.lookup(e1.getKey());
                            // Se tutti e due la inizializzano -> e' inizializzata altrimenti no
                            if(currEntry != null && e1.getValue().getInitialized() && e2.getValue().getInitialized()) {
                                currEntry.setInitialized(true);
                            }
                        }

                    }
                }

            }
        }
    }

    /* incrementa l'offset corrente nell'ambiente corrente della tabella */
    public void increaseoffset() {
        int n = offset.size() - 1 ;
        int offs = offset.get(n) ; //prendo l'offset corrente dall'ambiente corrente
        offset.remove(n) ; //rimuovo l'offset dalla lista
        offs = offs + 1 ; //incremento per ottenere il nuovo offset corrente
        offset.add(offs) ; //aggiungo il nuovo offset alla lista
    }
    /* NON SO PERCHè CI SIA NON MI PARE CHE IL PROF CE L'ABBIA */
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
