package az.edu.eshopcustomer.repository;

import az.edu.eshopcustomer.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
