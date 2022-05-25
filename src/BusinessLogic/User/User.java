package BusinessLogic.User;

import DataBase.DeleteFollower;
import DataBase.Follow;


import java.sql.SQLException;

public class User {
    private String username;
    private String password;
    private int number_of_followers;
    private int number_of_followings;

    public User(String username, String password)
            throws IllegalStateException {
        setUsername(username);
        setPassword(password);
        setNumberOfFollowings(0);
        setNumberOfFollowers(0);
    }

    public User(String username, String password, int number_of_followers, int number_of_followings)
            throws IllegalStateException {
        setUsername(username);
        setPassword(password);
        setNumberOfFollowers(number_of_followers);
        setNumberOfFollowings(number_of_followings);
    }

    private void setUsername(String username)
            throws IllegalStateException {
        UsernameValidation(username);
        this.username = username;
    }

    private void setPassword(String password)
            throws IllegalStateException {
        PasswordValidation(password);
        this.password = password;
    }

    private void setNumberOfFollowers(int number_of_followers)
            throws IllegalStateException {
        if(number_of_followers < 0)
            throw new IllegalStateException("Number of followers can not be a negative integer");
        this.number_of_followers = number_of_followers;
    }

    private void setNumberOfFollowings(int number_of_followings)
            throws IllegalStateException{
        if(number_of_followings < 0)
            throw new IllegalStateException("Number of followings can not be a negative integer");
        this.number_of_followings = number_of_followings;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getNumberOfFollowers() { return number_of_followers; }

    public int getNumberOfFollowings() { return number_of_followings; }

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

    // if username is valid nothing happens otherwise throws exception
    public static void UsernameValidation(String username)
            throws IllegalStateException {
        if(username.length() < 3 || username.length() > 20)
            throw new IllegalStateException("username should should have at " +
                    "least 3 character and at most 20 character");
    }

    // if password is valid nothing happens otherwise throws exception
    public static void PasswordValidation(String password)
            throws IllegalStateException {
        if(password.length() < 6 || password.length() > 20)
            throw new IllegalStateException("username should should have at " +
                    "least 6 character and at most 20 character");
        if(password.matches("[a-z]+") || password.matches("[A-Z]+"))
            throw new IllegalStateException("password must not have only lowercase " +
                    "or only uppercase characters");
        if(!password.matches("[a-zA-Z0-9@#$*./+\\\\-]+"))
            throw new IllegalStateException("not allowed character has been used");
    }

}
