package gift.dto;

import java.util.List;

public class ErrorResponses {
    private List<String> messages;

    public ErrorResponses(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
