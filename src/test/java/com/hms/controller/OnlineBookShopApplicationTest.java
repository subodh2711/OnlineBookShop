package com.hms.controller;

import com.hms.OnlineBookShopApplication;
import com.hms.dao.BookDao;
import com.hms.model.Book;
import com.hms.repository.BookRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineBookShopApplication.class)
public class OnlineBookShopApplicationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDao bookDao;


    @After
    public void resetDb() {
        bookRepository.deleteAll();
    }

    @Test
    public void givenBookAddBookFindByAuthorContaining_thenReturnBook() throws Exception {

        Book bookOne = new Book();
        bookOne.setTitle("The C Programming Language");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        Book bookTwo = new Book();
        bookTwo.setTitle("The C Programming Language");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        bookRepository.save(bookOne);
        List<Book> found = bookRepository.findAll();
       assertThat(found).extracting(Book::getAuthor).containsOnly("Hello");
    }


    @Test
    public void givenBookAddTWOBook_thenReturnBooks() throws Exception {

        Book bookOne = new Book();
        bookOne.setTitle("The C Programming Language");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        Book bookTwo = new Book();
        bookTwo.setTitle("The C Programming Language");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        bookRepository.save(bookOne);
        bookRepository.save(bookTwo);
        List<Book> foundTwo = bookRepository.findAll();
        assertThat(foundTwo.size()).isEqualTo(2);
    }

    @Test
    public void givenBookTitle_thenReturnBooks() throws Exception {

        Book bookOne = new Book();
        bookOne.setTitle("The C Programming Language");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        bookRepository.save(bookOne);

        List<Book> books =bookDao.findBookByTitle(bookOne.getTitle());
        assertThat(books.get(0).getTitle()).isEqualTo("The C Programming Language");
    }

    @Test
    public void givenBookAuthor_thenReturnBooks() throws Exception {

        Book bookOne = new Book();
        bookOne.setTitle("The C Programming Language");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        bookRepository.save(bookOne);
        List<Book> books =bookDao.findBookByAuthor(bookOne.getAuthor());
        assertThat(books.get(0).getAuthor()).isEqualTo("Hello");
    }

    @Test
    public void givenBookISBN_thenReturnBooks() throws Exception {

        Book bookOne = new Book();
        bookOne.setTitle("The C Programming Language");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        bookRepository.save(bookOne);
        Book books =bookDao.findBookByIsbn(bookOne.getIsbn());
        assertThat(books.getIsbn()).isEqualTo("12132");
    }


    @Test
    public void givenBookISBNSearchMediaCoverage_thenReturnBookTitleNull() throws Exception {

        Book bookOne = new Book();
        bookOne.setTitle("The C Programming Language");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        bookRepository.save(bookOne);
        String title =bookDao.searchMediaCoverageByISBN(bookOne.getIsbn());
        assertThat(title).isEqualTo(null);
    }


    @Test
    public void givenBookISBNSearchMediaCoverage_thenReturnBookTitle() throws Exception {

        Book bookOne = new Book();
        bookOne.setTitle("optio");
        bookOne.setIsbn("12132");
        bookOne.setAuthor("Hello");

        bookRepository.save(bookOne);
        String title =bookDao.searchMediaCoverageByISBN(bookOne.getIsbn());
        assertThat(title).isEqualTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }
}