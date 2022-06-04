package UI;

import BusinessLogic.Event.Event;
import BusinessLogic.Main.Main;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UI {

    private static final Scanner scanner = new Scanner(System.in);

    public enum SignUpSituations {
        START,
        REPEATED_PASSWORD_NOT_MATCH,
        PASSWORD_LENGTH_EXCEPTION,
        WEAK_PASSWORD_EXCEPTION,
        PASSWORD_FORMAT_EXCEPTION,
        USERNAME_LENGTH_EXCEPTION,
        USERNAME_FORMAT_EXCEPTION,
        USERNAME_EXIST_EXCEPTION,
        DATA_BASE_EXCEPTION
    }

    public static Event signUp(SignUpSituations code) {

        switch (code ) {
            case START:
                return getInfo();

            case REPEATED_PASSWORD_NOT_MATCH:
                clearScreen();
                System.out.print("repeated password does not match\nplease enter again\n");
                return getInfo();

            case PASSWORD_LENGTH_EXCEPTION:
                clearScreen();
                System.out.print("password must be at least 6 characters and at most 20 characters\n" +
                        "please enter again\n");
                return getInfo();

            case WEAK_PASSWORD_EXCEPTION:
                clearScreen();
                System.out.print("password is weak\npassword must not have only lowercase or only uppercase\n" +
                        "please enter again\n");
                return getInfo();

            case PASSWORD_FORMAT_EXCEPTION:
                clearScreen();
                System.out.print("password must include only lowercase letter, uppercase letter, digits " +
                        " and @-#-.-* signs\nplease enter again\n");
                return getInfo();

            case USERNAME_LENGTH_EXCEPTION:
                clearScreen();
                System.out.print("username must have at least 3 characters and at most 20 characters\n" +
                        "please enter again\n");
                return getInfo();

            case USERNAME_FORMAT_EXCEPTION:
                clearScreen();
                System.out.print("username must include only lowercase letter, uppercase letter, " +
                        "digits and under line sign\nusername must starts with a letter\n" +
                        "please enter again\n");
                return getInfo();

            case USERNAME_EXIST_EXCEPTION:
                clearScreen();
                System.out.print("this username is already taken\nchoose another username\n");
                return getInfo();

            case DATA_BASE_EXCEPTION:
                clearScreen();
                System.out.print("oops! we have some problem in connection :(\n" +
                        "please try again");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    System.out.print("something went wrong\nplease try later");
                    System.exit(0);
                }
                clearScreen();
                return getInfo();

            default:
                return new Event(Main.UserRequest.SIGN_UP);

        }
    }

    // helper functions
    private static Event getInfo() {
        String user_name;
        String password;
        String repeated_password;

        System.out.print("username : ");
        user_name = scanner.nextLine();
        System.out.print("password : ");
        password = scanner.nextLine();
        System.out.print("repeat password : ");
        repeated_password = scanner.nextLine();
        return new Event(Main.UserRequest.SIGN_UP, "username="+user_name,
                "password="+password, "repeatedpassword="+repeated_password);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
