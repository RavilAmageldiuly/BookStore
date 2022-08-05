package kz.halykacademy.bookstore.web.books;

import kz.halykacademy.bookstore.web.genres.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long id;
    private double price;
    private List<String> authorList;
    private String publisher;
    private String title;
    private int numberOfPages;
    private int releaseYear;
    private List<String> genreList;
    private double bookQuantity;
}
