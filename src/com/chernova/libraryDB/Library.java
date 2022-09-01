package com.chernova.libraryDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
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
            Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), getGenreById(rs.getInt(4),DBConnection.con));

            bookList.add(b);
        }
        System.out.format(Menu.ANSI_YELLOW + "\n%-5s %-30s %-20s %-10s", "id", "title", "author", "genre" + Menu.ANSI_RESET); // заголовки для вывода всех записей
        return bookList;
    }

    private static Genre getGenreById(int id, Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM genres WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Genre(rs.getInt(1), rs.getString(2));
    }
// read <<


    // create >>
//    public static void addBook(Book book) throws SQLException {
//
//        Book newBook = new Book();
//        System.out.println("Заполните все поля для добавления книги");
//
//        System.out.print("title: ");
//        String tmp = sc.nextLine();
//        while (tmp.isEmpty()) {
//            tmp = sc.nextLine();
//        }
//        newBook.setTitle(tmp);
//
//
//        System.out.print("text: ");
//        tmp = sc.nextLine();
//        while (tmp.isEmpty()) {
//            tmp = sc.nextLine();
//        }
//        newBook.setText(tmp);
//
//        System.out.println("genre: ");
//        System.out.print(ANSI_YELLOW);
//        Genre.printGenre();
//        System.out.print(ANSI_RESET);
//        boolean readyToSet = false;
//        while (!readyToSet) {
//            try {
//                readyToSet = true;
//                int genreAdd = sc.nextInt();
//                newBook.setGenre(Genre.values()[genreAdd - 1]);
//            } catch (InputMismatchException e) {
//                System.out.println("Введите номер жанра");
//                readyToSet = false;
//                sc.next();
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("Выберите жанр из списка");
//                readyToSet = false;
//            }
//        }




//        Genre genre = new Genre();
//        genre.setGenre("тестовый жанр");
//
//        PreparedStatement psGenre = DBConnection.con.prepareStatement("insert into genres (genre) values (?)", Statement.RETURN_GENERATED_KEYS);
//        psGenre.setString(1, genre.getGenre());
//        psGenre.executeUpdate();
//        ResultSet resSetGenre = psGenre.getGeneratedKeys();
//        if(resSetGenre.next())
//        {
//            genre.setId(resSetGenre.getInt(1));
//        }

//        Book newBook = new Book();
//        newBook.setTitle("test book");
//        //   newBook.setGenre(genre);
//        newBook.setAuthor("Author 1");
//        System.out.println("*****");
//        System.out.println(newBook);
//        System.out.println("***** DB Insertion");
//
//        PreparedStatement ps = DBConnection.con.prepareStatement("insert into books (title, author, genre_id) values (?, ?, ?)");
//        ps.setString(1, newBook.getTitle());
//        ps.setString(2, newBook.getAuthor());
//        ps.setInt(3, newBook.getGenre().getId());
//        ps.executeUpdate();

    }
// create <<


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


}
