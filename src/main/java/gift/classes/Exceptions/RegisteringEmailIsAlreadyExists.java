package gift.classes.Exceptions;

public class RegisteringEmailIsAlreadyExists extends Exception {
    public RegisteringEmailIsAlreadyExists() {
        super("Email is already exist.");
    }
}
