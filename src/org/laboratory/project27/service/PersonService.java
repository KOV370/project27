package org.laboratory.project27.service;

import org.laboratory.project27.concoleUserDialog.ConsoleUserDialog;
import org.laboratory.project27.person.Person;
import org.laboratory.project27.person.PersonJob;
import org.laboratory.project27.personConsoleApp.PersonConsoleApp;
import org.laboratory.project27.personFileApp.PersonFileApp;
import org.laboratory.project27.runProgram.RunProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PersonService {


    public static List<String> createListNewPerson(Person person) {
        List<String> arrayListList = new ArrayList<>();
        arrayListList.add("Name: " + person.getFirstName() + ";");
        arrayListList.add("LastName: " + person.getLastName() + ";");
        arrayListList.add("BirthYear: " + person.getBirthYear() + ";");
        arrayListList.add("Job: " + person.getJob() + ".");
        return arrayListList;
    }

    public static void addPersonToTreeMap(TreeMap<String, ArrayList> treeMap) {
        Person person = PersonConsoleApp.createNewPerson();//ввод данных нового сотр с консоли
        ArrayList arrayList = (ArrayList) PersonService.createListNewPerson(person);//создание листа с данными нового сотрудника
        treeMap.put(String.valueOf(person.getFirstName()), arrayList);//запись нового сотрудника в TreeMap
        //ключ - имя, значение - все данные: имя,фамилия, год рожд, должность
    }

    public static void choiceMenu() {
        switch (RunProgram.getReadnumber()) {
            case 1:
                PersonService.addPersonToTreeMap(RunProgram.getTreeMap());//запись нового сотрудника в  TreeMap
                System.out.println("Enter the number");
                break;
            case 2:
                System.out.println("Enter the Name for deleting");
                RunProgram.getTreeMap().remove(ConsoleUserDialog.readDataFromConsole());// удаление обьекта по введенному имени из TreeMap
                System.out.println("Enter the number");
                break;
            case 3: //выход
                PersonFileApp.unloadAllDataFromTreeMapToFile(RunProgram.getTreeMap()); //загрузка данных из TreeMap в файл
                System.out.println("Number of elements - " + RunProgram.getTreeMap().size());
                System.exit(0);
                System.out.println("Program has finished");
                break;
            default:
                System.out.println("Try again");
                break;
        }
    }

}

