package UI;

import BusinessLogic.Event.Event;
import BusinessLogic.Main.Main;
import BusinessLogic.Post.Post;
import BusinessLogic.User.User;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Profile {

    public enum ProfileSituation {
        NORMAL, DATABASE_EXCEPTION
    }

    public static Event profile(User user, boolean doesFollow, ProfileSituation situation) {
        int user_option = 0;
        boolean invalid_option = false;
        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------Profile--------------------\n" + UI.ANSI_RESET);

            System.out.print(UI.ANSI_PURPLE);
            System.out.println("                " + user.getUsername());
            System.out.println("   posts : " + user.getNumberOfPost() + "   followers : " +
                    user.getNumberOfFollowers() + "  followings : " + user.getNumberOfFollowings());
            System.out.println(UI.ANSI_RESET);

            if(situation == ProfileSituation.DATABASE_EXCEPTION) {
                System.out.println(UI.ANSI_RED + "we have some problem with connecting to database\n" +
                        "please try later" + UI.ANSI_RESET);
            }

            System.out.println("0 - back");
            System.out.println("1 - " + ((doesFollow) ? "unfollow" : "follow"));
            System.out.println("2 - posts");
            System.out.println("3 - followers");
            System.out.println("4 - followings");

            if(invalid_option)
                System.out.println(UI.ANSI_RED + "invalid option given" + UI.ANSI_RESET);

            System.out.print("\nenter your option : ");
            try {
                user_option = UI.scanner.nextInt();

                /// get enter
                UI.scanner.nextLine();

                invalid_option = user_option < 0 || user_option > 4;

            } catch (InputMismatchException ex) {
                invalid_option = true;
            }

        } while(invalid_option);

        return new Event(Main.UserRequest.PROFILE, Integer.toString(user_option));
    }

    public static Event posts(ArrayList<Post> posts, String username) {
        int user_option = 0;
        boolean invalid_option = false;
        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------Posts--------------------\n" + UI.ANSI_RESET);

            System.out.println(UI.ANSI_PURPLE + "                " + username + UI.ANSI_RESET);

            for (int i = 0; i < posts.size(); ++i) {
                System.out.print(UI.ANSI_CYAN + (i + 1) + " - " + posts.get(i).getContent().substring(0,
                        (Math.min(posts.get(i).getContent().length(), 30))));
                System.out.println(UI.ANSI_YELLOW + "     " + posts.get(i).getLikes() + " like" + UI.ANSI_RESET);
            }
            System.out.print(UI.ANSI_RESET);

            if(invalid_option)
                System.out.println(UI.ANSI_RED + "\ninvalid option given" + UI.ANSI_RESET);

            System.out.println("\n-enter number of post to see post");
            System.out.println("-enter 0 to go back");
            System.out.print("enter your option : ");
            user_option = UI.scanner.nextInt();

            /// get enter
            UI.scanner.nextLine();

            invalid_option = user_option < 0 || user_option > posts.size();

        } while(invalid_option);

        /// return "0" if user option iz 0 otherwise return post id
        return new Event(Main.UserRequest.POSTS, (user_option == 0) ? "0" : posts.get(user_option - 1).getId());

    }

    public static Event post(Post post, String username) {
        int user_option = 0;
        boolean invalid_option = false;
        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------Post--------------------\n" + UI.ANSI_RESET);

            System.out.println(UI.ANSI_PURPLE + "                " + username + UI.ANSI_PURPLE);

            System.out.println(post.getContent());
            System.out.println(UI.ANSI_RED + post.getLikes() + " like" + UI.ANSI_RESET);

            System.out.println("0 - back");
            System.out.println("1 - like");

            if(invalid_option)
                System.out.println(UI.ANSI_RED + "invalid option given" + UI.ANSI_RESET);

            System.out.print("enter your option : ");
            user_option = UI.scanner.nextInt();

            //get enter
            UI.scanner.nextLine();

            invalid_option = user_option < 0 || user_option > 1;

        } while(invalid_option);

        return new Event(Main.UserRequest.POST, Integer.toString(user_option), post.getId());
    }

    public static Event followersOrFollowings(ArrayList<String> usernames, String username, boolean followers
            /* true = followers - false = followings */) {
        boolean invalid_option = false;
        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------" + ((followers) ? "Followers" : "Followings")
                    + "--------------------\n" + UI.ANSI_RESET);

            System.out.print(UI.ANSI_PURPLE);
            System.out.println("                " + username);
            System.out.println(UI.ANSI_RESET);

            for(int i = 0; i < usernames.size(); ++i)
                System.out.println((i + 1) + " - " + usernames.get(i));

            System.out.print("\nEnter any key to go back : ");
            UI.scanner.nextLine();

            return new Event(Main.UserRequest.FOLLOW_LIST);

        } while(invalid_option);
    }
}
