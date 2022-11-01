package org.laboratory.project27.repository;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;
import org.laboratory.project27.runProgram.RunProgram;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public Person getPersonByName() {
        ConsoleUserDialog consoleUserDialog = new ConsoleUserDialog();
        String name;
        String dataPersonFromFile;
        BufferedReader bufferedReader = null;
        Person person = null;
        System.out.println("Enter the name for downloading.");
        name = consoleUserDialog.readString();
        try {
            bufferedReader = new BufferedReader(new FileReader(PersonFileRepository.FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            do {
                dataPersonFromFile = bufferedReader.readLine();
                if (dataPersonFromFile.contains("firstName=" + name)) {
                    person = returnFoundPerson(dataPersonFromFile);
                    break;
                }
            } while (!dataPersonFromFile.isEmpty() || dataPersonFromFile != null);
        } catch (NullPointerException r) {
            System.out.println("The name has not found.");
            System.out.println("Continue choice of menu.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    public Person returnFoundPerson(String line) {
        int[] arraysFirst = new int[5];
        int[] arraysEnd = new int[5];
        int firstIndex = 0;
        int secondIndex = 0;
        for (int i = 0; i < 5; i++) {
            firstIndex =  line.indexOf("=", firstIndex+1);
            arraysFirst[i] = firstIndex;
        }
        {for (int j = 0; j < 4; j++) {
            secondIndex = line.indexOf(",", secondIndex+1);
            arraysEnd[j] = secondIndex;
        }
        arraysEnd[4] = line.length();}
        String firstName = line.substring(arraysFirst[0] + 1, arraysEnd[0]);
        String lastName = line.substring(arraysFirst[1] + 1, arraysEnd[1]);
        int birthYear = Integer.parseInt(line.substring(arraysFirst[2] + 1, arraysEnd[2]));
        PersonJob job = PersonJob.valueOf(line.substring(arraysFirst[3] + 1, arraysEnd[3]));
        double salary =Double.parseDouble(line.substring(arraysFirst[4] + 1, arraysEnd[4]));
        Person person = new Person(firstName, lastName, birthYear, job, salary);
        System.out.println(person);
        return person;
    }
}

