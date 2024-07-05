package gift.user.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// user package 예외 핸들러
@ControllerAdvice(basePackages = "gift.user")
public class UserExceptionHandler {
    private static final String ERROR_PAGE = "html/error-user";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handler(MethodArgumentNotValidException methodArgumentNotValidException, Model model) {
        // 에러 메시지
        String message = methodArgumentNotValidException.getFieldError().getDefaultMessage();
        System.out.println(message);

        // 반환
        addAttributesForUserPage(message, model);
        return ERROR_PAGE;
    }

    // error-user.html에서 보여줄 attributes를 넣는 함수
    private void addAttributesForUserPage(String message, Model model) {
        // 에러 메시지 넣어줌
        model.addAttribute("message", message);
    }
}
