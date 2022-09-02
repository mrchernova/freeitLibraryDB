package com.chernova.libraryDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


    public static List<Genre> getAllGenres() throws SQLException {
        ResultSet rs = DBConnection.postman.executeQuery("SELECT * FROM genres");
        List<Genre> genreList = new ArrayList<>();
        while (rs.next()) {
            Genre b = new Genre(rs.getInt(1), rs.getString(2));
            genreList.add(b);
        }
        return genreList;
    }


    public static Genre getGenreById(int id) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement("SELECT * FROM genres WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Genre(rs.getInt(1), rs.getString(2));
        } else { // если записи с таким id нет, то  вернуть пустой жанр
            return new Genre();
        }
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
