package org.example.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }



    @Test
    void testBorrowBook() {
        Book book = new Book("12345", "Java Basics", "John Doe", 2020);
        library.addBook(book);

        library.borrowBook("12345");
        assertFalse(book.isAvailable());

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(0, availableBooks.size());
    }

    @Test
    void testBorrowUnavailableBookThrowsException() {
        Book book = new Book("12345", "Java Basics", "John Doe", 2020);
        library.addBook(book);
        library.borrowBook("12345");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            library.borrowBook("12345"); // Borrowing an already borrowed book
        });

        assertEquals("Book is not available.", exception.getMessage());
    }

    @Test
    void testBorrowNonexistentBookThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("99999"); // Nonexistent ISBN
        });

        assertEquals("Book not found.", exception.getMessage());
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

    @Test
    void testViewAvailableBooks() {
        Book book1 = new Book("12345", "Java Basics", "John Doe", 2020);
        Book book2 = new Book("67890", "Python Advanced", "Jane Doe", 2021);

        library.addBook(book1);
        library.addBook(book2);
        library.borrowBook("12345");

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(1, availableBooks.size());
        assertEquals("Python Advanced", availableBooks.get(0).getTitle());
    }
}
