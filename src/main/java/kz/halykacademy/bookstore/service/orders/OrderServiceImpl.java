package kz.halykacademy.bookstore.service.orders;

import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.orders.OrderEntity;
import kz.halykacademy.bookstore.dao.orders.OrderRepository;
import kz.halykacademy.bookstore.dao.users.UserRepository;
import kz.halykacademy.bookstore.web.exceptionHandling.BlockedUserException;
import kz.halykacademy.bookstore.web.exceptionHandling.PriceExceedsLimitException;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import kz.halykacademy.bookstore.web.orders.Order;
import kz.halykacademy.bookstore.web.orders.SaveOrder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    // replace to their services
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll().stream().map(OrderEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public Order getOne(Long id) {
        return orderRepository.findById(id).map(OrderEntity::toDto).orElseThrow(() -> new ResourceNotFoundException("Order not found. Invalid id supplied!"));
    }

    @Override
    public Order postOrder(SaveOrder saveOrder) {

        if (!userRepository.existsById(saveOrder.getUserId()))
            throw new ResourceNotFoundException("User not found! Invalid id supplied");

        if (userRepository.getReferenceById(saveOrder.getUserId()).getBlockFlag())
            throw new BlockedUserException("User blocked. Order can't be placed");

        return orderRepository.save(
                new OrderEntity(
                        saveOrder.getOrderId(),
                        userRepository.getReferenceById(saveOrder.getUserId()),
                        saveOrder.getOrderStatus(),
                        LocalDateTime.now(),
                        checkBooks(saveOrder)
                )
        ).toDto();
    }

    @Override
    public Order putOrder(Long id, SaveOrder saveOrder) {

        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found! Invalid id supplied"));

        switch (userRepository.getReferenceById(saveOrder.getUserId()).getUserRole()) {
            case "USER":
                return orderRepository.save(
                        new OrderEntity(
                                id,
                                order.getUser(),
                                order.getOrderStatus(),
                                order.getOrderTime(),
                                checkBooks(saveOrder)
                        )
                ).toDto();
            case "ADMIN":
                return orderRepository.save(
                        new OrderEntity(
                                id,
                                order.getUser(),
                                saveOrder.getOrderStatus(),
                                order.getOrderTime(),
                                checkBooks(saveOrder)
                        )
                ).toDto();
            default :
                return null;
        }
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id))
            throw new ResourceNotFoundException("Order not found! Invalid id supplied");

        orderRepository.deleteById(id);
    }


    public List<BookEntity> checkBooks(SaveOrder saveOrder) {

        // добавить проверку по количеству книг

        List<BookEntity> orderedBooks = new ArrayList<>();

        for (Long id: saveOrder.getOrderedBooks()) {
            orderedBooks.add(bookRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Book not found! Book with id: " + id + " does not exist")
            ));
        }

        if (orderedBooks.stream().mapToDouble(BookEntity::getPrice).sum() > 10000)
            throw new PriceExceedsLimitException("Total price of order should be less than 10000т.");

        return orderedBooks;
    }
}
