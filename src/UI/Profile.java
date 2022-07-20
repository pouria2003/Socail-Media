package UI;

import BusinessLogic.Event.Event;
import BusinessLogic.Main.Main;
import BusinessLogic.User.User;

import java.util.InputMismatchException;

public class Profile {
    public static Event profile(User user) {
        int user_option = 0;
        boolean invalid_option = false;
        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------Profile--------------------\n" + UI.ANSI_RESET);

            System.out.print(UI.ANSI_PURPLE);
            System.out.println("                " + user.getUsername());
            System.out.println("   followers : " + user.getNumberOfFollowers() + "  followings : " +
                    user.getNumberOfFollowings());
            System.out.println(UI.ANSI_RESET);

            System.out.println("0 - back");
            System.out.println("1 - follow");
            System.out.println("2 - followers");
            System.out.println("3 - followings");

            if(invalid_option)
                System.out.println(UI.ANSI_RED + "invalid option given" + UI.ANSI_RESET);

            System.out.print("\nenter your option : ");
            try {
                user_option = UI.scanner.nextInt();

                /// get enter
                UI.scanner.nextLine();

                invalid_option = user_option < 0 || user_option > 3;

            } catch (InputMismatchException ex) {
                System.out.println(UI.ANSI_RED + "invalid option given" + UI.ANSI_RESET);
                invalid_option = true;
            }

        } while(invalid_option);

        return new Event(Main.UserRequest.PROFILE, Integer.toString(user_option));
    }
}
