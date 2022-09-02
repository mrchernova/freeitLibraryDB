package com.chernova.libraryDB;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.sql.*;


public class Library {

    public Library() {
    }

    public static List<Book> getAllBooks() throws SQLException {
        ResultSet rs = DBConnection.postman.executeQuery("SELECT * FROM books");
        List<Book> bookList = new ArrayList<>();

        while (rs.next()) {
            Book b = new Book(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    Genre.getGenreById(rs.getInt(4)));
            bookList.add(b);
        }
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-30s %-20s %-10s", "id", "title", "author", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        return bookList;
    }


    public static String isLineEmpty() {
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
        newBook.setTitle(isLineEmpty()); // lineIn проверяет введенною строку на пустоту

        System.out.print("author: ");
        newBook.setAuthor(isLineEmpty());

        System.out.print("Выберите жанр из списка");
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-10s", "id", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        System.out.println(Genre.getAllGenres().toString().replaceAll("^\\[|,|\\]$", ""));
        System.out.println("genre: ");
        int genreId = 0;
        do {
            boolean validGenre = false;

            while (!validGenre) {
                try {
                    validGenre = true;
                    genreId = Menu.sc.nextInt();
                    newBook.setGenre(Genre.getGenreById(genreId));
                } catch (InputMismatchException e) {
                    System.out.println("Введите номер жанра");
                    validGenre = false;
                    Menu.sc.next();
                }
            }
        } while (Genre.getGenreById(genreId).getGenre() == null);
        newBook.setGenre(Genre.getGenreById(genreId));

        // если все данные получены, они добавляются в БД
        PreparedStatement ps = DBConnection.con.prepareStatement("INSERT INTO books (title, author, genre) VALUES (?, ?, ?)");
        ps.setString(1, newBook.getTitle());
        ps.setString(2, newBook.getAuthor());
        ps.setInt(3, newBook.getGenre().getId());
        ps.executeUpdate();
    }


    public static void updateBook(int id) throws SQLException {
        Book updateBook = new Book();
        System.out.print("title: ");
        updateBook.setTitle(isLineEmpty());

        System.out.print("author: ");
        updateBook.setAuthor(isLineEmpty());

        System.out.print("Выберите жанр из списка");
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-10s", "id", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        System.out.println(Genre.getAllGenres().toString().replaceAll("^\\[|,|\\]$", ""));
        System.out.println("genre: ");
        int genreId = 0;
        do {
            boolean validGenre = false;

            while (!validGenre) {
                try {
                    validGenre = true;
                    genreId = Menu.sc.nextInt();
                    updateBook.setGenre(Genre.getGenreById(genreId));
                } catch (InputMismatchException e) {
                    System.out.println("Введите номер жанра");
                    validGenre = false;
                    Menu.sc.next();
                }
            }
        } while (Genre.getGenreById(genreId).getGenre() == null);
        updateBook.setGenre(Genre.getGenreById(genreId));

        // если все данные получены, они добавляются в БД
        PreparedStatement ps = DBConnection.con.prepareStatement("UPDATE books SET title = ?, author = ?, genre = ? WHERE id = ?");
        ps.setString(1, updateBook.getTitle());
        ps.setString(2, updateBook.getAuthor());
        ps.setInt(3, updateBook.getGenre().getId());
        ps.setInt(4, id);
        ps.executeUpdate();
    }

    public static void deleteBook(int id) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement("DELETE FROM books WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate(); // В качестве результата возвращает количество строк, затронутых операцией
    }

}