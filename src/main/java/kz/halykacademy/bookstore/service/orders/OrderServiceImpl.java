package kz.halykacademy.bookstore.service.orders;

import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.orderBook.OrderBook;
import kz.halykacademy.bookstore.dao.orders.OrderEntity;
import kz.halykacademy.bookstore.dao.orders.OrderRepository;
import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.service.users.UserServiceImpl;
import kz.halykacademy.bookstore.web.exceptionHandling.*;
import kz.halykacademy.bookstore.web.orders.Order;
import kz.halykacademy.bookstore.web.orders.SaveOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserServiceImpl userService;
    private final BookRepository bookRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll().stream().map(OrderEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllForSpecificUser(String username) {
        return orderRepository.findOrderEntitiesByUser_Username(username).stream().map(OrderEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public Order getOne(Long id) {
        return orderRepository.findById(id).map(OrderEntity::toDto).orElseThrow(() -> new ResourceNotFoundException("Order not found. Invalid id supplied!"));
    }

    @Override
    public Order postOrder(String username, SaveOrder saveOrder) {

        UserEntity user = userService.findByUsername(username);

        OrderEntity order = orderRepository.save(
                new OrderEntity(
                        null,
                        user,
                        "created",
                        LocalDateTime.now(),
                        new ArrayList<>()
                ));

        order.setOrderedBooks(getOrderBooks(order, saveOrder));

        return orderRepository.save(order).toDto();

    }

    @Override
    public Order putOrder(String username, Long orderId, SaveOrder saveOrder) {

        /**
         *
         *      CHANGED BOOKS MUST RETURN TO THEIR PLACE (QUANTITY)
         *
         * */

        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found! Invalid id supplied"));
        UserEntity user = userService.findByUsername(username);
        UserEntity owner = order.getUser();
        String userRole = user.getUserRole();

        if (!owner.equals(user) && !userRole.equals("ADMIN"))
            throw new AttemptToUseAlienResource("Order does not belong to the current user!");

        List<OrderBook> booksList = getOrderBooks(order, saveOrder);

        switch (userRole) {
            case "USER":
                order.setOrderedBooks(booksList);

            case "ADMIN":
                if (!owner.equals(user))
                    booksList = order.getOrderedBooks();

                order.setOrderedBooks(booksList);
                order.setOrderStatus(saveOrder.getOrderStatus());
        }

        return orderRepository.save(order).toDto();
    }

    @Override
    public void deleteOrder(Long id) {

        if (!orderRepository.existsById(id))
            throw new ResourceNotFoundException("Order not found! Invalid id supplied");

        /**
         *
         *      change the order status to cancel, return ordered books to their places
         *
         * */
    }

    public List<OrderBook> getOrderBooks(OrderEntity orderEntity, SaveOrder saveOrder) {

        List<OrderBook> orderBookList = new ArrayList<>();

        List<BookEntity> orderedBooks = new ArrayList<>();

        List<Long> bookAmount = saveOrder.getBookAmount();


        for (Long id : saveOrder.getOrderedBooks()) {
            orderedBooks.add(bookRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Book not found! Book with id: " + id + " does not exist")
            ));
        }

        int total = 0;

        for (int i = 0; i < saveOrder.getOrderedBooks().size(); i++) {
            total += orderedBooks.get(i).getPrice() * bookAmount.get(i);
            if (total > 10000) {
                throw new PriceExceedsLimitException("Total price of order should be less than 10000₸.");
            }
        }

        int count = 0;

        for (BookEntity book : orderedBooks) {
            OrderBook orderBook = new OrderBook();
            orderBook.setOrder(orderEntity);
            orderBook.setBook(book);
            orderBook.setOrdered_book_amount(bookAmount.get(count));

            double checkBookAmount = book.getBookQuantity() - bookAmount.get(count);

            if (checkBookAmount < 0) {
                throw new NotEnoughBookException("Not enough books!");
            }

            book.setBookQuantity(checkBookAmount);
            orderBookList.add(orderBook);
            count++;
        }

        return orderBookList;
    }
}
