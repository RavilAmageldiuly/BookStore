package kz.halykacademy.bookstore.web.orders;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrder {
    private HashSet<Long> orderedBooks;
    private List<Integer> bookAmount;
}
