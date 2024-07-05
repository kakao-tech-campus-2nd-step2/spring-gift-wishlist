package gift.filter;

import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import gift.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    private static final Map<ErrorCode, HttpStatus> errorCodeToStatusMap = new HashMap<>() {{
        put(ErrorCode.INVALID_NAME_SIZE, HttpStatus.BAD_REQUEST);
        put(ErrorCode.INVALID_NAME_PATTERN, HttpStatus.BAD_REQUEST);
        put(ErrorCode.KAKAO_NAME_NOT_ALLOWED, HttpStatus.BAD_REQUEST);
        put(ErrorCode.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
        put(ErrorCode.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        put(ErrorCode.INVALID_CREDENTIALS, HttpStatus.FORBIDDEN);
        put(ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        put(ErrorCode.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        put(ErrorCode.UNAUTHORIZED_REQUEST, HttpStatus.UNAUTHORIZED);
        put(ErrorCode.INVALID_PRICE, HttpStatus.BAD_REQUEST);
        put(ErrorCode.INVALID_IMAGE_URL, HttpStatus.BAD_REQUEST);
    }};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        String token = authorizationHeader.substring(7);

        try {
            tokenService.validateToken(token);
        } catch (BusinessException e) {
            HttpStatus status = errorCodeToStatusMap.getOrDefault(e.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(status.value());
            return false;
        }

        return true;
    }
}
