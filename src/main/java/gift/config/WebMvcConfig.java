package gift.config;

import gift.authorization.JwtUtil;
import gift.service.LoginMemberArgumentResolver;
import gift.service.SignUpService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final SignUpService signUpService;
    private final JwtUtil jwtUtil;

    public WebMvcConfig(SignUpService signUpService, JwtUtil jwtUtil) {
        this.signUpService = signUpService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver(signUpService, jwtUtil));
    }
}
