package web.beecommerce.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    void addToBlacklist(String token);

    boolean isTokenBlacklisted(String token);

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isValid(String token, UserDetails user);
}
