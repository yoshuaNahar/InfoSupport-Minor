# Case 1

This is the first case of the InfoSupport Java Minor.

#####Notes:
Tests for the views and controllers will not 
be written. We have not covered that part in 
this course yet.

The backend of this project is English, but 
the presentation is Dutch.

I don't test the `dao`
classes, because they contain no business
logic and you shouldn't test your frameworks code. I
test them indirectly through the services. I don't mock.
https://zeroturnaround.com/rebellabs/dont-test-blindly-the-right-methods-for-unit-testing-your-java-apps/

I am using [springloaded](https://github.com/spring-projects/spring-loaded):<br>
Add this as a VM option
`-javaagent:/home/yoshua/.m2/springloaded-1.2.7.RELEASE.jar -noverify`

######Technology used:
- Spring WebMVC
- Spring ORM
- JPA/Hibernate 
- Thymeleaf
