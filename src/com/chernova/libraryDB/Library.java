package com.chernova.libraryDB;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class Library {

    public Library() {
    }


    public static final String SELECT_BOOKS = "SELECT * FROM books";
    public static final String SELECT_BOOK = "SELECT * FROM books WHERE id = ?";
    public static final String INSERT_BOOK = "INSERT INTO books (title, author, genre) VALUES (?, ?, ?)";
    public static final String UPDATE_BOOK = "UPDATE books SET title = ?, author = ?, genre = ? WHERE id = ?";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE id = ?";
    public static final String SELECT_GENRES = "SELECT * FROM genres";
    public static final String SELECT_GENRE = "SELECT * FROM genres WHERE id = ?";

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String GENRE = "genre";

    public static final String REQUEST_TO_CHOOSE_NUMBER = "Введите число";
    public static final String REQUEST_TO_CHOOSE_NUMBER_FROM_LIST = "Введите номер из списка";

    public static final String ENTER_ID = "id: ";
    public static final String ENTER_TITLE = "title: ";
    public static final String ENTER_AUTHOR = "author: ";
    public static final String ENTER_GENRE = "genre: ";

    



    public static List<Book> getAllBooks() throws SQLException {
        Statement postman = DBConnection.connection.createStatement();
        ResultSet rs = postman.executeQuery(SELECT_BOOKS);
        List<Book> bookList = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book(rs.getInt(ID),
                    rs.getString(TITLE),
                    rs.getString(AUTHOR),
                    getGenreById(rs.getInt(GENRE)));
            bookList.add(b);
        }
        postman.close();

        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-30s %-20s %-10s", ID, TITLE, AUTHOR, GENRE + Menu.ANSI_RESET); // заголовки для вывода всех записей
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
        System.out.print(ENTER_TITLE);
        newBook.setTitle(newRecord());

        System.out.print(ENTER_AUTHOR);
        newBook.setAuthor(newRecord());

        System.out.print(REQUEST_TO_CHOOSE_NUMBER);
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-10s", ID, GENRE + Menu.ANSI_RESET); // заголовки для вывода всех записей
        System.out.println(getAllGenres().toString().replaceAll("^\\[|,|\\]$", ""));
        System.out.println(ENTER_GENRE);

        boolean isInt = false;
        int genreId = 0;

        while (!isInt) {
            if (Menu.sc.hasNextInt()) {
                genreId = Menu.sc.nextInt();
                isInt = true;
                Genre currentGenre = getGenreById(genreId);
                if (currentGenre.getGenre() == null) {
                    isInt = false;
                    System.out.println(REQUEST_TO_CHOOSE_NUMBER_FROM_LIST);
                } else {
                    newBook.setGenre(currentGenre);
                }
            } else {
                System.out.println(REQUEST_TO_CHOOSE_NUMBER);
                Menu.sc.next();
            }
        }

        // если все данные получены, они добавляются в БД
        PreparedStatement ps = DBConnection.connection.prepareStatement(INSERT_BOOK);
        ps.setString(1, newBook.getTitle());
        ps.setString(2, newBook.getAuthor());
        ps.setInt(3, newBook.getGenre().getId());
        ps.executeUpdate();
        ps.close();
    }


    public static void updateBook(int id) throws SQLException {
        Book updateBook = new Book();
        System.out.print(ENTER_TITLE);
        updateBook.setTitle(newRecord());

        System.out.print(ENTER_AUTHOR);
        updateBook.setAuthor(newRecord());

        System.out.print(REQUEST_TO_CHOOSE_NUMBER_FROM_LIST);
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-10s", ID, GENRE + Menu.ANSI_RESET); // заголовки для вывода всех записей
        System.out.println(getAllGenres().toString().replaceAll("^\\[|,|\\]$", ""));
        System.out.println(ENTER_GENRE);

        boolean isInt = false;
        int genreId = 0;

        while (!isInt) {
            if (Menu.sc.hasNextInt()) {
                genreId = Menu.sc.nextInt();
                isInt = true;
                Genre currentGenre = getGenreById(genreId);
                if (currentGenre.getGenre() == null) {
                    isInt = false;
                    System.out.println(REQUEST_TO_CHOOSE_NUMBER_FROM_LIST);
                } else {
                    updateBook.setGenre(currentGenre);
                }
            } else {
                System.out.println(REQUEST_TO_CHOOSE_NUMBER);
                Menu.sc.next();
            }
        }

        // если все данные получены, они добавляются в БД
        PreparedStatement ps = DBConnection.connection.prepareStatement(UPDATE_BOOK);
        ps.setString(1, updateBook.getTitle());
        ps.setString(2, updateBook.getAuthor());
        ps.setInt(3, updateBook.getGenre().getId());
        ps.setInt(4, id);
        ps.executeUpdate();
        ps.close();
    }

    public static void deleteBook(int id) {
        PreparedStatement ps = null;
        try {
            ps = DBConnection.connection.prepareStatement(DELETE_BOOK);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static int isExistBook(int id) throws SQLException {

        PreparedStatement ps = DBConnection.connection.prepareStatement(SELECT_BOOK);
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

        ResultSet rs = postman.executeQuery(SELECT_GENRES);
        List<Genre> genreList = new ArrayList<>();
        while (rs.next()) {
            Genre b = new Genre(rs.getInt(ID), rs.getString(GENRE));
            genreList.add(b);
        }
        postman.close();
        return genreList;
    }


    public static Genre getGenreById(int id) throws SQLException {

        PreparedStatement ps = DBConnection.connection.prepareStatement(SELECT_GENRE);
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