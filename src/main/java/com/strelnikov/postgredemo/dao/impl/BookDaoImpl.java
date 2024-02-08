package com.strelnikov.postgredemo.dao.impl;

import com.strelnikov.postgredemo.dao.BookDao;
import com.strelnikov.postgredemo.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findOne(String isdn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT * FROM books WHERE isbn = ?",
                new BookRowMapper(),
                isdn
        );
        return results.stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        List<Book> result = jdbcTemplate.query(
                "SELECT isbn, name, author_id FROM books",
                new BookRowMapper()
        );
        return result;
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getString("isbn"),
                    rs.getString("title"),
                    rs.getLong("author_id"));
        }
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(), book.getTitle(), book.getAuthorId(), isbn
        );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn = ?",
                isbn
        );
    }
}
