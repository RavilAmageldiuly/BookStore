package kz.halykacademy.bookstore.dao.orderBook;


import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.orders.OrderEntity;

import javax.persistence.*;

@Entity
@Table(name = "order_book")
public class OrderBook {

    @EmbeddedId
    private OrderBookId id = new OrderBookId();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Column(name = "ordered_book_amount")
    private Integer ordered_book_amount;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public Integer getOrdered_book_amount() {
        return ordered_book_amount;
    }

    public void setOrdered_book_amount(Integer ordered_book_amount) {
        this.ordered_book_amount = ordered_book_amount;
    }
}
