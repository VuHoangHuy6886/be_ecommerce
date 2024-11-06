package web.beecommerce.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import web.beecommerce.service.JwtService;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final Set<String> blacklistTokens = new HashSet<>();
    @Value("${spring.jwt.timeout}")
    private Long timeout;
    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    // Thêm token vào danh sách đen
    @Override
    public void addToBlacklist(String token) {
        blacklistTokens.add(token);
    }

    // Kiểm tra token có trong danh sách đen không
    @Override
    public boolean isTokenBlacklisted(String token) {
        return blacklistTokens.contains(token);
    }

    @Override
    public String generateToken(UserDetails user) {
        log.info("user : {}", user.getUsername());
        // TODO CODE HANDLER CREATE TOKEN
        return generateToken(new HashMap<>(), user);
    }

    // extractUser
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // kiem tra token hop le ko  ?
    @Override
    public boolean isValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenBlacklisted(token);
    }

    // custom
    // Object> claims : nhung thong tin ko muon hien thi trong token
    private String generateToken(Map<String, Object> claims, UserDetails user) {
        System.out.println("Username: " + user.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // custom
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // custom extract token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            System.out.println("Token không hợp lệ: " + e.getMessage());
            return null;
        }
    }
}
