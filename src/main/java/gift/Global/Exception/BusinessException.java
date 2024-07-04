package gift.Global.Exception;

import gift.Global.Response.ErrorCode;

/**
 * RuntimeException 을 상속받는 커스텀 에러
 * 개발자가 직접 날리는 에러
 */
public class BusinessException extends RuntimeException{

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
