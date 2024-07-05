package gift.authentication.resolver;

import gift.authentication.annotation.LoginMember;
import gift.authentication.token.JwtResolver;
import gift.authentication.token.Token;
import gift.domain.Member;
import gift.repository.MemberRepository;
import gift.web.validation.exception.InvalidCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final JwtResolver jwtResolver;
    private final MemberRepository memberRepository;

    public LoginMemberArgumentResolver(JwtResolver jwtResolver, MemberRepository memberRepository) {
        this.jwtResolver = jwtResolver;
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(LoginMember.class);
        boolean isAssignable = Member.class.isAssignableFrom(parameter.getParameterType());
        return hasParameterAnnotation && isAssignable;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String authorization = webRequest.getHeader("Authorization");
        log.debug("Authorization : " + authorization);
        Token token = Token.from(extractToken(authorization));

        Long memberId = jwtResolver.resolveId(token)
            .orElseThrow(() -> new InvalidCredentialsException("토큰 정보와 일치하는 회원은 없습니다!"));

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new InvalidCredentialsException("토큰 정보와 일치하는 회원은 없습니다!"));

        return member;
    }

    private String extractToken(String Authorization) {
        return Authorization.substring(7);
    }

}
