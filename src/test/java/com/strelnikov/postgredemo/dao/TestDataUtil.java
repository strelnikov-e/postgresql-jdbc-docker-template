package com.strelnikov.postgredemo.dao;

import com.strelnikov.postgredemo.domain.Author;
import com.strelnikov.postgredemo.domain.Book;

public class TestDataUtil {

    private TestDataUtil() {}

    public static Author createTestAuthorA() {
        return new Author(1L, "Author One", 80);
    }

    public static Author createTestAuthorB() {
        return new Author(1L, "Author Two", 24);
    }

    public static Author createTestAuthorC() {
        return new Author(1L, "Author Three", 47);
    }

    public static Book createTestBookA() {
        return new Book("111-1-111-1000-0", "Book One", 1L);
    }

    public static Book createTestBookB() {
        return new Book("222-2-222-2000-0", "Book Two", 1L);
    }

    public static Book createTestBookC() {
        return new Book("333-3-333-3000-0", "Book Three", 1L);
    }
}
