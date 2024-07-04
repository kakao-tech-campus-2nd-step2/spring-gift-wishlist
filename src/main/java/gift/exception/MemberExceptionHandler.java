package gift.exception;

import java.util.List;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResult> joinExHandle(DuplicateKeyException e) {
        return new ResponseEntity<>(new ErrorResult("회원가입 에러", "중복된 이메일의 회원이 존재합니다."), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({LoginErrorException.class})
    public ResponseEntity<ErrorResult> notFoundUserExHandle(LoginErrorException e) {
        return new ResponseEntity<>(new ErrorResult("로그인 에러", "아이디 또는 비밀번호가 일치하지 않습니다."), HttpStatus.NOT_FOUND);
    }

}
