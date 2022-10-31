package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.fileUserDialog.PersonFileApp;
import org.laboratory.project27.person.Person;
import org.laboratory.project27.personConsoleApp.PersonConsoleApp;

import java.util.InputMismatchException;

public class RunProgram {
    public static int numberMenu;

    public static void main(String[] args) {
        System.out.println("Choose the number:");
        System.out.println("1-create new record from console, 2-save record to the file");
        System.out.println("3-find record from the file");
        System.out.println("9-exit");

        while (true) {
            try {
                numberMenu = ConsoleUserDialog.readInt();
                choiceMenu(numberMenu);
            } catch (InputMismatchException r) {
                System.out.println("Enter right number");//todo как вернуться в метод при неправильном формате?
                return;
            }
        }
    }
    static  Person correctPerson = new Person();//чтобы новая запись сохрянялась после создания и ожидала либо записи
    //в файл либо продолжения
    static Person person = null;
    private static void choiceMenu(int number) {
        switch (number) {
            case 1:
                person = PersonConsoleApp.createNewPerson();
                if (person == null) {
                    return;
                } else
                { correctPerson = person;
                    break;}
            case 2:
                    PersonFileApp.unloadToFife(String.valueOf(correctPerson), true);
                break;
            case 3:
                PersonConsoleApp.downloadFromFile();
                break;
            case 9:
                System.exit(0);
            default:
                System.out.println("Mistake with entering.");
                break;
        }
    }


}




