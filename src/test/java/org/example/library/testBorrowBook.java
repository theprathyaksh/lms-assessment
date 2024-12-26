package org.example.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class testBorrowBook {
    private Library library;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        library = new Library();

        // Create global books
        book1 = new Book("12345", "Java Basics", "John Doe", 2020);
        book2 = new Book("67890", "Advanced Java", "Jane Doe", 2021);

        // Add books to the library
        library.addBook(book1);
        library.addBook(book2);
    }


    @Test
    void testBorrowUnavailableBookThrowsException() {
        library.borrowBook(book1.getIsbn());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            library.borrowBook(book1.getIsbn());
        });

        assertEquals("No copies of this book are available to borrow.", exception.getMessage());
    }

    @Test
    void testBorrowNonexistentBookThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("99999");
        });

        assertEquals("Book not found.", exception.getMessage());
    }

    @Test
    void testBorrowBookWithInvalidIsbnFormatThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook(""); // Empty ISBN
        });

        assertEquals("Invalid ISBN format.", exception.getMessage());
    }

}
