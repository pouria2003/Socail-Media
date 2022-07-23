package DataBase;

import BusinessLogic.Post.Post;

import java.sql.SQLException;
import java.sql.Statement;

public class AddPost {
    public static void addPost(Post post) throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        statement.executeUpdate("INSERT INTO Posts (PostContent, PostId) VALUES " +
                "('" + post.getContent() + "', '" + post.getId() + "');");
    }

}
