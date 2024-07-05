package gift;

import gift.login.JwtTokenUtil;
import gift.login.LoginMember;
import gift.member.Member;
import gift.member.MemberDao;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberDao memberDao;
    private final JwtTokenUtil jwtTokenUtil;

    public LoginMemberArgumentResolver(MemberDao memberDao, JwtTokenUtil jwtTokenUtil) {
        this.memberDao = memberDao;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hashLoginUserAnnotation = parameter.hasMethodAnnotation(LoginMember.class);
        boolean isMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
        System.out.println("출력:"+isMemberType);
        return hashLoginUserAnnotation && isMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String token = webRequest.getHeader("Authorization").substring(7);
        Long userId = Long.parseLong((jwtTokenUtil.decodeJWT(token).getSubject()));
        System.out.println("출력:"+userId);
        return memberDao.findMemberById(userId);
    }
}
