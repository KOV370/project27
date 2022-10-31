package org.laboratory.project27.concoleUserDialog;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUserDialog {
    static Scanner scanner = new Scanner(System.in);

    public static String readString() {//todo как заблокировать ввод цифр?
        String readString;
        String correctReadString = null;
        boolean getreadString;
        //       do {
        readString = scanner.next();
        //           int i = readString.length();//попытка отделить буквы от цифр
//            if(readString.matches("\\D{4}"))
//            {
//                correctReadString = readString;
//               getreadString = true;
//            }
//            else
//            {getreadString = false;
//            }
//        }
//        while (!getreadString);

        return readString;
    }

    public static int readInt() {//todo во  всех этих методах нет проверки на привильный формат
        //todo я не знаю как ее сделать, чтобы возвращалось к началу выбора без рекурсии
        int x;
        x = scanner.nextInt();
        return x;
    }

    public static double readDouble() {
        double d = 0.0;
        if (scanner.hasNextDouble()) {
            d = scanner.nextDouble();
        } else {
            System.out.println("Mistake of format double.");
            d = 0;
        }
        return d;
    }
//    public static int readDouble() {
//        int x;
//        x = scanner.nextInt();
//        return x;
//    }
}
