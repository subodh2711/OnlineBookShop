package com.hms.dao;

import com.hms.model.Book;

import java.util.List;

public interface BookDao {
    public String addBook(Book book);

    public List<Book> getAllBooks();

    public Book findBookByIsbn(String isbn);

    public List<Book> findBookByTitle(String title);

    public List<Book> findBookByAuthor(String author);

    String searchMediaCoverageByISBN(String isbn);

    String buyBookById(Long isbn);
}
