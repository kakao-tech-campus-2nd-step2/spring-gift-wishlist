package gift.resolver;

import gift.annotation.LoginMember;
import gift.service.MemberService;
import gift.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginMember.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        String header = webRequest.getHeader("Authorization");
//        if (header == null || !header.startsWith("Bearer ")) {
//            throw new IllegalArgumentException("Invalid Authorization header");
//        }
//        String token = header.substring(7);
//
//        Claims claims = jwtUtil.getClaims(token);
        Claims claims = (Claims) webRequest.getAttribute("claims", NativeWebRequest.SCOPE_REQUEST);
        if (claims == null) {
            throw new IllegalArgumentException("JWT Claims 없음");
        }

        Long memberId = Long.parseLong(claims.getSubject());
        return memberService.findById(memberId);
    }
}
