package gift.exception;

public enum ProductErrorCode {
    HAS_KAKAO_WORD("\"kakao\"가 포함된 문구는 담당자와 협의 후 사용할 수 있습니다.");

    private final String message;

    private ProductErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
