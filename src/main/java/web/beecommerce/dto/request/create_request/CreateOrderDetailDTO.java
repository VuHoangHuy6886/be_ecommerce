package web.beecommerce.dto.request.create_request;

import lombok.Getter;

@Getter
public class CreateOrderDetailDTO {

    private Double price;


    private Integer quantity;


    private Long productId;


    private Long orderId;
}
