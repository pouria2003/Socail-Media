package DataBase;

import User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadUser {
    public static User readUser(String userName) throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT TOP 1 FROM Users WHERE UserName = '" +
                userName +"';");
        // check if user exists
        if(!resultSet.next())
            throw new SQLException("User does not exists");
        resultSet.close();
        statement.close();
        return new User(resultSet.getString("UserName"), resultSet.getString("Password"),
                resultSet.getString("FirstName"), resultSet.getString("LastName"),
                resultSet.getInt("NumberOfFollowers"), resultSet.getInt("NumberOfFollowing"));
    }
}
