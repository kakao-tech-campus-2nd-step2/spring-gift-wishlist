package gift.authentication.restapi.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
