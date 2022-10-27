package org.laboratory.project27.fileUserDialog;
import org.laboratory.project27.person.Person;
import java.awt.desktop.SystemEventListener;
import java.io.*;
import java.util.List;

public class FileUserDialog {
    private static String file = "C:\\JetBrains Projects\\Project27_laboratory.txt";

    public static void unloadToFife(String data, boolean addToData){
        try {
            FileWriter fileWriter = new FileWriter(file,addToData);
            fileWriter.write(data + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("02 -");
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
        FileUserDialog.file = file;
    }
}
