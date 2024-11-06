package web.beecommerce.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.SignInRequest;
import web.beecommerce.dto.response.TokenResponse;

@Service
public interface AuthenticationService {
    TokenResponse authenticate(SignInRequest signInRequest);

    String logout(HttpServletRequest request);
}
