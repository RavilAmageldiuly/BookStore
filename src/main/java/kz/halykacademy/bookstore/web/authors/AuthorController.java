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
        return authorService.getIndividual(id);
    }

    @PostMapping
    public Author postAuthor(@RequestBody SaveAuthor saveAuthor) {
        return authorService.postIndividual(saveAuthor);
    }

    @PutMapping("/{id}")
    public Author putAuthor(@PathVariable Long id, @RequestBody SaveAuthor author) {
        return authorService.putIndividual(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteIndividual(id);
    }

    @GetMapping("/findByGenres")
    public Page<Author> getAuthorsByGenre(Pageable pageRequest, @RequestParam(name = "values") List<String> genres) {
        return new PageImpl<>(
                bookService.getAuthorsByGenre(genres).stream().map(AuthorEntity::toDto).collect(Collectors.toList())
                        .stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }
}
