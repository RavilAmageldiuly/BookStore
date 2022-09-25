package kz.halykacademy.bookstore.service.books;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.authors.AuthorRepository;
import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.genres.GenreEntity;
import kz.halykacademy.bookstore.dao.genres.GenreRepository;
import kz.halykacademy.bookstore.dao.publishers.PublisherRepository;
import kz.halykacademy.bookstore.service.MainService;
import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.books.SaveBook;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements MainService<Book, SaveBook> {

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
    public Book getIndividual(long id) {
        return bookRepository.findById(id)
                .map(BookEntity::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found. Invalid id supplied!"));
    }


    @Transactional
    @Override
    public Book putIndividual(long id, SaveBook saveBook) {
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
    public Book postIndividual(SaveBook saveBook) {

        return bookRepository.save(
                new BookEntity(
                        null,
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
    public void deleteIndividual(long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found. Invalid id supplied!");
        }

        bookRepository.deleteById(id);
    }

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
        for (Long genreId : saveBook.getGenreList()) {
            genres.add(genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Invalid genre id supplied! Genre with id: " + genreId +
                    " does not exists")));
        }
        return genres;
    }

    public int overlapCount(BookEntity book, List<String> genres) {
        int overlapCount = 0;
        for (String genre : genres) {
            if (book.getGenre().contains(formatGenre(genre))) {
                overlapCount++;
            }
        }

        return overlapCount;
    }

    public List<BookEntity> getBooksByGenre(List<String> genres) {

        List<BookEntity> booksByGenre;

        HashMap<BookEntity, Integer> booksByGenreWithOverlap = new HashMap<>();

        for (BookEntity book : bookRepository.findAll()) {
            if (overlapCount(book, genres) > 0)
                booksByGenreWithOverlap.put(book, overlapCount(book, genres));
        }

        booksByGenre = sortValues(booksByGenreWithOverlap);

        Collections.reverse(booksByGenre);

        return booksByGenre;

    }

    private List sortValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        List sortedList = new ArrayList<>();

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedList.add(entry.getKey());
        }

        return sortedList;
    }

    public List<AuthorEntity> getAuthorsByGenre(List<String> genres) {

        List<AuthorEntity> allAuthors = authorRepository.findAll();
        HashMap<AuthorEntity, Integer> authorsWithOverlap = new HashMap<>();
        List<AuthorEntity> sortedAuthors;

        int counter = 0;
        for (AuthorEntity author : allAuthors) {
            Set<String> allAuthorGenres = author.getBooksList().stream().map(BookEntity::getGenre).flatMap(Collection::stream).collect(Collectors.toSet());
            for (String genre : genres) {
                if (allAuthorGenres.contains(formatGenre(genre))) {
                    counter++;
                }
            }
            if (counter > 0)
                authorsWithOverlap.put(author, counter);
            counter = 0;
        }

        sortedAuthors = sortValues(authorsWithOverlap);

        Collections.reverse(sortedAuthors);

        return sortedAuthors;
    }

    private String formatGenre(String genre) {
        return genre.substring(0, 1).toUpperCase() +
                genre.substring(1).toLowerCase();
    }
}
