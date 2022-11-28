package org.laboratory.project27.service;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonComparator;
import org.laboratory.project27.model.PersonJob;
import org.laboratory.project27.repository.PersonFileRepository;

import java.util.Arrays;
import java.util.List;

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
        double salary = 0d;
        boolean isError;
        do {
            try {
                isError = false;
                salary = ui.readDouble("Set the salary.");
                exitProgram((int) salary);
            } catch (NumberFormatException ex) {
                isError = true;
            }
        } while (isError);
        return salary;
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
            exitProgram(birthYear);
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

    public Person setUpdatedPerson(Person person) {
        person.setFirstName(ui.enterString("Enter the first name for updating"));
        person.setLastName(ui.enterString("Enter the last name for updating"));
        person.setBirthYear(enterBirthYear());
        person.setJob(Person.setVariableJob(ui.enterString(catalogPersonJobs())));
        person.setSalary(enterSalary());
        return person;
    }

    public boolean delete() {
        boolean successDelete = false;
        String id = ui.enterString("Enter the ID for deleting");
        List<Person> personList = repository.findAll();
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId().equals(id)) {
                ui.printMessage(personList.get(i).toString());
                ui.printMessage("1-for confirming deleting, other-exit");
                if (ui.enter_1()) {
                    Person deletedPerson = personList.get(i);
                    personList.remove(deletedPerson);
                    sortList(personList);
                    repository.saveAll(personList);
                    ui.printMessage(deletedPerson.toString());
                    successDelete = true;
                    return successDelete;
                } else break;
            }
        }
        return successDelete;
    }

    public void exitProgram(int zero) {
        if (zero == 0) {
            System.exit(0);
        }
    }

    public void sortList(List<Person> personList) {
        PersonComparator personComparator = new PersonComparator();
        personList.sort(personComparator);
    }


}


