package kz.halykacademy.bookstore.web.orders;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrder {
    private Long orderId;
    private Long userId;
    private String orderStatus;
    private LocalDateTime orderTime;
    private HashSet<Long> orderedBooks;
    private List<Long> bookAmount;
}
