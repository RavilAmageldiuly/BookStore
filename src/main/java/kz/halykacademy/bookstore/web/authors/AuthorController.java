package kz.halykacademy.bookstore.web.authors;

import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.service.authors.AuthorServiceImpl;
import kz.halykacademy.bookstore.service.books.BookServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;
    private final BookServiceImpl bookService;


    public AuthorController(AuthorServiceImpl authorService, BookServiceImpl bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public Page<Author> findAll(Pageable pageRequest) {
        return new PageImpl(
                authorService.getAll().stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public Author findOne(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping
    public Author postAuthor(@RequestBody SaveAuthor saveAuthor) {
        return authorService.postAuthor(saveAuthor);
    }

    @PutMapping("/{id}")
    public Author putAuthor(@PathVariable Long id, @RequestBody SaveAuthor author) {
        return authorService.putAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("/findByFio")
    public List<Author> getAuthorsByFio(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "patronymic") String patronymic) {
        return authorService.getAuthorsByFIO(firstName, lastName, patronymic);
    }

    @GetMapping("/findByGenre")
    public List<Author> getAuthorsByGenre(@RequestParam(name = "values") List<String> genres) {
        return bookService.getAuthorsByGenre(genres).stream().map(AuthorEntity::toDto).collect(Collectors.toList());
    }
}
