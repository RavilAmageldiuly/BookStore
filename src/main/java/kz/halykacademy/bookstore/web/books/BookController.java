package kz.halykacademy.bookstore.web.books;


import kz.halykacademy.bookstore.service.books.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.getAll();
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
}
