package gift.web.dto.response;

import java.time.LocalDateTime;
import org.springframework.validation.BindingResult;

public class ErrorResponse {

    private String code;
    private String reason;
    private String description;
    private LocalDateTime timestamp;

    public ErrorResponse(String code, String reason, String description) {
        this.code = code;
        this.description = description;
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponse from(BindingResult bindingResult) {
        return new ErrorResponse(
            bindingResult.getFieldError().getCode(),
            bindingResult.getFieldError().getField(),
            bindingResult.getFieldError().getDefaultMessage()
        );
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
