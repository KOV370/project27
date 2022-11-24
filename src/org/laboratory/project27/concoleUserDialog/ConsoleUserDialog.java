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
                printMessage("0-exit");
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
        double d = 0;
        boolean isError;
        do {
            try {
                isError = false;
                d = Double.parseDouble(enterString(message));
            } catch (NumberFormatException e) {
                printMessage("Wrong format double");
                printMessage("0-exit");
                isError = true;
            }
        }
        while (isError);
        return d;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public boolean confirmNumber() {
        boolean confirm =false;
        int number = 1;
        if (readInt("1-for confirming updating, other-cancel updating") == number) { //todo не должно быть захардкожено
            //информация о бизнес логике, можно написать метод который будет принимать 1 или 0
            //но что это конфирмация и апдейт не очень хорошо тут хардкодить
            confirm = true;
        }
        return confirm;
    }
}
