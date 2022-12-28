package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.IllegalValueException;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.repository.PersonFileRepository;
import org.laboratory.project27.repository.PersonRepository;
import org.laboratory.project27.service.PersonService;

import java.util.List;

public class RunProgram {
    public static final boolean PROGRAM_EXIT = false;
    public static final boolean CONTINUE_EXECUTION = true;
    private static final String MENU = """
            Menu : 0-exit,
            1-create new record from console, 2-sort list
            3-find record from the file by name, 4-find record from the file by ID
            5-find all persons, 6-update the person, 7-delete the person """;
    private static final String SORTMENU = """
            Menu :
            1-sortById, 2-sortByFirstName
            3-sortByBirthYear, 4-sortByJob
            5-sortBySalary""";


    private final ConsoleUserDialog ui;
    private final PersonFileRepository personFileRepository;
    private final PersonService personService;
    private final PersonRepository personRepository;
    private Person currentPerson;


    public RunProgram() {
        ui = new ConsoleUserDialog();
        personFileRepository = new PersonFileRepository();
        personRepository = new PersonRepository();
        personService = new PersonService(ui, personFileRepository, personRepository);
    }

    public static void main(String[] args) {
        new RunProgram().runMenu();
    }

    private void runMenu() {
        boolean continueExecution;
        ui.printMessage(MENU);
        do {
            continueExecution = choiceMenu();
        } while (continueExecution);
    }

    private boolean choiceMenu() {
        ui.printMessage("CurrentPerson = {" + currentPerson + "}");
        int numberMenu = ui.readInt("Make your choice");
        {
            switch (numberMenu) {
                case 1:
                    createPerson();
                    break;
                case 2:
                    sortList();
                    break;
                case 3:
                    findPersonByName();
                    break;
                case 4:
                    findPersonByID();
                    break;
                case 5:
                    findAllPersons();
                    break;
                case 6:
                    updatePerson();
                    break;
                case 7:
                    deletePerson();
                    break;
                case 0:
                    return PROGRAM_EXIT;
                default:
                    ui.printMessage("No menu item found");
                    break;
            }
            return CONTINUE_EXECUTION;
        }
    }

    private void sortList() {
        ui.printMessage(SORTMENU);
        int numberMenu = ui.readInt("Make your choice");
        {
            switch (numberMenu) {
                case 1:
                    sortByParam("id");
                    break;
                case 2:
                    sortByParam("firstName");
                    break;
                case 3:
                    sortByParam("birthYear");
                    break;
                case 4:
                    sortByParam("job");
                    break;
                case 5:
                    sortByParam("salary");
                    break;
                default:
                    ui.printMessage("No menu item found");
                    break;
            }
        }
    }

    public void sortByParam(String sortParam) {
        List<Person> persons = personService.sortAllBy(sortParam);
        for (Person person : persons) {
            ui.printMessage(String.valueOf(person));
        }
    }

    private void findAllPersons() {
        List<Person> persons = personService.findAll();
        for (Person person : persons) {
            ui.printMessage("CurrentPerson = {" + person + "}");
        }
    }

    private void findPersonByID() {
        currentPerson = personService.getPersonById(ui.enterString("Enter the ID for downloading."));
        if (currentPerson == null) {
            ui.printMessage("ID not found");
        }
    }

    private void findPersonByName() {
        List<Person> currentPerson = personService.getPersonByName(ui.enterString("Enter the name for downloading."));
        for (Person person : currentPerson) {
            ui.printMessage("CurrentPerson = {" + person + "}");
        }
    }

    private void createPerson() {
        ui.printMessage("If you do not know the value - enter 0, but your record wil not save");
        try {
            currentPerson = personRepository.create(personService.readPersonFromConsole());
        } catch (IllegalValueException ex) {
            ui.printMessage(ex.getMessage());
        }
        ui.printMessage("Current person is: " + currentPerson);
    }

    private void updatePerson() {
        currentPerson = personService.getPersonById(ui.enterString("Enter the ID for downloading."));
        if (currentPerson == null) {
            ui.printMessage("ID not found");
        } else {
            String id = currentPerson.getId();
            personService.update(id);
        }
    }

    public void deletePerson() {
        currentPerson = personService.getPersonById(ui.enterString("Enter the ID for deleting."));
        if (currentPerson == null) {
            ui.printMessage("Current person is null");
            return;
        }
        boolean deleledPerson = false;
        boolean confirm = ui.confirm(currentPerson + "\n" + "Enter \"Y\"- for confirming deleting, other -cancel deleting");
        if (confirm) {
            deleledPerson = personService.delete(currentPerson);
        } else
            ui.printMessage("Deleting was cancelled");
        if (deleledPerson) {
            ui.printMessage("Deleted successfully");
        } else {
            ui.printMessage("Error deleting");
        }
    }
}


//    private void updatePerson() {
//        if (currentPerson == null) {
//            ui.printMessage("Create person for saving");
//            return;
//        }
//        String currentId = currentPerson.getId();
//        currentPerson = personService.readPersonFromConsole();
//        currentPerson.setId(currentId);
//        Person updatedPerson = personService.update(currentPerson);
//        if (updatedPerson != null) {
//            ui.printMessage("Person saved successfully");
//        } else {
//            ui.printMessage("Error person has not saved");
//        }
//    }


//    public void sortByParam(String sortParam) {
//        List<Person> personList = personService.sortAllBy(sortParam);
//        if (personList != null) {
//            ui.printMessage("Sorted successfully");
//            boolean confirm = ui.confirm("Enter \"Y\"- or saving, other -cancel");
//            if (confirm) {
//                personFileRepository.saveAll(personList);
//            }
//        } else {
//            ui.printMessage("Error of sorting.");
//        }
//    }

    //    private void savePerson() {
//        if (currentPerson == null) {
//            ui.printMessage("Create person for saving.");
//            return;
//        }
//        Person createdPerson = personService.create(currentPerson);
//        if (createdPerson != null) {
//            ui.printMessage("Person saved successfully");
//        } else {
//            ui.printMessage("Error person has not saved");
//        }
//    }










