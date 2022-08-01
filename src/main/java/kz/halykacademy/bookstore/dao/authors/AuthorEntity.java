package kz.halykacademy.bookstore.dao.authors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.web.authors.Author;
import kz.halykacademy.bookstore.web.books.Book;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false)
    private long author_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column
    private LocalDate birthday;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorList")
    private List<BookEntity> booksList = new ArrayList<>();

    public Author toDto() {
        return new Author(
                this.author_id,
                this.firstName,
                this.lastName,
                this.patronymic,
                this.birthday,
                bookEntityToDto()
        );
    }

    private List<Book> bookEntityToDto(){
        return booksList.stream()
                .map(BookEntity::toDto)
                .collect(Collectors.toList());
    }

    public String getAuthorFullName() {
        return firstName + " " + lastName;
    }
}
