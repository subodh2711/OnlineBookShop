package com.hms.controller;

import com.hms.dao.BookDao;
import com.hms.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookShop")
public class OnlineBookShopController {

	@Autowired
	BookDao bookDao;
	
	@RequestMapping("/")
	String home() {
		return "Ok";
	}
   @PostMapping("/post")
   public ResponseEntity<String> addBook( @RequestBody Book book) throws  Exception{
     return ResponseEntity.ok(bookDao.addBook(book));
   }

   @GetMapping("/getBooks")
   List<Book> getBooks(){
	   return bookDao.getAllBooks();
   }


	@GetMapping("/getBooks/isbn/{name}")
	Book findBookByISBN(@PathVariable("name") String isbn ) {
		return bookDao.findBookByIsbn(isbn);
	}

    @GetMapping("/getBooks/title/{title}")
    List<Book> findBooksByTitle(@PathVariable("title") String title ) {
        return bookDao.findBookByTitle(title);
    }
    @GetMapping("/getBooks/author/{author}")
    List<Book> findBooksByAuthor(@PathVariable("author") String author ) {
        return bookDao.findBookByAuthor(author);
    }

    @GetMapping("/getBooks/searchMedia/{isbn}")
    String searchMediaCoverageByISBN(@PathVariable("isbn") String isbn ) {
        return bookDao.searchMediaCoverageByISBN(isbn);
    }

    @DeleteMapping("/buyBook/{id}")
    String buyBookById(@PathVariable("id") Long id ) {
        return bookDao.buyBookById(id);
    }
}
