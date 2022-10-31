package org.laboratory.project27.personConsoleApp;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.fileUserDialog.PersonFileApp;
import org.laboratory.project27.person.Person;
import org.laboratory.project27.person.PersonJob;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PersonConsoleApp {



    public static Person createNewPerson() {
        Person person = null;
        int birthYear_1 = 0;
        boolean correct = true;
        double correctSalary = 0.0;

        System.out.println("Enter first name");
        String firstName = ConsoleUserDialog.readString();
        System.out.println("Enter last name");
        String lastName = ConsoleUserDialog.readString();
        System.out.println("Enter birthYear");
        int birthYear = ConsoleUserDialog.readInt();
        if (birthYear > 1900 && birthYear < 2030) {
            birthYear_1 = birthYear;
        } else {
            System.out.println("Wrong interval.");
            correct = false;
        }
        System.out.println("Enter job from the list:");
        PersonJob job = PersonJob.DIRECTOR;//  вызов поля перечисления (название должности) - не получается
        //todo не работает подбор из перечисления требует static
//        try {
//            job = Person.setVariableJob(ConsoleUserDialog.readString());
//        } catch (NullPointerException | PersonException e) {
//            System.out.println("job - wrong format");
//        }
        System.out.println("Set the salary.");
        double salary = ConsoleUserDialog.readDouble();
        if (salary != 0)
            correctSalary = salary;
        else correct = false;

        if (!correct) {
            return null;

        } else {
            person = new Person(firstName, lastName, birthYear_1, job, correctSalary);
            System.out.println("01- " + person);
        }
        return person;
    }

    public static void downloadFromFile() {
        String name;
        String dataPersonFromFile;
        BufferedReader bufferedReader = null;
        System.out.println("Enter the name for downloading.");
        name = ConsoleUserDialog.readString();
        try {
            bufferedReader = new BufferedReader(new FileReader(PersonFileApp.getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            do {
                dataPersonFromFile = PersonFileApp.downloadFromFile(bufferedReader);
                if (dataPersonFromFile.contains("firstName='" + name)) {
                    System.out.println(dataPersonFromFile);
                    System.out.println("Continue choice of menu.");
                    break;
                }
            } while (!dataPersonFromFile.isEmpty() || dataPersonFromFile != null);
        } catch (NullPointerException r) {
            System.out.println("The name has not found.");
            System.out.println("Continue choice of menu.");
            return;
        }
    }

}
