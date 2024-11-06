package web.beecommerce.configuration;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import web.beecommerce.service.JwtService;
import web.beecommerce.service.UserService;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class PreFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("------------------Prefilter-------------------");

        // Lấy token từ header Authorization
        final String authorization = request.getHeader("Authorization");
        log.info("Authorization: {}", authorization);

        // Kiểm tra token có hợp lệ và bắt đầu với "Bearer "
        if (StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Chuyển tiếp yêu cầu nếu không có token hoặc token không hợp lệ
            return;
        }

        final String token = authorization.substring("Bearer ".length());

        // Kiểm tra nếu token đã nằm trong danh sách đen (bị vô hiệu hóa sau khi logout)
        if (jwtService.isTokenBlacklisted(token)) {
            log.warn("Token is blacklisted, denying access"); // Từ chối yêu cầu với token không hợp lệ
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has been blacklisted");
            return;
        }

        log.info("token: {}", token);

        // Trích xuất username từ token
        final String userName = jwtService.extractUsername(token);
        log.info("userName: {}", userName);

        // Xác thực người dùng nếu username không rỗng và chưa có thông tin xác thực trong SecurityContext
        if (StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.getUserDetailsService().loadUserByUsername(userName);

            // Kiểm tra tính hợp lệ của token với thông tin người dùng
            if (jwtService.isValid(token, userDetails)) {
                SecurityContext context = SecurityContextHolder.getContext();
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Thiết lập chi tiết bổ sung cho yêu cầu hiện tại
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }
        }

        // Chuyển tiếp yêu cầu sau khi xử lý token và thông tin xác thực
        filterChain.doFilter(request, response);
    }
}
