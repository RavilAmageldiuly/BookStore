package kz.halykacademy.bookstore.web.books;

import java.util.List;


public class SaveBook {

    private long id;
    private double price;
    private List<Long> authorList;
    private Long publisherId;
    private String title;
    private int numberOfPages;
    private int releaseYear;

    public SaveBook() {}

    public SaveBook(int price, List<Long> authorList, Long publisherId, String title, int numberOfPages, int releaseYear) {
        this.price = price;
        this.authorList = authorList;
        this.publisherId = publisherId;
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

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
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
                ", publisher='" + publisherId + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
