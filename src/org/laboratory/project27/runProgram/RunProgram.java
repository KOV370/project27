package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.repository.PersonFileRepository;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.service.PersonService;

import java.io.File;
import java.io.FileWriter;
import java.util.InputMismatchException;


public class RunProgram {
    private static final String MENU = """
            Menu
            1-create new record from console
            2-save record to the file
            3-find record from the file
            9-exit     
            """;
    public static int numberMenu;
    static Person correctPerson = new Person();//чтобы новая запись сохрянялась после создания и ожидала либо записи
    static Person person = null;
    private final ConsoleUserDialog ui = new ConsoleUserDialog();
    private final PersonFileRepository personFileRepository = new PersonFileRepository();
    private final PersonService personService = new PersonService(ui, personFileRepository);
    //в файл либо продолжения
 //   private final FileWriter fileWriter = new FileWriter(PersonFileRepository.FILE);

    public static void main(String[] args) {
        new RunProgram().runMenu();
    }

    private void runMenu() {
        ui.printMessage(MENU);
        while (true) {
            try {
                numberMenu = ui.readInt("Make your choice.");
                choiceMenu(numberMenu);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void choiceMenu(int number) throws Exception {
        switch (number) {
            case 1:
                person = personService.createNewPerson();
                if (person == null) {
                    return;
                } else {
                    correctPerson = person;
                    break;
                }
            case 2:
                PersonService.writeToTheDocument(PersonFileRepository.FILE, correctPerson, true);
                //todo убрать ссылку на PersonFileRepository заменить на метод personService.create()
                break;
            case 3:
                personService.getPersonByName(ui.enterString("Enter the name for downloading."));
                break;
            case 9:
                System.exit(0);
            default:
                System.out.println("Mistake with entering.");
                break;
        }
    }


}




