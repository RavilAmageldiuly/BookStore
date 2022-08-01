package kz.halykacademy.bookstore.service.books;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.authors.AuthorRepository;
import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.books.SaveBook;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBook(long id) {
        return bookRepository.getReferenceById(id).toDto();
    }


    @Transactional
    @Override
    public Book putBook(long id, SaveBook saveBook) {
        return bookRepository.save(
                new BookEntity(
                    id,
                    saveBook.getPrice(),
                    getAllAuthors(saveBook),
                    publisherRepository.getReferenceById(saveBook.getPublisherId()),
                    saveBook.getTitle(),
                    saveBook.getNumberOfPages(),
                    saveBook.getReleaseYear()
                )
        ).toDto();

    }


    @Override
    public Book postBook(SaveBook saveBook) {
        return bookRepository.save(
                new BookEntity(
                        saveBook.getId(),
                        saveBook.getPrice(),
                        getAllAuthors(saveBook),
                        publisherRepository.getReferenceById(saveBook.getPublisherId()),
                        saveBook.getTitle(),
                        saveBook.getNumberOfPages(),
                        saveBook.getReleaseYear()
                )
        ).toDto();
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public List<AuthorEntity> getAllAuthors(SaveBook saveBook) {
        return saveBook.getAuthorList().stream().map(authorRepository::getReferenceById).collect(Collectors.toList());
    }
}
