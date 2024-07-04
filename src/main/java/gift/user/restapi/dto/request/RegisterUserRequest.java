package gift.user.restapi.dto.request;

public record RegisterUserRequest(
        String name,
        String email,
        String password
) {
}
