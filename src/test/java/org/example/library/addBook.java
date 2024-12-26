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
        assertEquals("ISBN cannot be empty.", exception.getMessage());
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

    @Test
    void testAddBookIncrementsCount() {
        Book book = new Book("12345", "Java Basics", "John Doe", 2020);
        library.addBook(book);

        assertEquals(1, library.getAddCalledCount());
    }

    @Test
    void testAddMultipleBooks() {
        Book book1 = new Book("12345", "Java Basics", "John Doe", 2020);
        Book book2 = new Book("67890", "Python Advanced", "Jane Doe", 2021);

        library.addBook(book1);
        library.addBook(book2);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(2, availableBooks.size());
    }


    @Test
    void testAddBooksFromStringWithInvalidInputThrowsException() {
        String input = "//,\n12345,Java Basics";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBooksFromString(input);
        });

        assertEquals("Invalid book data format.", exception.getMessage());
    }

    @Test
    void testGetAddCalledCountWithMultipleAdditions() {
        library.addBook(new Book("12345", "Java Basics", "John Doe", 2020));
        library.addBook(new Book("67890", "Python Advanced", "Jane Doe", 2021));

        assertEquals(2, library.getAddCalledCount());
    }
    @Test
    void testAddBooksFromStringWithCustomDelimiter() {
        String input = "//[;]\n12345,Java Basics,John Doe,2020;67890,Advanced Java,Jane Doe,2019";
        library.addBooksFromString(input);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(2, availableBooks.size());

        assertEquals("12345", availableBooks.get(0).getIsbn());
        assertEquals("Java Basics", availableBooks.get(0).getTitle());
        assertEquals("John Doe", availableBooks.get(0).getAuthor());
        assertEquals(2020, availableBooks.get(0).getPublicationYear());

        assertEquals("67890", availableBooks.get(1).getIsbn());
        assertEquals("Advanced Java", availableBooks.get(1).getTitle());
        assertEquals("Jane Doe", availableBooks.get(1).getAuthor());
        assertEquals(2019, availableBooks.get(1).getPublicationYear());
    }

    @Test
    void testAddBooksFromStringWithInvalidFormatThrowsException() {
        String input = "12345,Java Basics,John Doe,2020"; // Missing custom delimiter specification

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBooksFromString(input);
        });

        assertEquals("Invalid input format. Specify delimiters using //.", exception.getMessage());
    }

    @Test
    void testAddBooksFromStringWithMalformedBookDataThrowsException() {
        String input = "//[;]\n12345,Java Basics,John Doe,2020;67890,Advanced Java"; // Second book data is incomplete

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBooksFromString(input);
        });

        assertEquals("Invalid book data format.", exception.getMessage());
    }
    @Test
    void testAddBookWithSpecialCharactersInFields() {
        Book book = new Book("12345", "Java@Basics!", "John#Doe$", 2020);
        library.addBook(book);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(1, availableBooks.size());
        assertEquals("Java@Basics!", availableBooks.get(0).getTitle());
        assertEquals("John#Doe$", availableBooks.get(0).getAuthor());
    }
    @Test
    void testAddBookWithLeadingOrTrailingWhitespaces() {
        Book book = new Book(" 12345 ", " Java Basics ", " John Doe ", 2020);
        library.addBook(book);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(1, availableBooks.size());
        assertEquals("12345", availableBooks.get(0).getIsbn());
        assertEquals("Java Basics", availableBooks.get(0).getTitle());
        assertEquals("John Doe", availableBooks.get(0).getAuthor());
    }
    @Test
    void testAddBooksFromStringWithEmptyInputThrowsException() {
        String input = "";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBooksFromString(input);
        });

        assertEquals("Invalid input format. Specify delimiters using //.", exception.getMessage());
    }
    @Test
    void testAddBookWithNonNumericISBNThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(new Book("ISBN123", "Java Basics", "John Doe", 2020));
        });

        assertEquals("ISBN must be numeric.", exception.getMessage());
    }


    @Test
    void testAddBookWithBoundaryPublicationYear() {
        Book book1 = new Book("12345", "Old Book", "John Doe", 1000); // Valid earliest year
        Book book2 = new Book("67890", "Future Book", "Jane Doe", 9999); // Valid latest year

        library.addBook(book1);
        library.addBook(book2);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(2, availableBooks.size());
        assertEquals(1000, availableBooks.get(0).getPublicationYear());
        assertEquals(9999, availableBooks.get(1).getPublicationYear());
    }

    @Test
    void testAddBookWithInvalidPublicationYear() {
        Library library = new Library();
        Book invalidBook = new Book("12345", "Title", "Author", 99); // Invalid year
        Exception exception = assertThrows(IllegalArgumentException.class, () -> library.addBook(invalidBook));
        assertEquals("Publication year must be a valid four-digit number.", exception.getMessage());
    }


}
