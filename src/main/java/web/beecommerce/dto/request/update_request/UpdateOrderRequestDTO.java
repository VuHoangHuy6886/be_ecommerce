package web.beecommerce.dto.request.update_request;

import lombok.Getter;

@Getter
public class UpdateOrderRequestDTO {

    private String consigneeName;

    private String consigneePhoneNumber;

    private String paymentMethod;

    private String address;

    private String orderStatus;
}
