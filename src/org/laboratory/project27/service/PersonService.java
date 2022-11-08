package org.laboratory.project27.service;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonException;
import org.laboratory.project27.model.PersonJob;
import org.laboratory.project27.repository.PersonFileRepository;

import java.io.FileNotFoundException;
import java.io.FileWriter;

public class PersonService {
    private ConsoleUserDialog ui;
    private PersonFileRepository repository;

    public PersonService(ConsoleUserDialog ui, PersonFileRepository repository) {
        this.ui = ui;
        this.repository = repository;
    }

    public static void writeToTheDocument(String file, Person person,boolean addToData) throws Exception {
        try (FileWriter fileWriter = new FileWriter(file,addToData)) {
            PersonFileRepository.printToFife(fileWriter, person);
        }
        catch (FileNotFoundException e) {
           throw new PersonException("Document wasn't found " + file);
      }
    }

    public Person createNewPerson() {
        Person person;
        int birthYear_1 = 0;
        boolean correct = true;
        double correctSalary = 0.0;
        String firstName = getValidatedString("Enter First Name");
        String lastName = getValidatedString("Enter last name");
        int birthYear = ui.readInt("Enter birthYear");
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
//            job = Person.setVariableJob(ui.readString());
//        } catch (NullPointerException | PersonException e) {
//            System.out.println("job - wrong format");
//        }
        double salary = ui.readDouble("Set the salary.");
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

    private String getValidatedString(String message) {
        String validatedString;
        do {
            validatedString = ui.enterString(message);
        }
        while (!validateName(validatedString));
        return validatedString;
    }

    private boolean validateName(String inputString) {
        return !inputString.matches(".*\\d+.*");//todo а этот фильтр работает, отсекает цифры
        //  return    inputString.contains("^[A-Z][a-zA-z]{1,30}$"); //todo не работает


    }

    public Person getPersonByName(String name) {
        Person person = repository.getPersonByName(name);
        if (person == null) {
            System.out.println("Person not found");
        } else {
            System.out.println(person);//todo перенсти в Run
        }
        return person;
    }
}
