package org.laboratory.project27.concoleUserDialog;

import org.laboratory.project27.model.Person;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
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
        double x = 0;
        boolean isError;
        do {
            try {
                isError = false;
                x = Double.parseDouble(enterString(message));
            } catch (NumberFormatException e) {
                printMessage("Wrong format double");
                isError = true;
            }
        } while (isError);
        return x;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public boolean confirm(String message) {
        return enterString(message).equalsIgnoreCase("Y");
    }

    public void printAll(List<Person> personList) {
        new BufferedReader(new InputStreamReader(System.in));
        for (Person person : personList
        ) {
            System.out.println(person);
        }
    }
}

