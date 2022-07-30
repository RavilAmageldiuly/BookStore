package kz.halykacademy.bookstore.service.authors;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.authors.AuthorRepository;
import kz.halykacademy.bookstore.web.authors.Author;
import kz.halykacademy.bookstore.web.authors.SaveAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface AuthorService {

    List<Author> getAll();

    Author getAuthor(long id);

    Author putAuthor(long id, SaveAuthor author);

    Author postAuthor(SaveAuthor author);

    void deleteAuthor(long id);
}


