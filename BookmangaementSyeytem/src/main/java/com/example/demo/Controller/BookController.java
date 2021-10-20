package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/book")
@Api(value = "Book Rest Controller", description = "REST API's Related To Book Entity")
public class BookController {

	@Autowired
	private BookService bservice;

	@PostMapping(value = "/saveBook")
	@ApiOperation(value = "This Method is for save operation,Providing Book Details and this method returns Saved Book Object")
	public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
		Book savedBook = bservice.saveBook(book);
		return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{bookId}")
	@ApiOperation(value = "This Method is for Get Single Book object By Providing Book ID as input")
	public ResponseEntity<Book> getBookById(@PathVariable Integer bookId) {
		Book book = bservice.findBookById(bookId);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@GetMapping(value = "/allBooks")
	@ApiOperation(value = "This Method is for Get All Book Objects in the form of List")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> allBooks = bservice.getAllBooks();
		return new ResponseEntity<List<Book>>(allBooks, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{bookId}")
	@ApiOperation(value = "This Method is for Delete Book object By Providing Book ID as input")
	public ResponseEntity<String> deleteBookById(@PathVariable Integer bookId) {
		bservice.deleteBook(bookId);
		String msg = "Book With ID :" + bookId + " Deleted Successfully";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping(value = "/bookName/{bookName}")
	@ApiOperation(value = "This Method is for Get List of Books object By Providing Book Name as input")
	public ResponseEntity<List<Book>> getBookById(@PathVariable String bookName) {
		List<Book> book = bservice.findByBookName(bookName);
		return new ResponseEntity<List<Book>>(book, HttpStatus.OK);
	}

	@GetMapping(value = "/bookAuthor/{bookAuthor}")
	@ApiOperation(value = "This Method is for Get List of Book object By Providing Book Name as input")
	public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String bookAuthor) {
		List<Book> BookByAuthor = bservice.findByBookAuthor(bookAuthor);
		return new ResponseEntity<List<Book>>(BookByAuthor, HttpStatus.OK);

	}

	@PostMapping(value = "/updateBook")
	@ApiOperation(value = "This Method is for update the Book Details and this method returns Updated Book Object")
	public ResponseEntity<Book> updateBook(@Valid @RequestBody Book Book) {
		Book updatedBook = bservice.updateBook(Book);
		return new ResponseEntity<Book>(updatedBook, HttpStatus.CREATED);
	}
}
