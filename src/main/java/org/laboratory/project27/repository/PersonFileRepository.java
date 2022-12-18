package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonFileRepository {
    public static final String FILE = "C:\\JetBrains Projects\\Project27_laboratory.txt";
    public static final String FILE_PERSON_LAST_ID = "C:\\JetBrains Projects\\Project27_lastID.txt";
    public static final String DELIMITER = "#";

    public List<Person> findAll() {
        return getLines().stream()
                .flatMap(line ->
                        Person.extractPerson(line, DELIMITER).stream().filter(Objects::nonNull)
                )
                .collect(Collectors.toList());
    }

    //todo variant 2
    // https://www.baeldung.com/java-filter-stream-of-optional
//    public List<Person> findAll() {
//        return getLines().stream()
//                .map(line -> Person.extractPerson(line, DELIMITER))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }

    //todo variant 3, java 9
//    public List<Person> findAll() {
//        return getLines().stream()
//                .map(line -> Person.extractPerson(line, DELIMITER))
//                .flatMap(Optional::stream)
//                .collect(Collectors.toList());
//    }

    public Person findPersonByName(String name) {
        List<String> lines = getLines();
        for (String line : lines) {
            Optional<Person> optionalPerson = Person.extractPerson(line, DELIMITER);
            //todo попробуй так же сделать вариант без использования for и if, а чисто через стримы.
            // см примеры выше в комментариях строки 26 и 36
            // if  optionalPerson.isPresent() плохо т.к. это не фукнциональный стиль, а стиль java7
            if (optionalPerson.isPresent() && name.equals(optionalPerson.get().getFirstName())) {
                return optionalPerson.get();
            }
        }
        return null;
    }

    public Person findPersonById(String id) { //todo попробуй так же сделать дополнительно два варианта реализации,
        // по аналагии с двумя моими примерами выше в комментариях, строки 26 и 36

        return getLines().stream()
                .flatMap(line -> Person.extractPerson(line, DELIMITER).stream().filter(Objects::nonNull))
                .filter(pers -> pers.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader(PersonFileRepository.FILE))) {
            Stream<String> linesStream = bufferedReader.lines();
            lines = linesStream.collect(Collectors.toList());
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
        person.setId(incrementId());
        try (FileWriter fileWriter = new FileWriter(FILE, true)) {
            fileWriter.write(Person.convertPerson(person, DELIMITER));
            saveID(person.getId());
            return person;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
    }

    public Person updateAppend(Person person) {
        try (FileWriter fileWriter = new FileWriter(FILE, true)) {
            fileWriter.write(Person.convertPerson(person, DELIMITER));
            return person;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
    }

    public Person update(Person person) {
        List<Person> personList = findAll().stream()
                .filter(p -> !p.getId().equals(person.getId()))
                .collect(Collectors.toList());
        personList.add(person);
        if (saveAll(personList)) {
            return person;
        }
        return null;
    }

    public boolean delete(Person person) {
        List<Person> personList = findAll().stream()
                .filter(p -> !p.getId().equals(person.getId()))
                .collect(Collectors.toList());
        if (saveAll(personList)) { //todo if statement can be simplified
            return true;
        }
        return false;
    }

    private String incrementId() {
        int id = 0; //todo Warning:(136, 18) Variable 'id' initializer '0' is redundant
        try {
            id = Integer.parseInt(getLastId()) + 1;
        } catch (NumberFormatException NullPointerException) {
            id = 0; //todo Warning:(140, 13) Variable is already assigned to this value
        }
        return String.valueOf(id);
    }

    public void saveID(String id) { //todo Warning:(146, 80) Unnecessary semicolon ';'
        try (FileWriter fileWriter = new FileWriter(FILE_PERSON_LAST_ID, false);) {
            fileWriter.write(String.valueOf(id));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public boolean saveAll(List<Person> personList) {
        boolean successful = true;
        try (FileWriter fileWriter = new FileWriter(FILE, false)) {
            for (Person person : personList) {
                fileWriter.write(Person.convertPerson(person, DELIMITER));
            }
        } catch (IOException ex) {
            System.err.println(ex);
            successful = false;
        }
        return successful;
    }

}
