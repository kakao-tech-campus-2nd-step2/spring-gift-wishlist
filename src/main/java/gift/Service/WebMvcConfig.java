package gift.Service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final BearerAuthInterceptor bearerAuthInterceptor;

    public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry){
        //인터셉터 등록
        System.out.println("");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/api/wish");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/api/wish/add/{id}");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/api/wish/delete/{id}");
    }
}
