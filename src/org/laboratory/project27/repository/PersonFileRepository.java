package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//todo повторить для удаления так же как и для апдейта

public class PersonFileRepository {
    public static final String FILE = "C:\\JetBrains Projects\\Project27_laboratory.txt";
    public static final String FILE_PERSON_LAST_ID = "C:\\JetBrains Projects\\Project27_lastID.txt";
    public static final String DELIMITER = "#";

    public List<Person> findAll() {
        return getLines().stream()
                .map(line -> Person.extractPerson(line, DELIMITER))
                .collect(Collectors.toList());
    }

    public Person findPersonByName(String name) {
        List<String> lines = getLines();
        for (String line : lines) {
            Person person = Person.extractPerson(line, DELIMITER);
            if (person.getFirstName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public Person findPersonById(String id) {
        return getLines().stream()
                .map(line -> Person.extractPerson(line, DELIMITER))
                .filter(pers -> pers.getId().equals(id))
                .findFirst()
                .orElse(null);//todo проверить  null
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
        personList.add(person);//todo добавление измененного person,иначе будет запись без этого кода
        if (saveAll(personList)) {
            return person;
        }
        return null;
    }

    public boolean delete(Person person) {
        List<Person> personList = findAll();
        personList = personList.stream().filter(p -> !p.getId().equals(person.getId())).collect(Collectors.toList());
        if (saveAll(personList)) {
            return true;
        }
        return false;
    }

    private String incrementId() {
        int id;
        try {
            id = Integer.parseInt(getLastId()) + 1;
        } catch (NumberFormatException NullPointerException) {
            id = 0;
        }
        return String.valueOf(id);
    }

    public void saveID(String id) {
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


//    public List<Person> delete(String id) {//todo
//        List<Person> personList = findAll();
//        Optional<Person> personStream = personList.stream().filter(n -> n.getId().equals(id)).findFirst();//todo в репозитори
//        if (personStream.isPresent()) {
//            personList.remove(personStream.get());
//        } else {
//            personList = null;
//        }
//        return personList;
//    }
}

