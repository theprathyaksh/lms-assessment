package org.example.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testReturnBook {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }
    @Test
    void testReturnBook() {
        Book book = new Book("12345", "Java Basics", "John Doe", 2020);
        library.addBook(book);
        library.borrowBook("12345");

        library.returnBook("12345");
        assertTrue(book.isAvailable());

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(1, availableBooks.size());
    }

    @Test
    void testReturnNonexistentBookThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.returnBook("99999"); // Nonexistent ISBN
        });

        assertEquals("Book not found.", exception.getMessage());
    }

    @Test
    void testReturnUnborrowedBookThrowsException() {
        Book book = new Book("12345", "Java Basics", "John Doe", 2020);
        library.addBook(book);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            library.returnBook("12345"); // Book was never borrowed
        });

        assertEquals("Book was not borrowed.", exception.getMessage());
    }
}
