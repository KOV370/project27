package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.repository.PersonFileRepository;
import org.laboratory.project27.service.PersonService;

public class RunProgram {
    public static final boolean PROGRAM_EXIT = false;
    public static final boolean CONTINUE_EXECUTION = true;
    private static final String MENU = """
            Menu
            1-create new record from console
            2-save record to the file
            3-find record from the file
            9-exit""";
    private final ConsoleUserDialog ui;
    private final PersonFileRepository personFileRepository;
    private final PersonService personService;
    private Person currentPerson;

    public RunProgram() { //todo почему объекты создаются в конструкторе?
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
                case 9:
                    return PROGRAM_EXIT;
                default:
                    ui.printMessage("No menu item found");
                    break;
            }
            return CONTINUE_EXECUTION;
        }
    }

    private void createPerson() {
        currentPerson = personService.createNewPerson();
    }

    private void savePerson() {
        try {
            PersonService.writeToTheDocument(PersonFileRepository.FILE, currentPerson, true);
        } catch (Exception e) {
            e.printStackTrace();
        }}

    private void findPersonByName() {
        Person person = personService.getPersonByName(ui.enterString("Enter the name for downloading."));
        if (person == null) {
            ui.printMessage("Person not found");
        } else {
            ui.printMessage(person.toString());
        }
    }
}





