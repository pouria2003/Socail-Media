package Exceptions.UserException;

public class UsernameLengthException extends IllegalStateException {
    public UsernameLengthException() { super(); }
    public UsernameLengthException(String message) { super(message); }
}
