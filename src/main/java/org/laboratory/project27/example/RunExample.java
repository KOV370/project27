package org.laboratory.project27.example;

import org.laboratory.project27.model.Person;

import java.util.Optional;
import java.util.Scanner;

public class RunExample {

    public static void main(String[] args) {
        System.out.println("Test 1");

        Scanner scanner = new Scanner(System.in);
        //            double a,b,c = 0;
//        if (scanner.hasNextDouble()) {
//            a = scanner.nextDouble();
//            b = scanner.nextDouble();
//            c = scanner.nextDouble();
//        }
//        double middle = (a + b + c) / 3;
//        System.out.println("Test 1 result- " + middle);


//        System.out.println("Test 2");
//        int d = 0;
//        int max = Integer.MIN_VALUE;
//        int min = Integer.MAX_VALUE;
//        if (scanner.hasNextInt()) {
//            for (int i = 0; i < 4; i++) {
//                d = scanner.nextInt();
//                if (max < d) {
//                    max = d;
//                }
//                if (min > d) {
//                    min = d;
//                }
//                System.out.println(d);
//            }
//            System.out.println("Differance max-min  = " + (max-min));
//        }


//        System.out.println("Test 3");
//        int a, b, c, d;
//        int min = 0, max = 0, middle = 0;
//        if (scanner.hasNextInt()) {
//            {
//                a = scanner.nextInt();
//                b = scanner.nextInt();
//                c = scanner.nextInt();
//                scanner.close();
//            }
//            System.out.println(a + " " + b + " " + c);
//            if (a < b) {
//                if (a < c) {
//                    min = a;
//                    max = Math.max(b, c);
//                    middle = Math.min(b, c);
//                } else {
//                    min = c;
//                    max = Math.max(a, b);
//                    middle = Math.min(a, b);
//                }
//            } else {
//                min = Math.min(b, c);
//                max = Math.max(a, c);
//                if (max == c) {
//                    middle = a;
//                } else {
//                    middle = Math.max(b, c);
//                }
//            }
//        }
//        a = min;
//        b = middle;
//        c = max;
//        System.out.println(a + " " + b + " " + c);

        System.out.println("Test 4");
        boolean a = false;
        boolean b = false;
        boolean c = false;
        int countTrue = 0;
        System.out.print("Enter three boolean " +
                "values (true or false) ");
        if (scanner.hasNextBoolean()) {
            a = scanner.nextBoolean();
            if (a) {
                countTrue += 1;
            }
            b = scanner.nextBoolean();
            if (b) {
                countTrue += 1;
            }
            c = scanner.nextBoolean();
            if (c) {
                countTrue += 1;
            }
            scanner.close();
        }
        System.out.println(countTrue);
        boolean allThree = false;
        boolean exactlyOne = false;
        boolean exactlyTwo = false;
        boolean atLeastOne = false;
        boolean atLeastTwo = false;
        if (countTrue == 3) {
            allThree = true;
        }
        if (countTrue == 1) {
            exactlyOne = true;
        }
        if (countTrue == 2) {
            exactlyTwo = true;
        }
        if (countTrue >= 1) {
            atLeastOne = true;
        }
        if (countTrue >= 2) {
            atLeastTwo = true;
        }
        System.out.println("a, b, c = " + a + ", " + b +
                ", " + c + "\nallThree: " + allThree +
                "\nexactlyOne: " + exactlyOne +
                "\nexactlyTwo: " + exactlyTwo +
                "\natLeastOne: " + atLeastOne +
                "\natLeastTwo: " + atLeastTwo);
    }
}




