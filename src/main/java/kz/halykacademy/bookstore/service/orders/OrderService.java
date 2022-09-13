package kz.halykacademy.bookstore.service.orders;

import kz.halykacademy.bookstore.web.orders.Order;
import kz.halykacademy.bookstore.web.orders.SaveOrder;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    List<Order> getAllForSpecificUser(String username);

    Order getOne(Long id);

    Order postOrder(String username, SaveOrder saveOrder);

    void deleteOrder(Long id);
}
