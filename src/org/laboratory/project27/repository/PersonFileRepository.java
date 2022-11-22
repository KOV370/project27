package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;

import javax.sql.rowset.serial.SerialStruct;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Stream;

public class PersonFileRepository {
    public static final String FILE = "C:\\JetBrains Projects\\Project27_laboratory.txt";
    public static final String FILE_PERSON_LAST_ID = "C:\\JetBrains Projects\\Project27_lastID.txt";

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        List<String> lines = getLines();
        for (String line : lines) {
            Person person = extractPerson(line);
            persons.add(person);
        }
        return persons;
    }

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
            convertPerson(fileWriter, person);
            return person;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
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

    public void convertPerson(FileWriter fileWriter, Person person) {
        try {
            fileWriter.write(person.getId() + "#" + person.getFirstName() + "#" + person.getLastName() + "#"
                    + person.getBirthYear() + "#" + person.getJob() + "#" +
                    person.getSalary() + "#" + "\n");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void saveID(String id) {
        try (FileWriter fileWriter = new FileWriter(FILE_PERSON_LAST_ID, false);) {
            fileWriter.write(String.valueOf(id));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void saveList(List<Person> personList) {
        try (FileWriter fileWriter = new FileWriter(FILE, false)) {
            for (Person person : personList) {
                convertPerson(fileWriter, person);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

//    public Person findPersonByIdStream(String id) {//todo почему этот метод показывает ошибку?
//        Person person = null;
//        List<String> lines = getLines();
//        Stream<String> personStream = lines.stream().sorted();
//        Person foundPerson = personStream.filter((line) -> (
//                person = extractPerson(line);
//        String n = person.getId();
//        n.equals(id);))
//
//        return foundPerson;
//    }
}


