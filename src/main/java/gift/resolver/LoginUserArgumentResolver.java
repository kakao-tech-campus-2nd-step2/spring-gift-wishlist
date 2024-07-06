package gift.resolver;

import gift.model.User;
import gift.model.dto.LoginUserDto;
import gift.repository.UserDao;
import gift.resolver.annotation.LoginUser;
import gift.service.AuthService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserDao userDao;
    private final AuthService authService;

    public LoginUserArgumentResolver(UserDao userDao, AuthService authService) {
        this.userDao = userDao;
        this.authService = authService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = authService.extractToken(webRequest.getHeader("Authorization"));
        Long id = Long.parseLong(authService.getClaims(token).getSubject());
        User user = userDao.selectUserById(id);
        return new LoginUserDto(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
