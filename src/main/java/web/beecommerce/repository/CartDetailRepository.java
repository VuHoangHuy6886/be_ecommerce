package web.beecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.beecommerce.entity.CartDetail;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Query("SELECT c FROM CartDetail c WHERE c.cart.id = :cartId")
    List<CartDetail> findCartDetailByCartId(@Param("cartId") Long cartId);

    @Query("SELECT c FROM CartDetail c WHERE c.product.id =:productId and c.cart.id =:cartId")
    CartDetail productExist(@Param("productId") Long productId, @Param("cartId") Long cartId);
}
