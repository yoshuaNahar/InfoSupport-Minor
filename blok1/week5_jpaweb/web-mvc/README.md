### Instructions to deploy application

1. The application was tested against Tomcat 8.5
1. Against mysql 5.6.21

#### Creating a database schema

1. To create a database scheme use the StandaloneJpaTest.javatest class inside module jpa 2.1
1. Inside  in the src/test/java folder execute the createDatabaseStructureAndPopulateBooksTable() test method. This method will create all the tables and a few rows inside the books table will be created.
1. The database assumes a mysql database.
  * name="javax.persistence.jdbc.user" value="cursist"
  * name="javax.persistence.jdbc.password" value="password"
  * name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/springdb"
  
#### Dabatabase Properties of the running app

1. Note that the database connection properties during a deploy are different.
1. Look at:   src/main/resources
  1. META-INF/persistence.xml
  1. dao-context.xml
  