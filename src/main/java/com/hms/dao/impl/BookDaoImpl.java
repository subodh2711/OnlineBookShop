package com.hms.dao.impl;

import com.hms.dao.BookDao;
import com.hms.model.Book;
import com.hms.repository.BookRepository;
import com.hms.response.MediaCoverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    BookRepository bookRepository;

    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public String addBook(Book book) {
        if(book.getIsbn() == null || book.getTitle() == null || book.getAuthor() == null){
            return "Can't Add Book as ISBN , TITLE and Author are Mandatory fields";
        }
        bookRepository.save(book);
        return "Book Successfully Added";
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCaseContaining(title);
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCaseContaining(author);
    }

    @Override
    public String searchMediaCoverageByISBN(String isbn) {
        final Book book = bookRepository.findByIsbn(isbn);
        if(book != null){
        final ResponseEntity<List<MediaCoverage>> responseEntity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
                HttpMethod.GET,null,new ParameterizedTypeReference<List<MediaCoverage>>() {
                });
        if(HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            for (MediaCoverage mediaCoverage : responseEntity.getBody()) {
                   if(mediaCoverage.getTitle().toLowerCase().contains(book.getTitle().toLowerCase())
                           || mediaCoverage.getBody().toLowerCase().equals(book.getTitle().toLowerCase())){
                       return mediaCoverage.getTitle();
                   }
            }
        }
        }
        return null;
    }

    @Override
    public String buyBookById(Long id) {
         bookRepository.delete(id);
         return "SucessFully Deleted";
    }
}
