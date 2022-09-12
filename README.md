

## Book Store API Project

This project is a RESTful API for online bookstore made with Spring boot  
You can create, read, update and delete (CRUD) books, authors, publishers, users, genres, and orders


### Used Technologies:

* Language: Java
* Framework: Spring
* Database: PostgreSQL
* Project building tool: Maven
* Database migration tool: Flyway
* Documentation: Openapi 3.0


### How to download, run and test a project:

1. Download the source code
2. Connect a database to your IDE (I've used Intellij idea and PostgreSQL)
3. Create a database (name it as you wish)
4. Create a file application.yaml in directory src/main/resources/
5. Include necessary fields (the sample is in the root directory of the project)
6. Run the project in your IDE
7. You can test it with API platform like Postman. There is swagger-conf.yaml openapi documentation for convenience of this process in the root directory.  
#### For some CRUD operations you need to authorize/authenticate
username and password for:
* user: user, user
* admin: admin, admin  
then copy the jwt token and include it in headers of your request with header Authorization, and with prefix Bearer_
