package com.example.demo.Exception;

public class BookNotFoundException extends RuntimeException {
	
	public BookNotFoundException() {
		}
	
	
	public BookNotFoundException(String msg) {
		super(msg);
	}
}
