package com.chernova.libraryDB;

import java.util.Date;
import java.util.Objects;

public class Book {

    private static int counter = 0;

    private int id;
    private String title;
    private String author;
    private String text;
    private String isbn;
    private Genre genre;
    private String publishDate;
    private Date localDate;

    public Book() {
        this.id = ++counter;
    }

    public Book(String title, String author, String text, String isbn, Genre genre, String publishDate, Date localDate) {
        this.id = ++counter;
        this.title = title;
        this.author = author;
        this.text = text;
        this.isbn = isbn;
        this.genre = genre;
        this.publishDate = publishDate;
        this.localDate = localDate;
    }

    public Book(int id, String title, String author, Genre genre) {
      //  this.id = ++counter;
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = new Date();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(text, book.text) && Objects.equals(isbn, book.isbn) && Objects.equals(genre, book.genre) && Objects.equals(publishDate, book.publishDate) && Objects.equals(localDate, book.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, text, isbn, genre, publishDate, localDate);
    }

    @Override
    public String toString() {
      String str = String.format("\n%-3s %-30s %-20s %-10s", id,title, author, genre.getGenre());
      return str;

    }
}