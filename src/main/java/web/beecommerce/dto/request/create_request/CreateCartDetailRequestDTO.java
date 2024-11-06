package web.beecommerce.dto.request.create_request;

import lombok.Getter;

@Getter
public class CreateCartDetailRequestDTO {

    private int quantity;

    private double price;

    private String status;


    // mappings

    private Long productId;

    private Long cartId;
}
