package gift.utils.filter;

import gift.utils.JwtTokenProvider;
import gift.utils.error.TokenAuthException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        // Filter 를 통과하지 않아도 되는 url
        if (path.equals("/user/login") || path.equals("/user/register") || path.startsWith("/user") || path.startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization header 존재하는지 확인
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || authHeader.isEmpty()) {
            httpResponse.sendRedirect("/user/login");
            return;
        }

        // JWT 토큰의 유효성 검사
        String token = authHeader.substring(7);
        if (!jwtTokenProvider.validateToken(token)) {
            throw new TokenAuthException("Token not exist");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
