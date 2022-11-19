package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.repository.PersonFileRepository;
import org.laboratory.project27.service.PersonService;

import java.util.List;

public class RunProgram {
    public static final boolean PROGRAM_EXIT = false;
    public static final boolean CONTINUE_EXECUTION = true;
    private static final String MENU = """
            Menu
            1-create new record from console, 2-save record to the file
            3-find record from the file by name, 4-find record from the file by ID
            5-find all persons, 6-update the person, 7-delete the person, 9-exit""";
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
        ui.printMessage("==============================");
        int numberMenu = ui.readInt("Make your choice");
        {
            switch (numberMenu) {
                case 1:
                    createPerson();
                    ui.printMessage("Current person is: " + currentPerson);
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
                case 9:
                    return PROGRAM_EXIT;
                default:
                    ui.printMessage("No menu item found");
                    break;
            }
            return CONTINUE_EXECUTION;
        }
    }

    private void deletePerson() {
        if (!personService.deletePerson()) {
            ui.enterString("ID did not find. Press enter.");
        }
    }

    private void updatePerson() {
        personService.updatePerson();
    }

    private void findAllPersons() {
        List<Person> persons = personService.findAll();
        for (Person person : persons) {
            ui.printMessage(person.toString());
        }
    }

    private void findPersonByID() {
        currentPerson = personService.getPersonById(ui.enterString("Enter the ID for downloading."));
        if (currentPerson != null) {
            ui.printMessage(currentPerson.toString());
        }
    }

    private void createPerson() {
        currentPerson = personService.createNewPerson();
    }

    private void savePerson() {
        if (currentPerson != null) {
            personService.add(currentPerson);
        } else
            ui.printMessage("Create person for saving.");
    }

    private void findPersonByName() {
        currentPerson = personService.getPersonByName(ui.enterString("Enter the name for downloading."));
        if (currentPerson != null) {
            ui.printMessage(currentPerson.toString());
        }
    }
}





