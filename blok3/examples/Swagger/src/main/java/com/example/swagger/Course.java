package com.example.swagger;

public class Course {
	private String location;
	
	private Book book;	

	public Course(String location, Book book) {
		super();
		this.location = location;
		this.book = book;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}	
}
