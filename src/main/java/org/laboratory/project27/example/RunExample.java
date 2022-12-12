package org.laboratory.project27.example;

import java.util.Scanner;

public class RunExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        System.out.println("Test 1");
//                   double a=0,b=0,c = 0;
//        if (scanner.hasNextDouble()) {
//            a = scanner.nextDouble();
//            b = scanner.nextDouble();
//            c = scanner.nextDouble();
//            scanner.close();
//        }
//        double middle = (a + b + c) / 3;
//        System.out.println("Test 1 result- " + middle);


//        System.out.println("Test 2");
//        int d = 0;
//        int max =0;
//        int min =0;
//            for (int i = 0; i < 4; i++)
//            {
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
//


        System.out.println("Test 3");
        int min, max, middle;
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();

        System.out.println("Test 3 example 2");
        //       test3Ex2(a, b, c);

        System.out.println("Test 3 example 1");
        test3Ex4(a, b, c);


//        System.out.println("Test 4");
//        boolean a = false;
//        boolean b = false;
//        boolean c = false;
//        int countTrue = 0;
//        System.out.print("Enter three boolean " +
//                "values (true or false) ");
//        {
//            if (a) {
//                countTrue += 1;
//            }
//            if (b) {
//                countTrue += 1;
//            }
//            if (c) {
//                countTrue += 1;
//            }
//            scanner.close();
//        }
//        System.out.println(countTrue);
//        boolean allThree = false;
//        boolean exactlyOne = false;
//        boolean exactlyTwo = false;
//        boolean atLeastOne = false;
//        boolean atLeastTwo = false;
//        if (countTrue == 3) {
//            allThree = true;
//        }
//        if (countTrue == 1) {
//            exactlyOne = true;
//        }
//        if (countTrue == 2) {
//            exactlyTwo = true;
//        }
//        if (countTrue >= 1) {
//            atLeastOne = true;
//        }
//        if (countTrue >= 2) {
//            atLeastTwo = true;
//        }
//        System.out.println("a, b, c = " + a + ", " + b +
//                ", " + c + "\nallThree: " + allThree +
//                "\nexactlyOne: " + exactlyOne +
//                "\nexactlyTwo: " + exactlyTwo +
//                "\natLeastOne: " + atLeastOne +
//                "\natLeastTwo: " + atLeastTwo);

    }

    private static void test3Ex1(int a, int b, int c) {
        int min;
        int max;
        int middle;
        System.out.println(a + " " + b + " " + c);
        int differA_B = a - b;
        int differA_C = a - c;
        if ((differA_B < 0) && (differA_C < 0)) {
            min = a;
            if (b > c) {
                max = b;
                middle = c;
            } else {
                max = c;
                middle = b;
            }
        } else if ((differA_B > 0) && (differA_C > 0)) {
            max = a;
            if (b > c) {
                min = c;
                middle = b;
            } else {
                min = b;
                middle = c;
            }
        } else {
            if (b > c) {
                max = b;
                min = c;
                middle = a;
            } else {
                max = c;
                min = b;
                middle = a;
            }
        }
        a = min;
        b = middle;
        c = max;
        System.out.println(a + " " + b + " " + c);
    }

    private static void test3Ex2(int a, int b, int c) {
        int max;
        int min;
        int middle;
        System.out.println(a + " " + b + " " + c);
        int firstMax = a > b ? a : b;
        max = firstMax > c ? firstMax : c;
        int firstMin = a < b ? a : b;
        min = firstMin < c ? firstMin : c;
        middle = a + b + c - max - min;
        a = min;
        b = middle;
        c = max;
        System.out.println(a + " " + b + " " + c);
    }

    private static void test3Ex3(int a, int b, int c) {
        System.out.println(a + " " + b + " " + c);
        int max = a > b ? a : b;
        max = max > c ? max : c;
        int min = a < b ? a : b;
        min = min < c ? min : c;
        int middle = a + b + c - max - min;
        a = min;
        b = middle;
        c = max;
        System.out.println(a + " " + b + " " + c);
    }

    private static void test3Ex4(int a, int b, int c) {
        System.out.println(a + " " + b + " " + c);
        int d = 0;
        //7 5 3
        if (a > b) {
            d = a;
            a = b;
            b = d;
        }
        //5 7 3
        if (b > c) {
            d = b;
            b = c;
            c = d;
        }
// 5 3 7
        if (a > b) {
            d = a;
            a = b;
            b = d;
        }
        System.out.println(a + " " + b + " " + c);
    }
}







