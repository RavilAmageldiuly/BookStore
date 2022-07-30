package kz.halykacademy.bookstore.dao.books;


import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dao.authors.AuthorEntity;
import kz.halykacademy.bookstore.dao.publishers.PublisherEntity;
import kz.halykacademy.bookstore.web.books.Book;
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
    private long book_id;

    @Column
    private double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "booksList")
    private List<AuthorEntity> authorList = new ArrayList<>();

    @Column
    private int publisher_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private PublisherEntity publisher;

    @Column
    private String title;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @Column(name = "release_year")
    private int releaseYear;

    public Book toDto() {
        return new Book(
                this.book_id,
                this.price,
                getAllAuthors(),
                this.publisher.getName(),
                this.title,
                this.numberOfPages,
                this.releaseYear
        );
    }

    private List<String> getAllAuthors() {
        return authorList.stream()
                .map(AuthorEntity::getAuthorFullName)
                .collect(Collectors.toList());
    }
}
