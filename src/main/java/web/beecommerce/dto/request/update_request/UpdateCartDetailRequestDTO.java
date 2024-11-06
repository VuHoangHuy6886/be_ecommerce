package web.beecommerce.dto.request.update_request;

import lombok.Getter;

@Getter
public class UpdateCartDetailRequestDTO {

    private int quantity;

    private double price;

    private String status;

}
