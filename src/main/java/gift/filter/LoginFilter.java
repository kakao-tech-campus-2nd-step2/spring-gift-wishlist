package gift.filter;



import gift.domain.TokenAuth;
import gift.repository.TokenRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class LoginFilter implements Filter {

    private final TokenRepository tokenRepository;

    public LoginFilter(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");

        if (!(authHeader == null || authHeader.isEmpty())){
            String token = authHeader.substring(7);
            Optional<TokenAuth> tokenAuthOptional = tokenRepository.findTokenByToken(token);

            if (tokenAuthOptional.isEmpty()){
                filterChain.doFilter(request, response);
                return;
            }

            httpResponse.sendRedirect("/home");
            return;
        }

        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {
    }
}