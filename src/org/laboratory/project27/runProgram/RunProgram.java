package org.laboratory.project27.runProgram;

import org.laboratory.project27.fileUserDialog.PersonFileApp;
import org.laboratory.project27.person.Person;
import org.laboratory.project27.personConsoleApp.PersonConsoleApp;

public class RunProgram {


    public static void main(String[] args) {
        Person person = PersonConsoleApp.createNewPerson();
        PersonFileApp.unloadToFife(String.valueOf(person),false);
        PersonConsoleApp.downloadFromFile();
    }


    }

