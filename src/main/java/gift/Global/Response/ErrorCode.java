package gift.Global.Response;

public enum ErrorCode {

    // Product CRUD 동작
    GET_ALL_PRODUCTS_FAILED(400, "PE001", "전체 상품 목록 조회에 실패했습니다."),
    CREATE_PRODUCT_FAILED(400, "PE002", "상품 추가에 실패했습니다."),
    UPDATE_PRODUCT_FAILED(400, "PE003", "상품 수정에 실패했습니다."),
    DELETE_PRODUCT_FAILED(400, "PE004", "상품 삭제에 실패했습니다."),
    DELETE_PRODUCTS_FAILED(400, "PE005", "상품들을 삭제에 실패했습니다."),

    // 값 유효성 검사
    INVALID_PRODUCT_ARGUMENT(400, "VE001", "상품 입력 정보가 유효하지 않습니다."),

    ;
    ;
    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
