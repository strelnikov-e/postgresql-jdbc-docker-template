package com.strelnikov.postgredemo.dao;

import com.strelnikov.postgredemo.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);
    Optional<Author> findOne (Long id);
    List<Author> findAll();
    void update(Long id, Author author);
    void delete(Long id);
}
