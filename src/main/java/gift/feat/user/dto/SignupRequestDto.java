package gift.feat.user.dto;

public record SignupRequestDto(
	Long id,
	String email,
	String password,
	String role
) {
}
