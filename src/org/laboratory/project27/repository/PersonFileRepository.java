package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonException;
import org.laboratory.project27.model.PersonJob;

import java.io.*;

public class PersonFileRepository {
    public static final String FILE = "C:\\JetBrains Projects\\Project27_laboratory.txt";


    public Person getPersonByName(String name) {
        Person person;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(PersonFileRepository.FILE))) {
            while ((line = bufferedReader.readLine()) != null && !line.isBlank()) {
                person = extractPerson(line);
                if (person.getFirstName().equals(name)) {
                    return person;
                }
            }
        } catch (IOException r) {
            System.out.println("IOException");
        }
        return null;
    }

    public Person extractPerson(String line) {
        String[] txt = line.split("#");
        String firstName = txt[0];
        String lastName = txt[1];
        int birthYear = Integer.parseInt(txt[2]);
        PersonJob job = PersonJob.valueOf(txt[3]);
        double salary = Double.parseDouble(txt[4]);
        int id = Integer.parseInt(txt[5]);
        return new Person(firstName, lastName, birthYear, job, salary, id);
    }

    public Person getPersonById(String id) {//todo сделать метод
        return null;
    }

    public Person create(Person person) {
        try (FileWriter fileWriter = new FileWriter(FILE, true)) {
            fileWriter.write(person.getFirstName() + "#" + person.getLastName() + "#"
                    + person.getBirthYear() + "#" + person.getJob() + "#" +
                    person.getSalary() + "#" + person.getId() + "#" + "\n");
            return person;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
    }
}


