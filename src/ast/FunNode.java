package ast;

import evaluator.SimpLanlib;
import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;

public class FunNode implements Node {
    private String id;
    private Type returntype ;
    private ArrayList<ParamNode> parlist ;
    private Node body ;
    private ArrowType type ;
    private int nesting ;
    private String flabel ;

    public FunNode (String id, Type type, ArrayList<ParamNode> parlist, Node body) {
        this.id = id ;
        this.returntype = type;
        this.parlist = parlist ;
        this.body = body ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int nesting) {

        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        this.nesting = nesting ;

        // Salviamo la vecchia ST da restorare dopo aver controllato il body
        SymbolTable oldST = new SymbolTable();
        oldST.setSymbol_table(ST.getSymbol_table());
        oldST.setOffset(ST.getOffset());

        if (ST.lookup(id) != null)
            errors.add(new SemanticError("Identifier " + id + " already declared"));
        else {
            HashMap<String,STentry> HM = new HashMap<String,STentry>() ;
            ArrayList<Type> partypes = new ArrayList<Type>() ;

            ST.add(HM);

            for (ParamNode arg : parlist){
                partypes.add(arg.getType());
                if (ST.top_lookup(arg.getId()))
                    errors.add(new SemanticError("Parameter id " + arg.getId() + " already declared")) ;
                else {
                    ST.insert(arg.getId(), arg.getType(), nesting+1, "") ;
                    // assumiamo che i parametri siano gia' inizializzati
                    STentry a = ST.lookup(arg.getId());
                    a.setInitialized(true);
                }
            }

            type = new ArrowType(partypes, returntype) ;

            // aggiungiamo la funzione anche prima di controllare il body per permettere la ricorsione
            flabel = SimpLanlib.freshFunLabel() ;
            ST.insert(id, type, nesting, flabel) ;

            if(body != null) {
                errors.addAll(body.checkSemantics(ST, nesting+1));
            }

            ST.remove();

            ST.restore(oldST.getSymbol_table(), oldST.getOffset());

            ST.insert(id, type, nesting, flabel) ;
        }
        return errors ;
    }

    public Type typeCheck () {

        if(body == null && returntype.getClass().equals(ast.VoidType.class)) {
            return new VoidType() ;
        } else if(body != null && body.typeCheck().getClass().equals(returntype.getClass())) {
            return returntype ;
        } else if(body != null && body.typeCheck() instanceof ErrorType) {
            return new ErrorType() ;
        } else {
            System.out.println("Type Error: Function return type doesn't match statement/expression type") ;
            return new ErrorType();
        }

    }

    public String codeGeneration() {
        String bodyCode = "";
        if(body != null){
            bodyCode = body.codeGeneration();
        }
        SimpLanlib.putCode(
                flabel + ":\n"
                        + "pushr RA \n"
                        + bodyCode
                        + "popr RA \n"
                        + "addi SP " + 	parlist.size() + "\n"
                        + "pop \n"
                        + "store FP 0(FP) \n"
                        + "move FP AL \n"
                        + "subi AL 1 \n"
                        + "pop \n"
                        + "rsub RA \n"
        );
        return  "//start FunNode\n" + "push "+ flabel +"\n" + "//end FunNode\n";
    }

    public String toPrint(String s) {
        StringBuilder parlstr = new StringBuilder();
        for (Node par:parlist){
            parlstr.append(par.toPrint(s));
        }
        if(body != null) {
            return s+ "Fun:" + id +"\n"
                    + parlstr
                    + "\n"
                    + body.toPrint(s+"  ") ;
        } else {
            return s+ "Fun:" + id +"\n"
                    + parlstr
                    + "\n"
                    + " (no body) " ;
        }
    }

}