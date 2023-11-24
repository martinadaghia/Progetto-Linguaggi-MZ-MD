package evaluator;

public class SimpLanlib {
    private static int labCount = 0;
    private static int funLabCount = 0;
    private static String funCode = "";

    public static String freshLabel() {
        return "label" + (labCount++); // nuova etichetta unica per i salti nel codice
    }

    public static String freshFunLabel() {
        return "function" + (funLabCount++); // nuova etichetta unica per le funzioni nel codice
    }

    public static void putCode(String c) {
        funCode += "\n" + c; // aggiunge una linea vuota di separazione prima di funzione
    }

    public static String getCode() {
        return funCode; // codice generato fino a quel momento
    }

    public static String generateLabel() {
        return "label" + (labCount++);
    }
}

