package gift.Global.Response;

import javax.xml.transform.Result;

/**
 * 수행 성공 시 반환하는 응답 객체에 사용되는 응답 코드
 */
public enum ResultCode {

    GET_ALL_PRODUCTS_SUCCESS(200, "P001", "전체 상품 목록 조회에 성공했습니다."),
    CREATE_PRODUCT_SUCCESS(200, "P002", "상품이 추가되었습니다."),
    UPDATE_PRODUCT_SUCCESS(200, "P003", "상품을 수정했습니다."),
    DELETE_PRODUCT_SUCCESS(200, "P004", "해당 상품이 삭제되었습니다."),
    DELETE_PRODUCTS_SUCCESS(200, "P005", "선택된 상품들을 삭제하였습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;

    ResultCode(int status, String code, String message) {
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
