package org.laboratory.project27.personConsoleApp;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.person.Person;
import org.laboratory.project27.person.PersonJob;

import java.util.Arrays;

public class PersonConsoleApp {
    public static int birthYearToArray;

    public static Person createNewPerson() {

        System.out.println("Enter first name");
        String firstName = ConsoleUserDialog.readDataFromConsole();
        System.out.println("Enter last name");
        String lastName = ConsoleUserDialog.readDataFromConsole();
        System.out.println("Enter birthYear");
        int birthYear = Integer.parseInt(ConsoleUserDialog.readDataFromConsole());
        if (birthYear > 1900 && birthYear < 2030) {//проверка на интервал дат
            birthYearToArray = birthYear;
        } else return createNewPerson(); //рекурсия при неправильном вводе даты
        System.out.println("Enter job from the list:");
        System.out.println(Arrays.toString(PersonJob.values()));//вызов массива для понимания списка должностей
        String jobChar = String.valueOf(((ConsoleUserDialog.readDataFromConsole())));//вызов с консоли перечисления
        PersonJob job = PersonJob.A;// todo вызов поля перечисления (название должности) - не получается
        System.out.println(job);
        Person person = new Person(firstName, lastName, birthYearToArray, job);
        System.out.println("01- " + person);
        return person;
    }


}
