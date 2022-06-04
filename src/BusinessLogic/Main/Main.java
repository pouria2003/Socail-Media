package BusinessLogic.Main;

import BusinessLogic.Event.Event;
import BusinessLogic.User.User;
import Exceptions.UserException.*;
import Exceptions.DataBaseExceptions.*;

import java.sql.SQLException;

//import static UI.SignUp.signUp;

interface Response {
    Event perform();
}

public class Main {

    public enum UserRequest {
        START_PAGE,
        SIGN_UP,
        SIGN_IN,
        SELF_PROFILE,
        SELF_FOLLOWERS,
        SELF_FOLLOWINGS,
        OTHER_PROFILE,
        SELF_POSTS,
        SELF_POST_LIKES,
        SELF_POST_COMMENT,
        OTHER_FOLLOWERS,
        OTHER_FOLLOWINGS,
        OTHER_POSTS,
        OTHER_POST_LIKES,
        OTHER_POST_COMMENT,
        EXIT
    }

    /// main() here function as an event handler
    public static void main(String[] args) {

        Response response = () -> UI.StartPage.startPage(UI.StartPage.StartPageSituation.EMPTY);
        Event event;
        User user;
        String username = null, password = null, rep_password = null;
        do {
            event = response.perform();
            switch (event.user_request) {

                case START_PAGE:
                    if (event.data[0].equals("1"))
                        response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.EMPTY);
                    else if (event.data[0].equals("2"))
                        response = () -> UI.SignIn.signIn(UI.SignIn.SignInSituation.EMPTY);
                    else if (event.data[0].equals("3"))
                        exitProgram(0);
                    else
                        response = () -> UI.StartPage.startPage(UI.StartPage.StartPageSituation.INVALID_OPTION);

                case SIGN_UP:
                    for(String str : event.data) {
                        if(str.startsWith("username="))
                            username = str.substring(9);
                        else if(str.startsWith("password="))
                            password = str.substring(9);
                        else if(str.startsWith("repeatedpassword="))
                            rep_password = str.substring(17);
                    }

                    if(!password.equals(rep_password)) {
                        response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.REPEATED_PASSWORD_NOT_MATCH);
                        break;
                    }

                    try {
                        user = new User(username, password);
                        DataBase.Signup.SignUserUp(user);
                        System.out.println("sign up successfully");
                        System.exit(0);
                    } catch (PasswordLengthException ex) {
                        response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.PASSWORD_LENGTH);
                    } catch (WeakPasswordException ex) {
                        response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.WEAK_PASSWORD);
                    } catch (PasswordFormatException ex) {
                        response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.PASSWORD_FORMAT);
                    } catch (UsernameLengthException ex) {
                        response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.USERNAME_LENGTH);
                    } catch (UsernameFormatException ex) {
                        response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.USERNAME_FORMAT);
                    } catch (UsernameExistException ex) {
                        response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.USERNAME_EXISTS);
                    } catch (SQLException ex) {
                        UI.SignUp.signUp(UI.SignUp.SignUpSituation.DATA_BASE);
                    }

                case SIGN_IN:
                    for(String str : event.data) {
                        if (str.startsWith("username="))
                            username = str.substring(9);
                        else if (str.startsWith("password="))
                            password = str.substring(9);
                    }
                    try {
                        user = DataBase.ReadUser.readUser(username, password);

                    } catch (UsernameNotExistException ex) {
                        response = () -> UI.SignIn.signIn(UI.SignIn.SignInSituation.USERNAME_NOT_FOUND);
                    } catch (WrongPasswordException ex) {
                        response = () -> UI.SignIn.signIn(UI.SignIn.SignInSituation.WRONG_PASSWORD);
                    } catch (SQLException ex) {
                        response = () -> UI.SignIn.signIn(UI.SignIn.SignInSituation.DATA_BASE);
                    }
            }

        } while (true);
    }

    private static void exitProgram(int code) {
        System.exit(code);
    }

}