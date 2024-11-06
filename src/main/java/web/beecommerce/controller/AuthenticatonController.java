package web.beecommerce.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.beecommerce.dto.request.SignInRequest;
import web.beecommerce.dto.response.TokenResponse;
import web.beecommerce.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticatonController {
    private final AuthenticationService authenticationService;

    // get token
    @PostMapping("/access")
    public ResponseEntity<TokenResponse> access(@RequestBody SignInRequest request) {
        log.info("Request-access-token: {} - {}", request.getEmail(), request.getPassword());
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return new ResponseEntity<>(authenticationService.logout(request), HttpStatus.OK);
    }
}
