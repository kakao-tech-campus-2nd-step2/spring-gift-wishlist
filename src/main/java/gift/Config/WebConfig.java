package gift.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final InterceptorOfToken interceptorOfToken;

    public WebConfig(InterceptorOfToken interceptorOfToken) {
        this.interceptorOfToken = interceptorOfToken;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorOfToken)
                .addPathPatterns("/**")
                .excludePathPatterns("/members/**");
    }
}