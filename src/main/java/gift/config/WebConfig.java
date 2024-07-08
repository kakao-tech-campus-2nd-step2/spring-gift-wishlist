package gift.config;

import gift.resolver.LoginUserArgumentResolver;
import gift.service.MemberService;
import gift.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtUtil jwtUtil;

    public WebConfig(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserArgumentResolver(memberService));
    }
}
