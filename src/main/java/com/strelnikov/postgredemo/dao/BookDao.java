package com.strelnikov.postgredemo.dao;

import com.strelnikov.postgredemo.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);
    Optional<Book> findOne(String isdn);
    List<Book> findAll();
    void update(String isbn, Book book);
    void delete(String isbn);
}
