package UI;

import java.util.Scanner;


public class UI {
    static final Scanner scanner = new Scanner(System.in);

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
