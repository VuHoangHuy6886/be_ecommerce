package web.beecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;

    // mappings
    @OneToMany(mappedBy = "product")
    private Set<CartDetail> cartDetails;
    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
