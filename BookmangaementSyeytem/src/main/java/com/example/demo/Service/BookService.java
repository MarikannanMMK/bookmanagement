package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Book;

public interface BookService {
	
	Book saveBook(Book book);
	Book findBookById(Integer bookId);
	List<Book> getAllBooks();
	void deleteBook(Integer bookId);
	List<Book> findByBookAuthor(String bookAuthor);
	List<Book> findByBookName(String bookName);
	Book updateBook(Book book);
}
