package kz.halykacademy.bookstore.service.books;

import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.books.SaveBook;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
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
    public Book putBook(long id, Book book) {
        bookRepository.updateBookById(
                id,
                book.getPrice(),
                book.getTitle(),
                book.getReleaseYear(),
                book.getNumberOfPages()
        );
        return bookRepository.getReferenceById(id).toDto();
    }

    @Override
    public Book postBook(SaveBook saveBook) {
//        long publisher_id = 1;
        BookEntity saved = bookRepository.save(
                new BookEntity(
                        saveBook.getId(),
                        saveBook.getPrice(),
                        null,
                        publisherRepository.getReferenceById(saveBook.getPublisherId()),
                        saveBook.getTitle(),
                        saveBook.getNumberOfPages(),
                        saveBook.getReleaseYear()
                )
        );
        return saved.toDto();
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
