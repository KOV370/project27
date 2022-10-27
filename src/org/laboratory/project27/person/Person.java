package org.laboratory.project27.person;

public class Person {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotEmpty
    private String firstName;
    private String lastName;
    private int birthYear;
    private PersonJob job;

    public Person(String firstName, String lastName, int birthYear, PersonJob job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.job = job;
    }

    Person(String firstName, String lastName) {//это требование методички, не использовал
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Person readFromFile() {
        return null;
    }

    public static void writeToFile(Person person) {

    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthYear=" + birthYear +
                ", job=" + job +
                '}';
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
