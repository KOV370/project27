package org.laboratory.project27.personConsoleApp;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.fileUserDialog.PersonFileApp;
import org.laboratory.project27.person.Person;
import org.laboratory.project27.person.PersonJob;

public class PersonConsoleApp {//todo по логике если есть в названии слово Console то не должны в названиях методов присутсовать слово File

    public static Person createNewPerson() { //todo метод не должен быть статическим
        Person person = null;
        System.out.println("Enter first name"); //todo все выводы System.out.println должны быть только в классе ConsoleUserDialog
        // создаем в ConsoleUserDialog методы типа print() или output(), их может быть несколько с разными параметрами
        String firstName = ConsoleUserDialog.readStringFromConsole(); //todo сообщение которое выводится на консоль можно сделать параметром в методах readString(String message), readInt(String message) и т.д.
        System.out.println("Enter last name");
        String lastName = ConsoleUserDialog.readStringFromConsole();
        System.out.println("Enter birthYear");
        int birthYear = ConsoleUserDialog.readIntFromConsole();
        System.out.println("Enter job from the list:");
        PersonJob job = PersonJob.DIRECTOR;//  вызов поля перечисления (название должности) - не получается
        //      PersonJob job = null;//todo не работает подбор из перечисления требует ststic
//        try {
//            job = person.setVariableJob(ConsoleUserDialog.readStringFromConsole());
//        } catch (PersonException e) {
//            e.printStackTrace();
//        }
        System.out.println("Set the salary.");
        double salary = ConsoleUserDialog.readDoubleFromConsole();

        person = new Person(firstName, lastName, birthYear, job, salary);
        System.out.println("01- " + person);
        return person;
    }

    public static void downloadFromFile() {
        System.out.println("Enter the name for downloading."); //todo тоже не должно быть System.out.println
        String name = ConsoleUserDialog.readStringFromConsole();
        String dataPersonFromFile = PersonFileApp.downloadFromFile();
        if (dataPersonFromFile.contains("firstName='" + name))
            System.out.println(dataPersonFromFile);
        else
            System.out.println("Name does not found");
    }


}
