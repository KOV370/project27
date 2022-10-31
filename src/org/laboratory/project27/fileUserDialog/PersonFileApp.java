package org.laboratory.project27.fileUserDialog;

import java.io.*;
import java.util.*;

public class PersonFileApp {
    private static String file = "C:\\JetBrains Projects\\Project27_laboratory.txt";


    public static void unloadToFife(String data, boolean addToData){
        try {
            FileWriter fileWriter = new FileWriter(file,addToData);
            fileWriter.write(data + "\n");
            fileWriter.close();
            System.out.println("Data added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String downloadFromFile(BufferedReader bufferedReader){
        String readLine = null;
        try {
            readLine = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readLine;
    }

    public static String getFile() {
        return file;
    }

    public static void setFile(String file) {
        PersonFileApp.file = file;
    }




}

