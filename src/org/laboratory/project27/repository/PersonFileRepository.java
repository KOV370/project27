package org.laboratory.project27.repository;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonException;
import org.laboratory.project27.model.PersonJob;

import java.io.*;

public class PersonFileRepository {
    private static final String FILE = "C:\\JetBrains Projects\\Project27_laboratory.txt";
    public static void unloadToFife(String data, boolean addToData) {//todo что такое uNload?)
        try {
            FileWriter fileWriter = new FileWriter(FILE, addToData);
            fileWriter.write(data + "\n");
            fileWriter.close();
            System.out.println("Data added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person getPersonByName(String name) {
        Person person = null;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PersonFileRepository.FILE))) {
            while ((line = bufferedReader.readLine()) != null && !line.isBlank()
                    && (person == null)) {
                person = getPersonByName(line, name);
            }
        } catch (IOException r) {

            System.out.println("IOException");
        }
        return person;
    }

    public Person getPersonByName(String line, String name) {
        String[] txt = line.split("#");
        String firstName = txt[0];
        if (!firstName.equals(name)) {
            return null;
        }
        String lastName = txt[1];
        int birthYear = Integer.parseInt(txt[2]);
        PersonJob job = PersonJob.valueOf(txt[3]);
        double salary = Double.parseDouble(txt[4]);
        Person person = new Person(firstName, lastName, birthYear, job, salary);
        System.out.println(person);
        return person;
    }
}


