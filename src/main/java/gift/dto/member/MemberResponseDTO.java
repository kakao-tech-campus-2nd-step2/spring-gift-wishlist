package gift.dto.member;

public record MemberResponseDTO(
    Long id,
    String email,
    String token
) { }
