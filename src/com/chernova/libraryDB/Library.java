package com.chernova.libraryDB;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class Library {

    public Library() {
    }

    public static List<Book> getAllBooks() throws SQLException {
        Statement postman = DBConnection.connection.createStatement();
        ResultSet rs = postman.executeQuery("SELECT * FROM books");
        List<Book> bookList = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book(rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    getGenreById(rs.getInt("genre")));
            bookList.add(b);
        }
        postman.close();

        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-30s %-20s %-10s", "id", "title", "author", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        return bookList;
    }


    public static String newRecord() {
        String newRecord = Menu.sc.nextLine();
        while (newRecord.isEmpty()) {
            newRecord = Menu.sc.nextLine();
        }
        return newRecord;
    }

    public static void addBook() throws SQLException {

        Book newBook = new Book();
        System.out.println("Заполните все поля для добавления книги");
        System.out.print("title: ");
        newBook.setTitle(newRecord());

        System.out.print("author: ");
        newBook.setAuthor(newRecord());

        System.out.print("Выберите жанр из списка");
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-10s", "id", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        System.out.println(getAllGenres().toString().replaceAll("^\\[|,|\\]$", ""));
        System.out.println("genre: ");

        boolean isInt = false;
        int genreId = 0;

        while (!isInt) {
            if (Menu.sc.hasNextInt()) {
                genreId = Menu.sc.nextInt();
                isInt = true;

                if (getGenreById(genreId).getGenre() == null) {
                    isInt = false;
                    System.out.println("Введите номер жанра из списка");
                } else {
                    newBook.setGenre(getGenreById(genreId));
                }
            } else {
                System.out.println("Введите номр жанра");
                Menu.sc.next();
            }
        }

        // если все данные получены, они добавляются в БД
        PreparedStatement ps = DBConnection.connection.prepareStatement("INSERT INTO books (title, author, genre) VALUES (?, ?, ?)");
        ps.setString(1, newBook.getTitle());
        ps.setString(2, newBook.getAuthor());
        ps.setInt(3, newBook.getGenre().getId());
        ps.executeUpdate();
        ps.close();
    }


    public static void updateBook(int id) throws SQLException {
        Book updateBook = new Book();
        System.out.print("title: ");
        updateBook.setTitle(newRecord());

        System.out.print("author: ");
        updateBook.setAuthor(newRecord());

        System.out.print("Выберите жанр из списка");
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-10s", "id", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        System.out.println(getAllGenres().toString().replaceAll("^\\[|,|\\]$", ""));
        System.out.println("genre: ");
        boolean isInt = false;
        int genreId = 0;

        while (!isInt) {
            if (Menu.sc.hasNextInt()) {
                genreId = Menu.sc.nextInt();
                isInt = true;

                if (getGenreById(genreId).getGenre() == null) {
                    isInt = false;
                    System.out.println("Введите номер жанра из списка");
                } else {
                    updateBook.setGenre(getGenreById(genreId));
                }
            } else {
                System.out.println("Введите номр жанра");
                Menu.sc.next();
            }
        }

        // если все данные получены, они добавляются в БД
        PreparedStatement ps = DBConnection.connection.prepareStatement("UPDATE books SET title = ?, author = ?, genre = ? WHERE id = ?");
        ps.setString(1, updateBook.getTitle());
        ps.setString(2, updateBook.getAuthor());
        ps.setInt(3, updateBook.getGenre().getId());
        ps.setInt(4, id);
        ps.executeUpdate();
        ps.close();
    }

    public static void deleteBook(int id) throws SQLException {
        PreparedStatement ps = DBConnection.connection.prepareStatement("DELETE FROM books WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }


    public static int isExistBook(int id) throws SQLException {

        PreparedStatement ps = DBConnection.connection.prepareStatement("SELECT * FROM books WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        // если записи с таким id нет, то вызвать isExistBook еще раз
        if (!rs.next()) {
            id = Menu.sc.nextInt();
            isExistBook(id);
        }
        ps.close();
        return id;
    }


    public static List<Genre> getAllGenres() throws SQLException {

        Statement postman = DBConnection.connection.createStatement();

        ResultSet rs = postman.executeQuery("SELECT * FROM genres");
        List<Genre> genreList = new ArrayList<>();
        while (rs.next()) {
            Genre b = new Genre(rs.getInt("id"), rs.getString("genre"));
            genreList.add(b);
        }
        postman.close();
        return genreList;
    }


    public static Genre getGenreById(int id) throws SQLException {

        PreparedStatement ps = DBConnection.connection.prepareStatement("SELECT * FROM genres WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Genre(rs.getInt(1), rs.getString(2));
        } else { // если записи с таким id нет, то  вернуть пустой жанр
            ps.close();
            return new Genre();
        }
    }


}