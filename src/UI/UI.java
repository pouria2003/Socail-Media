package UI;

import BusinessLogic.Event.Event;
import BusinessLogic.Main.Main;
import java.util.LinkedList;
import java.util.Scanner;

public class UI {

    public enum SignUpSituations {
        START,
        REPEATED_PASSWORD_NOT_MATCH,
        PASSWORD_LENGTH_EXCEPTION,
        WEAK_PASSWORD_EXCEPTION,
        PASSWORD_FORMAT_EXCEPTION,
        USERNAME_LENGTH_EXCEPTION,
        USERNAME_FORMAT_EXCEPTION
    }


    public static Event signUp(SignUpSituations code) {
        String user_name;
        String password;
        String repeated_password;

        switch (code ) {
            case START:
                System.out.print("username : ");
                user_name = scanner.nextLine();
                System.out.print("password : ");
                password = scanner.nextLine();
                System.out.print("repeat password : ");
                repeated_password = scanner.nextLine();
                return new Event(Main.UserRequest.SIGN_UP, "username="+user_name,
                        "password="+password, "repeadedpassword="+repeated_password);

            default:
                return null;
        }
        
    
    }

    private static final Scanner scanner = new Scanner(System.in);

}
