package util;

public class SemanticError {
    public final String msg;
    // Custom utility class that is used in the Semantic Analysis Phase
    public SemanticError(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {

        return msg;
    }
}
