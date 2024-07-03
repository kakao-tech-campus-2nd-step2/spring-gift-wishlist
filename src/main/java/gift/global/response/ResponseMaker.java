package gift.global.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 응답 객체를 생성하는 클래스
 */
public class ResponseMaker {

    /**
     * BODY 에 성공 메시지와 데이터를 보냄
     * @param message
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseEntity<ResultResponseDto<T>> createResponse(HttpStatus status, String message, T data) {
        ResultResponseDto<T> resultResponseDto = new ResultResponseDto<>(message, data);

        return ResponseEntity.status(status).body(resultResponseDto);
    }

    /**
     * BODY 에 성공 메시지만 보냄 (데이터 X)
     * @param message
     * @return
     */
    public static ResponseEntity<SimpleResultResponseDto> createSimpleResponse(HttpStatus status, String message) {
        System.out.println("message = " + message);
        SimpleResultResponseDto simpleResultResponseDto = new SimpleResultResponseDto(message);
        System.out.println("simpleResultResponseDto = " + simpleResultResponseDto);
        return ResponseEntity.status(status).body(simpleResultResponseDto);
    }

    /**
     * BODY 에 에러 메시지만 보냄 (데이터 X)
     * @param message
     * @return
     */
    public static ResponseEntity<ErrorResponseDto> createErrorResponse(HttpStatus status, String message) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(message);
        return ResponseEntity.status(status)
            .body(errorResponseDto);
    }

}
