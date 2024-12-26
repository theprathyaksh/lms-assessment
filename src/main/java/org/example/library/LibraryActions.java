package org.example.library;
import java.util.List;

public interface LibraryActions {
    void addBook(Book book);

    void borrowBook(String isbn);

    void returnBook(String isbn);

    List<Book> viewAvailableBooks();
}
