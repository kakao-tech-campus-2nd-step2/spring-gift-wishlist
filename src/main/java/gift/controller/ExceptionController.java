package gift.controller;

import gift.exception.ErrorResult;
import gift.exception.KakaoInNameException;
import gift.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResult errorResult = new ErrorResult("400", "잘못된 요청입니다.");

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorResult.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResult> productNotFoundHandler(ProductNotFoundException e){
        ErrorResult errorResult = new ErrorResult("404", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(KakaoInNameException.class)
    public ResponseEntity<ErrorResult> kakaoInNameHandler(KakaoInNameException e){
        ErrorResult errorResult = new ErrorResult("400", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> errorHandler(Exception e) {
        ErrorResult errorResult = new ErrorResult("500", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
