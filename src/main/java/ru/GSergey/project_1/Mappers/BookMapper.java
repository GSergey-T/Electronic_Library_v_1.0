package ru.GSergey.project_1.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.GSergey.project_1.Models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {

        Book book = new Book();

        book.setBook_id(resultSet.getInt("book_id"));
        book.setName(resultSet.getString("name"));
        book.setAutor(resultSet.getString("autor"));
        book.setPrint_date(resultSet.getInt("print_date"));
        book.setPerson_id(resultSet.getInt("person_id"));

        return book;
    }
}
