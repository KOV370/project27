package org.laboratory.project27.service;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.*;
import org.laboratory.project27.repository.PersonFileRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PersonService {
    private ConsoleUserDialog ui;
    private PersonFileRepository repository;

    public PersonService(ConsoleUserDialog ui, PersonFileRepository repository) {
        this.ui = ui;
        this.repository = repository;
    }

    public String catalogPersonJobs() {
        return "Enter person job from the list:" + Arrays.toString(PersonJob.values());
    }

    public Person readPersonFromConsole() {
        Person person;
        String firstName = getValidatedString("Enter First Name");
        String lastName = getValidatedString("Enter last name");
        int birthYear = enterBirthYear();
        PersonJob job;
        job = Person.setVariableJob(ui.enterString(catalogPersonJobs()));
        double salary = enterSalary();
        person = new Person(firstName, lastName, birthYear, job, salary);
        return person;
    }

    private double enterSalary() {
        double salary;
        do {
            salary = ui.readDouble("Set the salary.");
        } while (!validateSalary(salary));
        return salary;
    }

    private boolean validateSalary(double salary) {
        if (salary == 0) {
            throw new IllegalValueException("Exception! Salary value is 0");
        }
        if (salary < 0 || salary > 100000) {
            ui.printMessage("Salary must be from 0 till 100000");
            return false;
        } else {
            return true;
        }
    }

    public List<Person> findAll() {//todo
        return repository.findAll();
    }

    public Person getPersonByName(String name) {
        Person person = repository.findPersonByName(name);
        if (person == null) {
            ui.printMessage("Person not found");
        }
        return person;
    }

    public Person getPersonById(String id) {
        Person person = repository.findPersonById(id);
        if (person == null) {
            ui.printMessage("ID not found");
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

    private int enterBirthYear() {
        int birthYear;
        do {
            birthYear = ui.readInt("Enter birthYear");
        } while (!validateBirthday(birthYear));
        return birthYear;
    }

    private boolean validateBirthday(int birthYear) {
        if (birthYear == 0) {
            throw new IllegalValueException("Value is 0");
        }
        if (birthYear < 1900 || birthYear > 2030) {
            ui.printMessage("Year must be from 1900 till 2030");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateName(String inputString) {
        return !inputString.matches(".*\\d+.*");
    }

    public Person create(Person input) {
        return repository.create(input);
    }

    public Person update(Person person) {
        if (repository.findPersonById(person.getId()) != null) {
            return repository.update(person);
        } else {
            return repository.create(person);
        }
    }

    public boolean delete(Person person) {
        if (repository.findPersonById(person.getId()) != null) {
            return repository.delete(person);
        } else
            return false;
    }

    public void sortList(List<Person> personList) {
        PersonComparator personComparator = new PersonComparator();
        personList.sort(personComparator);
    }

}


//    public void updateOleg(String id) {
//        List<Person> personList = findAll();
//        Optional<Person> foundOptional = personList.stream().filter(n -> n.getId().equals(id)).findFirst();
//        if (foundOptional.isPresent()) {
//            Person foundPerson = foundOptional.get();
//            setUpdatedPersonOleg(foundPerson);
//        } else personList = null;
//            repository.saveAll(personList);
//    }
//
//    public void setUpdatedPersonOleg(Person person) {
//        person.setFirstName(ui.enterString("Enter the first name for updating"));
//        person.setLastName(ui.enterString("Enter the last name for updating"));
//        person.setBirthYear(enterBirthYear());
//        person.setJob(Person.setVariableJob(ui.enterString(catalogPersonJobs())));
//        person.setSalary(enterSalary());
//    }
