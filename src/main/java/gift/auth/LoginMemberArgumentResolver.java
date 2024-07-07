package gift.auth;

import gift.auth.token.AuthTokenGenerator;
import gift.member.repository.MemberRepository;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberRepository memberRepository;
    private final AuthTokenGenerator authTokenGenerator;

    public LoginMemberArgumentResolver(MemberRepository memberRepository, AuthTokenGenerator authTokenGenerator) {
        this.memberRepository = memberRepository;
        this.authTokenGenerator = authTokenGenerator;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {

        Long memberId = authTokenGenerator.extractMemberId(webRequest.getHeader(HttpHeaders.AUTHORIZATION));

        return memberRepository.findMemberByIdOrThrow(memberId);
    }
}
