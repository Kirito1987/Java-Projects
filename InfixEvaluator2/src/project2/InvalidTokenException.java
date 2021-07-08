package project2;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String token) {
        super(token);
    }
}
