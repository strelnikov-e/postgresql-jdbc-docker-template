package com.strelnikov.postgredemo.domain;


public class Book {

    private String isbn;
    private String title;
    private Long authorId;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Book(String isbn, String title, Long authorId) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
    }

    public Book() {
    }
}
