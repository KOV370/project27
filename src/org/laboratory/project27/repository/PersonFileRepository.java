package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PersonFileRepository {
    public static final String FILE = "C:\\JetBrains Projects\\Project27_laboratory.txt";
    public static final String FILE_PERSON_LAST_ID = "C:\\JetBrains Projects\\Project27_lastID.txt";

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        List<String> lines = getLinesStream();
        for (String line : lines) {
            Person person = Person.extractPerson(line);
            persons.add(person);
        }
        return persons;
    }

//    public List<Person> findAllStream(){//todo проверить метод, что не так с лямбда
//        List<Person> personList = new ArrayList<>();
//        List<String> stringList = getLinesFromStream();
//        Stream<Person> stringStream = stringList.stream (
//                (String n)->( new Person(extractPerson(n)));
//                personList = stringStream.collect(Collectors.toList());
//        return  personList;
//    }

    public Person findPersonByName(String name) {
        List<String> lines = getLinesStream();
        for (String line : lines) {
            Person person = Person.extractPerson(line);
            if (person.getFirstName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public Person findPersonById(String id) {
        List<String> lines = getLinesStream();
        for (String line : lines) {
            Person person = Person.extractPerson(line);
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }


    public Person findPersonByIdStream(String id) {
        Person person = null;
        List<String> lines = getLinesStream();
        Person foundPerson = lines.stream()
                .map(line -> person.extractPerson(line))
                .filter(pers -> pers.getId().equals(id))
                .findFirst()
                .orElse(null);//todo проверить  null
        return foundPerson;
    }

    private List<String> getLines() { //сделан другой вариант с испльзованием потоков getLinesFromStream
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

    public List<String> getLinesStream() {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(PersonFileRepository.FILE))) {
            Stream<String> linesStream = bufferedReader.lines();
            list = linesStream.collect(Collectors.toList());
        } catch (IOException r) {
            System.out.println("IOException");
        }
        return list;
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
        String delimiter = "#";
        try (FileWriter fileWriter = new FileWriter(FILE, true)) {
            fileWriter.write(Person.convertPerson(person, delimiter));
            return person;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
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
        String delimiter = "#";
        try (FileWriter fileWriter = new FileWriter(FILE, false)) {
            for (Person person : personList) {
                fileWriter.write(Person.convertPerson(person, delimiter));
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public List<Person> listPersons() {
        List<Person> personList = findAll();
        return personList;
    }
}



