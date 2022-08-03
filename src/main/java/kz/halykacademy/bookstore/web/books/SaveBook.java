package kz.halykacademy.bookstore.web.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveBook {

    private long id;
    private double price;
    private List<Long> authorList;
    private Long publisherId;
    private String title;
    private int numberOfPages;
    private int releaseYear;
    private List<Long> genreList;
}
