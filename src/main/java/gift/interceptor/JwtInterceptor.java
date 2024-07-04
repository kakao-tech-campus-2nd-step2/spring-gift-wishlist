package gift.interceptor;

import gift.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        System.out.println("token = " + token);
        if ("GET".equalsIgnoreCase(request.getMethod()) && "/api/products".equals(request.getRequestURI())) {
            return true;
        }
        System.out.println("token = " + token);
        if(token == null || !JwtUtil.validateToken(token)){
            response.addHeader("WWW-Authenticate", "Bearear");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        }

        return true;
    }
}
