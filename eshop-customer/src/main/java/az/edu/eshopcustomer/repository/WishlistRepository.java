package az.edu.eshopcustomer.repository;

import az.edu.eshopcustomer.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findWishlistByCustomerIdAndActive(Long customerId, Integer active);
}
