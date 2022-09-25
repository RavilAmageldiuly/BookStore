package kz.halykacademy.bookstore.service.authors;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.authors.AuthorRepository;
import kz.halykacademy.bookstore.service.MainService;
import kz.halykacademy.bookstore.web.authors.Author;
import kz.halykacademy.bookstore.web.authors.SaveAuthor;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthorServiceImpl implements MainService<Author, SaveAuthor> {

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
    public Author getIndividual(long id) {
        return authorRepository.findById(id)
                .map(AuthorEntity::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found. Invalid id supplied!"));
    }

    @Transactional
    @Override
    public Author putIndividual(long id, SaveAuthor author) {
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
    public Author postIndividual(SaveAuthor saveAuthor) {
        AuthorEntity saved = authorRepository.save(
                new AuthorEntity(
                        null,
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
    public void deleteIndividual(long id) {
        AuthorEntity author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author not found. Invalid id supplied!")
        );

        author.getBooksList().forEach(bookEntity -> bookEntity.removeAuthor(author));

        author.getBooksList().clear();
        authorRepository.save(author);

        authorRepository.deleteById(id);
    }
}