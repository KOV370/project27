package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;

import javax.sql.rowset.serial.SerialStruct; //todo optimize imports
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Stream; //todo doesn't used

public class PersonFileRepository {
    public static final String FILE = "Project27_laboratory.txt";
    public static final String FILE_PERSON_LAST_ID = "Project27_lastID.txt";

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

    public Person findPersonByIdStream(String id) {//todo пробуй так
        Person foundPerson = getLines().stream()
                .map(line -> extractPerson(line))
                .filter(pers -> pers.getId().equals(id))
                .findFirst()
                .orElse(null);

        return foundPerson;
//        Stream<String> personStream = lines.stream().sorted();
//        Person foundPerson = personStream.filter((line) -> (
//                person = extractPerson(line);
//        String n = person.getId();
//        n.equals(id);))
//
//        return foundPerson;
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

    public Person extractPerson(String line) { //todo этот метод тоже можно перенести в класс Person
        String[] txt = line.split("#");
        String id = txt[0];
        String firstName = txt[1];
        String lastName = txt[2];
        int birthYear = Integer.parseInt(txt[3]);
        PersonJob job = PersonJob.valueOf(txt[4]);
        double salary = Double.parseDouble(txt[5]);
        return new Person(id, firstName, lastName, birthYear, job, salary);
    }

    public void convertPerson(FileWriter fileWriter, Person person) { // todo  этот метод не должен принимать fileWriter,
        // только Person, и должен возвращать String convertedPesrson = person.getId() + "#" + person.getFirstName() ...
        // вообще этот метот так же можно разместить в классе Person, акак параметер передавать делимитер "#"
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

}


