package web.beecommerce.dto.request.create_request;

import lombok.Getter;

@Getter
public class CreateProductRequestDTO {

    private String code;

    private String name;

    private Double price;

    private Integer quantity;

    private String image;

    private String description;

    private String status;

    // mappings
    private Long categoryId;
}
