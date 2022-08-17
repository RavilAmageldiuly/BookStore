package kz.halykacademy.bookstore.service.authors;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.authors.AuthorRepository;
import kz.halykacademy.bookstore.web.authors.Author;
import kz.halykacademy.bookstore.web.authors.SaveAuthor;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Author getAuthor(long id) {
        return authorRepository.findById(id)
                .map(AuthorEntity::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found. Invalid id supplied!"));
    }

    @Transactional
    @Override
    public Author putAuthor(long id, SaveAuthor author) {
        if (!authorRepository.existsById(id))
            throw new ResourceNotFoundException("Author not found. Invalid id supplied!");
        authorRepository.updateAuthorById(
                id,
                author.getFirstName(),
                author.getLastName(),
                author.getPatronymic(),
                author.getBirthday()
        );

        return authorRepository.getReferenceById(id).toDto();
    }

    @Override
    public Author postAuthor(SaveAuthor saveAuthor) {
        AuthorEntity saved = authorRepository.save(
                new AuthorEntity(
                        saveAuthor.getId(),
                        saveAuthor.getFirstName(),
                        saveAuthor.getLastName(),
                        saveAuthor.getPatronymic(),
                        saveAuthor.getBirthday(),
                        null
                )
        );
        return saved.toDto();
    }

    @Override
    public void deleteAuthor(long id) {
        AuthorEntity author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author not found. Invalid id supplied!")
        );

        author.getBooksList().forEach(bookEntity -> {
            bookEntity.removeAuthor(author);
        });

        author.getBooksList().clear();
        authorRepository.save(author);

        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> getAuthorsByFIO(String firstName, String lastName, String patronymic) {
        return authorRepository.findAllByFirstNameContainingAndLastNameContainingAndPatronymicContainingIgnoreCase(firstName, lastName, patronymic).stream().map(AuthorEntity::toDto).collect(Collectors.toList());
    }
}