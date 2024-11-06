package web.beecommerce.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInRequest {
    // @NotBlank(message = "email must be not blank")
    private String email;
    //@NotBlank(message = "password must be not blank")
    private String password;
    // more  platform :  know user use thiet bi gi : web|mobile|
}
