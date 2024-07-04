package gift.dto;

public class ExceptionResponseDTO {

    private String message;

    public ExceptionResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
