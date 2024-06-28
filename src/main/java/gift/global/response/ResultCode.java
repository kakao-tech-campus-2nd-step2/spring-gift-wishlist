package gift.global.response;

import org.springframework.http.HttpStatusCode;

public enum ResultCode {
    // Product


    ;
    // HttpStatusCode 로 관리하는 것이 좋을까, 아니면 String으로 관리하는 것이 좋을까?
    private final HttpStatusCode status;
    private final String resultCode;
    private final String message;

    ResultCode(HttpStatusCode status, String resultCode, String message) {
        this.status = status;
        this.resultCode = resultCode;
        this.message = message;
    }
}
