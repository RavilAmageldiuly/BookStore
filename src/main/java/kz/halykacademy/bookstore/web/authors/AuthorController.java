package kz.halykacademy.bookstore.web.authors;

import kz.halykacademy.bookstore.service.authors.AuthorServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public Author findOne(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping
    public Author postAuthor(@RequestBody SaveAuthor author) {
        return authorService.postAuthor(author);
    }

    @PutMapping("/{id}")
    public Author putAuthor(@PathVariable Long id, @RequestBody SaveAuthor author) {
        return authorService.putAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
