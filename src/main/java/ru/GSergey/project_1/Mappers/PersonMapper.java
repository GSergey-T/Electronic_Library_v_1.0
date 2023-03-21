package ru.GSergey.project_1.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.GSergey.project_1.Models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setPerson_id(resultSet.getInt("person_id"));
        person.setFull_name(resultSet.getString("full_name"));
        person.setDate_of_birth(resultSet.getInt("date_of_birth"));

        return person;
    }

}
