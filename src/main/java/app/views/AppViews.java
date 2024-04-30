package app.views;


import app.utils.AppStarters;
import app.utils.Constant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppViews {

    Scanner scanner;
    int option;

    public int chooseOption() {
        scanner = new Scanner(System.in);
        showMenu();
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println(Constant.INCORRECT_OPTION_MSG);
            AppStarters.startApp();
        }
        return option;
    }

    private void showMenu() {
        System.out.print("""

                ______ MENU ___________
                1 - Create contact.
                2 - Read contacts.
                3 - Update contact.
                4 - Delete contact.
                5 - Read contact by id.
                0 - Close the App.
                """);
        System.out.print("Input option: ");
    }

    public void getOutput(String output) {
        if (output.equals("0")) {
            System.out.println(Constant.APP_CLOSE_MSG);
            System.exit(0);
        } else System.out.println(output);
    }
}
