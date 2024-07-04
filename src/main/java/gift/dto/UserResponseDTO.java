package gift.dto;

public class UserResponseDTO {
    public final Long id;
    public final String email;

    public UserResponseDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
