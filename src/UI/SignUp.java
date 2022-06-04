package UI;

import BusinessLogic.Event.Event;
import BusinessLogic.Main.Main;

import java.util.concurrent.TimeUnit;

public class SignUp {


    public enum SignUpSituation {
        EMPTY,
        REPEATED_PASSWORD_NOT_MATCH,
        PASSWORD_LENGTH,
        WEAK_PASSWORD,
        PASSWORD_FORMAT,
        USERNAME_LENGTH,
        USERNAME_FORMAT,
        USERNAME_EXISTS,
        DATA_BASE
    }

    public static Event signUp(SignUpSituation code) {
        UI.clearScreen();
        switch (code ) {
            case REPEATED_PASSWORD_NOT_MATCH:
                System.out.print("repeated password does not match\nplease enter again\n");
                break;

            case PASSWORD_LENGTH:
                System.out.print("password must be at least 6 characters and at most 20 characters\n" +
                        "please enter again\n");
                break;

            case WEAK_PASSWORD:
                System.out.print("password is weak\npassword must not have only lowercase or only uppercase\n" +
                        "please enter again\n");
                break;

            case PASSWORD_FORMAT:
                System.out.print("password must include only lowercase letter, uppercase letter, digits " +
                        " and @-#-.-* signs\nplease enter again\n");
                break;

            case USERNAME_LENGTH:
                System.out.print("username must have at least 3 characters and at most 20 characters\n" +
                        "please enter again\n");
                break;

            case USERNAME_FORMAT:
                System.out.print("username must include only lowercase letter, uppercase letter, " +
                        "digits and under line sign\nusername must starts with a letter\n" +
                        "please enter again\n");
                break;

            case USERNAME_EXISTS:
                System.out.print("this username is already taken\nchoose another username\n");
                break;

            case DATA_BASE:
                System.out.print("oops! we have some problem in connection :(\n" +
                        "please try again");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    System.out.print("something went wrong\nplease try later");
                    System.exit(0);
                }
                UI.clearScreen();
                break;
        }
        return getInfo();
    }

    private static Event getInfo() {
        String user_name;
        String password;
        String repeated_password;

        System.out.print("username : ");
        user_name = UI.scanner.nextLine();
        System.out.print("password : ");
        password = UI.scanner.nextLine();
        System.out.print("repeat password : ");
        repeated_password = UI.scanner.nextLine();
        return new Event(Main.UserRequest.SIGN_UP, "username="+user_name,
                "password="+password, "repeatedpassword="+repeated_password);
    }

}
