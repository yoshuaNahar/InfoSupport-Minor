package com.example.swagger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SwaggerCourseController {
	
	// Available on http://localhost:8080/swaggercourse
	// Swagger available on http://localhost:8080/swagger-ui.html
	@ApiOperation(notes = "This method allows you to retrieve a fixed course", value = "Retrieve course")
	    @ApiResponses({
	        @ApiResponse(code = 200, message = "Everything went ok.", response = Course.class),
	        @ApiResponse(code = 404, message = "Course not found.")
	})
	@RequestMapping(value="/swaggercourse",method={RequestMethod.GET})
	public Course helloWorld() {
		Book book = new Book("Core Spring");
		Course course = new Course("Veenendaal", book);
		return course;
	}
}
