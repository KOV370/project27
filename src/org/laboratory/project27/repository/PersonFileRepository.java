package org.laboratory.project27.repository;

import org.laboratory.project27.model.Person;

import java.io.*;

public class PersonFileRepository {
    private static String file = "C:\\JetBrains Projects\\Project27_laboratory.txt";


    public static void unloadToFife(String data, boolean addToData){//todo что такое uNload?)
        try {
            FileWriter fileWriter = new FileWriter(file,addToData);
            fileWriter.write(data + "\n");
            fileWriter.close();
            System.out.println("Data added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String downloadFromFile(BufferedReader bufferedReader){//todo обїединить
        String readLine = null;
        try {
            readLine = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readLine;
    }
      public Person getPersonByName(String name) {//todo
        return null;}
//        String name;
//        String dataPersonFromFile;
//        BufferedReader bufferedReader = null;
//        System.out.println("Enter the name for downloading.");
//        name = ui.readString();
//        try {
//            bufferedReader = new BufferedReader(new FileReader(PersonFileRepository.getFile()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            do {
//                dataPersonFromFile = PersonFileRepository.downloadFromFile(bufferedReader);
//                if (dataPersonFromFile.contains("firstName='" + name)) {
//                    System.out.println(dataPersonFromFile);
//                    System.out.println("Continue choice of menu.");
//                    break;
//                }
//            } while (!dataPersonFromFile.isEmpty() || dataPersonFromFile != null);
//        } catch (NullPointerException r) {
//            System.out.println("The name has not found.");
//            System.out.println("Continue choice of menu.");
//            return;
//        }


        public static String getFile() {
        return file;
    }

    public static void setFile(String file) {
        PersonFileRepository.file = file;
    }




}

