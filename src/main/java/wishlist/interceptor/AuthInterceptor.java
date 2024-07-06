package wishlist.interceptor;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import wishlist.exception.ErrorCode;
import wishlist.service.JwtProvider;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    public AuthInterceptor(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new JwtException(ErrorCode.ACCESS_DENIED.getMessage());
        }
        String token = authHeader.substring(7);
        if (!jwtProvider.validateToken(token)) {
            return false;
        }
        String email = jwtProvider.getEmailFromToken(token);
        request.setAttribute("email", email);
        return true;
    }

}
