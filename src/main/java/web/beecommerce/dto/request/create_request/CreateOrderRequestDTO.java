package web.beecommerce.dto.request.create_request;

import lombok.Getter;
import web.beecommerce.util.BuyAt;
import web.beecommerce.util.OderStatus;
import web.beecommerce.util.Payment;

@Getter
public class CreateOrderRequestDTO {

    private String code;

    private Integer totalQuantity;

    private Double totalPrice;

    private String buyAt;

    private String consigneeName;

    private String consigneePhoneNumber;

    private String paymentMethod;


    private String address;

    private String orderStatus;

    private Long customerId;
}
