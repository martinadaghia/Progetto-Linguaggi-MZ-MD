package semanticanalysis;

public class SemanticError {
    private String msg;

    public SemanticError(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return msg;
    }
}
