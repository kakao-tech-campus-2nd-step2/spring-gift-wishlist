package gift.product;

public final class ProductInfo {

    public static final String PRODUCT_NOT_FOUND = "상품을 찾을 수 없습니다.";
    public static final String PRODUCT_SAVE_SUCCESS = "상품 저장 성공";
    public static final String PRODUCT_SAVE_FAIL = "상품 저장 실패";
    public static final String PRODUCT_UPDATE_SUCCESS = "상품 수정 성공";
    public static final String PRODUCT_UPDATE_FAIL = "상품 수정 실패";
    public static final String PRODUCT_DELETE_SUCCESS = "상품 삭제 성공";
    public static final String PRODUCT_DELETE_FAIL = "상품 삭제 실패";

    private ProductInfo() {
        throw new IllegalStateException("유틸 클래스는 인스턴스화 할 수 없습니다.");
    }
}
