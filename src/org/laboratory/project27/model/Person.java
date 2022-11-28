package org.laboratory.project27.model;

public class Person {
    private String firstName;
    private String lastName;
    private int birthYear;
    private PersonJob job;
    private double salary;
    private String id;

    public Person(String id, String firstName, String lastName, int birthYear, PersonJob job, double salary) {
        this.id = id;
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

    public static Person extractPerson(String line, String delimiter) {
        String[] txt = line.split(delimiter);
        String id = txt[0];
        String firstName = txt[1];
        String lastName = txt[2];
        int birthYear = Integer.parseInt(txt[3]);
        PersonJob job = PersonJob.valueOf(txt[4]);
        double salary = Double.parseDouble(txt[5]);
        return new Person(id, firstName, lastName, birthYear, job, salary);
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
}
