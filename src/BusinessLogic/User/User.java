package BusinessLogic.User;

import DataBase.DeleteFollower;
import DataBase.Follow;
import Exceptions.UserException.*;
import Exceptions.DataBaseExceptions.UsernameExistException;


import java.sql.SQLException;

public class User {
    private String username;
    private String password;
    private int number_of_followers;
    private int number_of_followings;

    public User(String username, String password)
            throws IllegalStateException, SQLException {
        setUsername(username);
        setPassword(password);
        setNumberOfFollowings(0);
        setNumberOfFollowers(0);
    }

    public User(String username, String password, int number_of_followers, int number_of_followings)
            throws IllegalStateException, SQLException {
        setUsername(username);
        setPassword(password);
        setNumberOfFollowers(number_of_followers);
        setNumberOfFollowings(number_of_followings);
    }

    private void setUsername(String username)
            throws IllegalStateException, SQLException {
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

    // if username is valid nothing happens otherwise throws exception
    public static void UsernameValidation(String username)
            throws IllegalStateException {
        if(username.length() < 3 || username.length() > 20)
            throw new UsernameLengthException();
        if(!username.matches("[a-zA-Z]+[a-zA-Z0-9_]*"))
            throw new UsernameFormatException();
    }

    // if password is valid nothing happens otherwise throws exception
    public static void PasswordValidation(String password)
            throws IllegalStateException {
        if(password.length() < 6 || password.length() > 20)
            throw new PasswordLengthException();
        if(password.matches("[a-z]+") || password.matches("[A-Z]+"))
            throw new WeakPasswordException();
        if(!password.matches("[a-zA-Z0-9@#*.]+"))
            throw new PasswordFormatException();
    }

}
