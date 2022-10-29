package org.laboratory.project27.runProgram;

import org.laboratory.project27.fileUserDialog.PersonFileApp;
import org.laboratory.project27.person.Person;
import org.laboratory.project27.personConsoleApp.PersonConsoleApp;

public class RunProgram {

// todo не релазировано меню чтобы пользователь выбирал 1 вывод текущей записи. 2 ввод новой записи с консоли. 3 изменение записи, 5 чтение или 6 сохранение в файл.
    public static void main(String[] args) { //todo классы PersonConsoleApp и RunProgram можно объенить,
        // либо в RunProgram оставить только одну строку с созданием объекта PersonConsoleApp и  вызовом его метода
        Person person = PersonConsoleApp.createNewPerson();
        PersonFileApp.unloadToFife(String.valueOf(person),false);
        PersonConsoleApp.downloadFromFile();
    }


    } //todo format ctrl-alt-l

