package gift.error;

public class ErrorMessage {

    /* validation */
    public static final String VALIDATION_ERROR = "입력 데이터의 유효성을 검사하던 중 문제가 발생했습니다.";
    public static final String PRODUCT_NAME_CONTAINS_KAKAO = "카카오'가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.";
    public static final String PRODUCT_NAME_NOT_BLANK = "상품 이름을 입력해 주세요.";
    public static final String PRODUCT_NAME_EXCEEDS_MAX_LENGTH = "상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있습니다.";
    public static final String PRODUCT_NAME_INVALID_CHAR = "상품 이름에 허용되지 않는 특수 문자가 포함되어 있습니다.";
    public static final String PRODUCT_PRICE_NOT_NULL = "상품 가격을 입력해 주세요.";

}
