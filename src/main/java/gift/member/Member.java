package gift.member;

import gift.exception.FailedLoginException;
import jakarta.validation.constraints.Email;

public record Member(
    @Email(message = "This is not an email format")
    String email,
    String password) {

    public void isAuthentication(Member member) {
        if (!isSamePassword(member)) {
            throw new FailedLoginException("Wrong password");
        }
    }

    private boolean isSamePassword(Member member) {
        return this.password.equals(member.password());
    }
}
