package web.beecommerce.repository;

import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.beecommerce.entity.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(value = "SELECT  o from OrderDetail  o where  o.order.id =:orderId")
    List<OrderDetail> findAllByOrderId(@Param("orderId")Long orderId);
}
