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
