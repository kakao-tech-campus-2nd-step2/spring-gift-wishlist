package gift.dto;

public record MemberRequestDTO(
    Long id,
    String email,
    String password
) { }