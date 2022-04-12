package DataBase;

import User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Signup {
    public static void SignUserUp(User user) throws SQLException {
        if(user == null)
            throw new NullPointerException();


        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        // check if userName exists
        ResultSet rs = statement.executeQuery("SELECT TOP 1 FROM USERS WHERE UserName = '" +
                user.getUsername() + "';");
        if(rs.next())
            throw new SQLException("the user name is token");
        rs.close();

        statement.executeUpdate("INSERT INTO USERS (UserName, Password, FirstName, LastName," +
                "NumberOfFollowers, NumberOfFollowing) VALUES ('" +
                user.getUsername() + "', '" + user.getPassword() + "', '" +
                user.getFirstname() + "', '" + user.getLastname() + "', 0, 0);");
        statement.close();
    }
}
