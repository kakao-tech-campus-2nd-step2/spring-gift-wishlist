package gift.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    public JwtInterceptor(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(HEADER_AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            return true;
        }

        String token = authHeader.substring(TOKEN_PREFIX.length());
        try {
            Long id = tokenProvider.extractUserId(token);
            request.setAttribute("SUB", id);
            return true;
        } catch (JwtException e) {
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, e.getMessage());
            return false;
        } catch (Exception e) {
            sendErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return false;
        }
    }

    private void sendErrorResponse(HttpServletResponse response,
                                   HttpStatus status, String message) throws IOException {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter()
                .write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
