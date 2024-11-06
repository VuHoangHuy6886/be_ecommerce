package web.beecommerce.dto.request.update_request;

import lombok.Getter;

@Getter
public class UpdateOrderDetailDTO {
    private Long id;

    private double price;

    private int quantity;


    private Long productId;


    private Long orderId;
}
