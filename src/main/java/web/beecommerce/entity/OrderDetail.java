package web.beecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_order_detail")
public class OrderDetail extends BaseEntity {
    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;
    // mappings
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
