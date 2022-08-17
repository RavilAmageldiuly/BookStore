package kz.halykacademy.bookstore.service.orders;

import kz.halykacademy.bookstore.dao.books.BookEntity;
import kz.halykacademy.bookstore.dao.books.BookRepository;
import kz.halykacademy.bookstore.dao.orderBook.OrderBook;
import kz.halykacademy.bookstore.dao.orders.OrderEntity;
import kz.halykacademy.bookstore.dao.orders.OrderRepository;
import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.service.users.UserServiceImpl;
import kz.halykacademy.bookstore.web.exceptionHandling.AttemptToUseAlienResource;
import kz.halykacademy.bookstore.web.exceptionHandling.NotEnoughBooksException;
import kz.halykacademy.bookstore.web.exceptionHandling.PriceExceedsLimitException;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
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




        //          не работает!!




        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found! Invalid id supplied"));
        UserEntity user = userService.findByUsername(username);

        UserEntity owner = order.getUser();

        returnBooks(order.getOrderedBooks());

        if (!owner.equals(user))
            throw new AttemptToUseAlienResource("Order does not belong to the current user!");

        order.setOrderedBooks(getOrderBooks(order, saveOrder));

        return orderRepository.save(order).toDto();
    }

    public Order changeOrderStatus(String newStatus, Long id) {
        if (orderRepository.existsById(id))
            throw new ResourceNotFoundException("Order not found! Invalid id supplied");

        return orderRepository.changeOrderStatus(newStatus, id).toDto();
    }

    @Override
    public void deleteOrder(Long id) {
        OrderEntity canceledOrder = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found! Invalid id supplied"));
        canceledOrder.setOrderStatus("canceled");
        canceledOrder.setOrderTime(LocalDateTime.now());
        returnBooks(canceledOrder.getOrderedBooks());
        canceledOrder.getOrderedBooks().clear();

        orderRepository.save(canceledOrder);
    }

    public List<OrderBook> getOrderBooks(OrderEntity orderEntity, SaveOrder saveOrder) {

        List<OrderBook> orderBookList = new ArrayList<>();

        List<BookEntity> orderedBooks = new ArrayList<>();

        List<Integer> bookAmount = saveOrder.getBookAmount();


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

            int checkBookAmount = book.getBookQuantity() - bookAmount.get(count);

            if (checkBookAmount < 0) {
                throw new NotEnoughBooksException("Not enough books!");
            }

            book.setBookQuantity(checkBookAmount);
            orderBookList.add(orderBook);
            count++;
        }

        return orderBookList;
    }

    public void returnBooks(List<OrderBook> orderedBooks) {
        for (OrderBook orderedBook: orderedBooks) {
            BookEntity book = orderedBook.getBook();
            book.setBookQuantity(
                    book.getBookQuantity() + orderedBook.getOrdered_book_amount()
            );
            orderedBook.setOrdered_book_amount(0);
        }
    }
}
