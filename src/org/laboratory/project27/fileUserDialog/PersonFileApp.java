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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String downloadFromFile(){
        BufferedReader bufferedReader1 = null;
        try {
            bufferedReader1 = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String readLine = null;
        try {
            readLine = bufferedReader1.readLine();
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

