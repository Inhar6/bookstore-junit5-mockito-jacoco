package com.example.bookstore;

import java.util.*;

public class BookRepository {
    private final Map<String, Book> bookStore = new HashMap<>();

    public void save(Book book) {
        bookStore.put(book.getId(), book);
    }

    public Optional<Book> findById(String id) {
        return Optional.ofNullable(bookStore.get(id));
    }

    public void delete(String id) {
        bookStore.remove(id);
    }

    public List<Book> findAll() {
        return new ArrayList<>(bookStore.values());
    }
}
