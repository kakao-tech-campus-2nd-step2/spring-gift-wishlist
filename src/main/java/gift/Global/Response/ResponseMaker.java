package gift.Global.Response;

import org.springframework.http.ResponseEntity;

/**
 * 응답 객체를 생성하는 클래스
 */
public class ResponseMaker {

    /**
     * BODY 에 성공 메시지와 데이터를 보냄
     * @param resultCode
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseEntity<ResultResponseDto<T>> createResponse(ResultCode resultCode, T data) {
        ResultResponseDto<T> resultResponseDto = new ResultResponseDto<>(resultCode, data);
        return ResponseEntity.status(resultCode.getStatus())
            .body(resultResponseDto);
    }

    /**
     * BODY 에 성공 메시지만 보냄 (데이터 X)
     * @param resultCode
     * @return
     */
    public static ResponseEntity<SimpleResultResponseDto> createSimpleResponse(ResultCode resultCode) {
        SimpleResultResponseDto simpleResultResponseDto = new SimpleResultResponseDto(resultCode);
        return ResponseEntity.status(resultCode.getStatus())
            .body(simpleResultResponseDto);
    }

    /**
     * BODY 에 에러 메시지만 보냄 (데이터 X)
     * @param errorCode
     * @return
     */
    public static ResponseEntity<ErrorResponseDto> createErrorResponse(ErrorCode errorCode) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(errorCode);
        return ResponseEntity.status(errorCode.getStatus())
            .body(errorResponseDto);
    }
}
