package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

@Table(name = "Book_tb")
@ApiModel(description = "This is Book Entiy ,To perform various operations on Book")
public class Book {

	public Book(Integer bookId, @NotEmpty(message = "Please provide Book  Name") String bookName,
			@NotNull(message = "Please provide Book Price") Double bookPrice,
			@NotEmpty(message = "Please provide Book Author Name") String bookAuthor) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookAuthor = bookAuthor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	@Column
	@ApiModelProperty(notes = "Name of the Book", name = "bname", required = true)
	@NotEmpty(message = "Please provide Book  Name")
	private String bookName;
	@Column
	@ApiModelProperty(notes = "Price of the Book", name = "bprice", required = true)
	@NotNull(message = "Please provide Book Price")
	private Double bookPrice;
	@Column
	@ApiModelProperty(notes = "Name of the Book Author", name = "bauthor", required = true)
	@NotEmpty(message = "Please provide Book Author Name")
	private String bookAuthor;

}
