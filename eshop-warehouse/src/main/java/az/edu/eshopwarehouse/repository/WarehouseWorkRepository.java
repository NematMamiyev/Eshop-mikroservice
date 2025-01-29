package az.edu.eshopwarehouse.repository;

import az.edu.eshopwarehouse.entity.WarehouseWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseWorkRepository extends JpaRepository<WarehouseWork, Long> {
    List<WarehouseWork> findALLByActive(Integer active);
    WarehouseWork findWarehouseWorkByIdAndActive(Long id, Integer active);
}
