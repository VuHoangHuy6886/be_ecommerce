package web.beecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tbl_order")
public class Order extends BaseEntity {
    @Column(name = "code")
    private String code;
    @Column(name = "total_quantity")
    private Integer totalQuantity;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "buy_at")
    private String buyAt;
    @Column(name = "consignee_name")
    private String consigneeName;
    @Column(name = "consignee_phone_number")
    private String consigneePhoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "status")
    private String orderStatus;

    // mappings
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "order")
    private Set<HistoryStatusOrder> historyStatusOrders;
}
