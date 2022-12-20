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

public class PersonRepository {
//    private static final String GET_ALL = "SELECT * FROM project_27.person";
//todo метод написан хорошо. продолжаем дальше. query должно быть в константах, а не параметром.
// 2. добавь методы findById(String id) и findByName(String name), которые будут вызываться из сервиса.
// в каждом методе будет sql query "select * from project_27.person where " + "id = " + id
// (или "name = " + name) и т.д. параметр по которому будет фильтр.
// аналогично можно добавить парметр с полем сотрировки в методы (findAll(), findByNam() и т.д,
// есть разные варианты, можно погуглить примеры, на данный момент можно брать самую простую реализацию
// просто чтобы понять принцип
// 3. после пункта 2 можно реалзиовывать методы create() и update()
    public List<Person> findAll(String query) {
        List<Person> personList = new ArrayList<>();
        try (Connection conn = MySqlConnection.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM person");
            while (rs.next()) {
                String id = rs.getString("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int birthYear = rs.getInt("birthYear");
                PersonJob job = Person.setVariableJob(rs.getString("job"));
                double salary = rs.getDouble("salary");
                Person newPerson = new Person(id, firstName, lastName, birthYear, job, salary);
                personList.add(newPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
