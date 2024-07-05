package gift.global.response;

/**
 * 메시지, "데이터" 전달
 *
 * @param message
 * @param data
 * @param <T>
 */
public record ResultResponseDto<T>(String message, T data) {

}
