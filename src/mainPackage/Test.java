package mainPackage;
import java.io.*;
import java.util.ArrayList;

import ast.SimpLanPlusVisitorImpl;
import ast.SVMVisitorImpl;
import evaluator.ExecuteVM;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;
import parser.SVMLexer;
import parser.SVMParser;
import ast.ErrorType;
import ast.Node;

public class Test {
    public static void main(String[] args) throws Exception {

        String fileName = "prova.simplan";

        FileInputStream is = new FileInputStream(fileName);
        ANTLRInputStream input = new ANTLRInputStream(is);
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
        SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();


        ArrayList <String> Errors = new ArrayList<>();
        ErrorListener errorlis = new ErrorListener(Errors);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorlis);
        parser.removeErrorListeners();
        parser.addErrorListener(errorlis);

        Node ast = visitor.visit(parser.prog()); //generazione AST

        //Controllo il numero totale di errori che ha raccolto il listener, faccio il controllo sull'arraylist e stampo il numero totale di errori e gli errori sul file txt
        int numErrors = Errors.size();
        //System.out.println("prima dell'if " + numErrors);
        //CONTROLLO ERRORI LESSICALI NO SINTATTICI
        if (numErrors > 0){
            System.out.println("The program was not in the right format. Exiting the compilation process now");
            System.out.println("*** Analisi lessicale fallita ***");
            try {
                PrintWriter writer = new PrintWriter("errorilessicali.txt", "UTF-8");
                writer.write("Numero totale di errori: " + numErrors + "\n");
                writer.println("* Analisi lessicale fallita");

                for (String error : Errors) {
                    writer.println("ERRORE lessicale " + error);
                    System.out.println("Errore lessicale " + error);
                }
                writer.close();
                System.out.println("\n" + "Gli errori e il numero totale di errori sono stati scritti su errorilessicali.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //chiudi il programma alla fine
        } else {
            //ERRORI SEMANTICI
            SymbolTable ST = new SymbolTable();
            ArrayList<SemanticError> errors = ast.checkSemantics(ST, 0);
            if(errors.size()>0){
                System.out.println("You had: " + errors.size() + " errors:");
                for(SemanticError e : errors)
                    System.out.println("\t" + e);
            } else {
                System.out.println("Visualizing AST...");
                System.out.println(ast.toPrint(""));

                Node type = ast.typeCheck(); //type-checking bottom-up
                if (type instanceof ErrorType)
                    System.out.println("Type checking is WRONG!");
                else
                    System.out.println(type.toPrint("Type checking ok! Type of the program is: "));


                // CODE GENERATION  prova.SimpLan.asm
                String code=ast.codeGeneration();
                BufferedWriter out = new BufferedWriter(new FileWriter(fileName+".asm"));
                out.write(code);
                out.close();
                System.out.println("Code generated! Assembling and running generated code.");

                FileInputStream isASM = new FileInputStream(fileName+".asm");
                ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
                SVMLexer lexerASM = new SVMLexer(inputASM);
                CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
                SVMParser parserASM = new SVMParser(tokensASM);

                //parserASM.assembly();

                SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
                visitorSVM.visit(parserASM.assembly());

                //System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
                //if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) System.exit(1);

                System.out.println("Starting Virtual Machine...");
                ExecuteVM vm = new ExecuteVM(visitorSVM.code);
                vm.cpu();


            }
        }


    }
}
