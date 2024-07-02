package gift.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getCode();
    HttpStatus getHttpStatus();
    String getTitle();
    String getDetail();
}
