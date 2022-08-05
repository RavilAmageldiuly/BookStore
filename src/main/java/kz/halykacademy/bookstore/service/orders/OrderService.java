package kz.halykacademy.bookstore.service.orders;

import kz.halykacademy.bookstore.web.orders.Order;
import kz.halykacademy.bookstore.web.orders.SaveOrder;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    Order getOne(Long id);

    Order postOrder(SaveOrder saveOrder);

    Order putOrder(Long id, SaveOrder saveOrder);

    void deleteOrder(Long id);
}
