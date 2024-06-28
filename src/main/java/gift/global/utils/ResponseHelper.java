package gift.global.utils;

import gift.global.response.ResultCode;
import gift.global.response.ResultResponseDto;
import gift.global.response.SimpleResultResponseDto;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    private ResponseHelper() {}

    public static <T> ResponseEntity<ResultResponseDto<T>> createResponse(ResultCode resultCode, T data) {
        ResultResponseDto<T> resultResponseDto = new ResultResponseDto<>(resultCode, data);
        return org.springframework.http.ResponseEntity.status(resultCode.getStatus())
                .body(resultResponseDto);
    }

    public static ResponseEntity<SimpleResultResponseDto> createSimpleResponse(ResultCode resultCode) {
        var resultResponseDto = new SimpleResultResponseDto(resultCode);
        return ResponseEntity.status(resultCode.getStatus())
                .body(resultResponseDto);
    }
}
