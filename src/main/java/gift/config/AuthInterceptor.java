package gift.config;

import gift.exception.UnauthorizedAccessException;
import gift.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    public AuthInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        var header = getHeader(request);
        var token = authService.getTokenWithAuthorizationHeader(header);
        setMemberIdInAttribute(request, token);
        setMemberRoleInAttribute(request, token);
        return true;
    }

    private String getHeader(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header == null) throw new UnauthorizedAccessException("인가되지 않은 요청입니다.");
        return header;
    }

    private void setMemberIdInAttribute(HttpServletRequest request, String token) {
        var memberId = authService.getMemberIdWithToken(token);
        request.setAttribute("memberId", memberId);
    }

    private void setMemberRoleInAttribute(HttpServletRequest request, String token) {
        var memberRole = authService.getMemberRoleWithToken(token);
        request.setAttribute("memberRole", memberRole);
    }
}
