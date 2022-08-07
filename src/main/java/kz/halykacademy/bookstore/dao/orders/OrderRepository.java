package kz.halykacademy.bookstore.dao.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findOrderEntitiesByUser_Username(String username);
}
