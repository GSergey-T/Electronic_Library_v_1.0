package ru.GSergey.project_1.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.GSergey.project_1.Mappers.PersonMapper;
import ru.GSergey.project_1.Models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> allPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public void create(Person person) {
        jdbcTemplate.update("INSERT INTO Person (full_name,date_of_birth) VALUES (?,?)", person.getFull_name(), person.getDate_of_birth());
    }

    public Person showId(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, date_of_birth=? WHERE person_id=?",
                person.getFull_name(), person.getDate_of_birth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public Person presense(int person_id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?",new Object[]{person_id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public Person validator(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?",new Object[]{name},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

}
