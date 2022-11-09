package org.laboratory.project27.concoleUserDialog;

import java.util.Scanner;

public class ConsoleUserDialog {

    private Scanner scanner = new Scanner(System.in);

    public String readString() {
        String readString = scanner.nextLine();
        return readString;
    }

    public int readInt(String message) {
        int x = 0;
        boolean isError;
        do {
            try {
                isError = false;
                x = Integer.parseInt(enterString(message));
            } catch (NumberFormatException e) {
                System.out.println("Wrong format int");
                isError = true;
            }
        } while (isError);

        return x;
    }

    public String enterString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public double readDouble(String message) {
        double d = 0;
        boolean isError;
        do {
            try {
                isError = false;
                d = Double.parseDouble(enterString(message));
            } catch (NumberFormatException e) {
                System.out.println("Wrong format double");
                isError = true;
            }
        }
        while (isError);
        return d;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
