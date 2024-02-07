package com.strelnikov.postgredemo.dao;

import com.strelnikov.postgredemo.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);
    Optional<Book> findOne(String isdn);
}
