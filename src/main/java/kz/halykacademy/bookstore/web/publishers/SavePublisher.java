package kz.halykacademy.bookstore.web.publishers;

import kz.halykacademy.bookstore.web.books.Book;
import org.springframework.stereotype.Component;

import java.util.List;


public class SavePublisher {

    private long id;
    private String name;
    private List<Book> publishedBooks;

    public SavePublisher() {}

    public SavePublisher(String name, List<Book> publishedBooks) {
        this.name = name;
        this.publishedBooks = publishedBooks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<Book> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishedBooks=" + publishedBooks.toString() +
                '}';
    }
}
