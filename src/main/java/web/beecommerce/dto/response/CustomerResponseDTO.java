package web.beecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private String status;
}
