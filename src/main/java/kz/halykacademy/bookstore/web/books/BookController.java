package kz.halykacademy.bookstore.web.books;


import kz.halykacademy.bookstore.dao.books.BookEntity;
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
    public Book postBook(@RequestBody SaveBook book) {
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

    @GetMapping("/findByTitle")
    public Page<Book> getBooksByTitle(Pageable pageRequest, @RequestParam(value = "title") String title) {
        return new PageImpl<>(
                bookService.getBooksByTitle(title).stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping("/findByGenres")
    public Page<Book> getBooksByGenre(Pageable pageRequest, @RequestParam(value = "values") List<String> genres) {
        return new PageImpl<>(
                bookService.getBooksByGenre(genres).stream().map(BookEntity::toDto).collect(Collectors.toList())
                        .stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }
}
