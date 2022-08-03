package kz.halykacademy.bookstore.web.books;


import kz.halykacademy.bookstore.service.books.BookServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<Book> findAll(Pageable pageRequest) {
        return new PageImpl(
                bookService.getAll().stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public Book postBoot(@RequestBody SaveBook book) {
        return bookService.postBook(book);
    }

    @PutMapping("/{id}")
    public Book putBook(@PathVariable Long id, @RequestBody SaveBook book) {
        return bookService.putBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/getByTitle")
    public List<Book> getBooksByTitle(@RequestParam(value = "title") String title) {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/findByGenre")
    public List<Book> getBooksByGenre(@RequestParam(value = "values") List<String> genres) {
        return bookService.getBooksByGenre(genres);
    }
}
