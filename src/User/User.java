package User;

import DataBase.DeleteFollower;
import DataBase.Follow;
import Person.Person;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class User extends Person{
    protected String username;
    protected String password;
    protected int number_of_followers;
    protected int number_of_followings;

    public User(String username, String password)
            throws IllegalStateException, IllegalArgumentException{
        setUsername(username);
        setPassword(password);
    }

    public User(String username, String password, String firstname, String lastname)
            throws IllegalStateException, IllegalArgumentException {
        super(firstname, lastname);
        setUsername(username);
        setPassword(password);
    }

    public User(String username, String password, String firstname, String lastname,
                int number_of_followers, int number_of_followings)
            throws IllegalStateException, IllegalArgumentException {
        super(firstname, lastname);
        setUsername(username);
        setPassword(password);
        this.number_of_followers = number_of_followers;
        this.number_of_followings = number_of_followings;
    }

    protected void setUsername(@NotNull String username)
            throws IllegalStateException, IllegalArgumentException {

        if(username.length() < 3 || username.length() > 20)
            throw new IllegalStateException("username should should have at " +
                    "least 3 character and at most 20 character");
        this.username = username;
    }

    protected void setPassword(@NotNull String password)
            throws IllegalStateException, IllegalArgumentException {

        if(password.length() < 6 || password.length() > 20)
            throw new IllegalStateException("username should should have at " +
                    "least 6 character and at most 20 character");
        if(password.matches("[a-z]+") || password.matches("[A-Z]+"))
            throw new IllegalStateException("password must not have only lowercase " +
                    "or only uppercase character");
        if(!password.matches("[a-zA-Z@#$*./+\\\\-]+"))
            throw new IllegalStateException("not allowed character has been used");
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static void registerUser(String username, String password) {

        try {
            User new_user = new User(username, password);
        } catch (Exception e) {
            // do something
        }
    }

    public void Follow(String following_userName) {
        //if(following_user == null)
            // do something with GUI

        try {
            Follow.follow(this.username, following_userName);
        } catch (SQLException e) {
            // do something with GUI
        }
    }

    public void deleteFollower(String follower_userName) {
        try {
            DeleteFollower.deleteFollower(this.username, follower_userName);
        } catch (SQLException e) {
            /// do something with GUI
        }
    }

}
