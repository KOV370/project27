package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;

import java.io.*;

public class PersonFileRepository {
    public static final String FILE = "C:\\JetBrains Projects\\Project27_laboratory.txt";
    public static final String FILELASTID = "C:\\JetBrains Projects\\Project27_lastID.txt";

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
        int id = Integer.parseInt(txt[0]);
        String firstName = txt[1];
        String lastName = txt[2];
        int birthYear = Integer.parseInt(txt[3]);
        PersonJob job = PersonJob.valueOf(txt[4]);
        double salary = Double.parseDouble(txt[5]);

        return new Person(id, firstName, lastName, birthYear, job, salary);
    }

    public Person getPersonById(String id) {//todo сделать метод
        return null;
    }

    public int getID() {
        BufferedReader bufferedReader = null;
        int previousId = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(PersonFileRepository.FILELASTID));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            previousId = Integer.valueOf(bufferedReader.readLine());
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void enterID(int id) {
        try {
            FileWriter fileWriter = new FileWriter(FILELASTID, false);
            fileWriter.write(String.valueOf(id));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


