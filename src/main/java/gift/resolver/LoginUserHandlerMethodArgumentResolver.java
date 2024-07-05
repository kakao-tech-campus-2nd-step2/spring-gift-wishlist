package gift.resolver;

import gift.annotation.LoginUser;
import gift.service.UserService;
import gift.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    private final JwtUtil jwtUtil;
    LoginUserHandlerMethodArgumentResolver(JwtUtil jwtUtil){
        this.jwtUtil=jwtUtil;
    }
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory){
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = request.getHeader("Authorization");

        if(token!=null){
            return jwtUtil.getEmailFromToken(token);
        }
        return null; //권한 없음 exception 처리 필요

    }

}
