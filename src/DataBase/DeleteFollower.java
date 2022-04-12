package DataBase;

import java.sql.SQLException;
import java.sql.Statement;

public class DeleteFollower {
    public static void deleteFollower(String following_userName, String follower_userName)
            throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        statement.executeUpdate("DELETE FROM Follows WHERE FollowingUserName = '" +
                following_userName + "' AND FollowerUserName = '" + follower_userName + "';");
    }
}
