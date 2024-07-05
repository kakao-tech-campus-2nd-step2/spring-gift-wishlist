package gift.model;

public class User {
    private Long id;
    private String email;
    private String password;

    public User() {}
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean matchPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }
}
