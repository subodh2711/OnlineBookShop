package com.hms.repository;

import com.hms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);

    List<Book> findByTitleIgnoreCaseContaining(String title);

    List<Book> findByAuthorIgnoreCaseContaining(String author);
}