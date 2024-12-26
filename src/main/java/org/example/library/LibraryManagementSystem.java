package org.example.library;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while(true){
            System.out.println("Enter option: ");
            System.out.println("1.Add Books");
            System.out.println("2.Borrow Books");
            System.out.println("3.Return Books");
            System.out.println("4.View Available Books");
            System.out.println("5.Exit");
            int choice = sc.nextInt();

            sc.nextLine();

            switch (choice){
                case  1 :
                    System.out.println("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.println("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.println("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.println("Enter Publication Year: ");
                    int year = sc.nextInt();
                    try {
                        Book book = new Book(isbn, title, author, year);
                        library.addBook(book);
                        System.out.println("Book added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2 :
                    System.out.println("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = sc.nextLine();
                    try {
                        library.borrowBook(borrowIsbn);
                        System.out.println("Book borrowed successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3 :
                    System.out.println("Enter ISBN of the book to return: ");
                    String returnIsbn = sc.nextLine();
                    try {
                        library.returnBook(returnIsbn);
                        System.out.println("Book returned successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4 :
                    System.out.println("Available Books:");
                    for (Book book : library.viewAvailableBooks()) {
                        System.out.println(book);
                    }
                    break;

                case 5 :
                    System.out.println("Exiting the system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
            System.out.println();
        }
    }
}
