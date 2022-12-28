package org.laboratory.project27.repository;

import org.laboratory.project27.db.MySqlConnection;
import org.laboratory.project27.model.Person;
import org.laboratory.project27.model.PersonJob;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepository {
    private static final String QUERY_FIND_ALL = "SELECT * FROM person";
    private static final String QUERY_FIND_BY_NAME = "SELECT * FROM person WHERE firstName = ";
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM person WHERE id = ";
    private static final String CREATE_NEW_PERSON = "INSERT INTO person(id, firstName, lastName, birthYear, job, salary) " +
            "values(?,?,?,?,?,?)";
    private static final String QUERY_CREATE_ID = "SELECT id FROM person";
    private static final String QUERY_UPDATE = "UPDATE person " +
            "SET firstName = ?, lastName = ?, birthYear = ?, job = ?, salary = ? WHERE id = ";
    private static final String QUERY_SORT_BY = "SELECT * FROM person ORDER BY ";
    private static final String QUERY_DELETE = "DELETE FROM person WHERE id = ";

    public List<Person> findAll() {
        List<Person> personList = null;
        try (Connection conn = MySqlConnection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_FIND_ALL)) {
            personList = extractPersonsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public List<Person> findPersonByName(String name) {
        List<Person> newPerson = null;
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_FIND_BY_NAME + "\'" + name + "\'")) {
            newPerson = extractPersonsFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPerson;
    }

    public Optional<Person> findPersonById(String id) {
        Optional<Person> person = Optional.empty();
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_FIND_BY_ID + "\'" + id + "\'")) {
            List<Person> personList = extractPersonsFromResultSet(resultSet);
            if (personList.size() > 0) {
                person = Optional.ofNullable(personList.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public List<Person> extractPersonsFromResultSet(ResultSet resultSet) {
        List<Person> persons = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String id = resultSet.getString("id");
                String lastName = resultSet.getString("lastName");
                int birthYear = resultSet.getInt("birthYear");
                PersonJob job = Person.setVariableJob(resultSet.getString("job"));
                double salary = resultSet.getDouble("salary");
                Person person = new Person(id, firstName, lastName, birthYear, job, salary);
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public Person create(Person person) {
        String id = createNewId();
        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_PERSON)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setInt(4, person.getBirthYear());
            preparedStatement.setString(5, String.valueOf(person.getJob()));
            preparedStatement.setDouble(6, person.getSalary());
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return person;
    }

    private String createNewId() {
        int id;
        int max = 0;
        try {
            Connection conn = MySqlConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_CREATE_ID);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                if (id > max) {
                    max = id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(max + 1);
    }

    public Person update(String id, Person person) {
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE + "\'" + id + "\'")) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getBirthYear());
            preparedStatement.setString(4, String.valueOf(person.getJob()));
            preparedStatement.setDouble(5, person.getSalary());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public List<Person> sortAllBy(String sortParam) { //todo сортирует только на экране, как сделать сортировку в таблице SQL?
        List<Person> personList = null;
        try {
            Connection connection = MySqlConnection.getConnection();
            Statement statement = connection.createStatement();
           ResultSet resultSet =  statement.executeQuery(QUERY_SORT_BY + sortParam);
            personList = extractPersonsFromResultSet(resultSet);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }return personList;
    }

    public boolean delete(Person person) {
        boolean successDeleting = false;
        String id = person.getId();
        try {
            Connection connection = MySqlConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(QUERY_DELETE + "\'" + id + "\'" + " LIMIT 1");
            successDeleting = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return successDeleting;
    }
}
