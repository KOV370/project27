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
        if (currentPerson != null) {
            ui.printMessage("CurrentPerson = {" + currentPerson + "}");
        } else {
            ui.printMessage("CurrentPerson is null");
        }
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
                case 9:
                    return PROGRAM_EXIT;
                default:
                    ui.printMessage("No menu item found");
                    break;
            }
            return CONTINUE_EXECUTION;
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
        if (currentPerson != null) {
            ui.printMessage(currentPerson.toString());
        }
    }

    private void findPersonByName() {
        currentPerson = personService.getPersonByName(ui.enterString("Enter the name for downloading."));
        if (currentPerson != null) {
            ui.printMessage(currentPerson.toString());
        }
    }

    private void createPerson() {
        currentPerson = personService.readPersonFromConsole();
        ui.printMessage("Current person is: " + currentPerson);
    }

    private void savePerson() {
        if (currentPerson != null) {
            Person createdPerson = personService.create(currentPerson);
            if (createdPerson != null) {
                ui.printMessage("Person saved successfully");
            } else {
                ui.printMessage("Error person has not saved");
            }
        } else {
            ui.printMessage("Create person for saving.");
        }
    }

    private void updatePerson() {
        if (currentPerson != null) {
            String currentId = currentPerson.getId();
            currentPerson = personService.readPersonFromConsole();
            currentPerson.setId(currentId);
            Person updatedPerson = personService.update(currentPerson);
            if (updatedPerson != null) {
                ui.printMessage("Person saved successfully");
            } else {
                ui.printMessage("Error person has not saved");
            }
        } else {
            ui.printMessage("Create person for saving.");
        }


    }

    private void deletePerson() {
        if (!personService.delete()) {
            ui.enterString("ID did not find. Press enter.");
        }
    }

    public boolean confirm() {//todo
        boolean confirm = false;
        String yes = "y";
        if (ui.enterString("Enter number:").equalsIgnoreCase(yes)) {
            confirm = true;
        }
        return confirm;
    }
}





