package gift.login;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public record TokenResponseDto(String token) {

    @Configuration
    public static class WebConfig implements WebMvcConfigurer {
        private final LoginMember.LoginMemberArgumentResolver loginMemberArgumentResolver;

        public WebConfig(LoginMember.LoginMemberArgumentResolver loginMemberArgumentResolver) {
            this.loginMemberArgumentResolver = loginMemberArgumentResolver;
        }

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            argumentResolvers.add(loginMemberArgumentResolver);
        }
    }
}
