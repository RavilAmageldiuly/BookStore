package kz.halykacademy.bookstore.web.orders;


import kz.halykacademy.bookstore.service.orders.OrderServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Order> findAll(Pageable pageRequest) {
        return new PageImpl(
                orderService.getAll().stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public Order findOne(@PathVariable Long id) {
        return orderService.getOne(id);
    }

    @PostMapping
    public Order postOrder(@RequestBody SaveOrder saveOrder) {
        return orderService.postOrder(saveOrder);
    }

    @PutMapping("/{id}")
    public Order putOrder(@PathVariable Long id, @RequestBody SaveOrder saveOrder) {
        return orderService.putOrder(id, saveOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
