package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Follow {
    public static void follow(String follower_userName, String following_userName)
            throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        // check if user exists
        ResultSet rs = statement.executeQuery("SELECT TOP 1 FROM Users WHERE UserName = '" +
                follower_userName + "';");
        if(!rs.next())
            throw new SQLException("UserName does not exists");

        // check if not followed already
        rs = statement.executeQuery("SELECT TOP 1 FROM Follows WHERE FollowerUserName = '"+
              follower_userName + "' AND FollowingUserName = '" + following_userName + "';");
        if(rs.next())
            throw new SQLException("you are following user already");

        rs.close();

        statement.executeUpdate("INSERT INTO Follows (FollowerUserName, FollowingUserName)" +
                 " VALUES ('" + follower_userName + "', '" + following_userName + "';");

        statement.close();

    }
}
