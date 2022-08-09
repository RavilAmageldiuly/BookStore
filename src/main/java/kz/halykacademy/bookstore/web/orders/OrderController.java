package kz.halykacademy.bookstore.web.orders;


import kz.halykacademy.bookstore.service.orders.OrderServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Order> findAllForSpecificUser(@AuthenticationPrincipal UserDetails userDetails, Pageable pageRequest) {
        return new PageImpl(
                orderService.getAll(userDetails.getUsername()).stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping("/findAll")
    public Page<Order> findAll(Pageable pageRequest) {
        return new PageImpl(
                orderService.findAll().stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public Order findOne(@PathVariable Long id) {
        return orderService.getOne(id);
    }

    @PostMapping
    public Order postOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SaveOrder saveOrder) {
        return orderService.postOrder(userDetails.getUsername(), saveOrder);
    }

    @PutMapping("/putOwn/{id}")
    public Order putOwnOrder(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody SaveOrder saveOrder) {
        return orderService.putOrder(userDetails.getUsername(), id, saveOrder);
    }

    @PutMapping("/{id}")
    public Order putOrder(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody SaveOrder saveOrder) {
        return orderService.putOrder(userDetails.getUsername(), id, saveOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}