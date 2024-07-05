package gift.dto;

public record UserEncryptedDTO(
        String email,
        String encryptedPW
) { }
