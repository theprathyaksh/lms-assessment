package org.example.library;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class addBook {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void testAddBook() {
        Book book = new Book("12345", "Java Basics", "John Doe", 2020);
        library.addBook(book);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(1, availableBooks.size());
        assertEquals("Java Basics", availableBooks.get(0).getTitle());
    }

    @Test
    void testAddEmptyBookThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(new Book("", "", "", 0));
        });
        assertEquals("Book details cannot be empty.", exception.getMessage());
    }

    @Test
    void testAddBookWithInvalidPublicationYearThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(new Book("12345", "Java Basics", "John Doe", 99)); // Invalid year
        });
        assertEquals("Publication year must be a valid four-digit number.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(new Book("12345", "Java Basics", "John Doe", 10000)); // Invalid year
        });
        assertEquals("Publication year must be a valid four-digit number.", exception.getMessage());
    }

    @Test
    void testAddBookWithValidPublicationYear() {
        Book book = new Book("12345", "Java Basics", "John Doe", 2020); // Valid year
        library.addBook(book);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(1, availableBooks.size());
        assertEquals(2020, availableBooks.get(0).getPublicationYear());
    }



    @Test
    void testAddDuplicateBookThrowsException() {
        Book book = new Book("12345", "Java Basics", "John Doe", 2020);
        library.addBook(book);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(book); // Adding the same book
        });

        assertEquals("Book with this ISBN already exists.", exception.getMessage());
    }
}
