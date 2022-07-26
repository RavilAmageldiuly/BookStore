package kz.halykacademy.bookstore.web.genres;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private Long genreId;
    private String genreName;
}
