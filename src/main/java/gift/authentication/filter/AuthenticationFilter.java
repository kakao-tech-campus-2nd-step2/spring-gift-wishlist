package gift.authentication.filter;

import gift.authentication.token.JwtResolver;
import gift.authentication.token.Token;
import gift.web.validation.exception.InvalidCredentialsException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final List<String> ignorePaths = List.of("/api/members/login", "/api/members/register");
    private final JwtResolver jwtResolver;

    public AuthenticationFilter(JwtResolver jwtResolver) {
        this.jwtResolver = jwtResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        log.debug("LoginFilter - doFilterInternal()");
        log.debug("requestURI:" + request.getRequestURI());

        //검증이 필요없는 요청
        if (ignorePaths.contains(request.getRequestURI())) {
            log.debug("LoginFilter: ignorePaths contains requestURI!");
            filterChain.doFilter(request, response);
            return;
        }

        //검증이 필요한 요청
        String authorization = request.getHeader("Authorization");
        if(Objects.nonNull(authorization) && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            log.debug("token : " + token);
            Claims claims = jwtResolver.resolve(Token.from(token));

            log.debug("====================== claims ======================");
            claims.keySet()
                .stream()
                .forEach(key -> log.debug(key + " : " + claims.get(key)));
            log.debug("====================================================");

            filterChain.doFilter(request, response);
            return;
        }

        throw new InvalidCredentialsException("Invalid credentials! (from LoginFilter)");
    }
}
