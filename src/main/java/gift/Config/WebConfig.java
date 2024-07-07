package gift.Config;

import gift.Annotation.LoginMemberArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginMemberArgumentResolver loginMemberResolverHandlerMethodArgumentResolver;

    @Autowired
    public WebConfig(LoginMemberArgumentResolver loginMemberResolverHandlerMethodArgumentResolver) {
        this.loginMemberResolverHandlerMethodArgumentResolver = loginMemberResolverHandlerMethodArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginMemberResolverHandlerMethodArgumentResolver);
    }

    /*
    private final WishListResolverHandlerMethodArgumentResolver wishListResolverHandlerMethodArgumentResolver;

    @Autowired
    public WebConfig(WishListResolverHandlerMethodArgumentResolver wishListResolverHandlerMethodArgumentResolver) {
        this.wishListResolverHandlerMethodArgumentResolver = wishListResolverHandlerMethodArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(wishListResolverHandlerMethodArgumentResolver);
    }

     */
}
