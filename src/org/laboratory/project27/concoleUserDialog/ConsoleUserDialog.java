package org.laboratory.project27.concoleUserDialog;

import java.util.Scanner;

public class ConsoleUserDialog {

    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String message) {
        int x;
        try { //todo у нас был хорошее решение с циклом, надо вернуть
            x = Integer.parseInt(enterString(message));
        } catch (NumberFormatException e) {
            printMessage("Wrong format int");
            x = 0; //todo присвоение 0 должно быть в строке 10 при объявлении
        }
        return x;
    }

    public String enterString(String message) {
        printMessage(message);
        return scanner.nextLine();
    }

    public double readDouble(String message) {
        double d;
        try {
            d = Double.parseDouble(enterString(message));
        } catch (NumberFormatException e) {
            printMessage("Wrong format double");
            d = 0;
        }
        return d;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }


}
