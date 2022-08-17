package kz.halykacademy.bookstore.dao.publishers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.publishers.Publisher;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", nullable = false)
    private Long publisher_id;

    @Column(name = "publisher_name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    private List<BookEntity> publishedBooks = new ArrayList<>();

    public Publisher toDto() {
        return new Publisher(
                this.publisher_id,
                this.name,
                bookEntityToDto()
        );
    }

    private List<Book> bookEntityToDto() {
        try {
            return publishedBooks.stream().map(BookEntity::toDto).collect(Collectors.toList());
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }
}
