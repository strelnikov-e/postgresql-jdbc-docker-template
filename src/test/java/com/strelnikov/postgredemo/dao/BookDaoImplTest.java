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
        Book book = new Book("987-6-543-2100-0", "The shadow in the attic", 1L);

        underTest.create(book);

        Mockito.verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("987-6-543-2100-0"),
                eq("The shadow in the attic"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql() {
        underTest.findOne("987-6-543-2100-0");

        Mockito.verify(jdbcTemplate).query(
                eq("SELECT * FROM books WHERE books.isdn = ?"),
        ArgumentMatchers.< BookDaoImpl.BookRowMapper>any(),
        eq("987-6-543-2100-0")
        );
    }

}
