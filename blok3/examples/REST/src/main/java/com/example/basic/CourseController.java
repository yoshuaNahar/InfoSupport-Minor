package com.example.basic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	
	// Available on http://localhost:8080/hello
	@RequestMapping("/course")
	public Course helloWorld() {
		Book book = new Book("Core Spring");
		Course course = new Course("Veenendaal", book);
		return course;
	}
}
