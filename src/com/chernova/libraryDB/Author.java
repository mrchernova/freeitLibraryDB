package com.chernova.libraryDB;

import java.util.Objects;

public class Author {
    private int id;
    private String author;

    public Author() {
    }

    Author(int id, String author) {
        this.id = id;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author1 = (Author) o;
        return id == author1.id && Objects.equals(author, author1.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author);
    }

    @Override
    public String toString() {
        String str = String.format("\n%-3s %-10s", id, author);
        return str;
    }
}