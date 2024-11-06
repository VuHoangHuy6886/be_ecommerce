package web.beecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private String role;

    private String status;
}
