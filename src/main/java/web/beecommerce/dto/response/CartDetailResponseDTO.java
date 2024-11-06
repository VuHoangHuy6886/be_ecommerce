package web.beecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailResponseDTO {
    private Long id;

    private int quantity;

    private double price;

    private double totalPrice;

    private String status;


    // mappings
    private String productName;
    private Long productId;
    private Long cartId;
}
