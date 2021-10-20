package com.example.demo.Contoller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookRepository Brepo;

	private final ObjectMapper om=new ObjectMapper();
	
	@Test
	public void testSaveBookShouldReturnCreated() throws JsonProcessingException, Exception
	{
		Book book=new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.save(any(Book.class))).thenReturn(book);
		mockMvc.perform(post("/book/saveBook")				
				.content(om.writeValueAsString(book))
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.bookId",is(1)))
				.andExpect(jsonPath("$.bookName",is("Advanced Java")))
				.andExpect(jsonPath("$.bookPrice",is(1500.00)))
				.andExpect(jsonPath("$.bookAuthor",is("MMK")))
				;
			verify(Brepo,times(1)).save(any(Book.class));
		}
	
	@Test
	public void testAllBooks() throws Exception
	{
		List<Book> allBooks=Arrays.asList(new Book(1, "Advanced Java", 1500.00, "MMK"),
					  new Book(2, "Basic of Cloud Computing", 1000.00, "logan"));
		when(Brepo.findAll()).thenReturn(allBooks);
		mockMvc.perform(get("/book/allBooks"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$",hasSize(2)))
						.andExpect(jsonPath("$[0].bookId",is(1)))
						.andExpect(jsonPath("$[0].bookName",is("Advanced Java")))
						.andExpect(jsonPath("$[0].bookPrice",is(1500.00)))
						.andExpect(jsonPath("$[0].bookAuthor",is("MMK")))
						.andExpect(jsonPath("$[1].bookId",is(2)))
						.andExpect(jsonPath("$[1].bookName",is("Basic of Cloud Computing")))
						.andExpect(jsonPath("$[1].bookPrice",is(1000.00)))
						.andExpect(jsonPath("$[1].bookAuthor",is("logan")))
						;
		
		verify(Brepo,times(1)).findAll();
		
	}
	
	@Test
	public void testFindBookById() throws JsonProcessingException, Exception
	{
		Book book=new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findById(any(Integer.class))).thenReturn(Optional.of(book));
		Integer bookId=any(Integer.class);
		mockMvc.perform(get("/book/{bookId}",bookId)				
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.bookId",is(1)))
				.andExpect(jsonPath("$.bookName",is("Advanced Java")))
				.andExpect(jsonPath("$.bookPrice",is(1500.00)))
				.andExpect(jsonPath("$.bookAuthor",is("MMK")))
				;
		verify(Brepo,times(1)).findById(any(Integer.class));
		}
	
	
	@Test
	public void testUpdateBookShouldReturnCreated() throws JsonProcessingException, Exception
	{
	Book book=new Book(1, "Advanced Java", 1500.00, "MMK");
	when(Brepo.save(any(Book.class))).thenReturn(book);
	when(Brepo.findById(any(Integer.class))).thenReturn(Optional.of(book));
	mockMvc.perform(post("/book/updateBook")
	.content(om.writeValueAsString(book))
	.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
	.andExpect(status().isCreated())
	.andExpect(jsonPath("$.bookId",is(1)))
	.andExpect(jsonPath("$.bookName",is("Advanced Java")))
	.andExpect(jsonPath("$.bookAuthor",is("MMK")))
	.andExpect(jsonPath("$.bookPrice",is(1500.00)))
	;
	verify(Brepo,times(2)).findById(any(Integer.class));
	}
	
	@Test
	public void testFindBookByBookAuthor() throws JsonProcessingException, Exception
	{
		Book book=new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findByBookAuthor(any(String.class))).thenReturn(Arrays.asList(book));
		String authorName="MMk";
		mockMvc.perform(get("/book/bookAuthor/{authorName}",authorName)
		.content(om.writeValueAsString(book))
		.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].bookId",is(1)))
		.andExpect(jsonPath("$[0].bookName",is("Advanced Java")))
		.andExpect(jsonPath("$[0].bookPrice",is(1500.00)))
		.andExpect(jsonPath("$[0].bookAuthor",is("MMK")))
		;
		verify(Brepo,times(1)).findByBookAuthor(any(String.class));
		}
	@Test
    public void testDeleteBookById() throws JsonProcessingException, Exception
    {
        Book book=new Book(1, "Advanced Java", 1500.00, "MMK");
        when(Brepo.findById(any(Integer.class))).thenReturn(Optional.of(book));
        mockMvc.perform(delete("/book/{bookId}",1))           
                .andExpect(status().isOk())       
                ;
            verify(Brepo,times(1)).deleteById(any(Integer.class));
        }
	@Test
	public void testFindBookByBookName() throws JsonProcessingException, Exception
	{
		Book book=new Book(1, "Advanced Java", 1500.00, "MMK");
		when(Brepo.findByBookName(any(String.class))).thenReturn(Arrays.asList(book));
		String bookname="Advanced Java";
		mockMvc.perform(get("/book/bookName/{bookName}",bookname)
		.content(om.writeValueAsString(book))
		.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].bookId",is(1)))
		.andExpect(jsonPath("$[0].bookName",is("Advanced Java")))
		.andExpect(jsonPath("$[0].bookPrice",is(1500.00)))
		.andExpect(jsonPath("$[0].bookAuthor",is("MMK")))
		;
		verify(Brepo,times(1)).findByBookName(any(String.class));
		}
}
