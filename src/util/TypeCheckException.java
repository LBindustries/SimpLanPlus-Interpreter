package util;

public class TypeCheckException extends Exception{
    // Custom exception that is used in TypeCheck Phase
    public TypeCheckException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
