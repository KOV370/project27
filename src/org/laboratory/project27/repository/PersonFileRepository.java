package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;

import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class PersonFileRepository {
    public static final String FILE = "C:\\JetBrains Projects\\Project27_laboratory.txt";
    public static final String FILE_PERSON_LAST_ID = "C:\\JetBrains Projects\\Project27_lastID.txt";

    public Person findPersonByName(String name) {
        List<String> lines = getLines();
        for (String line : lines) {
            Person person = extractPerson(line);
            if (person.getFirstName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    private List<String> getLines() {
        List<String> lines = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(PersonFileRepository.FILE))) {
            while ((line = bufferedReader.readLine()) != null && !line.isBlank()) {
                lines.add(line);
            }
        } catch (IOException r) {
            System.out.println("IOException");
        }
        return lines;
    }

    public Person findPersonById(String id) {
        List<String> lines = getLines();
        for (String line : lines) {
            Person person = extractPerson(line);
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        List<String> lines = getLines();
        for (String line : lines) {
            Person person = extractPerson(line);
            persons.add(person);
        }
        return persons;
    }

    public Person extractPerson(String line) {
        String[] txt = line.split("#");
        String id = txt[0];
        String firstName = txt[1];
        String lastName = txt[2];
        int birthYear = Integer.parseInt(txt[3]);
        PersonJob job = PersonJob.valueOf(txt[4]);
        double salary = Double.parseDouble(txt[5]);
        return new Person(id, firstName, lastName, birthYear, job, salary);
    }

    public String getLastId() {
        String previousId = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PersonFileRepository.FILE_PERSON_LAST_ID))) {
            previousId = (bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException");
        } catch (IOException e) {
            System.err.println("IOException");
        }
        return previousId;
    }

    public Person create(Person person) {
        try (FileWriter fileWriter = new FileWriter(FILE, true)) {
            fileWriter.write(person.getId() + "#" + person.getFirstName() + "#" + person.getLastName() + "#"
                    + person.getBirthYear() + "#" + person.getJob() + "#" +
                    person.getSalary() + "#" + "\n");
            return person;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
    }

    public void saveID(String id) {
        try(FileWriter fileWriter = new FileWriter(FILE_PERSON_LAST_ID, false);) {
            fileWriter.write(String.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveList(List<Person> personList) {
        try (FileWriter fileWriter = new FileWriter(FILE, false)) {
            for (Person person : personList) {
                fileWriter.write(person.getId() + "#" + person.getFirstName() + "#" + person.getLastName() + "#"
                        + person.getBirthYear() + "#" + person.getJob() + "#" +
                        person.getSalary() + "#" + "\n");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}


