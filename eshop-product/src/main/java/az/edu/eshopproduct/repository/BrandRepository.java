package az.edu.eshopproduct.repository;

import az.edu.eshopproduct.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
  List<Brand> findAllByActive(Integer active);
  Brand findByIdAndActive(Long id,Integer active);
  boolean existsBrandByIdAndActive(Long id,Integer active);
  boolean existsBrandByNameAndActive(String name, Integer active);
  boolean existsBrandByNameAndActiveAndIdNot(String name, Integer active, Long id);
}
