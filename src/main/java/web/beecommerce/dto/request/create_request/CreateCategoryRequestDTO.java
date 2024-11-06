package web.beecommerce.dto.request.create_request;

import lombok.Getter;

@Getter
public class CreateCategoryRequestDTO {
    private String name;
    private String description;
    private String status;
}
