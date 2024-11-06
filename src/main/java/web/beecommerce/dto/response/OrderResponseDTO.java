package web.beecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long id;

    private String code;

    private Integer totalQuantity;

    private Double totalPrice;

    private String buyAt;

    private String consigneeName;

    private String consigneePhoneNumber;

    private String paymentMethod;

    private String createDate;

    private String updateDate;

    private String address;

    private String orderStatus;

    private Long customerId;
}
