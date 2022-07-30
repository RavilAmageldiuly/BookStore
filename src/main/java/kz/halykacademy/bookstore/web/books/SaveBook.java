package kz.halykacademy.bookstore.web.books;

import org.springframework.stereotype.Component;

import java.util.List;


public class SaveBook {

    private long id;
    private double price;
    private List<Long> authorList;
    private String publisher;
    private String title;
    private int numberOfPages;
    private int releaseYear;

    public SaveBook() {}

    public SaveBook(int price, List<Long> authorList, String publisher, String title, int numberOfPages, int releaseYear) {
        this.price = price;
        this.authorList = authorList;
        this.publisher = publisher;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.releaseYear = releaseYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Long> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Long> authorList) {
        this.authorList = authorList;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
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
                ", publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
