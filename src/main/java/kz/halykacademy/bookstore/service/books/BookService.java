package kz.halykacademy.bookstore.service.books;


import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.books.SaveBook;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getBook(long id);

    Book putBook(long id, Book book);

    Book postBook(SaveBook saveBook);

    void deleteBook(long id);
}
