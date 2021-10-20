package com.example.demo.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.Entity.Book;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository Brepo;

	@Test
	@Rollback(value = false)
	@Order(1) // Save Book
	public void testSaveBook() {
		Book book = new Book(1, "Advanced Java", 1500.00, "sedhu");
		Book savedBook = Brepo.save(book);
		Assertions.assertNotNull(savedBook);
		Assertions.assertEquals("sedhu", savedBook.getBookAuthor());
	}

	@Test
	@Order(2) // Get All Book
	public void testGetAllBooks() {
		List<Book> allbooks = Brepo.findAll();
		System.out.println(allbooks);
		Assertions.assertEquals(1, allbooks.size());
	}

	@Test
	@Order(3) // Get By Book By ID
	public void testGetBookById() {
		Integer bookId = 1;
		Optional<Book> book = Brepo.findById(bookId);
		boolean isBookAvialable = book.isPresent();
		System.out.println("Book With ID :" + bookId + book.get());
		Assertions.assertTrue(isBookAvialable);
	}

	@Test
	@Order(4) // Get By Book Name
	public void findTestByBookName() {
		String Bookname = "Advanced Java";
		List<Book> allBooks = Brepo.findByBookName(Bookname);
		System.out.println(allBooks);
		assertThat(allBooks).hasSize(1);
	}

	@Test
	@Order(5) // Get Book By Name
	public void findTestByBookAuthor() {
		String Authorname = "sedhu";
		List<Book> allBooks = Brepo.findByBookAuthor(Authorname);
		System.out.println(allBooks);
		assertThat(allBooks).hasSize(1);
	}

	@Test
	@Order(6) // Delete Book By ID
	public void testDeleteBookById() {
		Integer BookId = 1;
		Brepo.deleteById(BookId);
		Optional<Book> book = Brepo.findById(1);
		assertTrue(book.isEmpty());
	}
}
