package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.repository.PersonFileRepository;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.service.PersonService;

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
    //в файл либо продолжения
    static Person person = null;
    private final ConsoleUserDialog ui = new ConsoleUserDialog();
    private final PersonFileRepository personFileRepository = new PersonFileRepository();
    private final PersonService personService = new PersonService(ui,personFileRepository);

    public static void main(String[] args) {
        new RunProgram().runMenu();
    }

    private void runMenu() {
        ui.printMessage(MENU);
        while (true) {
            try {
                numberMenu = ui.readInt("Make your choice.");
                choiceMenu(numberMenu);
            } catch (InputMismatchException r) {
                System.out.println("Enter right number");//todo как вернуться в метод при неправильном формате?
                return;
            }
        }
    }

    private void choiceMenu(int number) {
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
                PersonFileRepository.unloadToFife(String.valueOf(correctPerson), true);
                break;
            case 3:
                //        PersonService.downloadFromFile();
                break;
            case 9:
                System.exit(0);
            default:
                System.out.println("Mistake with entering.");
                break;
        }
    }


}




