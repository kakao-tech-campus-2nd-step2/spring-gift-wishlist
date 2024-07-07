package gift.resolver;

import gift.model.User;
import gift.service.UserService;
import gift.util.JwtUtil;
import gift.annotation.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    public LoginUserArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginUser.class) != null && parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, org.springframework.web.bind.support.WebDataBinderFactory binderFactory) throws Exception {
        String authorizationHeader = webRequest.getHeader("Authorization");

        if (isTokenPresent(authorizationHeader)) {
            String token = extractToken(authorizationHeader);
            if (JwtUtil.validateToken(token)) {
                return getUserFromToken(token);
            }
        }
        return null;
    }

    private boolean isTokenPresent(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

    private User getUserFromToken(String token) {
        String email = JwtUtil.getSubject(token);
        return userService.findUserByEmail(email);
    }
}