package org.laboratory.project27.repository;

import org.laboratory.project27.db.MySqlConnection;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepository {
    private static final String QUERYFINDALL = """
            SELECT*FROM person
            """;
    private static final String QUERYFINDBYNAME = """
            SELECT * FROM person
            WHERE firstName=
            """;
    private static final String QUERYFINDBYID = """
            SELECT * FROM person
            WHERE id=
            """;

    public List<Person> findAll() {
        List<Person> personList = null;
        try {
            Connection conn = MySqlConnection.getConnection();
            Statement statement = conn.createStatement();
            Optional<ResultSet> optional = Optional.ofNullable(statement.executeQuery(QUERYFINDALL));
            if (optional.isPresent()) {
                personList = putPersonsFromSql(optional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public List<Person> findPersonByName(String name) {
        List<Person> newPerson = null;
        try {
            Connection connection = MySqlConnection.getConnection();
            Statement statement = connection.createStatement();
            Optional<ResultSet> optional = Optional.ofNullable(statement.executeQuery(QUERYFINDBYNAME + "\'" + name + "\'"));
            if (optional.isPresent()) {
                newPerson = putPersonsFromSql(optional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPerson;
    }

    public Person findPersonById(String id) {
        Person newPerson = null;
        try {
            Connection connection = MySqlConnection.getConnection();
            Statement statement = connection.createStatement();
            Optional<ResultSet> optional = Optional.ofNullable(statement.executeQuery(QUERYFINDBYID + "\'" + id + "\'"));
            if (optional.isPresent()) {
                try {
                    newPerson = putPersonsFromSql(optional).get(0);
                } catch (IndexOutOfBoundsException ex) {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPerson;
    }


    public List<Person> putPersonsFromSql(Optional<ResultSet> optional) {
        List<Person> newPerson = new ArrayList<>();
        try {
            while (optional.get().next()) {
                String firstName = optional.get().getString("firstName");
                String id = optional.get().getString("id");
                String lastName = optional.get().getString("lastName");
                int birthYear = optional.get().getInt("birthYear");
                PersonJob job = Person.setVariableJob(optional.get().getString("job"));
                double salary = optional.get().getDouble("salary");
                Person person = new Person(id, firstName, lastName, birthYear, job, salary);
                newPerson.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPerson;
    }
}
