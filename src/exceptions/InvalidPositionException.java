package exceptions;

public class InvalidPositionException extends Exception {
    public InvalidPositionException() {}

    public InvalidPositionException(String message) {
        super(message);
    }
}