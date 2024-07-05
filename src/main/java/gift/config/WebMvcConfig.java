package gift.config;

import gift.controller.LoginMemberArgumentResolver;
import gift.interceptor.JwtInterceptor;
import gift.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    UserService userService;

    @Autowired
    WebMvcConfig(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/api/products/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginMemberArgumentResolver(userService));
    }
}
