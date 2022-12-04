package org.laboratory.project27.model;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return String.valueOf(p1.getId()).compareTo(String.valueOf(p2.getId()));
    }
}
