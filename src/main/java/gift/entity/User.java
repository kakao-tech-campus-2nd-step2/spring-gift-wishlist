package gift.entity;

import gift.exception.BusinessException;
import gift.exception.ErrorCode;

public class User {
    public final Long id;
    public String email;
    public String password;

    public User(Long id, String email, String password) {
        System.out.println("Creating User: id=" + id + ", email=" + email + ", password=" + password);
        validateEmail(email);
        validatePassword(password);
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public void update(String email, String password) {
        validateEmail(email);
        validatePassword(password);
        this.email = email;
        this.password = password;
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_EMAIL);
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }
}
