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
                printMessage("0-exit"); //todo писал раньше в комментах что не должно быть этого тут. Это бизнес логика
                // а метод readDouble просто должнен возвращать число. Значение можем проверять в сервисе которые вызывает
                // этот метод в validate()
                isError = true;
            }
        }
        while (isError);
        return d;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }


    //todo не исправлен прошлый комментарий, не должно быть захардкожено
    //информация о бизнес логике, можно написать метод который будет принимать 1 или 0
    //но что это конфирмация и апдейт не очень хорошо тут хардкодить
    //не понятно почему метод enter_1  возвращает true.
    //если этот метод критически необходим, то нужно тогда его называть что типа boolean confirm()
    //и пользователь должен ввести yes, либо Y, y, тогда возвращаем true.
    // если вводит любое другое слово то считаем что это No
    public boolean enter_1() {
        boolean equals_1 = false;
        int number = 1;
        if (readInt("Enter number:") == number) {
            equals_1 = true;
        }
        System.out.println(equals_1);
        return equals_1;
    }


}
