package kz.halykacademy.bookstore.web.authors;

import kz.halykacademy.bookstore.web.books.Book;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


public class Author {

    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private List<Book> booksList;

    public Author() {}

    public Author(long id, String firstName, String lastName, String patronymic, LocalDate birthday, List<Book> booksList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.booksList = booksList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday.toString() +
                ", booksList=" + booksList.toString() +
                '}';
    }
}
