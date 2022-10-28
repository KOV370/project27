package org.laboratory.project27.personConsoleApp;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.fileUserDialog.PersonFileApp;
import org.laboratory.project27.person.Person;
import org.laboratory.project27.person.PersonException;
import org.laboratory.project27.person.PersonJob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonConsoleApp {

    public static Person createNewPerson() {
        Person person = null;
        System.out.println("Enter first name");
        String firstName = ConsoleUserDialog.readStringFromConsole();
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

    public static void downloadFromFile(){
        System.out.println("Enter the name for downloading.");
        String name = ConsoleUserDialog.readStringFromConsole();
        String dataPersonFromFile = PersonFileApp.downloadFromFile();
        if(dataPersonFromFile.contains(name))
            System.out.println(dataPersonFromFile);
        else
        System.out.println("Name does not found");
    }


}
