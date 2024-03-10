package main03;

public class NoUserException extends RuntimeException {
    public NoUserException() {
        super("No User Found!");
    }
}
