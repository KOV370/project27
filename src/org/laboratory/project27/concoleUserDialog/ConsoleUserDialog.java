package org.laboratory.project27.concoleUserDialog;

import java.util.Scanner;

public class ConsoleUserDialog {

    public static String readStringFromConsole() {
        String readString ;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            readString = scanner.next();

        } else {
            System.out.println("Mistake of format. Try again");
          return   readStringFromConsole();//конверсия
        }
        return readString;
    }

    public static int readIntFromConsole() {
        int i ;
        int x ;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            i = scanner.nextInt();
            if (i > 1900 && i < 2030) {//проверка на интервал дат
                x = i;
            } else {
                System.out.println("Wrong date interval");
              return   readIntFromConsole();//конверсия
            }
        } else {
            System.out.println("Mistake of format. Try again");
          return   readIntFromConsole();//конверсия
        }
        return x;
    }

    public static double readDoubleFromConsole() {
        double d ;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextDouble()) {
            d = scanner.nextDouble();
        } else {
            System.out.println("Mistake of format. Try again");
           return readDoubleFromConsole();//конверсия
        }
        return d;
    }

}
