package com.strelnikov.postgredemo.dao;

import com.strelnikov.postgredemo.dao.impl.AuthorDaoImpl;
import com.strelnikov.postgredemo.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = new Author(1L, "Abigail Rose", 80);
        underTest.create(author);

        Mockito.verify(jdbcTemplate).update(
                Mockito.eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                Mockito.eq(1L),
                Mockito.eq("Abigail Rose"),
                Mockito.eq(80)

        );
    }
}
