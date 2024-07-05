package gift.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter  {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {"/members/register", "/members/login"};
        String path = request.getRequestURI();
        return Arrays
                .stream(excludePath)
                .anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "인증이 유효하지 않습니다.");
            return;
        }

        if (!authHeader.startsWith("Bearer ")) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "인증에 실패하였습니다.");
            return;
        }

        String token = authHeader.substring(7);

        try {
            Long memberId = jwtUtil.extractId(token);
            request.setAttribute("memberId", memberId);
        } catch (Exception exception) {
            response.sendError(HttpStatus.FORBIDDEN.value(), exception.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

}
