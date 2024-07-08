package gift.dto;

public record UserInfoDTO(
        long id,
        String email,
        String encryptedPw
) {

}
