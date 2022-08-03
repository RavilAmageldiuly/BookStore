package kz.halykacademy.bookstore.service.books;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.authors.AuthorRepository;
import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.genres.GenreEntity;
import kz.halykacademy.bookstore.dao.genres.GenreRepository;
import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import kz.halykacademy.bookstore.web.authors.Author;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.books.SaveBook;
import kz.halykacademy.bookstore.web.genres.Genre;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
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
        return bookRepository.findById(id)
                .map(BookEntity::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found. Invalid id supplied!"));
    }


    @Transactional
    @Override
    public Book putBook(long id, SaveBook saveBook) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found. Invalid id supplied!");
        } else if (!publisherRepository.existsById(saveBook.getPublisherId())) {
            throw new ResourceNotFoundException("Invalid publisher id supplied!");
        }

        checkAuthorAndGenreIds(saveBook);

        return bookRepository.save(
                new BookEntity(
                        id,
                        saveBook.getPrice(),
                        getAllAuthors(saveBook),
                        publisherRepository.getReferenceById(saveBook.getPublisherId()),
                        saveBook.getTitle(),
                        saveBook.getNumberOfPages(),
                        saveBook.getReleaseYear(),
                        getAllGenres(saveBook)
                )
        ).toDto();
    }


    @Override
    public Book postBook(SaveBook saveBook) {

        if (!publisherRepository.existsById(saveBook.getPublisherId())) {
            throw new ResourceNotFoundException("Invalid publisher id supplied!");
        }

        checkAuthorAndGenreIds(saveBook);

        return bookRepository.save(
                new BookEntity(
                        saveBook.getId(),
                        saveBook.getPrice(),
                        getAllAuthors(saveBook),
                        publisherRepository.getReferenceById(saveBook.getPublisherId()),
                        saveBook.getTitle(),
                        saveBook.getNumberOfPages(),
                        saveBook.getReleaseYear(),
                        getAllGenres(saveBook)
                )
        ).toDto();
    }

    @Override
    public void deleteBook(long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found. Invalid id supplied!");
        }

        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitle(title).stream().map(BookEntity::toDto).collect(Collectors.toList());
    }

    public List<AuthorEntity> getAllAuthors(SaveBook saveBook) {
        return saveBook.getAuthorList().stream().map(authorRepository::getReferenceById).collect(Collectors.toList());
    }

    public List<GenreEntity> getAllGenres(SaveBook saveBook) {
        return saveBook.getGenreList().stream().map(genreRepository::getReferenceById).collect(Collectors.toList());
    }

    public void checkAuthorAndGenreIds(SaveBook saveBook) {

        for (Long authorId : saveBook.getAuthorList()) {
            if (!authorRepository.existsById(authorId)) {
                throw new ResourceNotFoundException("Invalid author id supplied! Author with id: " + authorId +
                        " does not exists");
            }
        }

        for (Long genreId: saveBook.getGenreList()) {
            if (!genreRepository.existsById(genreId)) {
                throw new ResourceNotFoundException("Invalid genre id supplied! Genre with id: " + genreId +
                        " does not exists");
            }
        }
    }

    public List<Book> getBooksByGenre(List<String> genres) {
        List<Book> booksByGenre = new ArrayList<>();
        for (BookEntity book: bookRepository.findAll()) {
            if (book.getGenre().containsAll(genres)) {
                booksByGenre.add(book.toDto());
            }
        }
        return booksByGenre;
    }

    public List<AuthorEntity> getAuthorsByGenre(List<String> genres) {
        List<AuthorEntity> authorsByGenre = new ArrayList<>();
        for (BookEntity book: bookRepository.findAll()) {
            if (book.getGenre().containsAll(genres)) {
                authorsByGenre.addAll(book.getAuthorList());
            }
        }
        return authorsByGenre;
    }
}
