package org.laboratory.project27.concoleUserDialog;

import java.util.Scanner;

public class ConsoleUserDialog {

    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String message) {
        int x = 0;
        boolean isError;
        do {
            try {
                isError = false;
                x = Integer.parseInt(enterString(message));
            } catch (NumberFormatException e) {
                printMessage("Wrong format int");
                isError = true;
            }
        } while (isError);
        return x;
    }

    public String enterString(String message) {
        printMessage(message);
        return scanner.nextLine();
    }

    public double readDouble(String message) {
        double x = 0;
        boolean isError;
        do {
            try {
                isError = false;
                x = Double.parseDouble(enterString(message));
            } catch (NumberFormatException e) {
                printMessage("Wrong format double");
                isError = true;
            }
        } while (isError);
        return x;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }


}
