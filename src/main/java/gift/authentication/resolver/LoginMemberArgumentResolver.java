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
    private final String AUTHORIZATION_HEADER = "Authorization";


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

        String authorization = webRequest.getHeader(AUTHORIZATION_HEADER);
        log.debug("Authorization : " + authorization);
        Token token = Token.from(extractToken(authorization));

        Long memberId = jwtResolver.resolveId(token)
            .orElseThrow(InvalidCredentialsException::new);

        return memberRepository.findById(memberId)
            .orElseThrow(InvalidCredentialsException::new);
    }

    private String extractToken(String Authorization) {
        return Authorization.substring(7);
    }

}
