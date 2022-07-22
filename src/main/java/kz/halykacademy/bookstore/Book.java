package kz.halykacademy.bookstore;

import java.util.List;

public class Book {

    private int id;
    private double price;
    private List<Author> authorList;
    private Publisher publisher;
    private String title;
    private int numberOfPages;
    private int releaseYear;

    public Book(int price, List<Author> authorList, Publisher publisher, String title, int numberOfPages, int releaseYear) {
        this.price = price;
        this.authorList = authorList;
        this.publisher = publisher;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.releaseYear = releaseYear;
    }

    public Book(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", price=" + price +
                ", authorList=" + authorList.toString() +
                ", publisher='" + publisher.toString() + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
