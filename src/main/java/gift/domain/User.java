package gift.domain;

public class User {
    private Long id;
    private String email;
    private String password;

    public User(Long id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
