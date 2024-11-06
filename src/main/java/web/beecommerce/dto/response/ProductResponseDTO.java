package web.beecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;

    private String code;

    private String name;

    private Double price;

    private Integer quantity;

    private String image;

    private String description;

    private String status;

    private Long categoryId;
}
