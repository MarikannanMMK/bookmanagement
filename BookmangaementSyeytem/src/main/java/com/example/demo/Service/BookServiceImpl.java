package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Exception.BookNotFoundException;
import com.example.demo.Repository.BookRepository;

@Service

public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository brepo;

	@Override
	public Book saveBook(Book book) {
		Book savedBook = brepo.save(book);
		return savedBook;
	}

	@Override
	public void deleteBook(Integer bookId) {
		Book book = findBookById(bookId);
		brepo.deleteById(book.getBookId());
	}

	@Override
	public Book findBookById(Integer bookId) {
		return brepo.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book With Given ID :" + bookId + " Not Available"));
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> allBooks = brepo.findAll();
		return allBooks;
	}

	@Override
	public Book updateBook(Book book) {
		brepo.findById(book.getBookId()).orElseThrow(
				() -> new BookNotFoundException("Book With Given ID :" + book.getBookId() + " Not Available"));
		Book book1 = brepo.findById(book.getBookId()).get();
		book1.setBookName(book.getBookName());
		book1.setBookAuthor(book.getBookAuthor());
		book1.setBookPrice(book.getBookPrice());
		return brepo.save(book1);
	}

	@Override
	public List<Book> findByBookAuthor(String bookAuthor) {
		List<Book> BookByAuthor = brepo.findByBookAuthor(bookAuthor);
		return BookByAuthor;
	}

	@Override
	public List<Book> findByBookName(String bookName) {
		List<Book> Books = brepo.findByBookName(bookName);
		return Books;
	}

}
