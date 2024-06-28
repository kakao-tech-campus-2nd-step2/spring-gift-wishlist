package gift.global.response;

public enum ResultCode {
    // Product
    GET_ALL_PRODUCTS_SUCCESS("200", "P001", "모든 제품 조회 성공"),
    GET_PRODUCT_BY_ID_SUCCESS("200", "P002", "단일 제품 조회 성공"),
    CREATE_PRODUCT_SUCCESS("200", "P003", "제품 추가 성공"),
    UPDATE_PRODUCT_SUCCESS("200", "P002", "제품 수정 성공"),
    DELETE_PRODUCT_SUCCESS("200", "P002", "제품 삭제 성공"),

    ;

    // status 를 HttpStatus 로 관리하는 것이 좋을까, 아니면 String으로 관리하는 것이 좋을까?
    private final String status;
    private final String resultCode;
    private final String message;

    ResultCode(String status, String resultCode, String message) {
        this.status = status;
        this.resultCode = resultCode;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getMessage() {
        return message;
    }
}
