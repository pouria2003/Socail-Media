package DataBase;

import Exceptions.DataBaseExceptions.UsernameNotExistException;
import Exceptions.DataBaseExceptions.WrongPasswordException;
import User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadUser {
    public static User readUser(String userName, String password) throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE UserName = '" +
                userName +"';");
        // check if user exists
        if(!resultSet.next()) {
            resultSet.close();
            statement.close();
            throw new UsernameNotExistException("User does not exists");
        }
        if(!resultSet.getString("Password").equals(password)){
            resultSet.close();
            statement.close();
            throw new WrongPasswordException("password is not correct");
        }
        User user = new User(resultSet.getString("UserName"), resultSet.getString("Password"),
                resultSet.getString("FirstName"), resultSet.getString("LastName"),
                resultSet.getInt("NumberOfFollowers"), resultSet.getInt("NumberOfFollowing"));
        resultSet.close();
        statement.close();
        return user;
    }
}
