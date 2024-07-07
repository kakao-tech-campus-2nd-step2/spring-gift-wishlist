package gift.service;


import gift.authorization.JwtUtil;
import gift.entity.LoginUser;
import jdk.jfr.Description;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public LoginMemberArgumentResolver(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Description("token 추출 후 loginUser 객체 반환. 만약 토큰이 유효하지 않다면 null 반환")
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("Authorization").substring("Bearer ".length());
        System.out.println(token);
        if(jwtUtil.checkClaim(token)){
            String email = jwtUtil.getUserEmail(token);
            String type = jwtUtil.getUserType(token);
            LoginUser loginUser = new LoginUser(email ,type, token);
            return loginUser;
        }
        return null;
    }
}
