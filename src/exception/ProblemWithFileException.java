package exception;

public class ProblemWithFileException extends RuntimeException {
    public ProblemWithFileException(String message){
        super(message);
    }
}
