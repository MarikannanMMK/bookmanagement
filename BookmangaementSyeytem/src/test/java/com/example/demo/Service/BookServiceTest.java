package com.example.demo.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepository;

@SpringBootTest
public class BookServiceTest {

	@MockBean
	private BookRepository Brepo;

	@Autowired
	private BookService Bservice;

	@Test
	public void testSaveBookShouldReturnSavedBook() {
		Book book = new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.save(book)).thenReturn(book);

		Book savedBook = Bservice.saveBook(book);
		assertThat(savedBook).isNotNull();
		Assertions.assertEquals(book.getBookName(), savedBook.getBookName());

	}

	@Test
	public void testUpdateBook() {
		Book book = new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findById(1)).thenReturn(Optional.of(book));
		when(Brepo.save(book)).thenReturn(book);
		Book updatedBook = Bservice.updateBook(book);
		assertThat(updatedBook).isNotNull();
		Assertions.assertEquals(book.getBookName(), updatedBook.getBookName());
	}

	@Test
	public void testGetAllBooks() {
		Book book = new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findAll()).thenReturn(Arrays.asList(book));
		List<Book> BooksByName = Bservice.getAllBooks();
		assertThat(BooksByName).hasSize(1);
	}

	@Test
	public void testGetBookById() {
		Integer bookId = 1;
		Book book = new Book(bookId, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findById(bookId)).thenReturn(Optional.of(book));
		Book bookById = Bservice.findBookById(bookId);
		assertThat(bookById).isNotNull();
		assertThat(bookById.getBookId()).isEqualTo(bookId);
	}

	@Test
	public void testGetBookByBookAuthor() {
		String authorName = "DevRaj";
		Book book = new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findByBookAuthor(authorName)).thenReturn(Arrays.asList(book));
		List<Book> BooksByAuthor = Bservice.findByBookAuthor(authorName);
		assertThat(BooksByAuthor).hasSize(1);
	}

	@Test
	public void testGetBookByBookName() {
		String BookName = "DevRaj";
		Book book = new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findByBookAuthor(BookName)).thenReturn(Arrays.asList(book));
		List<Book> BooksByAuthor = Bservice.findByBookAuthor(BookName);
		assertThat(BooksByAuthor).hasSize(1);
	}

	@Test
	public void testDeleteUser() {
		Integer bookId = 1;
		Book book = new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findById(bookId)).thenReturn(Optional.of(book));
		Bservice.deleteBook(bookId);
		verify(Brepo, times(1)).deleteById(bookId);
	}

}
