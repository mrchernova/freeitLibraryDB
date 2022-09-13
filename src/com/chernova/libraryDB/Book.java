package com.chernova.libraryDB;

import java.util.Objects;

public class Book {

    private int id;
    private String title;
    private Author author;
    private Genre genre;

    public Book() {

    }

    public Book(int id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre);
    }

    @Override
    public String toString() {
        String str = String.format("\n%-3s %-30s %-20s %-10s", id, title, author.getAuthor(), genre.getGenre());
        return str;

    }
}