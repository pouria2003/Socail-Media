package Exceptions.UserException;

public class WeakPasswordException extends IllegalStateException {
    public WeakPasswordException() { super(); }
    public WeakPasswordException(String message) { super(message); }
}
