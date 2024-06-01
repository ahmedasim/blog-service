# Blog Service Assignment

## Description
This project is a simple Blog Management System developed using Spring Boot, Java 17, Hibernate, Spring Data JPA, and MySQL. It provides REST APIs for creating and fetching authors, as well as adding posts associated with authors. The system ensures all required validations are in place and the database tables are properly indexed for optimized performance. Additionally, unit tests are included to ensure code coverage and reliability.

## Technology Stack
- Spring Boot 3.3.0
- Java 17 (**Used Java 17 instead of Java 18 due to some Maven compatibility issues on my machine, you can change to Java 18+ in `pom.xml`**)
- JUnit 5
- Maven
- Hibernate
- Spring Data JPA
- MySQL
- Flyway
- Swagger (spring doc open ui)
- Jacoco

## Instructions to Run the Project

1. Clone the repository from GitHub: [https://github.com/ahmedasim/blog-service](https://github.com/ahmedasim/blog-service)
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse, SpringToolSuite).
3. Ensure you have Java 17+ installed on your machine.
4. Configure MySQL database settings in `application.yml` file located in `src/main/resources`.
5. Create Database `blog-service` in MySQL. Change the database name in `application.yml` if you are using a different name.
6. Change the database username and password in `application.yml`.
7. Default port of the database is 7000. Configure as per your database port in `application.yml`.
8. Build the project using Maven: `mvn clean install`.
9. Run the application: `mvn spring-boot:run`.
10. The application will start running on `localhost:9091`.

## API Endpoints
You can use the Swagger URL ([http://localhost:9091/swagger-ui/index.html](http://localhost:9091/swagger-ui/index.html)) for API Endpoints.

### Author Controller
- Create Author: `POST /api/v1/authors`
- Update Author: `PUT /api/v1/authors/{authorId}`
- Get Author By ID: `GET /api/v1/authors/{authorId}`
- Delete Author: `DELETE /api/v1/authors/{authorId}`
- Get All Authors: `GET /api/v1/authors`

### Post Controller
- Create Post: `POST /api/v1/posts`
- Update Post: `PUT /api/v1/posts/{postId}`
- Get Post By ID: `GET /api/v1/posts/{postId}`
- Delete Post: `DELETE /api/v1/posts/{postId}`
- Get All Posts: `GET /api/v1/posts`
- Get Posts By Author: `GET /api/v1/posts/author-posts/{authorId}`
- Get Author Deleted Posts: `GET /api/v1/posts/author-deleted-posts/{authorId}`
- Get Author Active Posts: `GET /api/v1/posts/author-active-posts/{authorId}`

## Scripts for Database Setup

- Create a script file named `V01__DB_SCHEMA.sql` containing SQL commands to create the necessary database schema and tables, as well as any required indexes.
- Provide sample data insertion scripts (`V01__DB_SCHEMA.sql`) to populate the database with initial data.
- Used Flyway for database migration script (`src/main/resources/db/migration/V01__DB_SCHEMA.sql`).
  - Application will automatically execute these scripts on startup.   
      

## Running Unit Tests

- Run unit tests using Maven: `mvn test` or right-click on the project and Run As Maven Test or Junit Test.
- Check the test coverage report generated in the `target/site/jacoco/index.html` file. 
- Test coverage is not 100% due to some getter and setter methods. It's 97%.

## Project Repository
The project is hosted on GitHub: [https://github.com/ahmedasim/blog-service](https://github.com/ahmedasim/blog-service)

## Project Structure

```markdown
blog-service/  
├── src/  
│   ├── main/  
│   │   ├── java/  
│   │   │   └── com/  
│   │   │       └── aa/  
│   │   │           └── blog_service/  
│   │   │               ├── BlogServiceApplication.java  
│   │   │               ├── controller/  
│   │   │               │   ├── AuthorController.java  
│   │   │               │   └── PostController.java  
│   │   │               ├── dto/  
│   │   │               │   ├── AuthorRequestDto.java  
│   │   │               │   ├── AuthorResponseDto.java  
│   │   │               │   ├── PostRequestDto.java  
│   │   │               │   ├── PostResponseDto.java  
│   │   │               │   └── common/  
│   │   │               │       ├── ApiError.java  
│   │   │               │       └── ApiResponse.java  
│   │   │               ├── entity/  
│   │   │               │   ├── Author.java  
│   │   │               │   └── Post.java  
│   │   │               ├── exception/  
│   │   │               │   └── GlobalExceptionHandler.java  
│   │   │               ├── repo/  
│   │   │               │   ├── AuthorRepo.java  
│   │   │               │   └── PostRepo.java  
│   │   │               ├── service/  
│   │   │               │   ├── AuthorService.java  
│   │   │               │   ├── PostService.java  
│   │   │               │   └── impl/  
│   │   │                   ├── AuthorServiceImpl.java  
│   │   │                   └── PostServiceImpl.java  
│   │   ├── resources/  
│   │   │   ├── application.yml  
│   │   │   └── db/  
│   │   │       └── migration/  
│   │   │           └── V01__DB_SCHEMA.sql  
│   ├── test/  
│   │   ├── java/  
│   │   │   └── com/  
│   │   │       └── aa/  
│   │   │           └── blog_service/  
│   │   │               ├── BlogServiceApplicationTests.java  
│   │   │               ├── controller/  
│   │   │               │   ├── AuthorControllerTest.java  
│   │   │               │   └── PostControllerTest.java  
│   │   │               ├── service/  
│   │   │                   ├── AuthorServiceImplTest.java  
│   │   │                   └── PostServiceImplTest.java  
├── pom.xml  
└── README.md  
```

## Contributors
Asim Ahmed
Feel free to reach out for any further assistance or inquiries!

## Contact me:
- Email: **sendtoasimoff@gmail.com**
- LinkedIn:  ([https://www.linkedin.com/in/ahmedasim01/](https://www.linkedin.com/in/ahmedasim01/))
- HackerRank: ([https://www.hackerrank.com/ahmedasim01/](https://www.hackerrank.com/ahmedasim01/))
- Medium: ([https://medium.com/@ahmedasim01 /](https://medium.com/@ahmedasim01/))
