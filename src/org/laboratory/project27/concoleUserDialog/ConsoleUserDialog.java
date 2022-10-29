package org.laboratory.project27.concoleUserDialog;

import java.util.Scanner;

public class ConsoleUserDialog {

    public static String readStringFromConsole() { //todo не нужно в названиях метода указывать слово Console, это уже подразумеватся т.к класс называется ConsoleUserDialog
        String readString ;
        Scanner scanner = new Scanner(System.in); //todo scanner можно сделать полем класса чтобы при каждом обращении к методу не создвать объект через new
        if (scanner.hasNext()) {
            readString = scanner.next();

        } else {
            System.out.println("Mistake of format. Try again");
          return   readStringFromConsole();//конверсия //todo не нужна рекурсия. рекурсия вообще редко используется обычно в математических алгоритмах
            // проще сделать цикл do while , определить внутри переменную boolean isError и проверять ее на выходе в секции while
        }
        return readString;
    }

    public static int readIntFromConsole() { //todo не указывать Console в названии
        int i ;
        int x ;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            i = scanner.nextInt();
            if (i > 1900 && i < 2030) {//проверка на интервал дат //todo в этом методе недолжно быть таких проверок, это может быть в методе валидация
                // сам посебе класс ConsoleUserDialog утилитный. Он не должен знать ни о каких значениях, это задача слоя бизнес логики.
                // здесь мы просто считываем число с консоли. Если тип (int) совпадает то все ок
                x = i;
            } else {
                System.out.println("Wrong date interval");
              return   readIntFromConsole();//конверсия //todo сделать через do while см коммент выше
            }
        } else {
            System.out.println("Mistake of format. Try again");
          return   readIntFromConsole();//конверсия //todo сделать через do while см коммент выше
        }
        return x;
    }

    public static double readDoubleFromConsole() { //todo учесть все замечания выше
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
