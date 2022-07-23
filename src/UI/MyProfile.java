package UI;

import BusinessLogic.Event.Event;
import BusinessLogic.Main.Main;
import BusinessLogic.User.User;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class MyProfile {

    public static Event myProfile(User user, Profile.ProfileSituation situation) {
        int user_option = 0;
        boolean invalid_option = false;
        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------MyProfile--------------------\n" + UI.ANSI_RESET);

            System.out.print(UI.ANSI_PURPLE);
            System.out.println("                " + user.getUsername());
            System.out.println("   followers : " + user.getNumberOfFollowers() + "  followings : " +
                    user.getNumberOfFollowings());
            System.out.println(UI.ANSI_RESET);

            if(situation == Profile.ProfileSituation.DATABASE_EXCEPTION) {
                System.out.println(UI.ANSI_RED + "we have some problem with connecting to database\n" +
                        "please try later" + UI.ANSI_RESET);
            }

            System.out.println("0 - back");
            System.out.println("1 - followers");
            System.out.println("2 - followings");

            if(invalid_option)
                System.out.println(UI.ANSI_RED + "invalid option given" + UI.ANSI_RESET);

            System.out.print("\nenter your option : ");
            try {
                user_option = UI.scanner.nextInt();

                /// get enter
                UI.scanner.nextLine();

                invalid_option = user_option < 0 || user_option > 2;

            } catch (InputMismatchException ex) {
                System.out.println(UI.ANSI_RED + "invalid option given" + UI.ANSI_RESET);
                invalid_option = true;
            }

        } while(invalid_option);

        return new Event(Main.UserRequest.MY_PROFILE, Integer.toString(user_option));
    }

    public static Event myFollowersList(ArrayList<String> followers) {
        int user_option = 0;
        boolean invalid_option = false;

        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------Followers--------------------\n" + UI.ANSI_RESET);

            for (int i = 0; i < followers.size(); ++i)
                System.out.println((i + 1) + " - " + followers.get(i));

            if(invalid_option)
                System.out.println(UI.ANSI_RED + "invalid option given" + UI.ANSI_RESET);

            System.out.println("\n-enter follower number to remove the follower");
            System.out.println("-enter 0 to go back\n");

            System.out.print("Enter your option : ");
            user_option = UI.scanner.nextInt();

            // get enter
            UI.scanner.nextLine();

            invalid_option = user_option < 0 || user_option > followers.size();

        } while(invalid_option);

        return new Event(Main.UserRequest.MY_FOLLOWERS_LIST, (user_option == 0) ? "0" : followers.get(user_option - 1));
    }

    public static Event myFollowingsList(ArrayList<String> followings) {
        int user_option = 0;
        boolean invalid_option = false;

        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------Followings--------------------\n" + UI.ANSI_RESET);

            for (int i = 0; i < followings.size(); ++i)
                System.out.println((i + 1) + " - " + followings.get(i));

            if(invalid_option)
                System.out.println(UI.ANSI_RED + "invalid option given" + UI.ANSI_RESET);

            System.out.println("\n-enter following number to unfollow");
            System.out.println("-enter 0 to go back\n");

            System.out.print("Enter your option : ");
            user_option = UI.scanner.nextInt();

            // get enter
            UI.scanner.nextLine();

            invalid_option = user_option < 0 || user_option > followings.size();

        } while(invalid_option);

        return new Event(Main.UserRequest.MY_FOLLOWINGS_LIST, (user_option == 0) ? "0" : followings.get(user_option - 1));
    }
}
