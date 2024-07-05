package gift.exception;

import lombok.Getter;

public class UserException {

    @Getter
    public static class Forbidden extends RuntimeException{
        private UserErrorCode errorCode;
        private String detailMessage;

        public Forbidden(UserErrorCode errorCode){
            super(errorCode.getMessage());
            this.errorCode = errorCode;
            this.detailMessage = errorCode.getMessage();
        }
        public Forbidden(UserErrorCode errorCode, String detailMessage){
            super(detailMessage);
            this.errorCode = errorCode;
            this.detailMessage = detailMessage;
        }
    }
}
