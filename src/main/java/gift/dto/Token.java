package gift.dto;

public class Token {
    Long userId;
    String value;

    public Token(Long userId, String value) {
        this.userId = userId;
        this.value = value;
    }

    public Token() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
