package kz.halykacademy.bookstore.dao.books;


import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.genres.GenreEntity;
import kz.halykacademy.bookstore.dao.publishers.PublisherEntity;
import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.genres.Genre;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private long bookId;

    @Column
    private double price;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> authorList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    private PublisherEntity publisher;

    @Column
    private String title;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToMany
    @JoinTable(
            name = "genre_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntity> genreList = new ArrayList<>();

    @Column(name = "book_quantity")
    private double bookQuantity;

    public Book toDto() {
        return new Book(
                this.bookId,
                this.price,
                getAllAuthors(),
                this.publisher.getName(),
                this.title,
                this.numberOfPages,
                this.releaseYear,
                getGenre(),
                this.bookQuantity
        );
    }

    private List<String> getAllAuthors() {
        return authorList.stream()
                .map(AuthorEntity::getAuthorFullName)
                .collect(Collectors.toList());
    }

    public List<String> getGenre() {
        return genreList.stream()
                .map(GenreEntity::getGenreName)
                .collect(Collectors.toList());
    }

}
