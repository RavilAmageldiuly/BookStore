package kz.halykacademy.bookstore.service.orders;

import kz.halykacademy.bookstore.web.orders.Order;
import kz.halykacademy.bookstore.web.orders.SaveOrder;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    List<Order> getAll(String username);

    Order getOne(Long id);

    Order postOrder(String username, SaveOrder saveOrder);

    Order putOrder(String username, Long orderId, SaveOrder saveOrder);

    void deleteOrder(Long id);
}
