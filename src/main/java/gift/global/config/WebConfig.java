package gift.global.config;

import gift.global.jwt.JwtAuthorizationArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtAuthorizationArgumentResolver jwtAuthorizationArgumentResolver;

    public WebConfig(JwtAuthorizationArgumentResolver jwtAuthorizationArgumentResolver) {
        this.jwtAuthorizationArgumentResolver = jwtAuthorizationArgumentResolver;
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtAuthorizationArgumentResolver);
    }

}
