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

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String viewBooks(Model model) {
        model.addAttribute("books",bookDAO.allBook());
        return "book/view";
    }

    @GetMapping("/{id}")
    public String showBookForId(@PathVariable("id") int id, Model model) {
        Book bookResult = bookDAO.showId(id);
        model.addAttribute("book", bookResult);

        if (bookResult.getPerson_id() != 0) {
            model.addAttribute("person1", personDAO.presense(bookResult.getPerson_id()));
            model.addAttribute("bool", true);
        } else
            model.addAttribute("bool", false);
            model.addAttribute("person2", new Person());
            model.addAttribute("personList", personDAO.allPeople());

        return "book/view-id";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("newBook") Book book) {
        return "book/new";
    }

    @PostMapping("/new")
    public String creation(@ModelAttribute("newBook") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";
        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("updateBook", bookDAO.showId(id));
        return "book/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editing (@ModelAttribute("updateBook") @Valid Book book, BindingResult bindingResult,
                           @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/edit";
        bookDAO.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/emancipate")
    public String emancipate (@PathVariable("id") int id) {
        bookDAO.emancipate(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assignABook")
    public String assignABook (@ModelAttribute() Person person, @PathVariable("id") int idBook) {
        bookDAO.assignABook(idBook,person.getPerson_id());
        return "redirect:/books/" + idBook;
    }

}
