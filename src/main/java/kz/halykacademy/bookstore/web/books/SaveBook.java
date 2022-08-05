package kz.halykacademy.bookstore.web.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveBook {

    private long id;
    private double price;
    private HashSet<Long> authorList;
    private Long publisherId;
    private String title;
    private int numberOfPages;
    private int releaseYear;
    private HashSet<Long> genreList;
    private double bookQuantity;
}
