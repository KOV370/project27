package org.laboratory.project27.personFileApp;

import org.laboratory.project27.fileUserDialog.FileUserDialog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PersonFileApp {

    public static void unloadOneLineToFile(String lineFromTreeMap, boolean add) {
        FileUserDialog.unloadToFife(lineFromTreeMap, add);
    }

    public static void unloadAllDataFromTreeMapToFile(TreeMap<String, ArrayList> treeMap) { //метод  загрузки из
        //из TreeMap данных с добавленной/измененной новой строко в файл
        Set setFromTreeMap = treeMap.entrySet(); //модуль  загрузки строк в файл
        for (Object i : setFromTreeMap
        ) {
            Map.Entry<String, ArrayList> mapEntry = (Map.Entry) i;
            String lineFromTreeMap = mapEntry.getKey() + "/" + mapEntry.getValue();
            String delete_1 = lineFromTreeMap.replace("[", "");//убирание нарастающих скобок
            String delete_2 = delete_1.replaceAll("]", "");//убирание нарастающих скобок
            PersonFileApp.unloadOneLineToFile(delete_2, true);
        }
    }

    public static TreeMap<String, ArrayList> downloadDataFromFileToTreeMap() { //загрузка данных из файла в treeMap для
        TreeMap<String, ArrayList> treeMap = new TreeMap<>();//добавления, изменения данных
        BufferedReader bufferedReader = null;
        String lineFromFile;
        try {
            bufferedReader = new BufferedReader(new FileReader(FileUserDialog.getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        do {
            lineFromFile = FileUserDialog.downloadFromFile(bufferedReader);//вывод строки

            if (lineFromFile != null && lineFromFile.length() != 0 && !lineFromFile.isEmpty()) {
                int length = lineFromFile.length();// длина строки
                int indexName = lineFromFile.indexOf("/");//индекс отделителя имени от всех данных(в данных тоже есть имя)
                if (indexName == -1) {
                    indexName = 1;
                }
                String name = lineFromFile.substring(0, indexName);//вывод имени - ключ
                String personData = lineFromFile.substring(indexName + 1, length);//нахождение всех данных
                ArrayList<String> arrayList = new ArrayList<>();

                arrayList.add(personData);//создание листа данных - значения
                treeMap.put(name, arrayList);
            }//ввод ключа и значений в treeMap
            else
                break;
        }
        while (lineFromFile != null);
        return treeMap;
    }



}

