package gift.domain.vo;

public class Password {

    private final String passwordValue;

    private Password(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public static Password from(String password) {
        return new Password(password);
    }

    public String getPasswordValue() {
        return passwordValue;
    }

}
