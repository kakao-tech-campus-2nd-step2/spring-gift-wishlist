package gift.web.dto;

public record Token(String token) {
    @Override
    public String toString() {
        return token;
    }
}
