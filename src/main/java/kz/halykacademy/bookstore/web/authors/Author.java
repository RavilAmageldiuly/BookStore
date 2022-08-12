package kz.halykacademy.bookstore.web.authors;

import kz.halykacademy.bookstore.web.books.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private List<Book> booksList;
    private HashSet<String> genreList;
}
