package gift.Jwt;

public record JwtDto(
        String accessToken,
        String refreshToken) {
}