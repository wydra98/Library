package exception;

public class WrongLengthException extends RuntimeException{
    public WrongLengthException(String message){
        super(message);
    }
}
