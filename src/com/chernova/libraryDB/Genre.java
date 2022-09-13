package com.chernova.libraryDB;

import java.util.Objects;

public class Genre {
    private int id;
    private String genre;

    public Genre() {
    }

    Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre1 = (Genre) o;
        return id == genre1.id && Objects.equals(genre, genre1.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }

    @Override
    public String toString() {
        String str = String.format("\n%-3s %-10s", id, genre);
        return str;
    }
}