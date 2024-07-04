package gift.entity;

public class User {
    public final Long id;
    public final String email;
    public final String password;

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
