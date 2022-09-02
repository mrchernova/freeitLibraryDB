package com.chernova.libraryDB;

import java.sql.SQLException;
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
            "0. Выход";

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
                    break;

                case 3:
                    System.out.print("id книги для редактирования: ");
                    try {
                        int idEdit = sc.nextInt();
                        int verifiedId = Book.isExistBook(idEdit);
                        Library.updateBook(verifiedId);
                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Книга не найдена" + ANSI_RESET);
                        sc.next();
                    }
                    break;

                case 4:
                    System.out.println("id книги для удаления:");
                    try {
                        int idDelete = sc.nextInt();
                        int verifiedId = Book.isExistBook(idDelete);
                        Library.deleteBook(verifiedId);
                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Книга не найдена" + ANSI_RESET);
                        sc.next();
                    }
                    break;


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