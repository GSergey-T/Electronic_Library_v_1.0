package ru.GSergey.project_1.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.GSergey.project_1.Mappers.BookMapper;
import ru.GSergey.project_1.Models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> allBook() {
        return  jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book showId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name,autor, print_date) VALUES (?,?,?)",
                book.getName(), book.getAutor(), book.getPrint_date());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, autor=?, print_date=? WHERE book_id=?",
                book.getName(), book.getAutor(), book.getPrint_date(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void emancipate(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = null WHERE book_id=?", id);
    }

    public void assignABook(int idBook, int idPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id=?", idPerson, idBook);
    }

}
