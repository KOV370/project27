package org.laboratory.project27.service;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonComparator;
import org.laboratory.project27.model.PersonJob;
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

    public void add(Person input) {
        Person person = repository.create(input);
        if (person == null) {
            ui.printMessage("Can not create the person");
        } else {
            ui.printMessage("Person saved successfully");
            repository.saveID(person.getId());
        }
    }

    public Person createNewPerson() { //todo можно назвать просто create()
        return createOrUpdatePerson(incrementId());
    }

    //todo этот метод нигде не вызывается
    public Person createUpdatedPerson(String id) {
        return createOrUpdatePerson(id);
    }

    // todo update person в параметр нужно передавать объект Person person
    // нужно разделить логику. сделаем метод readPersonFromConsole() который будет считаывать данные с консоли.
    // и будет два метода (create(Person person) и update(Person person). методы create() и update() с простейшей логикой
    // которые будут просто дергать repository.create(person), repository.update(person)
    // в методе update() сначала делаем поиск по id, если находим то делаем апдейт. если не находим то бросам эксепшн
    public Person createOrUpdatePerson(String id) {//если сделать ссылку на boolean, то все равно надо как то передавать
        Person person;// ID по изменяемому объекту в параметре, сам person может меняться, но ID  должен беть оставлен без изменений.
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
        } catch (NumberFormatException NullPointerException) {
            id = 0;
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
                exitProgram((int) salary); //todo тут не должно быть exitProgram(), максумум тут может быть метод validate()
                //и если введенное значение не валидно, то можно выйти в верхнее меню, где пользователь решит что делать дальше
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
        Person person = repository.findPersonByIdStream(id);
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

    public void updatePerson() { //этот метод должен на вход принимать параметр Person person
        //ввод с консоли вынести в другой метод Person readPersonFromConsole()
        Person updatedPerson = null;
        String id = ui.enterString("Enter the ID for updating");
        List<Person> personList = repository.listPersons();
        Optional<Person> foundPerson = Optional.ofNullable(repository.findPersonByIdStream(id));
        if (foundPerson.isPresent()) {
            for (Person person : personList) { //todo тут цикл не нужен, просто вызываем repository.update(person)
                if (person.getId().equals(id)) {
                    updatedPerson = setUpdatedPerson(person);
                }
            }
            sortList(personList);
            repository.saveList(personList);
            ui.printMessage("ID " + id + " has updated successfully"); //todo это лучше выносить на верхний уровень,
            // в сервисах можем сделать логирование, а вывод сообщений пользователю это уже задача слоя контроллера,
            // а не сервиса
            ui.printMessage(updatedPerson != null ? updatedPerson.toString() : null);
        } else ui.printMessage("ID did not found");
    }

    public Person setUpdatedPerson(Person person) {
        person.setFirstName(ui.enterString("Enter the first name for updating"));
        person.setLastName(ui.enterString("Enter the last name for updating"));
        person.setBirthYear(enterBirthYear());
        person.setJob(Person.setVariableJob(ui.enterString(catalogPersonJobs())));
        person.setSalary(enterSalary());
        return person;
    }

    public boolean deletePerson() { //todo этот метод должен на вход принимать id
        // делать repository.findById() и если нашли repository.delete()
        boolean successDelete = false;
        String id = ui.enterString("Enter the ID for deleting");
        List<Person> personList = repository.listPersons();
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId().equals(id)) {
                ui.printMessage(personList.get(i).toString());
                ui.printMessage("1-for confirming deleting, other-exit");
                if (ui.enter_1()) {
                    Person deletedPerson = personList.get(i);
                    personList.remove(deletedPerson);
                    sortList(personList);
                    repository.saveList(personList);
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


