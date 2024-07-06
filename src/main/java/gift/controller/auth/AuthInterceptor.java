package gift.controller.auth;

import gift.exception.UnauthorizedAccessException;
import gift.utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Value("${SECRET_KEY}")
    private String secretKey;

    public AuthInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        var header = getHeader(request);
        var token = getTokenWithAuthorizationHeader(header);
        setMemberIdInAttribute(request, token);
        setMemberRoleInAttribute(request, token);
        return true;
    }

    private String getTokenWithAuthorizationHeader(String authorizationHeader) {
        var header = authorizationHeader.split(" ");
        if (header.length != 2) throw new IllegalArgumentException("잘못된 헤더 정보입니다.");
        return header[1];
    }

    private String getHeader(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header == null) throw new UnauthorizedAccessException("인가되지 않은 요청입니다.");
        return header;
    }

    private void setMemberIdInAttribute(HttpServletRequest request, String token) {
        var memberId = AuthUtils.getMemberIdWithToken(token, secretKey);
        request.setAttribute("memberId", memberId);
    }

    private void setMemberRoleInAttribute(HttpServletRequest request, String token) {
        var memberRole = AuthUtils.getMemberRoleWithToken(token, secretKey);
        request.setAttribute("memberRole", memberRole);
    }
}
