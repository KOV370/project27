package org.laboratory.project27.service;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;
import org.laboratory.project27.repository.PersonFileRepository;

public class PersonService {
    private ConsoleUserDialog ui;
    private PersonFileRepository repository;

    public PersonService(ConsoleUserDialog ui, PersonFileRepository repository) {
        this.ui = ui;
        this.repository = repository;
    }

    public Person createNewPerson() {
        Person person;
        int birthYear_1 = 0;
        boolean correct = true;
        double correctSalary = 0.0;
        String firstName = getValidatedString("Enter First Name");
        String lastName = ui.enterString("Enter last name");
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
        System.out.println("Set the salary.");
        double salary = ui.readDouble();
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
     //   return !inputString.matches(".*\\D+.*");
      return   inputString.contains("[a-zA-Z]+");
    }

    public Person getPersonByName(String name) {//todo implement this method
        return repository.getPersonByName(name);
    }
}
