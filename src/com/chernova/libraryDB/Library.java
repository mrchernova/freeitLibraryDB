package com.chernova.libraryDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.*;


public class Library {

    public static List<Book> list = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    public Library() {
    }

    //CRUD - Create / read / update / delete
    //  public void addBook(Book book) {
//        books.add(book);
//    }

    // read >>
    public static List<Book> getAllBooks() throws SQLException {
        // read DB
        ResultSet rs = DBConnection.postman.executeQuery("SELECT * FROM books");
        List<Book> bookList = new ArrayList<>();

        while (rs.next()) {
            Book b = new Book(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    Genre.getGenreById(rs.getInt(4)));  // в этот booklist выводится только genre_id. надо объединять таблицы при выводе
            bookList.add(b);
        }
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-30s %-20s %-10s", "id", "title", "author", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        return bookList;
    }
    // read <<



// lineIn проверяет введенною строку на пустоту
    public static String newRecord = "";
    public static String lineIn() {
        newRecord = Menu.sc.nextLine();
        while (newRecord.isEmpty()) {
            newRecord = Menu.sc.nextLine();
        }
        return newRecord;
    }

    // create >>
    public static void addBook() throws SQLException {

        Book newBook = new Book();
        System.out.println("Заполните все поля для добавления книги");
        System.out.print("title: ");
        newBook.setTitle(lineIn()); // lineIn проверяет введенною строку на пустоту

        System.out.print("author: ");
        newBook.setAuthor(lineIn());

        System.out.print("Выберите жанр из списка");
        System.out.format(Menu.ANSI_YELLOW + "\n%-3s %-10s", "id", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        System.out.println(Genre.getAllGenres().toString().replaceAll("^\\[|,|\\]$", ""));


// как было >>
        System.out.println("genre: ");

        boolean readyToSet = false;
        while (!readyToSet) {
            try {
                readyToSet = true;
                int genreAdd = Menu.sc.nextInt();
                newBook.setGenre(Genre.getGenreById(genreAdd));
            } catch (InputMismatchException e) {
                System.out.println("Введите номер жанра");
                readyToSet = false;
                Menu.sc.next();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Выберите жанр из списка");
                readyToSet = false;
            }
        }
// как было <<


    }


}
// create <<


//???
/*
//    public Book getBook(int id) {
//        return books.stream()
//                .filter(b -> b.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Provided id not found %s", id)));
//    }
//
//    public List<Book> getBook(String title) { //list  из-за одинаковых названий
//        return books.stream()
//                .filter(b -> b.getTitle().equals(title))
//                .collect(Collectors.toList()); // собираем все книги в лист. если киги нет, то выведет пустой список
//    }


//    public Book updateBook(Book book) {
//        Book found = books.stream()
//                .filter(b -> b.getId() == book.getId())
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Provided id not found %s", book.getId())));
//
//     //   found.setAuthor(book.getAuthor());
//        found.setTitle(book.getTitle());
//        found.setGenre(book.getGenre());
//        return found;
//    }


//    public boolean deleteBook(int id) {
//        Book found = books.stream()
//                .filter(b -> b.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Provided id not found %s", id)));
//        return books.remove(found);
//    }
*/