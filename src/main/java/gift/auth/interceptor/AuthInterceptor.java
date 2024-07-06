package gift.auth.interceptor;

import gift.auth.Authorization;
import gift.auth.jwt.JwtProvider;
import gift.model.user.Role;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    public AuthInterceptor(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            return handleHandler(request, response,
                ((HandlerMethod) handler).getMethodAnnotation(Authorization.class));
        } else if (handler instanceof ResourceHttpRequestHandler) {
            return handleHandler(request, response,
                handler.getClass().getAnnotation(Authorization.class));
        }
        return true;
    }

    private boolean handleHandler(HttpServletRequest request, HttpServletResponse response,
        Authorization authorization) throws Exception {
        if (!checkAuthorization(request, response, authorization)) {
            return false;
        }
        return true;
    }

    private boolean checkAuthorization(HttpServletRequest request, HttpServletResponse response,
        Authorization authorization) throws Exception {
        if (authorization == null) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !jwtProvider.validateToken(authHeader)) {
            sendUnauthorizedError(response, "Unauthorized");
            return false;
        }

        Claims claims = jwtProvider.getClaims(authHeader);
        Role requiredRole = authorization.role();
        Role userRole = Role.valueOf(claims.get("roles", String.class));

        if (userRole == Role.ADMIN || requiredRole == userRole) {
            request.setAttribute("userId", String.valueOf(claims.getSubject()));
            request.setAttribute("name", claims.get("name", String.class));
            return true;
        }

        sendForbiddenError(response, "Forbidden");
        return false;
    }

    private void sendUnauthorizedError(HttpServletResponse response, String message)
        throws Exception {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    private void sendForbiddenError(HttpServletResponse response, String message) throws Exception {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, message);
    }
}
