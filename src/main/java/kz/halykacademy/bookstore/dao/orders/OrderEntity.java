package kz.halykacademy.bookstore.dao.orders;


import kz.halykacademy.bookstore.dao.orderBook.OrderBook;
import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.web.books.Book;
import kz.halykacademy.bookstore.web.orders.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @OneToMany(mappedBy =  "order", cascade = CascadeType.MERGE)
    private List<OrderBook> orderedBooks = new ArrayList<>();


    public Order toDto() {
        return new Order(
                this.orderId,
                this.user.getUserId(),
                this.orderStatus,
                this.orderTime,
                getPrice(),
                getOrderedBookDTOs(),
                getOrderedBookAmount()
        );
    }

    public List<Integer> getOrderedBookAmount() {
        return orderedBooks.stream().map(OrderBook::getOrdered_book_amount).collect(Collectors.toList());
    }

    public List<OrderBook> getOrderedBooks() {
        return orderedBooks;
    }

    public List<Book> getOrderedBookDTOs() {
        return orderedBooks.stream().map(OrderBook::getBook).map(BookEntity::toDto).collect(Collectors.toList());
    }

    public double getPrice() {

        int total = 0;

        for (int i = 0; i < getOrderedBookAmount().size(); i++) {
            total += getOrderedBookAmount().get(i) * orderedBooks.get(i).getBook().getPrice();
        }

        return total;
    }

}
