package org.laboratory.project27.service;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonException;
import org.laboratory.project27.model.PersonJob;
import org.laboratory.project27.repository.PersonFileRepository;

public class PersonService {
    private static final String namePersonJob = """
            Enter person job from the list:
            DIRECTOR
            MANAGER
            DRIVER
            SELLER
            LOADER
            OFFICE_MANAGER""";
    private ConsoleUserDialog ui;
    private PersonFileRepository repository;

    //todo что означает этот конструктор?
    public PersonService(ConsoleUserDialog ui, PersonFileRepository repository) {
        this.ui = ui;
        this.repository = repository;
    }

    public Person add(Person input) {
        Person person = repository.create(input);//todo add ID
        if (person == null) {
            ui.printMessage("Can not create the person");
        } else {
            ui.printMessage("Person saved successfully");
        }
        return person;
    }

    public Person createNewPerson() {
        Person person;
        boolean correct = true;
        double correctSalary = 0.0;
        String firstName = getValidatedString("Enter First Name");
        String lastName = getValidatedString("Enter last name");
        int birthYear = enterBirthYear();
        PersonJob job = null;
        try {
            job = Person.setVariableJob(getValidatedString("Enter job:"));
        } catch (PersonException e) {
            ui.printMessage("Wrong job");
        }
        double salary = ui.readDouble("Set the salary.");//todo сделать по аналогии с годом
        if (salary != 0)
            correctSalary = salary;//todo удалить
        else correct = false;//todo удалить
        int id = 1;//todo  организовать автоматический ввод id по порядку
        if (!correct) {
            System.out.println("New person did not created");
            return null;
        } else {
            person = new Person(firstName, lastName, birthYear, job, correctSalary, id);
        }
        return person;
    }

    private int enterBirthYear() {
        int birthYear;
        do {
            birthYear = ui.readInt("Enter birthYear");
        } while (!isValid(birthYear));
        return birthYear;
    }

    private boolean isValid(int birthYear) {
        if (birthYear < 1900 || birthYear > 2030) {
            ui.printMessage("Year must be from 1900 till 2030");
            return false;
        } else {
            return true;
        }
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
        return !inputString.matches(".*\\d+.*");
    }

    public Person getPersonByName(String name) {
        Person person = repository.getPersonByName(name);
        if (person == null) {
            System.out.println("Person not found");
        }
        return person;
    }

    public Person getPersonById(String id) {
        Person person = repository.getPersonById(id);
        if (person == null) {
            System.out.println("Person not found");
        }
        return person;
    }
}
