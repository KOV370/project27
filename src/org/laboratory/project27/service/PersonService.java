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
        Person person;
        String id = incrementId();
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
                exitProgram((int) salary); //todo использовать не exitProgram() a установить salary = 0d;
                // а проверку что salary !=0 делать в вызывающем методе
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
            exitProgram(birthYear); //todo плохой стиль использовать exitProgram(), в идеале exit должен быть только
            // в одном месте в самом верхнем меню, в остальных случаях должна быть возможность диалога
            // можно дать возможность указывать 0, тоогда это будет означать что пользователь не знает года рождения
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

    public List<Person> findAll() {
        return repository.findAll();
    }

    public boolean deletePerson() {
        List<Person> personList = findAll();
        PersonComparator personComparator = new PersonComparator();
        String id = ui.enterString("Enter the ID for deleting");
        boolean successDelete = false;
        for (int i = 0; i < personList.size(); i++) { //todo лучше чтобы эта логика была на уровне repository а не в сервисе
            // кроме вывода сообщений запроса пользователю, вопрос на уверены ли вы что хотите удалить должен заваться
            // до того как начинаем искть. То есть в сервисе задаем вопрос удалить? если ответ Да, вызываем repository.delete(id)
            try {
                if (personList.get(i).getId().equals(id)) {
                    int indexPerson = i;
                    ui.printMessage(personList.get(indexPerson).toString());
                    String confirm = ui.enterString("1-for confirming deleting, other- cancel deleting"); //todo использовать ui.readInt()
                    if (Integer.valueOf(confirm) == 1) {
                        personList.remove(personList.get(indexPerson));
                        personList.sort(personComparator);
                        repository.saveList(personList);
                        successDelete = true;
                        break;
                    } else break;
                }
            } catch (NumberFormatException ex) { // todo будет эксепшена, у нас есть метод readInt который обрабатывает эксепшены внутри
                break; //todo что это
            }
        }
        return successDelete;
    }

    public void updatePerson() {
        List<Person> personList = findAll();
        PersonComparator personComparator = new PersonComparator(); //todo сортировку лучше реализовать не здесь а отдельным пунктом меню
        // иначе непонятно, почему пользователь обновляет сотрудника, а при этом еще выполняется сортировка других сотридникв
        // действие должно быть атомарным. и название метода должно совпадать с действием. два в одном (и сортировка и апдейд)
        // лучше не делать
        String id = ui.enterString("Enter the ID for updating");
        int indexPerson;
        for (int i = 0; i < personList.size(); i++) {
            try {
                if (personList.get(i).getId().equals(id)) { //todo те же самые замечания что и в методе delete person.
                    indexPerson = i;
                    ui.printMessage(personList.get(indexPerson).toString());
                    String confirm = ui.enterString("1-for confirming updating, other-cancel updating");
                    if (Integer.valueOf(confirm) == 1) {
                        personList.remove(personList.get(indexPerson));
                        personList.add(createUpdatedPerson(id));
                        personList.sort(personComparator);
                        repository.saveList(personList);
                        ui.printMessage("ID " + id + " has updated successfully");
                        break;
                    } else break;
                }
            } catch (NumberFormatException ex) {
                break;
            }
        }
    }

    public Person createUpdatedPerson(String indexPerson) { //todo этот метод практически полностью дублирует метод createPerson()
        // не должно быть дублирования кода, расположи методы рядом, посмотри на отличие, и нужно либо общую логику вынести
        // в отдельный метод. либо добавить параметр boolean в метод который будет отличать вызываем create() или update()
        Person person;
        String id = indexPerson; //todo зачем дублировать переменную?
        String firstName = getValidatedString("Enter First Name");
        String lastName = getValidatedString("Enter last name");
        int birthYear = enterBirthYear();
        PersonJob job;
        job = Person.setVariableJob(ui.enterString(catalogPersonJobs()));
        double salary = enterSalary();
        person = new Person(id, firstName, lastName, birthYear, job, salary);
        return person;
    }

    public void exitProgram(int zero) { //todo удалить
        if (zero == 0) {
            System.exit(0);
        }
    }
}


