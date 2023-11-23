package mainPackage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import ast.*;
import evaluator.ExecuteVM;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.SimpLanPlusLexer;
import parser.SimpLanPlusParser;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;
import parser.SVMLexer;
import parser.SVMParser;

public class Main {
    public static void main(String[] args) throws Exception {

        String fileName = "prova.simplan";
        String input = new String(Files.readAllBytes(Paths.get(fileName).toAbsolutePath()));

        CharStream stream = CharStreams.fromString(input);
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpLanPlusParser parser = new SimpLanPlusParser(tokens);


        ArrayList <String> errorsList = new ArrayList<>();
        ErrorListener errorListener = new ErrorListener(errorsList);

        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();

        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        Node ast = visitor.visit(parser.prog()); //generazione AST

        //Controllo il numero totale di errori che ha raccolto il listener, faccio il controllo sull'arraylist e stampo il numero totale di errori e gli errori sul file txt
        int numErrors = errorsList.size();

        //CONTROLLO ERRORI LESSICALI NO SINTATTICI

        if (numErrors > 0){
            System.out.println("The program was not in the right format. Exiting the compilation process now");
            System.out.println(" ** Analisi lessicale fallita ** ");
            try {
                PrintWriter writer = new PrintWriter("errorilessicali.txt", "UTF-8");
                writer.write("Numero totale di errori: " + numErrors + "\n");
                writer.println(" ** Analisi lessicale fallita ** ");

                for (String error : errorsList) {
                    writer.println("ERRORE lessicale " + error);
                    System.out.println("ERRORE lessicale " + error);
                }
                writer.close();
                System.out.println("\n" + "Gli errori e il numero totale di errori sono stati scritti su errorilessicali.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.exit(1); //chiudi il programma alla fine
        } else {
            //ERRORI SEMANTICI
            SymbolTable ST = new SymbolTable();
            ArrayList<SemanticError> errors = ast.checkSemantics(ST, 0);
            if(errors.size()>0){
                System.out.println(" ** Analisi semantica fallita! ** ");
                System.out.println("You had: " + errors.size() + " errors:");
                for(SemanticError e : errors)
                    System.out.println("\t" + e);

            } else {
                System.out.println(" ** Analisi semantica OK! **");

                System.out.println("Visualizing AST...");
                System.out.println(ast.toPrint(""));

                Node type = ast.typeCheck(); //type-checking bottom-up
                if (type instanceof ErrorType) {
                    System.out.println(" ** Type checking is WRONG! **");
                } else {
                    System.out.println(" ** Type checking OK! ** ");
                    System.out.println(type.toPrint("Type of the program is: "));
                }

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

                SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
                visitorSVM.visit(parserASM.assembly());

                //System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
                //if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) System.exit(1);

                System.out.println("Starting Virtual Machine...");
                ExecuteVM vm = new ExecuteVM(visitorSVM.code, type instanceof VoidType);
                vm.cpu();


            }
        }


    }
}
