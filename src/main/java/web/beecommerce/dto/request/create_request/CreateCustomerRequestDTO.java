package web.beecommerce.dto.request.create_request;

import lombok.Getter;

@Getter
public class CreateCustomerRequestDTO {
    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private String status;
}
