package gift.util;

import gift.auth.jwt.JwtProvider;
import gift.domain.user.dao.UserDao;
import gift.domain.user.entity.User;
import gift.exception.InvalidAuthException;
import io.jsonwebtoken.Claims;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;
    private final UserDao userDao;

    public LoginUserArgumentResolver(JwtProvider jwtProvider, UserDao userDao) {
        this.jwtProvider = jwtProvider;
        this.userDao = userDao;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean isUserType = User.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && isUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String field = webRequest.getHeader("Authorization");
        if (field == null) {
            throw new InvalidAuthException("error.invalid.token.header");
        }

        String[] splitField = field.split(" ");
        if ((splitField.length != 2) || (!splitField[0].equals("Bearer"))) {
            throw new InvalidAuthException("error.invalid.token.header");
        }

        Claims claims = jwtProvider.getAuthentication(splitField[1]);
        return userDao.findById(Long.parseLong(claims.getSubject()))
            .orElseThrow(() -> new InvalidAuthException("error.invalid.token"));
    }
}
