package com.strelnikov.postgredemo.dao;

import com.strelnikov.postgredemo.dao.impl.BookDaoImpl;
import com.strelnikov.postgredemo.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        Book book = TestDataUtil.createTestBookA();

        underTest.create(book);

        Mockito.verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("111-1-111-1000-0"),
                eq("Book One"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql() {
        underTest.findOne("111-1-111-1000-0");

        Mockito.verify(jdbcTemplate).query(
                eq("SELECT * FROM books WHERE isbn = ?"),
        ArgumentMatchers.< BookDaoImpl.BookRowMapper>any(),
        eq("111-1-111-1000-0")
        );
    }

    @Test
    public void testThatFindAllBooksGeneratesCorrectSql() {
        underTest.findAll();

        Mockito.verify(jdbcTemplate).query(
                eq("SELECT isbn, name, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Book book = TestDataUtil.createTestBookA();
        book.setTitle("Updated");
        underTest.update(book.getIsbn(), book);

        Mockito.verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(), "Updated", book.getAuthorId(), book.getIsbn()
        );
    }

    @Test
    public void testThatDeleteBookGeneratesCorrectSql() {
        underTest.delete("111-1-111-1000-0");

        Mockito.verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                "111-1-111-1000-0"
        );
    }
}
