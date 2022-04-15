package Exceptions.DataBaseExceptions;

import java.sql.SQLException;

public class UsernameExistException extends SQLException {
    public UsernameExistException(String message){
        super(message);
    }
}
