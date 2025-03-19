package com.example.bookstore;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(Book book) {
        repository.save(book);
    }

    public Book getBookById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public void deleteBook(String id) {
        repository.delete(id);
    }
}
