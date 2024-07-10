package gift.security.authfilter;

import gift.security.jwt.TokenExtractor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistrationBean(TokenExtractor tokenExtractor) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthenticationFilter(tokenExtractor));
        registrationBean.addUrlPatterns("/api/products");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
