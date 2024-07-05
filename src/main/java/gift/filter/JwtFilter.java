package gift.filter;

import gift.service.UserService;
import gift.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;


    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 로직
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authorizationHeader = httpRequest.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String email = jwtUtil.extractUsername(token);

            if (email != null && jwtUtil.validateToken(token, email)) {
                httpRequest.setAttribute("userEmail", email);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid or expired JWT token");
                return;
            }
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Missing or invalid Authorization header");
            return;
        }

        chain.doFilter(request, response);
    }


    public void destroy() {
        // 종료 로직
    }


    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
