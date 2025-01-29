package az.edu.eshopproduct.repository;

import az.edu.eshopproduct.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    boolean existsSubcategoryByNameAndActive(String name, Integer active);

    boolean existsSubcategoryByNameAndActiveAndIdNot(String name, Integer active, Long id);

    boolean existsSubcategoryByIdAndActive(Long id, Integer active);

    List<Subcategory> findAllByActive(Integer active);

    Subcategory findSubcategoryByIdAndActive(Long id, Integer active);
}
