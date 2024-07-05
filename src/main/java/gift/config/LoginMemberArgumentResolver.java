package gift.config;

import gift.dto.UserRequestDTO;
import gift.model.User;
import gift.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.stereotype.Component;

@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;

    public LoginMemberArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginMember.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String token = webRequest.getHeader("Authorization").substring(7);
        User user = userService.findByToken(token);

        // User 객체를 UserRequestDTO로 변환
        return new UserRequestDTO(user.getEmail(), user.getPassword());
    }
}
