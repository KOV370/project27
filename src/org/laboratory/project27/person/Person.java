package org.laboratory.project27.person;

public class Person {
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotEmpty
    private String firstName;
    private String lastName;
    private int birthYear;
    private PersonJob job;
    private double salary;

    public Person() {};

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Person(String firstName, String lastName, int birthYear, PersonJob job, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.job = job;
        this.salary = salary;
    }

    public Person(String firstName, String lastName) {//это требование методички, не использовал
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
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


    public PersonJob setVariableJob(String inputJob) throws PersonException {
        if (inputJob == null || inputJob.isBlank()) {
            this.job = PersonJob.UNKNOWN;
            return PersonJob.UNKNOWN;
        }
        for(PersonJob job : PersonJob.values()){
            if (job.toString().equalsIgnoreCase(inputJob)) {
                this.job = job;
                return job;
            }
        }
        throw new PersonException("No such job.");
    }

}
