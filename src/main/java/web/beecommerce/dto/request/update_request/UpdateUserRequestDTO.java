package web.beecommerce.dto.request.update_request;

import lombok.Getter;

@Getter
public class UpdateUserRequestDTO {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private String role;

    private String status;
}
