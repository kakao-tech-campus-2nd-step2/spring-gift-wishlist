package gift.controller;

import gift.exception.KakaoValidationException;
import gift.exception.StringValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = KakaoValidationException.class)
  public ResponseEntity<String> handleKakaoException(KakaoValidationException ex) {
    return new ResponseEntity<>("Kakao Exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = StringValidationException.class)
  public ResponseEntity<String> handleStringException(Exception ex) {
    return new ResponseEntity<>("String error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}

