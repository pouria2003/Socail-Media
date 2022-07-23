package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Post {
    public static void addPost(BusinessLogic.Post.Post post, String username) throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        statement.executeUpdate("INSERT INTO Posts (PostContent, PostId) VALUES " +
                "('" + post.getContent() + "', '" + post.getId() + "');");

        ResultSet resultset = statement.getResultSet();

        resultset = statement.executeQuery("SELECT NumberOfPosts, LastPostId " +
                "FROM Users WHERE Username = '" + username + "';");

        resultset.next();

        System.out.println(resultset.getInt(1) + " " + resultset.getInt(2));
        statement.executeUpdate("UPDATE Users SET NumberOfPosts = "
                + (resultset.getInt("NumberOfPosts") + 1) + ", LastPostId = "
                + (resultset.getInt("LastPostId") + 1)
                + " WHERE Username = '" + username + "';");
    }

    public static ArrayList<BusinessLogic.Post.Post> PostsList(String username) throws SQLException {
        ArrayList<BusinessLogic.Post.Post> posts = new ArrayList<BusinessLogic.Post.Post>();

        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultset = statement.executeQuery("SELECT PostContent, PostId, Likes " +
                "FROM Posts WHERE PostId Like '" + username + "%';");

        while(resultset.next())
            posts.add(new BusinessLogic.Post.Post(resultset.getString(1),
                    resultset.getString(2), resultset.getInt(3)));

        resultset.close();
        statement.close();

        return posts;
    }

    public static BusinessLogic.Post.Post post(String post_id) throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultset = statement.executeQuery("SELECT PostContent, PostId, Likes " +
                "FROM Posts WHERE PostId = '" + post_id + "';");

        return new BusinessLogic.Post.Post(resultset.getString(1),
                resultset.getString(2), resultset.getInt(3));
    }
}
