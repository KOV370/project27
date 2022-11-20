package org.laboratory.project27.model;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return Integer.valueOf(o1.getId()).compareTo(Integer.valueOf(o2.getId()));
    }
}
