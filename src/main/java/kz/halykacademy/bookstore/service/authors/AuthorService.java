package kz.halykacademy.bookstore.service.authors;

import kz.halykacademy.bookstore.web.authors.Author;
import kz.halykacademy.bookstore.web.authors.SaveAuthor;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getAuthor(long id);

    Author putAuthor(long id, SaveAuthor author);

    Author postAuthor(SaveAuthor saveAuthor);

    void deleteAuthor(long id);
}


