package kz.halykacademy.bookstore.dao.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findOrderEntitiesByUser_Username(String username);

    @Modifying
    @Query("update OrderEntity o set o.orderStatus = ?1 where o.orderId = ?2")
    OrderEntity changeOrderStatus(String newStatus, Long id);
}
