package az.edu.eshopcustomer.repository;

import az.edu.eshopcustomer.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    List<OrderStatus> findOrderStatusByOrderIdAndActive(Long orderId, Integer active);
}
