package gift.global;

import gift.domain.service.UserService;
import gift.domain.annotation.ValidUserArgumentResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserService userService;

    @Autowired
    public WebConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ValidUserArgumentResolver(userService));
    }
}
