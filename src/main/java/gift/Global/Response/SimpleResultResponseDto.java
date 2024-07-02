package gift.Global.Response;

/**
 * 사용자 정의 상태 코드와 메시지 전달 (데이터 X)
 * @param code
 * @param message
 */
public record SimpleResultResponseDto(String code, String message) {
    public SimpleResultResponseDto(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }
}