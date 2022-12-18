package org.laboratory.project27.model;

import java.util.Optional;

public class Person {
    private String firstName;
    private String lastName;
    private int birthYear;
    private PersonJob job;
    private double salary;
    private String id;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public Person(String id, String firstName, String lastName, int birthYear, PersonJob job, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.job = job;
        this.salary = salary;
    }

    public Person(String firstName, String lastName, int birthYear, PersonJob job, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.job = job;
        this.salary = salary;
    }

    public static PersonJob setVariableJob(String inputJob) {
        if (inputJob == null || inputJob.isBlank()) {
            return PersonJob.UNKNOWN;
        }
        for (PersonJob job : PersonJob.values()) {
            if (job.toString().equalsIgnoreCase(inputJob)) {
                return job;
            }
        }
        return PersonJob.UNKNOWN;
    }

    public static Optional<Person> extractPerson(String line, String delimiter) {
        try {
            String[] txt = line.split(delimiter);
            String id = txt[0];
            String firstName = txt[1];
            String lastName = txt[2];
            int birthYear = Integer.parseInt(txt[3]);
            PersonJob job = PersonJob.valueOf(txt[4]);
            double salary = Double.parseDouble(txt[5]);
            return Optional.ofNullable(new Person(id, firstName, lastName, birthYear, job, salary));
        } catch (ArrayIndexOutOfBoundsException ex) {
            return Optional.empty();
        }
    }

    public static String convertPerson(Person person, String delimiter) {
        String convertedPerson;
        convertedPerson = person.getId() + delimiter + person.getFirstName() + delimiter + person.getLastName() + delimiter
                + person.getBirthYear() + delimiter + person.getJob() + delimiter + person.getSalary() + delimiter + "\n";
        return convertedPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", firstName=" + firstName +
                        ", lastName=" + lastName +
                        ", birthYear=" + birthYear +
                        ", job=" + job +
                        ", salary=" + salary;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public PersonJob getJob() {
        return job;
    }

    public void setJob(PersonJob job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (birthYear != person.birthYear) return false;
        if (Double.compare(person.salary, salary) != 0) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (job != person.job) return false;
        return id != null ? id.equals(person.id) : person.id == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + birthYear;
        result = 31 * result + (job != null ? job.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
