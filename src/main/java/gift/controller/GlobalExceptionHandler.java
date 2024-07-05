package gift.controller;

import gift.exception.KakaoValidationException;
import gift.exception.StringValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(KakaoValidationException.class)
  public String handleKakaoException(KakaoValidationException ex, Model model) {
    model.addAttribute("errorMessage", ex.getMessage());
    return "kakao-error";
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(StringValidationException.class)
  public String handleStringException(StringValidationException ex, Model model) {
    model.addAttribute("errorMessage", ex.getMessage());
    return "validation-error";
  }
}
