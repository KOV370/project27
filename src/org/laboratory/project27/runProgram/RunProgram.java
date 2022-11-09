package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.repository.PersonFileRepository;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.service.PersonService;

import java.io.File;
import java.io.FileWriter;
import java.util.InputMismatchException;


public class RunProgram {
    public static final boolean PROGRAM_EXIT = false;
    public static final boolean CONTINUE_EXECUTION = true;
    private static final String MENU = """
            Menu
            1-create new record from console
            2-save record to the file
            3-find record from the file
            9-exit     
            """;
    private final ConsoleUserDialog ui;
    private final PersonFileRepository personFileRepository;
    private final PersonService personService;
    //    public static int numberMenu;
//    private static Person correctPerson = new Person();//чтобы новая запись сохрянялась после создания и ожидала либо записи
//    private static Person person = null;
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
//        while (true) {
//            try {
//                numberMenu = ui.readInt("Make your choice.");
//                choiceMenu(numberMenu);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return;
//            }
//        }
    }

    private boolean choiceMenu() {
        ui.printMessage("==============================");
        ui.printMessage("Current person is: " + currentPerson);
        int numberMenu = ui.readInt("Make your choice");
        {
            switch (numberMenu) {
                case 1:
//                person = personService.createNewPerson();
//                if (person == null) {
//                    return;
//                } else {
//                    correctPerson = person;
                    createPerson();
                    break;

                case 2:
                    //    PersonService.writeToTheDocument(PersonFileRepository.FILE, currentPerson, true);
                    //todo убрать ссылку на PersonFileRepository заменить на метод personService.create()
                    savePerson();
                    break;
                case 3:
                    //             personService.getPersonByName(ui.enterString("Enter the name for downloading."));
                    findPersonByName();
                    break;
                case 9:
                    return PROGRAM_EXIT;
                default:
                    System.out.println("No menu item found"); //todo use ui.printMessage()
                    break;
            }
            return CONTINUE_EXECUTION;
        }
    }
        private void createPerson () {
            currentPerson = personService.createNewPerson();
        }
        private void savePerson ()  {
            try {
                personService.writeToTheDocument(PersonFileRepository.FILE, currentPerson, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // PersonFileRepository.unloadToFife(String.valueOf(currentPerson), true);
            //todo убрать ссылку на PersonFileRepository заменить на метод personService.create()
        }
        private void findPersonByName () {
            Person person = personService.getPersonByName(ui.enterString("Enter the name for downloading."));

            if (person == null) {
                ui.printMessage("Person not found");
            } else {
                ui.printMessage(person.toString());
            }
        }
    }





