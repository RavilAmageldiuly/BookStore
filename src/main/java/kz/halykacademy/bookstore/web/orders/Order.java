package kz.halykacademy.bookstore.web.orders;

import kz.halykacademy.bookstore.web.books.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long orderId;
    private Long userId;
    private String orderStatus;
    private LocalDateTime orderTime;
    private double totalPrice;
    private List<Book> orderedBooks;
    private List<Long> bookAmount;
}
