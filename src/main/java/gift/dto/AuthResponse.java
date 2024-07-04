package gift.dto;

public record AuthResponse(String token) {
    public static AuthResponse from(String token) {
        return new AuthResponse(token);
    }
}
