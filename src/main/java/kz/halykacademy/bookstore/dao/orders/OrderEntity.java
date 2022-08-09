package kz.halykacademy.bookstore.dao.orders;


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

    @ManyToMany
    @JoinTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<BookEntity> orderedBooks = new ArrayList<>();


    public Order toDto() {
        return new Order(
                this.orderId,
                this.user.getUserId(),
                this.orderStatus,
                this.orderTime,
                getPrice(),
                getOrderedBooks(),
                null
        );
    }

    private List<Book> getOrderedBooks() {
        return orderedBooks.stream().map(BookEntity::toDto).collect(Collectors.toList());
    }

    public double getPrice() {
        return orderedBooks.stream().mapToDouble(BookEntity::getPrice).sum();
    }

    public List<BookEntity> getOrderedBookEntities() {
        return orderedBooks;
    }
}
