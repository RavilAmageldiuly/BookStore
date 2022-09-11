

## Book Store API Project

This project is a RESTful API for online bookstore made with Spring boot
You can create, read, update, delete (CRUD) books, authors, publishers, users, genres, and orders


### Used Technologies:

Language: Java
Framework: Spring
Database: PostgreSQL
Project building tool: Maven
Database migration tool: Flyway
Documentation: Openapi 3.0


### How to download, run and test project:

1. Download the source code
2. Create file application.yaml in directory src/main/resources/
3. Include necessary fields (sample is in root directory of the project)
4. Create database in your DBMS
5. Run the project in your IDE (I've used Intellij idea)
6. You can test it with API platform like Postman. There is swagger-conf.yaml openapi documentation for convenience of this process in root directory.
** For some CRUD operations you need to authorize/authenticate **
username and password for:
* user: user, user
* admin: admin, admin
then copy the jwt token and include it in headers of your request with header Authorization, and with prefix Bearer_
