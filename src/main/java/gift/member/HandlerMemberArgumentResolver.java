package gift.member;


import gift.token.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@Component
public class HandlerMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;
    private final MemberService memberService;
    private final Validator validator;

    public HandlerMemberArgumentResolver(
        JwtProvider jwtProvider,
        MemberService memberService,
        Validator validator
    ) {
        this.jwtProvider = jwtProvider;
        this.memberService = memberService;
        this.validator = validator;
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

        // JSON 값을 통해 직접 Member객체를 생성하는 경우
        if (token == null) {
            HttpMessageConverter<?> converter = new MappingJackson2HttpMessageConverter();
            RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(
                Collections.singletonList(converter));
            return (Member) processor.resolveArgument(parameter, mavContainer, webRequest,
                binderFactory);
        }

        Member member = jwtProvider.getMemberFromToken(token);
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(member, "member");
        validator.validate(member, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(parameter, bindingResult);
        }
        memberService.authenticateMember(member);
        return member;
    }
}
