package kz.halykacademy.bookstore;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private int id;
    private String name;
    private List<Book> publishedBooks;

    public Publisher(String name) {
        this.name = name;
        this.publishedBooks = new ArrayList<>();
    }

    public Publisher(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
