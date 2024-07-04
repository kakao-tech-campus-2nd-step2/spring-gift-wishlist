package gift;

import gift.auth.AuthInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/api/login", "/api/join"
            );
    }
}
