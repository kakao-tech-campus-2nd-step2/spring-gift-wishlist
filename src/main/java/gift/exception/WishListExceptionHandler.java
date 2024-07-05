package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WishListExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedWishException.class)
    public ErrorResult duplicatedWish(DuplicatedWishException e) {
        return new ErrorResult("위시 리스트 에러", "해당 제품이 이미 위시 리스트에 존재합니다.");
    }
}
