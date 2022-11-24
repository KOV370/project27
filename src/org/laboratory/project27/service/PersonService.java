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

    public Person add(Person input) {
        Person person = repository.create(input);
        if (person == null) {
            ui.printMessage("Can not create the person");
        } else {
            ui.printMessage("Person saved successfully");
            repository.saveID(person.getId());
        }
        return person;
    }

    public Person createNewPerson() {
        return newPersonPattern(incrementId());
    }

    public Person createUpdatedPerson(String id) {
        return newPersonPattern(id);
    }

    public Person newPersonPattern(String id) {
        Person person;
        String firstName = getValidatedString("Enter First Name");
        String lastName = getValidatedString("Enter last name");
        int birthYear = enterBirthYear();
        PersonJob job;
        job = Person.setVariableJob(ui.enterString(catalogPersonJobs()));
        double salary = enterSalary();
        person = new Person(id, firstName, lastName, birthYear, job, salary);
        return person;
    }

    private String incrementId() {
        int id;
        try {
            id = Integer.parseInt(repository.getLastId()) + 1;
        } catch (NumberFormatException r) {
            repository.saveID("0");
            return "0";
        }
        return String.valueOf(id);
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

    public List<Person> findAll() {
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

    public void updatePerson() {
        List<Person> personList = findAll();
        String id = ui.enterString("Enter the ID for updating");
        int indexPerson;
        for (int i = 0; i < personList.size(); i++) {
            try {
                if (personList.get(i).getId().equals(id)) {
                    indexPerson = i;
                    ui.printMessage(personList.get(indexPerson).toString());
                    if (ui.confirmNumber()) {
                        personList.remove(personList.get(indexPerson));
                        Person updatedPerson = createUpdatedPerson(id);
                        personList.add(updatedPerson);
                        sortList(personList);
                        repository.saveList(personList);
                        ui.printMessage("ID " + id + " has updated successfully");
                        ui.printMessage(updatedPerson.toString());
                        break;
                    } else break;
                }
            } catch (NumberFormatException ex) {
                break;
            }
        }
    }

    public boolean deletePerson() {
        List<Person> personList = findAll();
        String id = ui.enterString("Enter the ID for deleting");
        boolean successDelete = false;
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId().equals(id)) {
                ui.printMessage(personList.get(i).toString());
                if (ui.confirmNumber()) {
                    Person deletedPerson = personList.get(i);
                    personList.remove(deletedPerson);
                    sortList(personList);
                    repository.saveList(personList);
                    ui.printMessage(deletedPerson.toString());
                    successDelete = true;
                    break;
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


