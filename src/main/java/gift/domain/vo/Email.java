package gift.domain.vo;

public class Email {

    private final String emailValue;

    private Email(String emailValue) {
        this.emailValue = emailValue;
    }

    public static Email from(String email) {
        return new Email(email);
    }

    public String getEmailValue() {
        return emailValue;
    }
}
