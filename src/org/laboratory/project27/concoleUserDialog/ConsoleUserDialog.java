package org.laboratory.project27.concoleUserDialog;

import java.util.Scanner;

public class ConsoleUserDialog {

    Scanner scanner = new Scanner(System.in);

//    public String readString() {
//        String readString;
//        String correctReadString = null;
//        boolean getreadString;
//        //       do {
//        readString = scanner.next();
//
//            if(readString.matches(".*\\D+.*")) //todo вынести проверку PersonService в метод validateName
//            { // в-т булеан
//                correctReadString = readString;
//               getreadString = true;
//            }
//            else
//            {getreadString = false;
//            }
//        }
//        while (!getreadString);
//
//        return readString;
//    }

    public int readInt(String message) {
        int x = 0;
        boolean isError;
        do {
            try {
                isError = false;
                x = Integer.parseInt(enterString(message));
            } catch (NumberFormatException e) {
                System.out.println("Wrong format int");//todo
                isError = true;
            }
        } while (isError);

        return x;
    }

    public String enterString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public double readDouble() { //todo  сделать по аналогии с int
        double d = 0.0;
        if (scanner.hasNextDouble()) {
            d = scanner.nextDouble();
        } else {
            System.out.println("Mistake of format double.");
            d = 0;
        }
        return d;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
