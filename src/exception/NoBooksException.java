package exception;


public class NoBooksException  extends RuntimeException{
    public NoBooksException(String message){
        super(message);
    }
}
