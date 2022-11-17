package org.laboratory.project27.concoleUserDialog;

import java.util.Scanner;

public class ConsoleUserDialog {

    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String message) {
        int x = 0;
        boolean isError = false;
        do {
            try {
                isError = false;
                x = Integer.parseInt(enterString(message));
            } catch (NumberFormatException e) {
                printMessage("Wrong format int");
//                exitProgram(isError);
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

    public void exitProgram() {
        System.out.println("9 - exit, other - continue. Make your choice:");
        int numberMenu = scanner.nextInt();
        if (numberMenu == 9) {
            System.exit(0);
        }
    }

}
