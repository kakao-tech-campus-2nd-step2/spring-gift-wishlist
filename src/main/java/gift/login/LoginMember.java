package gift.login;

import gift.member.MemberDao;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface LoginMember {

    @Component
    class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

        private final MemberDao memberDao;
        private final JwtTokenUtil jwtTokenUtil;

        public LoginMemberArgumentResolver(MemberDao memberDao, JwtTokenUtil jwtTokenUtil) {
            this.memberDao = memberDao;
            this.jwtTokenUtil = jwtTokenUtil;
        }

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            boolean hashLoginUserAnnotation = parameter.hasParameterAnnotation(LoginMember.class);
            return hashLoginUserAnnotation;
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

            String token = webRequest.getHeader("Authorization").substring(7);
            System.out.println("토큰"+token);
            String userEmail = jwtTokenUtil.decodeJWT(token).getSubject();
            System.out.println("아이디"+userEmail);

            return memberDao.findMemberById(userEmail);
        }
    }
}
