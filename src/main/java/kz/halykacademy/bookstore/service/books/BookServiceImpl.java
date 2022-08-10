package kz.halykacademy.bookstore.service.books;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.authors.AuthorRepository;
import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.genres.GenreEntity;
import kz.halykacademy.bookstore.dao.genres.GenreRepository;
import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.books.SaveBook;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
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
        if (!bookRepository.existsById(id))
            throw new ResourceNotFoundException("Book not found. Invalid id supplied!");

        return bookRepository.save(
                new BookEntity(
                        id,
                        saveBook.getPrice(),
                        checkAndGetAuthors(saveBook),
                        publisherRepository.findById(saveBook.getPublisherId()).orElseThrow(() ->
                                new ResourceNotFoundException("Invalid publisher id supplied!")),
                        saveBook.getTitle(),
                        saveBook.getNumberOfPages(),
                        saveBook.getReleaseYear(),
                        checkAndGetGenres(saveBook),
                        saveBook.getBookQuantity(),
                        null
                )
        ).toDto();
    }


    @Override
    public Book postBook(SaveBook saveBook) {

        return bookRepository.save(
                new BookEntity(
                        saveBook.getId(),
                        saveBook.getPrice(),
                        checkAndGetAuthors(saveBook),
                        publisherRepository.findById(saveBook.getPublisherId()).orElseThrow(() ->
                                new ResourceNotFoundException("Invalid publisher id supplied!")),
                        saveBook.getTitle(),
                        saveBook.getNumberOfPages(),
                        saveBook.getReleaseYear(),
                        checkAndGetGenres(saveBook),
                        saveBook.getBookQuantity(),
                        null
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
        return bookRepository.getBookEntitiesByTitleContainingIgnoreCase(title).stream().map(BookEntity::toDto).collect(Collectors.toList());
    }

    public List<AuthorEntity> checkAndGetAuthors(SaveBook saveBook) {
        List<AuthorEntity> authors = new ArrayList<>();
        for (Long authorId : saveBook.getAuthorList()) {
            authors.add(authorRepository.findById(authorId).orElseThrow(() ->
                    new ResourceNotFoundException("Invalid author id supplied! Author with id: " + authorId +
                    " does not exists")));
        }
        return authors;
    }

    public List<GenreEntity> checkAndGetGenres(SaveBook saveBook) {
        List<GenreEntity> genres = new ArrayList<>();
        for (Long genreId: saveBook.getGenreList()) {
            genres.add(genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Invalid genre id supplied! Genre with id: " + genreId +
                    " does not exists")));
        }
        return genres;
    }

    public List<Book> getBooksByGenre(List<String> genres) {
        List<Book> booksByGenre = new ArrayList<>();
        for (BookEntity book : bookRepository.findAll()) {
            if (new HashSet<>(book.getGenre()).containsAll(genres)) {
                booksByGenre.add(book.toDto());
            }
        }
        return booksByGenre;
    }

    public List<AuthorEntity> getAuthorsByGenre(List<String> genres) {
        List<AuthorEntity> authorsByGenre = new ArrayList<>();
        for (BookEntity book : bookRepository.findAll()) {
            if (new HashSet<>(book.getGenre()).containsAll(genres)) {
                authorsByGenre.addAll(book.getAuthorList());
            }
        }
        return authorsByGenre;
    }
}
