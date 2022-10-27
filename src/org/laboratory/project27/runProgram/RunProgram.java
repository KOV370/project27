package org.laboratory.project27.runProgram;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.personFileApp.PersonFileApp;
import org.laboratory.project27.service.PersonService;

import java.util.ArrayList;

import java.util.TreeMap;

public class RunProgram {
    //TreeMap - основной держатель данных, при запуске программы в него копируются данные из файла,
    //затем файл очищается, в TreeMap происходит добавление/изменение/удаление + сотрировка по имени(ключу)
    //после чего измененные данные загружаются снова в очищенный файл для длительного хранения
    private static TreeMap<String, ArrayList> treeMap = new TreeMap<>(PersonFileApp.downloadDataFromFileToTreeMap());

    public static TreeMap<String, ArrayList> getTreeMap() {
        return treeMap;
    }

    public static void setTreeMap(TreeMap<String, ArrayList> treeMap) {
        RunProgram.treeMap = treeMap;
    }

    public static int getReadnumber() {
        return readnumber;
    }

    public static void setReadnumber(int readnumber) {
        RunProgram.readnumber = readnumber;
    }

    private static int readnumber; //номер выбора меню

    public static void main(String[] args) {
        System.out.println("Choose the number: ");
        System.out.println("1 - add person  or change persons data, 2- delete persons data, ");
        System.out.println("3 - exit");
        System.out.println("Enter the number");

        PersonFileApp.downloadDataFromFileToTreeMap().putAll(treeMap);//загрузка всего списка из файла в TreeMap
        PersonFileApp.unloadOneLineToFile("10/10", false);//очистка файла после загрузки данных в TreeMap
        //вынужденно добавил 10/10,

        while (true) {
            readnumber = Integer.parseInt(ConsoleUserDialog.readDataFromConsole());//номер выбора меню}
            PersonService.choiceMenu();
        }
    }

    }

