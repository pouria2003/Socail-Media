package UI;

import BusinessLogic.Event.Event;
import BusinessLogic.Main.Main;

import java.util.InputMismatchException;
import java.util.concurrent.TimeUnit;

public class HomePage {
    public static Event homePage() {
        int user_option = 0;
        boolean invalid_option = true;
        do {
            UI.clearScreen();
            System.out.println(UI.ANSI_BLUE + "\n--------------------HomePage--------------------\n" + UI.ANSI_RESET);
            try {
                System.out.println("0 - exit");
                System.out.println("1 - my profile");
                System.out.println("2 - search");
                System.out.println("3 - logout\n");
                System.out.print("choose your option : ");
                user_option = UI.scanner.nextInt();
                invalid_option = false;
            } catch (InputMismatchException ex) {
                System.out.println("invalid option given");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } while (invalid_option);

        return new Event(Main.UserRequest.HOME_PAGE, Integer.toString(user_option));
    }
}
