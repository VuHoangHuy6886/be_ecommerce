package web.beecommerce.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.beecommerce.dto.request.SignInRequest;
import web.beecommerce.dto.response.TokenResponse;
import web.beecommerce.repository.UserRepository;
import web.beecommerce.service.AuthenticationService;
import web.beecommerce.service.JwtService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public TokenResponse authenticate(SignInRequest signInRequest) {
        log.info("----------Authentication service started---------------");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email or password not found"));
        log.info("user gen : {}-{}", user.getId(), user.getEmail());
        // call to jwt service to gen token
        String accessToken = jwtService.generateToken(user);
        log.info("----------Authentication service finished---------------");
        log.info("My-token: {}", accessToken);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .userId(user.getId())
                .build();
    }

    @Override
    public String logout(HttpServletRequest request) {
        log.info("----------Logout service started---------------");
        final String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            final String token = authorization.substring("Bearer ".length());
            jwtService.addToBlacklist(token); // Thêm token vào danh sách đen
            log.info("Token has been blacklisted");
        }
        return "Logged out successfully";
    }
}
