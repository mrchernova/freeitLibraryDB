package com.chernova.libraryDB;

import java.sql.SQLException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String MAIN_MENU = "\n1. Получить список книг\n" +
            "2. Добавить книгу\n" +
            "3. Редактировать книгу\n" +
            "4. Удалить книгу\n" +
            "5. Сохранить библиотеку в файл\n" +
            "0. Выход";

    public static final String INNER_MENU = "\tВ каком порядке вывести книги?\n" +
            "\t1. По алфавиту А-Я\n" +
            "\t2. По алфавиту Я-А\n" +
            "\t3. По добавлению";

    static Scanner sc = new Scanner(System.in);


    public static void launchMenu() throws SQLException {

        int option = -1;
        while (option != 0) {
            System.out.println(ANSI_YELLOW + MAIN_MENU + ANSI_RESET);
            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
                sc.next();
            }

            switch (option) {

                case 1:
                        System.out.print("Список книг:");
                        System.out.println(Library.getAllBooks().toString().replaceAll("^\\[|,|\\]$", ""));
                        break;


                case 2:
                  Library.addBook();
               /*     Book newBook = new Book();
                    System.out.println("Заполните все поля для добавления книги");

                    System.out.print("title: ");
                    String tmp = sc.nextLine();
                    while (tmp.isEmpty()) {
                        tmp = sc.nextLine();
                    }
                    newBook.setTitle(tmp);


                    System.out.print("text: ");
                    tmp = sc.nextLine();
                    while (tmp.isEmpty()) {
                        tmp = sc.nextLine();
                    }
                    newBook.setText(tmp);

                    System.out.println("genre: ");
                    System.out.print(ANSI_YELLOW);
                    Genre.printGenre();
                    System.out.print(ANSI_RESET);
                    boolean readyToSet = false;
                    while (!readyToSet) {
                        try {
                            readyToSet = true;
                            int genreAdd = sc.nextInt();
                            newBook.setGenre(Genre.values()[genreAdd - 1]);
                        } catch (InputMismatchException e) {
                            System.out.println("Введите номер жанра");
                            readyToSet = false;
                            sc.next();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Выберите жанр из списка");
                            readyToSet = false;
                        }
                    }

                    System.out.print("Введите 10-ый код isbn в формате: х-хххх-хххх-х ");
                    sc.nextLine();
                    String inputISBN = sc.nextLine();
                    boolean check = false;
                    while (!check) {
                        if (inputISBN.matches("[0-9]-[0-9]{4}-[0-9]{4}-[0-9]")) {
                            check = true;
                        } else {
                            System.out.println("Введенный isbn не соответствует формату. Попробуйте еще раз ");
                            inputISBN = sc.nextLine();
                        }
                    }
                    newBook.setISBN(inputISBN);

                    System.out.print("Введите дату публикации в формате: yyyy-MM-dd ");
                    String publishDate = sc.nextLine();
                    check = false;
                    while (!check) {
                        if (publishDate.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                            check = true;
                        } else {
                            System.out.println("Введенная дата не соответствует формату. Попробуйте еще раз ");
                            publishDate = sc.nextLine();
                        }
                    }
                    newBook.setPublishDate(publishDate);

                    Library.addBook(newBook);
                    System.out.println(ANSI_GREEN + "Книга успешно добавлена" + ANSI_RESET);
*/                    break;


                case 3:
 /*                   System.out.print("id книги: ");
                    try {
                        int idEdit = sc.nextInt();
                        boolean existBook = false;
                        for (int i = 0; i < Library.list.size(); i++) {
                            if (idEdit == Library.list.get(i).getId()) {
                                existBook = true;
                                Book chosenBook = Library.list.get(i);
                                Library.editBook(chosenBook);
                            }
                        }
                        if (!existBook) {
                            System.out.println(ANSI_RED + "Такой книги нет в списке" + ANSI_RESET);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Книга не найдена" + ANSI_RESET);
                        sc.next();
                    }
   */                 break;


                case 4:
   /*                 System.out.println("Введите id книги, которую надо удалить");
                    try {
                        int idDel = sc.nextInt();
                        Library.delBook(idDel);

                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Книга не найдена" + ANSI_RESET);
                        sc.next();
                    }
      */              break;


                case 5:
     /*               try {
                        // Создается построитель документа
                        DocumentBuilder docParser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                        // Создается дерево DOM документа из файла
                        Document document = docParser.parse(FILE_PATH + "book.xml");

                        Library.addBooksToXML();
                        System.out.println(ANSI_GREEN + "Файл записан на диск" + ANSI_RESET);

                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace(System.out);
                    } catch (SAXException ex) {
                        ex.printStackTrace(System.out);
                    } catch (IOException ex) {
                        ex.printStackTrace(System.out);
                    }
     */               break;


                case 0:
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Выберите номер из списка");
                    break;
            }
        }
    }
}