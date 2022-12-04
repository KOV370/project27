package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.IllegalValueException;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.repository.PersonFileRepository;
import org.laboratory.project27.service.PersonService;

import java.util.List;

public class RunProgram {
    public static final boolean PROGRAM_EXIT = false;
    public static final boolean CONTINUE_EXECUTION = true;
    private static final String MENU = """
            Menu : 0-exit,
            1-create new record from console, 2-save record to the file
            3-find record from the file by name, 4-find record from the file by ID
            5-find all persons, 6-update the person, 7-delete the person, 8-sort list""";
    private static final String SORTMENU = """
            Menu :
            1-sortById, 2-sortByFirstName
            3-sortByLastName, 4-sortByBirthYear
            5-sortByJob""";
    private final ConsoleUserDialog ui;
    private final PersonFileRepository personFileRepository;
    private final PersonService personService;
    private Person currentPerson;

    public RunProgram() {
        ui = new ConsoleUserDialog();
        personFileRepository = new PersonFileRepository();
        personService = new PersonService(ui, personFileRepository);
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
                    savePerson();
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
                case 8:
                    sortList();
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
                    sortById();
                    break;
                case 2:
                    sortByFirstName();
                    break;
                case 3:
//                        sortByLastName();
                    break;
                case 4:
//                        sortByBirthYear();
                    break;
                case 5:
//                        sortByJob();
                    break;
                case 6:
                    //                    sortBySalary();
                    break;
                default:
                    ui.printMessage("No menu item found");
                    break;
            }
        }
    }


    public void sortById() {//todo хочу сделать общий метод для сортировки с передачей только параметра сортировки
        List<Person> personList;
        if ((personList = personService.sortListById()) != null) {
            ui.printMessage("Sorted successfully");
            boolean confirm = ui.confirm("Enter \"Y\"- or saving, other -cancel");
            if (confirm) {
                personFileRepository.saveAll(personList);
            } else return;
        } else {
            ui.printMessage("Error of sorting.");
        }
    }

    private void sortByFirstName() {
        List<Person> personList;
        if ((personList = personService.sortListByFirstName()) != null) {
            ui.printMessage("Sorted successfully");
            boolean confirm = ui.confirm("Enter \"Y\"- or saving, other -cancel");
            if (confirm) {
                personFileRepository.saveAll(personList);
            } else return;
        } else {
            ui.printMessage("Error of sorting.");
        }
    }


    private void findAllPersons() {
        List<Person> persons = personService.findAll();
        for (Person person : persons) {
            ui.printMessage(person.toString());
        }
    }

    private void findPersonByID() {
        currentPerson = personService.getPersonById(ui.enterString("Enter the ID for downloading."));
//        if (currentPerson != null) {
//            ui.printMessage(currentPerson.toString());
//        }
    }

    private void findPersonByName() {
        currentPerson = personService.getPersonByName(ui.enterString("Enter the name for downloading."));
        if (currentPerson != null) {
            ui.printMessage(currentPerson.toString());
        }
    }

    private void createPerson() {
        ui.printMessage("If you do not know the value - enter 0, but your record wil not save");
        try {
            currentPerson = personService.readPersonFromConsole();
        } catch (IllegalValueException ex) {
            ui.printMessage(ex.getMessage());
        }
        ui.printMessage("Current person is: " + currentPerson);
    }

    private void savePerson() {
        if (currentPerson == null) {
            ui.printMessage("Create person for saving.");
            return;
        }
        Person createdPerson = personService.create(currentPerson);
        if (createdPerson != null) {
            ui.printMessage("Person saved successfully");
        } else {
            ui.printMessage("Error person has not saved");
        }
    }


    private void updatePerson() {
        if (currentPerson == null) {
            ui.printMessage("Create person for saving");
            return;
        }
        String currentId = currentPerson.getId();
        currentPerson = personService.readPersonFromConsole();
        currentPerson.setId(currentId);
        Person updatedPerson = personService.update(currentPerson);
        if (updatedPerson != null) {
            ui.printMessage("Person saved successfully");
        } else {
            ui.printMessage("Error person has not saved");
        }
    }

    public void deletePerson() {
        if (currentPerson == null) {
            ui.printMessage("Current person is null");
            return;
        }
        boolean deleledPerson = false;
        boolean confirm = ui.confirm("Enter \"Y\"- for confirming deleting, other -cancel deleting");
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


//    private void updatePersonOleg() {
//        currentPerson = personService.getPersonById(ui.enterString("Enter the ID for downloading."));
//        if (currentPerson != null) {
//            ui.enterString("01" + currentPerson.toString());
//            String currentId = currentPerson.getId();
//            personService.updateOleg(currentId);
//        } else {
//            ui.enterString("ID did not find. Press enter.");
//        }
//    }





