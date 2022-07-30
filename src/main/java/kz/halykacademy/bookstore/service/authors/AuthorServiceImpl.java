package kz.halykacademy.bookstore.service.authors;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.authors.AuthorRepository;
import kz.halykacademy.bookstore.web.authors.Author;
import kz.halykacademy.bookstore.web.authors.SaveAuthor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;





/**
 *
 *
 *
 *              Допиши Везьде Exception Handling!!!
 *
 *              Внедрить Pageble!
 *
 *              Внедрить Exception Handling!
 *
 *              Поменять openApi спецификацию
 *
 *
 *
 * */



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
//        return authorRepository.findById(id)
//                .map(AuthorEntity::toDto)
//                .orElseThrow((Supplier<Throwable>) () -> {
//                    new ResourceNotFoundException();
//                });

        return authorRepository.getReferenceById(id).toDto();
    }

    @Transactional
    @Override
    public Author putAuthor(long id, SaveAuthor author) {
        authorRepository.updateAuthorById(
                id,
                author.getFirstName(),
                author.getLastName(),
                author.getPatronymic(),
                author.getBirthday()
        );

        AuthorEntity updated = authorRepository.getReferenceById(id);
        return updated.toDto();
    }

    @Override
    public Author postAuthor(SaveAuthor author) {
        AuthorEntity saved = authorRepository.save(
                new AuthorEntity(
                        author.getId(),
                        author.getFirstName(),
                        author.getLastName(),
                        author.getPatronymic(),
                        author.getBirthday(),
                        null
                )
        );
        return saved.toDto();
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}