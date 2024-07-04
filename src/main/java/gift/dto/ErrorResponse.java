package gift.dto;

import java.util.List;

public class ErrorResponse {
    private String message;
    private List<String> messages;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
