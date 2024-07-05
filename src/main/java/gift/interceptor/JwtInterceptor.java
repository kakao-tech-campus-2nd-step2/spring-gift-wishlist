package gift.interceptor;

import gift.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if ("GET".equalsIgnoreCase(request.getMethod()) && "/api/products".equals(request.getRequestURI())) {
            return true;
        }

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (JwtUtil.validateToken(token)) {
                return true;
            }
        }

        // 토큰이 없거나 유효하지 않은 경우
        response.addHeader("WWW-Authenticate", "Bearer");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        return false;
    }

}
