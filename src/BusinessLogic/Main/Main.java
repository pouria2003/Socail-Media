package BusinessLogic.Main;

import BusinessLogic.Event.Event;
import BusinessLogic.User.User;
import Exceptions.UserException.*;
import Exceptions.DataBaseExceptions.*;
import UI.Search;

import java.sql.SQLException;

interface Response {
    Event perform();
}

public class Main {

    private static Response response;
    private static User user;
    private static Event event;
    private static String searched_username;


    public enum UserRequest {
        START_PAGE,
        SIGN_UP,
        SIGN_IN,
        HOME_PAGE,
        SEARCH,
        EXIT
    }

    /// main() here function as an event handler
    /// it has a loop - run an ui function and receive an event and
    /// analyze that to detect appropriate response to run in next iteration of loop
    public static void main(String[] args) {

        response = () -> UI.StartPage.startPage(UI.StartPage.StartPageSituation.EMPTY);

        do {
            event = response.perform();
            switch (event.user_request) {
                case START_PAGE -> startPage();
                case SIGN_UP -> signUp();
                case SIGN_IN -> signIn();
                case HOME_PAGE -> homePage();
                case SEARCH -> search();
            }

        } while (true);
    }

    private static void startPage() {
        switch (event.data[0]) {
            case "1" -> response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.EMPTY);
            case "2" -> response = () -> UI.SignIn.signIn(UI.SignIn.SignInSituation.EMPTY);
            case "3" -> exitProgram(0);
            default -> response = () -> UI.StartPage.startPage(UI.StartPage.StartPageSituation.INVALID_OPTION);
        }
    }

    private static void signUp() {
        String username = null;
        String password = null;
        String rep_password = null;
        String sec_ans1 = null;
        String sec_ans2 = null;
        String sec_ans3 = null;

        for(String str : event.data) {
            if(str.startsWith("username="))
                username = str.substring(9);
            else if(str.startsWith("password="))
                password = str.substring(9);
            else if(str.startsWith("repeatedpassword="))
                rep_password = str.substring(17);
            else if(str.startsWith("sec_ans1="))
                sec_ans1 = str.substring(9);
            else if(str.startsWith("sec_ans2="))
                sec_ans2 = str.substring(9);
            else if(str.startsWith("sec_ans3="))
                sec_ans3 = str.substring(9);

        }

        if(!password.equals(rep_password)) {
            response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.REPEATED_PASSWORD_NOT_MATCH);
            return;
        }

        if(sec_ans1.length() > 20 || sec_ans1.isEmpty() || sec_ans2.length() > 20
            || sec_ans2.isEmpty() || sec_ans3.length() > 20 || sec_ans3.isEmpty()) {
            response = () -> UI.SignUp.signUp(UI.SignUp.SignUpSituation.SECURITY_ANSWERS_LENGTH);
            return;
        }

        try {
            user = new User(username, password);
            DataBase.Signup.SignUserUp(user, sec_ans1, sec_ans2, sec_ans3);
            System.out.println(UI.UI.ANSI_GREEN + "sign up successfully" + UI.UI.ANSI_RESET);
            response = () -> UI.HomePage.homePage();
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
    }

    private static void signIn() {
        String username = null;
        String password = null;
        for(String str : event.data) {
            if (str.startsWith("username="))
                username = str.substring(9);
            else if (str.startsWith("password="))
                password = str.substring(9);
        }
        try {
            user = DataBase.ReadUser.readUser(username, password);
            response = () -> UI.HomePage.homePage();
        } catch (UsernameNotExistException ex) {
            response = () -> UI.SignIn.signIn(UI.SignIn.SignInSituation.USERNAME_NOT_FOUND);
        } catch (WrongPasswordException ex) {
            response = () -> UI.SignIn.signIn(UI.SignIn.SignInSituation.WRONG_PASSWORD);
        } catch (SQLException ex) {
            response = () -> UI.SignIn.signIn(UI.SignIn.SignInSituation.DATA_BASE);
        }
    }

    private static void homePage() {
        int user_option = Integer.parseInt(event.data[0]);
        switch (user_option) {
            case 0, 1 -> exitProgram(0);
            case 2 -> response = () -> UI.Search.search(Search.SearchStatus.Normal, null);
            case 3 -> response = () -> UI.StartPage.startPage(UI.StartPage.StartPageSituation.EMPTY);
        }
    }

    private static void search() {
        String username = event.data[0];
        if(username.equals("0")) {
            response = () -> UI.HomePage.homePage();
            return;
        }
        try {
            if(DataBase.Signup.isUsernameExist(username)){
                System.out.println("found");
                response = () -> UI.HomePage.homePage();
                searched_username = username;
            }
            else
                response = () -> UI.Search.search(Search.SearchStatus.UsernameNotExist, username);
        } catch (SQLException e) {
            response = () -> UI.Search.search(Search.SearchStatus.DataBaseProblem, null);
        }
    }

    private static void exitProgram(int code) {
        System.exit(code);
    }

}