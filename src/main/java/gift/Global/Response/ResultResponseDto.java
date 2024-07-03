package gift.Global.Response;

/**
 *  사용자 정의 상태 코드와 메시지, "데이터" 전달
 *
 * @param code
 * @param message
 * @param data
 * @param <T>
 */
public record ResultResponseDto<T>(String code, String message, T data) {

    public ResultResponseDto(ResultCode resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMessage(), data);
    }
}
