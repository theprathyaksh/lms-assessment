package org.example.library;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library implements LibraryActions {
    private final Map<String, Book> books;

    public Library() {
        this.books = new HashMap<>();
    }
    @Override
    public void addBook(Book book) {
        if (book == null || book.getIsbn().trim().isEmpty() || book.getTitle().trim().isEmpty() || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Book details cannot be empty.");
        }
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists.");
        }
        books.put(book.getIsbn(), book);
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

