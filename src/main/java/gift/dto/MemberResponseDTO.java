package gift.dto;

public record MemberResponseDTO(
    Long id,
    String email,
    String token
) { }
