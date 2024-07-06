package gift.global.authentication.config;

import gift.global.authentication.interceptor.TokenCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final TokenCheckInterceptor tokenCheckInterceptor;

    public WebConfig(TokenCheckInterceptor tokenCheckInterceptor) {
        this.tokenCheckInterceptor = tokenCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenCheckInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns(
                "/api/members/register",
                "/api/members/login",
                "/api/members/reissue"
            );
    }
}
