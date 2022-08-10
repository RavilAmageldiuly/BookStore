package kz.halykacademy.bookstore.web.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveBook {
    private long id;
    private double price;
    private Set<Long> authorList;
    private Long publisherId;
    private String title;
    private int numberOfPages;
    private int releaseYear;
    private Set<Long> genreList;
    private double bookQuantity;
}
