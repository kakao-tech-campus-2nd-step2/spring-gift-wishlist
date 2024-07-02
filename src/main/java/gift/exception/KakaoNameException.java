package gift.exception;

public class KakaoNameException extends RuntimeException {
    public KakaoNameException() {
        super("상품 이름에 '카카오'가 포함되어 있습니다. 담당 MD와 협의해 주세요.");
    }
}