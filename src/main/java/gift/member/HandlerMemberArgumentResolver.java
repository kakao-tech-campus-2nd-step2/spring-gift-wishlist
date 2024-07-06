package gift.member;


import com.github.dockerjava.api.exception.UnauthorizedException;
import gift.token.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class HandlerMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    public HandlerMemberArgumentResolver(
        JwtProvider jwtProvider,
        MemberService memberService
    ) {
        this.jwtProvider = jwtProvider;
        this.memberService = memberService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Member.class);
    }

    @Override
    public Member resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new UnauthorizedException("Unauthorized");
        }
        Member member = jwtProvider.getMemberFromToken(token);
        memberService.authenticateMember(member);
        return member;
    }
}
