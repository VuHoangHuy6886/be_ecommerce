package web.beecommerce.dto.request.update_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequestDTO {
    private Long id;
    private String name;
    private String description;
    private String status;
}
