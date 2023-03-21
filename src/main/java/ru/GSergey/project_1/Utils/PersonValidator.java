package ru.GSergey.project_1.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.GSergey.project_1.DAO.PersonDAO;
import ru.GSergey.project_1.Models.Person;

@Component
public class PersonValidator implements Validator {

    public final PersonDAO personDAO;

    @Autowired
    PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personDAO.validator(person.getFull_name()) != null)
            errors.rejectValue("full_name", "", "Такой человек уже в базе");
    }
}
