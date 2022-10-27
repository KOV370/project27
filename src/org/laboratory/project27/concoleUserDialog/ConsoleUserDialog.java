package org.laboratory.project27.concoleUserDialog;

import java.io.*;

public class ConsoleUserDialog {
    public static void unloadToConsole(String data) {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        printWriter.write(data);
        printWriter.close();
    }

    public static String readDataFromConsole() {
        String read = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            read =  bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Mistake String readDataFromConsole()");
            e.printStackTrace();
        }
        return read;
    }

}
