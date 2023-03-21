package ru.GSergey.project_1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.GSergey.project_1.DAO.BookDAO;
import ru.GSergey.project_1.DAO.PersonDAO;
import ru.GSergey.project_1.Models.Book;
import ru.GSergey.project_1.Models.Person;
import ru.GSergey.project_1.Utils.PersonValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String viewAllPeople(Model model) {
        model.addAttribute("people",personDAO.allPeople());
        return "people/view";
    }

    @GetMapping("/new")
    public String createPerson(@ModelAttribute("newPerson") Person person) {
        return "people/new";
    }

    @PostMapping("/new")
    public String creation(@ModelAttribute("newPerson") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";
        personDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPersonForId(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.showId(id));

        List<Book> bookList = new ArrayList<>(bookDAO.allBook());
        List<String> stringList = new ArrayList<>();

        for (Book book: bookList) {
            if (book.getPerson_id() == id) {
                stringList.add(book.getName() + ", " + book.getAutor() + ", " + book.getPrint_date());
            }
        }

        if (!stringList.isEmpty()) {
            model.addAttribute("books", stringList);
        } else
            model.addAttribute("books", null);

        return "people/view-id";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("updatePerson", personDAO.showId(id));
        return "people/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editing (@PathVariable("id") int id, @ModelAttribute("updatePerson") @Valid Person person
            , BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.update(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
