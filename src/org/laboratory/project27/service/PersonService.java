package org.laboratory.project27.service;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;
import org.laboratory.project27.repository.PersonFileRepository;

public class PersonService { //todo получить из Person Job  эта константа не нужна
    private static final String NAME_PERSON_JOB = """  
            Enter person job from the list:
            DIRECTOR, MANAGER, DRIVER, SELLER, LOADER, OFFICE_MANAGER""";
    private ConsoleUserDialog ui;
    private PersonFileRepository repository;

    public PersonService(ConsoleUserDialog ui, PersonFileRepository repository) {
        this.ui = ui;
        this.repository = repository;
    }

    public Person add(Person input) {
        Person person = repository.create(input);
        if (person == null) {
            ui.printMessage("Can not create the person");
        } else {
            ui.printMessage("Person saved successfully");
            repository.saveID(person.getId()); //перенес запись ID при подтверждении записи клиента
        }
        return person;
    }

    public Person createNewPerson() {
        Person person;
        String id = incrementId();
        String firstName = getValidatedString("Enter First Name");
        String lastName = getValidatedString("Enter last name");
        int birthYear = enterBirthYear();
        PersonJob job;

        job = Person.setVariableJob(ui.enterString(NAME_PERSON_JOB));

        double salary = enterSalary();
        person = new Person(id, firstName, lastName, birthYear, job, salary);
        return person;
    }

    private String incrementId() {
        int id = Integer.parseInt(repository.getLastId()) + 1;
        return String.valueOf(id);
    }

    private double enterSalary() {
        double salary = 0d;
        boolean isError;
        do {
            try {
                isError = false;
                salary = ui.readDouble("Set the salary.");
            } catch (NumberFormatException ex) {
                isError = true;
            }
        } while (isError);
        return salary;
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
            ui.printMessage("Person not found");
        }
        return person;
    }

    public Person getPersonById(String id) {
        Person person = repository.getPersonById(id);
        if (person == null) {
            ui.printMessage("ID not found");
        }
        return person;
    }
}