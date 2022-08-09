package kz.halykacademy.bookstore.service.orders;

import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.orders.OrderEntity;
import kz.halykacademy.bookstore.dao.orders.OrderRepository;
import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.service.users.UserServiceImpl;
import kz.halykacademy.bookstore.web.exceptionHandling.AttemptToUseAlienResource;
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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserServiceImpl userService;
    private final BookRepository bookRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserServiceImpl userService, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream().map(OrderEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Order> getAll(String username) {
        return orderRepository.findOrderEntitiesByUser_Username(username).stream().map(OrderEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public Order getOne(Long id) {
        return orderRepository.findById(id).map(OrderEntity::toDto).orElseThrow(() -> new ResourceNotFoundException("Order not found. Invalid id supplied!"));
    }

    @Override
    public Order postOrder(String username, SaveOrder saveOrder) {

        UserEntity user = userService.findByUsername(username);

        if (user.getBlockFlag())
            throw new BlockedUserException("User blocked. Order can't be placed");

        return orderRepository.save(
                new OrderEntity(
                        saveOrder.getOrderId(),
                        user,
                        "created",
                        LocalDateTime.now(),
                        checkBooks(saveOrder)
                )
        ).toDto();

    }

    @Override
    public Order putOrder(String username, Long orderId, SaveOrder saveOrder) {

        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found! Invalid id supplied"));
        UserEntity user = userService.findByUsername(username);
        UserEntity owner = order.getUser();
        String userRole = user.getUserRole();

        if (!owner.equals(user) && !userRole.equals("ADMIN"))
            throw new AttemptToUseAlienResource("Order does not belong to the current user!");

        List<BookEntity> booksList = checkBooks(saveOrder);

        switch (userRole) {
            case "USER":
                return orderRepository.save(
                        new OrderEntity(
                                order.getOrderId(),
                                owner,
                                order.getOrderStatus(),
                                order.getOrderTime(),
                                booksList
                        )
                ).toDto();

            case "ADMIN":

                if (!owner.equals(user))
                    booksList = order.getOrderedBookEntities();

                return orderRepository.save(
                        new OrderEntity(
                                orderId,
                                order.getUser(),
                                saveOrder.getOrderStatus(),
                                order.getOrderTime(),
                                booksList
                        )
                ).toDto();

            default:
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

//         добавить проверку по количеству книг

        List<BookEntity> orderedBooks = new ArrayList<>();

        for (Long id : saveOrder.getOrderedBooks()) {
            orderedBooks.add(bookRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Book not found! Book with id: " + id + " does not exist")
            ));
        }

        if (orderedBooks.stream().mapToDouble(BookEntity::getPrice).sum() > 10000)
            throw new PriceExceedsLimitException("Total price of order should be less than 10000т.");

        return orderedBooks;
    }
}
