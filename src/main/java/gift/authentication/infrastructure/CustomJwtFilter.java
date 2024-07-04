package gift.authentication.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.authentication.TokenProvider;
import gift.core.exception.APIException;
import gift.core.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    @Value("${app.headers.auth-token}")
    private String authTokenHeader;

    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomJwtFilter(TokenProvider tokenProvider, ObjectMapper objectMapper) {
        this.tokenProvider = tokenProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (shouldSkipRequest(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(authTokenHeader);
        if (token == null) {
            writeErrorResponse(response, ErrorCode.AUTHENTICATION_REQUIRED);
            return;
        }
        if (!token.startsWith("Bearer ")) {
            writeErrorResponse(response, ErrorCode.AUTHENTICATION_FAILED);
            return;
        }
        token = token.substring(7);
        try {
            Long userId = tokenProvider.extractUserId(token);
            // TODO : userId 를 바탕으로 하는 사용자 구분은 step3에 포함되어 있어서 아직 구현하지 않았습니다.
        } catch (APIException exception) {
            writeErrorResponse(response, ErrorCode.AUTHENTICATION_FAILED);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean shouldSkipRequest(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/auth");
    }

    private void writeErrorResponse(HttpServletResponse response, ErrorCode errorCode) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getStatus().value());
        try {
            response.getWriter().write(objectMapper.writeValueAsString(errorCode.getMessage()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
