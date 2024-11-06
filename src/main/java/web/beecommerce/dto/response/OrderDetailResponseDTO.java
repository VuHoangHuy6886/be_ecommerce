package web.beecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponseDTO {
    private Long id;

    private Double price;


    private Integer quantity;


    private String productName;


    private Long productId;


    private Long orderId;
}
