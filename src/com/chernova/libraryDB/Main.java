package com.chernova.libraryDB;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        Menu.launchMenu();



//        // Book creation
//        Genre genre = new Genre();
//        genre.setGenre("тестовый жанр");
//
//        PreparedStatement psGenre = con.prepareStatement("insert into genres (genre) values (?)", Statement.RETURN_GENERATED_KEYS);
//        psGenre.setString(1, genre.getGenre());
//        psGenre.executeUpdate();
//        ResultSet resSetGenre = psGenre.getGeneratedKeys();
//        if(resSetGenre.next())
//        {
//            genre.setId(resSetGenre.getInt(1));
//        }
//
//        Book newBook = new Book();
//        newBook.setTitle("test book");
//        newBook.setGenre(genre);
//        newBook.setAuthor("Author 1");
//        System.out.println("*****");
//        System.out.println(newBook);
//        System.out.println("***** DB Insertion");
//
//        PreparedStatement ps = con.prepareStatement("insert into books (title, author, genre_id) values (?, ?, ?)");
//        ps.setString(1, newBook.getTitle());
//        ps.setString(2, newBook.getAuthor());
//        ps.setInt(3, newBook.getGenre().getId());
//        ps.executeUpdate();



//        //Book Update
//        PreparedStatement ps = con.prepareStatement("UPDATE books SET author = ? WHERE id = ?");
//        ps.setString(1, "Updated!");
//        ps.setInt(2, 13);
//        ps.executeUpdate(); //В качестве результата возвращает количество строк, затронутых операцией


        // book delete
//        PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE id = ?");
//       // ps.setString(1, "deleted!");
//        ps.setInt(1, bookList.get(0).getId()); // всегда удаляет последнюю запись
//        int i = ps.executeUpdate(); // В качестве результата возвращает количество строк, затронутых операцией
//        System.out.println("rows= " + i);
//        if (i > 0) {
//            System.out.println("delete successful");
//        }


        System.out.println();
    } // main


}
