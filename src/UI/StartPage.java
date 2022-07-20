package UI;

import BusinessLogic.Event.Event;
import BusinessLogic.Main.Main;

public class StartPage {
    public enum StartPageSituation {
        EMPTY,
        INVALID_OPTION
    }

    public static Event startPage(StartPageSituation situation) {
        if(situation == StartPageSituation.INVALID_OPTION)
            System.out.println("invalid option given");
        System.out.println("-Welcome to program");
        System.out.println("-press Backspace to go to previous step");
        System.out.println("-press Esc to exit program");
        System.out.println("1- sign up");
        System.out.println("2- sign in");
        System.out.println("3- exit");

        System.out.print("Enter your option : ");
        int option = UI.scanner.nextInt();
        UI.scanner.nextLine();
        return new Event(Main.UserRequest.START_PAGE, Integer.toString(option));

    }
}
