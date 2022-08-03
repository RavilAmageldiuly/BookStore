package kz.halykacademy.bookstore.dao.genres;


import kz.halykacademy.bookstore.web.genres.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @Column(name = "genre_name")
    private String genreName;

    public Genre toDto() {
        return new Genre(this.genreId, this.genreName);
    }
}
