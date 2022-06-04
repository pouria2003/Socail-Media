package BusinessLogic.Main;

import BusinessLogic.Event.Event;
import BusinessLogic.User.User;
import Exceptions.UserException.*;
import Exceptions.DataBaseExceptions.*;
import UI.UI;

import java.sql.SQLException;

interface Response {
    Event perform();
}

public class Main {

    public enum UserRequest {
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
    }

    /// main() here function as an event handler
    public static void main(String[] args) {

        Response response = () -> UI.signUp(UI.SignUpSituations.START);
        Event event;

        do {
            event = response.perform();
            switch (event.user_request) {
                case SIGN_UP:
                    String user_name = null, password = null, rep_password = null;
                    for(String str : event.data) {
                        if(str.startsWith("username="))
                            user_name = str.substring(9);
                        else if(str.startsWith("password="))
                            password = str.substring(9);
                        else if(str.startsWith("repeatedpassword="))
                            rep_password = str.substring(17);
                    }

                    if(!password.equals(rep_password)) {
                        response = () -> UI.signUp(UI.SignUpSituations.REPEATED_PASSWORD_NOT_MATCH);
                        break;
                    }

                    try {
                        User user = new User(user_name, password);
                        DataBase.Signup.SignUserUp(user);
                        System.out.println("sign up successfully");
                        System.exit(0);
                    } catch (PasswordLengthException ex) {
                        response = () -> UI.signUp(UI.SignUpSituations.PASSWORD_LENGTH_EXCEPTION);
                    } catch (WeakPasswordException ex) {
                        response = () -> UI.signUp(UI.SignUpSituations.WEAK_PASSWORD_EXCEPTION);
                    } catch (PasswordFormatException ex) {
                        response = () -> UI.signUp(UI.SignUpSituations.PASSWORD_FORMAT_EXCEPTION);
                    } catch (UsernameLengthException ex) {
                        response = () -> UI.signUp(UI.SignUpSituations.USERNAME_LENGTH_EXCEPTION);
                    } catch (UsernameFormatException ex) {
                        response = () -> UI.signUp(UI.SignUpSituations.USERNAME_FORMAT_EXCEPTION);
                    } catch (UsernameExistException ex) {
                        response = () -> UI.signUp(UI.SignUpSituations.USERNAME_EXIST_EXCEPTION);
                    } catch (SQLException ex) {
                        UI.signUp(UI.SignUpSituations.DATA_BASE_EXCEPTION);
                    }

            }

        } while (true);
    }
}