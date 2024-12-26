package org.example.library;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library implements LibraryActions {
    private final Map<String, Book> books;
    private int addCallCount = 0; //to track number of times addBook method is invoked

    public Library() {
        this.books = new HashMap<>();
    }
    @Override
    public void addBook(Book book) {
        addCallCount++;

        // Ensure the book object is not null
        if (book == null) {
            throw new IllegalArgumentException("Book details cannot be null.");
        }

        // Trim all fields
        String isbn = book.getIsbn().trim();
        String title = book.getTitle().trim();
        String author = book.getAuthor().trim();
        int publicationYear = book.getPublicationYear();

        // Validate ISBN: cannot be empty and must be numeric
        if (isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        if (!isbn.matches("\\d+")) { // Ensure ISBN contains only digits
            throw new IllegalArgumentException("ISBN must be numeric.");
        }

        // Validate title and author: cannot be empty
        if (title.isEmpty() || author.isEmpty()) {
            throw new IllegalArgumentException("Book title and author cannot be empty.");
        }

        // Validate publication year: must be four digits
        if (publicationYear < 1000 || publicationYear > 9999) {
            throw new IllegalArgumentException("Publication year must be a valid four-digit number.");
        }

        // Ensure the ISBN is unique
        if (books.containsKey(isbn)) {
            throw new IllegalArgumentException("Book with this ISBN already exists.");
        }

        // Add the book to the collection
        books.put(isbn, new Book(isbn, title, author, publicationYear));
    }

    public void addBooksFromString(String input) {
        addCallCount++;

        if (input.startsWith("//")) {
            // Split input into delimiter part and book data part
            String[] parts = input.split("\n", 2);
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid input format.");
            }

            String delimiterPart = parts[0].substring(2); // Remove the `//`
            String bookData = parts[1];

            // Build regex for multiple delimiters
            String delimiterRegex;
            if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
                // Extract multiple delimiters
                delimiterRegex = delimiterPart.substring(1, delimiterPart.length() - 1) // Remove outer []
                        .replace("][", "|") // Replace ][ with |
                        .replaceAll("([\\\\*+?\\[\\](){}.^$|])", "\\\\$1"); // Escape special characters
            } else {
                // Single delimiter
                delimiterRegex = delimiterPart.replaceAll("([\\\\*+?\\[\\](){}.^$|])", "\\\\$1");
            }

            // Split books by delimiter
            String[] bookEntries = bookData.split(delimiterRegex);

            for (String entry : bookEntries) {
                String[] bookDetails = entry.split(",");
                if (bookDetails.length == 4) {
                    int year;
                    try {
                        year = Integer.parseInt(bookDetails[3]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid publication year format.");
                    }
                    if (String.valueOf(year).length() != 4) {
                        throw new IllegalArgumentException("Publication year must be a four-digit number.");
                    }
                    addBook(new Book(bookDetails[0], bookDetails[1], bookDetails[2], year));
                } else {
                    throw new IllegalArgumentException("Invalid book data format.");
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid input format. Specify delimiters using //.");
        }
    }



    public int getAddCalledCount() {
        return addCallCount;
    }

    @Override
    public void borrowBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available.");
        }
        book.setAvailable(false);
    }

    @Override
    public void returnBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        if (book.isAvailable()) {
            throw new IllegalStateException("Book was not borrowed.");
        }
        book.setAvailable(true);
    }

    @Override
    public List<Book> viewAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}

